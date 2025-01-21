package com.example.filesmanagement.Repositories;

import com.example.filesmanagement.models.entites.FileEntity;
import com.example.filesmanagement.models.entites.ItemEntity;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    Optional<ItemEntity> findByName(String name);


    @Query( value ="SELECT * FROM item i WHERE i.name = :fileName", nativeQuery = true )
    ItemEntity findByNameInNativeQuery(String fileName);
}
