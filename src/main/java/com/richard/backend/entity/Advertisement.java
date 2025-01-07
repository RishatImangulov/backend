package com.richard.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "advertisement", schema = "ticketing_schema")
public class Advertisement extends BaseEntity {
    @Size(max = 64)
    @NotNull
    @Column(name = "title", nullable = false, length = 64)
    private String title;

    @Size(max = 255)
    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

}