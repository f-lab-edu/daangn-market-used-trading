package com.ssibongee.daangnmarket.image.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.ssibongee.daangnmarket.commons.properties.AwsS3Properties;
import com.ssibongee.daangnmarket.image.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AwsS3Service implements StorageService {

    private final AwsS3Properties properties;

    private final AmazonS3 client;

    public String upload(MultipartFile file, String filename) throws IOException {
        return putObjectToS3Storage(client, FileUtils.getFilePath(file, filename), file);
    }

    private String putObjectToS3Storage(AmazonS3 client, String filepath, MultipartFile file) throws IOException {
        String bucket = properties.getBucket();

        ObjectMetadata metadata = new ObjectMetadata();
        ByteArrayInputStream stream = getByteArrayInputStream(file, metadata);

        client.putObject(new PutObjectRequest(bucket, filepath, stream, metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return client.getUrl(bucket, filepath).toString();
    }

    private ByteArrayInputStream getByteArrayInputStream(MultipartFile file, ObjectMetadata metadata) throws IOException {
        byte[] bytes = IOUtils.toByteArray(file.getInputStream());
        metadata.setContentLength(bytes.length);
        return new ByteArrayInputStream(bytes);
    }

}
