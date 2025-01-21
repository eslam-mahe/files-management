package com.example.filesmanagement.models.entites;

import com.example.filesmanagement.constants.enums.ItemType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemType type;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_group_id")
    private PermissionGroupEntity permissionGroupEntity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ItemEntity parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ItemEntity> childEntities = new HashSet<>();


    public Long getId() {
        return id;
    }


    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<ItemEntity> getChildEntities() {
        return childEntities;
    }

    public void setChildEntities(Set<ItemEntity> childEntities) {
        this.childEntities = childEntities;
    }



    public PermissionGroupEntity getPermissionGroupEntity() {
        return permissionGroupEntity;
    }

    public void setPermissionGroupEntity(PermissionGroupEntity permissionGroupEntity) {
        this.permissionGroupEntity = permissionGroupEntity;
    }

    public ItemEntity getParent() {
        return parent;
    }

    public void setParent(ItemEntity parent) {
        this.parent = parent;
    }
}
