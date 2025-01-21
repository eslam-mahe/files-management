package com.example.filesmanagement.services;

import com.example.filesmanagement.Repositories.*;
import com.example.filesmanagement.constants.enums.*;
import com.example.filesmanagement.models.api.request.SpaceRequest;
import com.example.filesmanagement.models.api.response.SpaceResponse;
import com.example.filesmanagement.models.entites.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Map;

@Service
public class SpaceService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PermissionGroupRepository permissionGroupRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    public SpaceResponse createSpace(SpaceRequest newSpaceRequest) {

        ItemEntity space = new ItemEntity();
        space.setType(ItemType.SPACE);
        space.setName(newSpaceRequest.getName());

        PermissionGroupEntity permissionGroup = permissionGroupRepository.findByGroupName(newSpaceRequest.getPermissionGroupName()).orElseGet(() -> {
            PermissionGroupEntity newGroup = new PermissionGroupEntity();
            newGroup.setGroupName(newSpaceRequest.getPermissionGroupName());
            permissionGroupRepository.save(newGroup);
            return newGroup;
        });

        for (Map.Entry<PermissionLevel, String> entry : newSpaceRequest.getPermissions().entrySet()) {
            assignUserPermissions(entry.getValue(), entry.getKey(), permissionGroup);
        }

        // Assign the created permission group to the space
        space.setPermissionGroupEntity(permissionGroup);
        // Save the space item
        itemRepository.save(space);

        return new SpaceResponse(space.getId(), space.getName(), space.getPermissionGroupEntity().getGroupName());

    }

    private void assignUserPermissions(String userEmail, PermissionLevel permissionLevel, PermissionGroupEntity permissionGroup) {
        PermissionEntity permission = new PermissionEntity();
        permission.setUserEmail(userEmail);
        permission.setPermissionLevel(permissionLevel);
        permission.setPermissionGroup(permissionGroup);
        permissionRepository.save(permission);

    }

    public void checkUserWriteAccess(String userEmail) throws AccessDeniedException {

        boolean hasWriteAccess = permissionRepository.existsByUserEmailAndPermissionLevel(userEmail, PermissionLevel.EDIT);
        if (!hasWriteAccess) {
            throw new AccessDeniedException("User does not have write permission.");
        }

    }
}


