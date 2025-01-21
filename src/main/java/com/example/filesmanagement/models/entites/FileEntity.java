package com.example.filesmanagement.models.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "file")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] binary;

    @OneToOne
    @JoinColumn(name = "item_id")
    private ItemEntity itemEntity; // Getters and setters }

    public Long getId() {
        return id;
    }


    public byte[] getBinary() {
        return binary;
    }

    public void setBinary(byte[] binary) {
        this.binary = binary;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }
}