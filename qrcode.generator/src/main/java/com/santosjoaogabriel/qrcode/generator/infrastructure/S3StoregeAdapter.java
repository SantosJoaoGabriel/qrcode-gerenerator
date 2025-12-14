package com.santosjoaogabriel.qrcode.generator.infrastructure;

import com.santosjoaogabriel.qrcode.generator.ports.StoregePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


@Component
public class S3StoregeAdapter implements StoregePort {

    private final S3Client s3Client;

    private final String bucketName;

    public final String region;

    public S3StoregeAdapter(@Value("${aws.s3.region}") String region,
                            @Value("{aws.s3.bucket-name}") String bucketName) {
        this.bucketName = bucketName;
        this.region = region;
        this.s3Client = S3Client.builder()
                .region(software.amazon.awssdk.regions.Region.of(region))
                .build();
    }


    @Override
    public String uploadFile(byte[] fileDate, String fileName, String contentType) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(contentType)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileDate));

        return String.format("https://%s.s3.%s.amazonaws.com/%s",
                bucketName, region, fileName);
    }
}
