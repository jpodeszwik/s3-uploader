package pl.jp.s3uploader

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.net.URL


@RestController
class FileController(val s3UploaderService: S3UploaderService) {

    @PostMapping("files")
    fun uploadFile(
            @RequestParam("bucket") bucket: String,
            @RequestParam("accessKey") accessKey: String,
            @RequestParam("secretKey") secretKey: String,
            @RequestParam("targetPath") targetPath: String,
            @RequestParam("file") file: MultipartFile): ResponseEntity<UploadResponse> {

        val url = s3UploaderService.upload(bucket, accessKey, secretKey, targetPath, file.inputStream, file.size)

        return ResponseEntity.created(url.toURI())
                .body(UploadResponse(url))
    }

    data class UploadResponse(val location: URL)
}
