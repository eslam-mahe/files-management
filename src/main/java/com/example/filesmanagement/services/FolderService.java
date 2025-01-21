package com.example.filesmanagement.services;

import com.example.filesmanagement.Repositories.*;
import com.example.filesmanagement.constants.enums.ItemType;
import com.example.filesmanagement.constants.enums.PermissionLevel;
import com.example.filesmanagement.models.api.response.FolderResponse;
import com.example.filesmanagement.models.entites.ItemEntity;
import com.example.filesmanagement.models.entites.PermissionGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Set;

@Service
public class FolderService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PermissionGroupRepository permissionGroupRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    public void checkUserCanAddFolderInSpace(String userEmail, String spaceName) throws AccessDeniedException {
        ItemEntity space = itemRepository.findByName(spaceName).orElseThrow(() -> new IllegalArgumentException(spaceName + " space not found"));
        PermissionGroupEntity permissionGroup = space.getPermissionGroupEntity();

        boolean hasEditAccess = permissionRepository.existsByUserEmailAndPermissionLevelAndPermissionGroup(userEmail, PermissionLevel.EDIT, permissionGroup);
        if (!hasEditAccess) {
            throw new AccessDeniedException("User does not have edit permission for the specified space.");
        }
    }

    public FolderResponse createFolder(String userEmail, String spaceName, String folderName) throws AccessDeniedException {
        checkUserCanAddFolderInSpace(userEmail, spaceName);
        ItemEntity currentSpace= itemRepository.findByName(spaceName)
                .orElseThrow(() -> new IllegalArgumentException(spaceName + " space not found"));

        ItemEntity folder = new ItemEntity();
        folder.setType(ItemType.FOLDER);
        folder.setName(folderName);
        folder.setParent(currentSpace);
        folder.setPermissionGroupEntity(currentSpace.getPermissionGroupEntity());

        itemRepository.save(folder);

        return new FolderResponse(folder.getId(), folder.getName(), spaceName);
    }
}
