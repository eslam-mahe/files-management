package com.example.filesmanagement.Repositories;

import com.example.filesmanagement.models.entites.FileEntity;
import com.example.filesmanagement.models.entites.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

    @Query( value ="SELECT * FROM file i WHERE i.item_id = :itemId", nativeQuery = true )
    Optional<FileEntity> findByItemId(Long itemId);
}
