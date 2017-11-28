package pl.jp.s3uploader

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.InputStream


@Service
class S3UploaderService {
    val log: Logger = LoggerFactory.getLogger(S3UploaderService::class.java)

    fun upload(bucket: String, accessKey: String, secretKey: String,
               targetPath: String, inputStream: InputStream, fileSize: Long) {
        log.info("Uploading $targetPath in bucket $bucket")

        val awsCredentials = BasicAWSCredentials(accessKey, secretKey)

        val s3Client: AmazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(AWSStaticCredentialsProvider(awsCredentials))
                .build()

        val metadata = ObjectMetadata()
        metadata.contentLength = fileSize

        val acl = AccessControlList()
        acl.grantPermission(GroupGrantee.AllUsers, Permission.Read)

        val request = PutObjectRequest(bucket, targetPath, inputStream, metadata)
                .withAccessControlList(acl)

        s3Client.putObject(request)
    }
}
