package com.richard.backend.dto;

import com.richard.backend.entity.Advertisement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO for {@link Advertisement}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvRequestDto {
    private UUID id;

    @NotNull
    @Size(max = 64)
    @NotBlank
    private String title;

    @NotNull
    @Size(max = 255)
    @NotBlank
    private String description;
}