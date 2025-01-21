package com.example.filesmanagement.controllers;

import com.example.filesmanagement.models.api.response.FileResponse;
import com.example.filesmanagement.services.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/{folderName}/{fileName}")
    public ResponseEntity<FileResponse> createFile(@RequestHeader("user-email") String userEmail,
                                                   @PathVariable String folderName,
                                                   @PathVariable String fileName,
                                                   @RequestBody byte[] fileContent) throws AccessDeniedException {
        FileResponse fileResponse = fileService.createFile(userEmail, folderName, fileName, fileContent);
        return new ResponseEntity<>(fileResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<FileResponse> FetchFileIdByName(@RequestHeader("user-email") String userEmail,
                                                          @PathVariable String fileName) throws AccessDeniedException {
        FileResponse fileResponse = fileService.FetchFileIdByName(userEmail, fileName);
        return new ResponseEntity<>(fileResponse, HttpStatus.CREATED);
    }

    @GetMapping("/download/{fileName}")
    public void downloadFileIdByName(@RequestHeader("user-email") String userEmail, @PathVariable String fileName, HttpServletResponse response) throws  IOException {
        FileResponse fileResponse = fileService.DownloadFileIdByName(userEmail, fileName);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileResponse.getName());
        try (OutputStream os = response.getOutputStream()) {
            os.write(fileResponse.getFileBinary());
            os.flush();
        }
    }
}
