package com.example.filesmanagement.models.api.request;

import com.example.filesmanagement.constants.enums.PermissionLevel;

import java.util.Map;


public class SpaceRequest {

    private String name;
    private String permissionGroupName;

    private Map<PermissionLevel, String> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermissionGroupName() {
        return permissionGroupName;
    }

    public void setPermissionGroupName(String permissionGroupName) {
        this.permissionGroupName = permissionGroupName;
    }

    public Map<PermissionLevel, String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Map<PermissionLevel, String> permissions) {
        this.permissions = permissions;
    }
}
