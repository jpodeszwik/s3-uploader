package pl.jp.s3uploader

import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class FileController(val s3UploaderService: S3UploaderService) {

    @PostMapping("files")
    fun uploadFile(
            @RequestParam("file") file: MultipartFile,
            @RequestParam("fileName") fileName: String): String {

        s3UploaderService.upload(fileName, file.inputStream, file.size)

        return "success"
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
