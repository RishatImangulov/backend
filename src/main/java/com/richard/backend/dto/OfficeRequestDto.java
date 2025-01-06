package com.richard.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO for {@link com.richard.backend.entity.Office}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Accessors(chain = true)
public class OfficeRequestDto {
    private UUID id;

    @NotBlank(message = "office name can't be blank")
    @Size(min = 2, max = 255, message = "The length of the office name must be between 2 and 5 inclusive")
    private String name;

    @NotBlank(message = "office short name can't be blank")
    @Size(min = 2, max = 5, message = "The length of the office short name must be between 2 and 5 inclusive")
    private String shortName;
}