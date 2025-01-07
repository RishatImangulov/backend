package com.richard.backend.entity;

import com.richard.backend.enumeration.ClientStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "person", schema = "ticketing_schema")

public class Person extends BaseEntity {
    @Size(max = 255)
    @NotNull
    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Size(max = 15)
    @NotNull
    @Column(name = "phone_primary", nullable = false, length = 15)
    private String phonePrimary;

    @Size(max = 15)
    @Column(name = "phone_secondary", length = 15)
    private String phoneSecondary;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

    @Size(max = 200)
    @Column(name = "telegram", length = 200)
    private String telegram;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement")
    private Advertisement advertisement;

    @Size(max = 128)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "client_status", nullable = false, length = 128)
    private ClientStatus clientStatus;

    @Size(max = 200)
    @Column(name = "comment", length = 200)
    private String comment;

}