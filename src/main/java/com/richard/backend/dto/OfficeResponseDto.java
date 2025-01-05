package com.richard.backend.dto;

import java.util.UUID;

/**
 * DTO for {@link com.richard.backend.entity.Office}
 */
public record OfficeResponseDto(
        UUID id,
        String name,
        String shortName) {
}