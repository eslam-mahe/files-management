package com.example.filesmanagement.models.entites;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permission_group")
public class PermissionGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String groupName;
    @OneToMany(mappedBy = "permissionGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PermissionEntity> permissionEntities = new HashSet<>();


    public Long getId() {
        return id;
    }


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<PermissionEntity> getPermissions() {
        return permissionEntities;
    }

    public void setPermissions(Set<PermissionEntity> permissionEntities) {
        this.permissionEntities = permissionEntities;
    }
}