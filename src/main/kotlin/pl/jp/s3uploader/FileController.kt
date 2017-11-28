package pl.jp.s3uploader

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
class FileController(val s3UploaderService: S3UploaderService) {

    @PostMapping("files")
    fun uploadFile(
            @RequestParam("bucket") bucket: String,
            @RequestParam("accessKey") accessKey: String,
            @RequestParam("secretKey") secretKey: String,
            @RequestParam("targetPath") targetPath: String,
            @RequestParam("file") file: MultipartFile) {

        s3UploaderService.upload(bucket, accessKey, secretKey, targetPath, file.inputStream, file.size)
    }
}
