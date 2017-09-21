package pl.jp.s3uploader

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {

    @GetMapping
    fun getName(): ApplicationName {
        return ApplicationName("s3-uploader")
    }
}

data class ApplicationName(val applicationName: String)
