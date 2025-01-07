package com.richard.backend.repository;

import com.richard.backend.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AdvertisementRepository extends JpaRepository<Advertisement, UUID> {

    List<Advertisement> findAllByIsDeletedFalse();

    List<Advertisement> findByTitleContainingIgnoreCase(String fragment);

    boolean existsByTitleIgnoreCase(String fragment);

    List<Advertisement> findByDescriptionContainingIgnoreCase(String fragment);

    boolean existsByDescriptionIgnoreCase(String fragment);

    List<Advertisement> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String Title, String Description);

    default List<Advertisement> searchByFragment(String fragment){
        return findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(fragment, fragment);
    };

}