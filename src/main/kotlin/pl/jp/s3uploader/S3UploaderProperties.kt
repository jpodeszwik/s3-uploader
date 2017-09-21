package pl.jp.s3uploader

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("pl.jp.s3uploader")
class S3UploaderProperties {
    lateinit var outputDir: String
}