package com.ssibongee.daangnmarket.image.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.ssibongee.daangnmarket.commons.properties.AwsS3Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AmazonS3Config {

    private final AwsS3Properties properties;

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(properties.getEndPoint(), properties.getRegion()))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(properties.getAccessKey(), properties.getSecretKey())))
                .build();
    }


}
