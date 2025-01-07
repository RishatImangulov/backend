package com.richard.backend.repository;

import com.richard.backend.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OfficeRepository extends JpaRepository<Office, UUID> {

    List<Office> findAllByIsDeletedFalse();

    List<Office> findByNameContainingIgnoreCase(String fragment);

    boolean existsByNameIgnoreCase(String name);

    List<Office> findByShortNameContainingIgnoreCase(String fragment);

    boolean existsByShortNameIgnoreCase(String name);

    List<Office> findByNameContainingIgnoreCaseOrShortNameContainingIgnoreCase(String name, String shortName);

    default List<Office> searchByFragment(String fragment){
        return findByNameContainingIgnoreCaseOrShortNameContainingIgnoreCase(fragment, fragment);
    };

}