package com.richard.backend.dto;

import com.richard.backend.entity.Person;
import com.richard.backend.enumeration.ClientStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link Person}
 */
public record PersonResponseDto(
        UUID id,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate,
        UUID createdBy,
        UUID lastModifiedBy,
        Long version,
        Boolean isDeleted,
        @NotNull @Size(max = 255) String fullname,
        @NotNull @Size(max = 15) String phonePrimary,
        @Size(max = 15) String phoneSecondary,
        @Size(max = 100) String email,
        @Size(max = 200) String telegram,
        AdvResponseDto advertisement,
        @NotNull ClientStatus clientStatus,
        @Size(max = 200) String comment
) implements Serializable {
}