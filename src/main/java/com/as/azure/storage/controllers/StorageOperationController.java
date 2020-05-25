package com.as.azure.storage.controllers;

import com.as.azure.storage.services.BlobStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage")
public class StorageOperationController {

    @Autowired
    private BlobStorageService blobStorageService;

    @GetMapping("/files")
    public Object getListOfFiles() {
        return blobStorageService.listOfFiles();
    }
}
