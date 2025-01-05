package com.richard.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
    private String name;
    private String shortName;
}