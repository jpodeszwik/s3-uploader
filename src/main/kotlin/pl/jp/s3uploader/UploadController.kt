package pl.jp.s3uploader

import org.apache.commons.io.FileUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File

@RestController
class UploadController(uploaderProperties: S3UploaderProperties) {
    val log: Logger = LoggerFactory.getLogger(UploadController::class.java)
    
    val outputDir = uploaderProperties.outputDir

    @PostMapping("files")
    fun uploadFile(
            @RequestParam("file") file: MultipartFile,
            @RequestParam("fileName") fileName: String): String {

        log.info("Saving file: $fileName to $outputDir")

        FileUtils.copyInputStreamToFile(file.inputStream, File("/$outputDir/$fileName"))

        return "success"
    }
}
