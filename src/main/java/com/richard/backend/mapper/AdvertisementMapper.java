package com.richard.backend.mapper;

import com.richard.backend.dto.AdvResponseDto;
import com.richard.backend.dto.AdvRequestDto;
import com.richard.backend.entity.Advertisement;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface AdvertisementMapper {
    Advertisement toEntity(AdvResponseDto advertisementDto);

    AdvResponseDto toAdvResponseDto(Advertisement advertisement);

    @Mapping(target = "id", ignore = true, nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
    Advertisement updateWithNull(AdvRequestDto advResponseDto, @MappingTarget Advertisement advertisement);

    Advertisement toEntity(AdvRequestDto advRequestDto);

    AdvRequestDto toAdvRequestDto(Advertisement advertisement);

    Advertisement toEntity(Advertisement advertisement);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Advertisement partialUpdate(AdvResponseDto advResponseDto, @MappingTarget Advertisement advertisement);
}