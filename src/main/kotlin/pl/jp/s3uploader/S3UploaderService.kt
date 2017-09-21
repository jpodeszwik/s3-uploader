package pl.jp.s3uploader

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class S3UploaderService(s3UploaderProperties: S3UploaderProperties) {
    private final val bucket = s3UploaderProperties.s3Bucket

    private final val awsCredentials = BasicAWSCredentials(s3UploaderProperties.s3AccessKey, s3UploaderProperties.s3SecretKey)
    private final val s3Client: AmazonS3 = AmazonS3ClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(awsCredentials))
            .build()

    fun upload(fileName: String, inputStream: InputStream, fileSize: Long) {
        val metadata = ObjectMetadata()
        metadata.contentLength = fileSize

        val request = PutObjectRequest(bucket, fileName, inputStream, metadata)

        s3Client.putObject(request)
    }
}