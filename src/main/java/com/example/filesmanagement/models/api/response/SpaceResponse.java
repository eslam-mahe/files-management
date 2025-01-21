package com.example.filesmanagement.models.api.response;

public class SpaceResponse {
    private Long id;
    private String spaceName;
    private String permissionGroupName;

    public SpaceResponse(Long id, String name, String groupName) {
        this.id=id;
        this.spaceName=name;
        this.permissionGroupName=groupName;
    }
}