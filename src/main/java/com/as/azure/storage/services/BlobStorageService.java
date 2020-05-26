package com.as.azure.storage.services;

import com.as.azure.storage.models.FileItem;
import com.azure.core.http.rest.PagedFlux;
import com.azure.storage.blob.*;
import com.azure.storage.blob.models.BlobItem;
import com.azure.storage.blob.models.ParallelTransferOptions;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlobStorageService {

    private final BlobContainerAsyncClient blobContainerAsyncClient;
    int blockSize = 10 * 1024;
    int numBuffers = 5;

    public BlobStorageService(BlobContainerAsyncClient blobContainerAsyncClient) {
        this.blobContainerAsyncClient = blobContainerAsyncClient;
    }

    public List<FileItem> listOfFiles() {
        return blobContainerAsyncClient.listBlobs().toStream()
                .map(blobItem -> {
                    return new FileItem(blobItem.getName());
                })
                .collect(Collectors.toList());
    }

    public void upload(Resource resource) throws IOException {
        BlobAsyncClient blobAsyncClient = blobContainerAsyncClient.getBlobAsyncClient(resource.getFilename());
        Flux<ByteBuffer> data = Flux.just(ByteBuffer.wrap(resource.getInputStream().readAllBytes()));
        ParallelTransferOptions parallelTransferOptions = new ParallelTransferOptions(numBuffers, blockSize, null);
        blobAsyncClient.upload(data,parallelTransferOptions, true).block();
    }

    public void delete(String fileName) {
        BlobAsyncClient blobAsyncClient = blobContainerAsyncClient.getBlobAsyncClient(fileName);
        blobAsyncClient.delete().block();
    }

    public byte[] download(String fileName) {
        BlobAsyncClient blobAsyncClient = blobContainerAsyncClient.getBlobAsyncClient(fileName);
        return blobAsyncClient.download().blockLast().array();
    }
}
