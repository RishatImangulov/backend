package com.richard.backend.mapper;

import com.richard.backend.dto.PersonRequestDto;
import com.richard.backend.entity.Person;
import com.richard.backend.dto.PersonResponseDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {AdvertisementMapper.class})
public interface PersonMapper {
    Person toEntity(PersonResponseDto personResponseDto);

    PersonResponseDto toDto(Person person);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Person partialUpdate(PersonResponseDto personResponseDto, @MappingTarget Person person);

    @Mapping(source = "advertisementId", target = "advertisement.id")
    Person toEntity(PersonRequestDto personRequestDto);

    @Mapping(source = "advertisement.id", target = "advertisementId")
    PersonRequestDto toPersonRequestDto(Person person);
}