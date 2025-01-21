package com.example.filesmanagement.models.api.response;

public class FileResponse {
    private Long id;
    private String name;
    private String folderName;

    private byte[] fileBinary;

    public FileResponse(Long id, String name, String folderName) {
        this.id = id;
        this.name=name;
        this.folderName=folderName;
    }
    public FileResponse(Long id, String name ){
        this.id = id;
        this.name=name;
    }
    public FileResponse( String name, byte[] fileBinary) {
        this.fileBinary=fileBinary;
        this.folderName=name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public byte[] getFileBinary() {
        return fileBinary;
    }

    public void setFileBinary(byte[] fileBinary) {
        this.fileBinary = fileBinary;
    }
}