package com.example.filesmanagement.models.api.response;

public class FolderResponse {

    private Long id;
    private String name;
    private String spaceName;

    public FolderResponse(Long id, String name, String spaceName) {
        this.id = id;
        this.name = name;
        this.spaceName = spaceName;
    }
}
