package com.as.azure.storage;

import com.as.azure.storage.services.BlobStorageService;
import com.azure.storage.blob.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfiguration {

    @Value("${azure.storage.connection-string}")
    public String azureStorageConnectionString;

    @Value("${azure.storage.container-name}")
    public String azureStorageContainerName;

    @Bean
    public BlobServiceAsyncClient blobServiceClient() {
        return new BlobServiceClientBuilder().connectionString(azureStorageConnectionString).buildAsyncClient();
    }

    @Bean
    public BlobContainerAsyncClient blobAsyncClient() {
        return blobServiceClient().getBlobContainerAsyncClient(azureStorageContainerName);
    }

}