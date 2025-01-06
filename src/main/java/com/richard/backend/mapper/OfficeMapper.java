package com.richard.backend.mapper;

import com.richard.backend.dto.OfficeRequestDto;
import com.richard.backend.dto.OfficeResponseDto;
import com.richard.backend.entity.Office;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface OfficeMapper {
    Office toEntity(OfficeResponseDto officeResponseDto);

    OfficeResponseDto toOfficeResponseDto(Office office);

    Office toEntity(OfficeRequestDto officeRequestDto);

    OfficeRequestDto toOfficeRequestDto(Office office);

    @Mapping(target = "id", ignore = true, nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
    Office updateWithNull(OfficeRequestDto officeRequestDto, @MappingTarget Office office);
}