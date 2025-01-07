package com.richard.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "ticket", schema = "ticketing_schema")

public class Ticket extends BaseEntity {
    @NotNull
    @Column(name = "number", nullable = false)
    private Long number;

    @NotNull
    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_user")
    private User receiverUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "executor_user")
    private User executorUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

}