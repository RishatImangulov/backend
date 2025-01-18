package com.richard.backend.dto;


import jakarta.persistence.Cacheable;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.richard.backend.entity.Office}
 */
public record OfficeResponseDto(
        UUID id,
        String name,
        String shortName,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate,
        UUID createdBy,
        UUID lastModifiedBy,
        Long version,
        Boolean isDeleted) {
}