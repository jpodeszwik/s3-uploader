package pl.jp.s3uploader

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("pl.jp.s3uploader")
class S3UploaderProperties {
    lateinit var s3AccessKey: String
    lateinit var s3SecretKey: String
    lateinit var s3Bucket: String

    lateinit var username: String
    lateinit var password: String
}