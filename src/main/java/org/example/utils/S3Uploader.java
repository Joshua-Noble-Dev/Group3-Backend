package org.example.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;

public class S3Uploader {

    private final AmazonS3 s3Client;
    private final String bucketName;

    public S3Uploader(final AmazonS3 s3Client, final String bucketName) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    public String uploadCv(final InputStream inputStream,
                           final String fileName, final String applicantId) {
        String key = "cvs/" + applicantId + "/" + fileName;
        ObjectMetadata metadata = new ObjectMetadata();
        s3Client.putObject(bucketName, key, inputStream, metadata);
        return s3Client.getUrl(bucketName, key).toString();
    }
}
