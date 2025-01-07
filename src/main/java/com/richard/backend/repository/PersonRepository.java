package com.richard.backend.repository;

import com.richard.backend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    List<Person> findAllByIsDeletedFalse();

    List<Person> findByFullnameContainingIgnoreCase(String fragment);

    boolean existsByFullnameIgnoreCase(String name);

    List<Person> findByShortNameContainingIgnoreCase(String fragment);

    boolean existsByShortNameIgnoreCase(String name);

    List<Person> findByNameContainingIgnoreCaseOrShortNameContainingIgnoreCase(String name, String shortName);

    default List<Person> searchByFragment(String fragment){
        return findByNameContainingIgnoreCaseOrShortNameContainingIgnoreCase(fragment, fragment);
    };

}