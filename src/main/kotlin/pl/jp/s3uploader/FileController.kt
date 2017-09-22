package pl.jp.s3uploader

import org.apache.commons.io.FilenameUtils
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.util.*


@RestController
class FileController(val s3UploaderService: S3UploaderService) {

    @PostMapping("files")
    fun uploadFile(
            @RequestParam("file") file: MultipartFile): ResponseEntity<CreatedResponse> {

        val baseFileName = UUID.randomUUID().toString()
        val extension = FilenameUtils.getExtension(file.originalFilename)

        val fileName = if (extension.isEmpty()) baseFileName else "$baseFileName.$extension"

        s3UploaderService.upload(fileName, file.inputStream, file.size)

        val location = ServletUriComponentsBuilder
                .fromCurrentRequest().queryParam("fileName", fileName)
                .build().toUri()

        return ResponseEntity.created(location).body(CreatedResponse(location))
    }

    @GetMapping("files")
    fun downloadFile(@RequestParam("fileName") fileName: String): ResponseEntity<InputStreamResource> {
        val file = s3UploaderService.download(fileName)

        val inputStreamResource = InputStreamResource(file.inputStream)
        val httpHeaders = HttpHeaders()
        httpHeaders.contentLength = file.size

        return ResponseEntity(inputStreamResource, httpHeaders, HttpStatus.OK)
    }
}

data class CreatedResponse(val location: URI)
