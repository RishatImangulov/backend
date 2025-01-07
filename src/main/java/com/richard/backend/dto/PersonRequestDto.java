package com.richard.backend.dto;

import com.richard.backend.enumeration.ClientStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO for {@link com.richard.backend.entity.Person}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequestDto {
    private UUID id;
    @NotNull
    @Size(max = 255)
    private String fullname;
    @NotNull
    @Size(max = 15)
    private String phonePrimary;
    @Size(max = 15)
    private String phoneSecondary;
    @Size(max = 100)
    private String email;
    @Size(max = 200)
    private String telegram;
    private UUID advertisementId;
    @NotNull
    private ClientStatus clientStatus;
    @Size(max = 200)
    private String comment;
}