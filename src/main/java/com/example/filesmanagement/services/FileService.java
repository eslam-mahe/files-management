package com.example.filesmanagement.services;

import com.example.filesmanagement.Repositories.*;
import com.example.filesmanagement.constants.enums.*;
import com.example.filesmanagement.models.api.response.*;
import com.example.filesmanagement.models.entites.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;


@Service
public class FileService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private FileRepository fileRepository;


    public FileResponse createFile(String userEmail, String folderName, String fileName, byte[] fileContent) throws AccessDeniedException {
        checkUserCanEditFolder(userEmail,folderName);
        ItemEntity fileItem =createItem( folderName, fileName);
        FileEntity fileEntity = new FileEntity();
        fileEntity.setBinary(fileContent);
        fileEntity.setItemEntity(fileItem);
        fileRepository.save(fileEntity);

        return new FileResponse(fileEntity.getId(), fileItem.getName(), folderName);

    }


    public FileResponse FetchFileIdByName(String userEmail, String fileName) throws AccessDeniedException {
          checkUserCanViewFile(userEmail,fileName);
        ItemEntity itemEntity =itemRepository.findByNameInNativeQuery(fileName);
        return new FileResponse(itemEntity.getId(), itemEntity.getName());
    }

    public FileResponse DownloadFileIdByName(String userEmail, String fileName) throws AccessDeniedException {
        checkUserCanViewFile(userEmail,fileName);
        ItemEntity itemEntity =itemRepository.findByName(fileName).orElseThrow(() -> new IllegalArgumentException(fileName + " file not found"));
        FileEntity fileEntity = fileRepository.findByItemId(itemEntity.getId())
                .orElseThrow(() -> new IllegalArgumentException(fileName + " file not found"));
        return new FileResponse( itemEntity.getName(),fileEntity.getBinary());
    }
    public ItemEntity  createItem(String folderName, String fileName){

        ItemEntity currentFolder= itemRepository.findByName(folderName)
                .orElseThrow(() -> new IllegalArgumentException(folderName + " folder not found"));

        ItemEntity file = new ItemEntity();
        file.setType(ItemType.FILE);
        file.setName(fileName);
        file.setParent(currentFolder);
        PermissionGroupEntity permissionGroup = currentFolder.getPermissionGroupEntity();

        file.setPermissionGroupEntity(permissionGroup);
        itemRepository.save(file);

        return file;
    }

    public void checkUserCanEditFolder(String userEmail, String folderName) throws AccessDeniedException {
        ItemEntity folder = itemRepository.findByName(folderName).orElseThrow(() -> new IllegalArgumentException(folderName + " folder not found"));
        PermissionGroupEntity permissionGroup = folder.getPermissionGroupEntity();

        boolean hasEditAccess = permissionRepository.existsByUserEmailAndPermissionLevelAndPermissionGroup(userEmail, PermissionLevel.EDIT, permissionGroup);
        if (!hasEditAccess) {
            throw new AccessDeniedException("User does not have edit permission for the specified folder.");
        }
    }
    public void checkUserCanViewFile(String userEmail, String fileName) throws AccessDeniedException {
        ItemEntity file = itemRepository.findByName(fileName).orElseThrow(() -> new IllegalArgumentException(fileName + " file not found"));

        boolean hasViewAccess = permissionRepository.existsByUserEmailAndPermissionLevelAndPermissionGroup(userEmail, PermissionLevel.VIEW, file.getPermissionGroupEntity());
        if (!hasViewAccess) {
            throw new AccessDeniedException("User does not have view permission for the specified file.");
        }

    }
}