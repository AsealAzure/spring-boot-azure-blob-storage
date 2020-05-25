package com.as.azure.storage.services;

import com.azure.core.http.rest.PagedFlux;
import com.azure.storage.blob.*;
import com.azure.storage.blob.models.BlobItem;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlobStorageService {

    private final BlobContainerAsyncClient blobContainerAsyncClient;

    public BlobStorageService(BlobContainerAsyncClient blobContainerAsyncClient) {
        this.blobContainerAsyncClient = blobContainerAsyncClient;
    }

    public List<BlobItem> listOfFiles() {
        return blobContainerAsyncClient.listBlobs().toStream().collect(Collectors.toList());
    }

    public void uploadFile(File resource) {
        BlobAsyncClient blobAsyncClient = blobContainerAsyncClient.getBlobAsyncClient(resource.getName());
        blobAsyncClient.uploadFromFile(resource.getPath());
    }
}
