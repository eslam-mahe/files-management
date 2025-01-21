package com.example.filesmanagement.controllers;

import com.example.filesmanagement.models.api.response.FolderResponse;
import com.example.filesmanagement.services.FolderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/folders")
public class FolderController {
    @Autowired
    private FolderService folderService;


    @PostMapping("/{spaceName}/{folderName}")
    public ResponseEntity<FolderResponse> createBackendFolder(@RequestHeader("user-email") String userEmail, @PathVariable String spaceName, @PathVariable String folderName) throws AccessDeniedException {
        FolderResponse folderResponse =folderService.createFolder(userEmail, spaceName, folderName);
        return new ResponseEntity<>(folderResponse, HttpStatus.CREATED);    }
}
