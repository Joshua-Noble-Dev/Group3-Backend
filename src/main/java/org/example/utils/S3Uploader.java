package org.example.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.InputStream;

public class S3Uploader {

    private final AmazonS3 s3Client;
    private final String bucketName;

    public S3Uploader(final AmazonS3 s3Client, final String bucketName) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    public void uploadCv(final String key,
                           final InputStream inputStream
//                           final String fileName
        ) throws IOException {
//        String timestamp = new SimpleDateFormat(
//                "yyyyMMddHHmmssSSS").format(new Date());
//        String key = "cvs/" + fileName + "-" + timestamp;
//        ObjectMetadata metadata = new ObjectMetadata();
//        s3Client.putObject(bucketName, key, inputStream, metadata);
//        return s3Client.getUrl(bucketName, key).toString();

        ObjectMetadata metadata = new ObjectMetadata();
        byte[] contentBytes = inputStream.readAllBytes();
        metadata.setContentLength(contentBytes.length);

        try (InputStream contentStream =
                     new ByteArrayInputStream(contentBytes)) {
            s3Client.putObject(bucketName, key, contentStream, metadata);
        }
    }
}
