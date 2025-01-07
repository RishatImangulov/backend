package com.richard.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.richard.backend.entity.Advertisement}
 */
public record AdvResponseDto(UUID id, LocalDateTime createdDate, LocalDateTime lastModifiedDate, UUID createdBy,
                             UUID lastModifiedBy, Long version, Boolean isDeleted,
                             @NotNull @Size(max = 64) String title, @NotNull @Size(max = 255) String description) {
}