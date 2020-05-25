package com.as.azure.storage.controllers;

import com.as.azure.storage.models.SuccessfulOperation;
import com.as.azure.storage.services.BlobStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/storage")
public class StorageOperationController {

    @Autowired
    private BlobStorageService blobStorageService;

    @GetMapping(value = "/files", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getListOfFiles() {
        return blobStorageService.listOfFiles();
    }

    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object handleFileUpload(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        blobStorageService.upload(multipartFile.getResource());
        return new ResponseEntity(new SuccessfulOperation(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{fileName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object handleFileUpload(@PathVariable String fileName) throws IOException {
        blobStorageService.delete(fileName);
        return new ResponseEntity(new SuccessfulOperation(), HttpStatus.OK);
    }

    @GetMapping(value = "/download/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] handleFileDownload(@PathVariable String fileName) throws IOException {
         return blobStorageService.download(fileName).toByteArray();
    }
}
