package com.example.filesmanagement.Repositories;

import com.example.filesmanagement.constants.enums.PermissionLevel;
import com.example.filesmanagement.models.entites.PermissionEntity;
import com.example.filesmanagement.models.entites.PermissionGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity,Long> {


    boolean existsByUserEmailAndPermissionLevel(String userEmail, PermissionLevel permissionLevel);

    boolean existsByUserEmailAndPermissionLevelAndPermissionGroup(String userEmail, PermissionLevel permissionLevel, PermissionGroupEntity permissionGroup);
}
