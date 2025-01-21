package com.example.filesmanagement.controllers;

import com.example.filesmanagement.models.api.response.SpaceResponse;
import com.example.filesmanagement.services.SpaceService;
import com.example.filesmanagement.models.api.request.SpaceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
    @RequestMapping("/api/spaces")
    public class SpaceController {
        @Autowired
        private SpaceService spaceService;

        @PostMapping()
        public ResponseEntity<SpaceResponse> createSpace(@RequestHeader("user-email") String userEmail, @RequestBody SpaceRequest newSpaceRequest) throws AccessDeniedException {
            spaceService.checkUserWriteAccess(userEmail);
            SpaceResponse spaceResponse =  spaceService.createSpace(newSpaceRequest);
            return new  ResponseEntity<>(spaceResponse, HttpStatus.CREATED);
        }
    }

