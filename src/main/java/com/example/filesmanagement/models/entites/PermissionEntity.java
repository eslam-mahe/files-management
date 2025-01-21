package com.example.filesmanagement.models.entites;
import com.example.filesmanagement.constants.enums.PermissionLevel;
import jakarta.persistence.*;

@Entity
@Table(name = "permission")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String userEmail;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PermissionLevel permissionLevel;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private PermissionGroupEntity permissionGroup;


    public Long getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public PermissionLevel getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(PermissionLevel permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public PermissionGroupEntity getPermissionGroup() {
        return permissionGroup;
    }

    public void setPermissionGroup(PermissionGroupEntity permissionGroup) {
        this.permissionGroup = permissionGroup;
    }
}
