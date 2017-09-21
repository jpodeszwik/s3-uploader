package pl.jp.s3uploader

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class UploadController(val s3UploaderService: S3UploaderService) {

    @PostMapping("files")
    fun uploadFile(
            @RequestParam("file") file: MultipartFile,
            @RequestParam("fileName") fileName: String): String {

        s3UploaderService.upload(fileName, file.inputStream, file.size)

        return "success"
    }
}
