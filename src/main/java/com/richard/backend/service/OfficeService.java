package com.richard.backend.service;

import com.richard.backend.dto.OfficeRequestDto;
import com.richard.backend.dto.OfficeResponseDto;
import com.richard.backend.entity.Office;
import com.richard.backend.exception.DuplicateFieldException;
import com.richard.backend.exception.FieldIsBlank;
import com.richard.backend.exception.NotFoundEntityByUuid;
import com.richard.backend.mapper.OfficeMapper;
import com.richard.backend.repository.OfficeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Service
public class OfficeService {

    private final String ENTITY_NAME = "Office";

    private final OfficeRepository officeRepository;
    private final OfficeMapper officeMapper;
    private final CacheManager cacheManager;

    @Cacheable(value = "offices")
    public List<OfficeResponseDto> getAll() {
        return officeRepository.findAllByIsDeletedFalse()
                .stream()
                .map(officeMapper::toOfficeResponseDto)
                .toList();
    }

    public List<OfficeResponseDto> getAllWithDeleted() {
        var offices = officeRepository.findAll();
        offices.forEach(office -> Objects.requireNonNull(cacheManager.getCache("office"))
                .put(office.getId(), officeMapper.toOfficeResponseDto(office)));

        return offices.stream()
                .map(officeMapper::toOfficeResponseDto)
                .toList();
    }


    @Cacheable(value = "office", key = "#id")
    public OfficeResponseDto getById(UUID id) {
        return officeRepository.findById(id)
                .map(officeMapper::toOfficeResponseDto)
                .orElseThrow(() -> new NotFoundEntityByUuid(ENTITY_NAME, id.toString()));
    }

    public List<OfficeResponseDto> findAllByFragment(String fragment) {
        return officeRepository.searchByFragment(fragment)
                .stream()
                .map(officeMapper::toOfficeResponseDto)
                .toList();
    }

    @Transactional
    @CacheEvict(value = "offices", allEntries = true)
    public OfficeResponseDto create(OfficeRequestDto requestDto) {
        if (requestDto.getShortName() == null || requestDto.getShortName().isBlank()) {
            throw new FieldIsBlank("Short name", ENTITY_NAME);
        }

        requestDto.setId(null);
        requestDto.setShortName(requestDto.getShortName().trim());
        requestDto.setName(requestDto.getName().trim());


        if (officeRepository.existsByShortNameIgnoreCase(requestDto.getShortName())) {
            throw new DuplicateFieldException("ShortName", ENTITY_NAME);
        }
        if (officeRepository.existsByNameIgnoreCase(requestDto.getShortName())) {
            throw new DuplicateFieldException("Name", ENTITY_NAME);
        }

        Office office = officeMapper.toEntity(requestDto);

        var saved = officeRepository.save(office);
        return officeMapper.toOfficeResponseDto(saved);

    }

    @Transactional
    @CacheEvict(value = "offices", allEntries = true)
    @CachePut(value = "office", key = "#id")
    public OfficeResponseDto update(UUID id, OfficeRequestDto requestDto) {

        var office = officeRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityByUuid(ENTITY_NAME, id.toString()));

        requestDto.setId(id);
        requestDto.setShortName(requestDto.getShortName() == null ? null : requestDto.getShortName().trim());
        requestDto.setName(requestDto.getName() == null ? null : requestDto.getName().trim());

        validateUniqueness(requestDto, office);

        var saved = officeMapper.updateWithNull(requestDto, office);

        return officeMapper.toOfficeResponseDto(saved);
    }

    @Transactional
    @CacheEvict(value = "offices", allEntries = true)
    @CachePut(value = "office", key = "#id")
    public void delete(UUID id) {
        var office = officeRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityByUuid(ENTITY_NAME, id.toString()));
        if (office != null) {
            officeRepository.delete(office);
        }
    }


    public void validateUniqueness(OfficeRequestDto requestDto, Office existingOffice) {
        if (!existingOffice.getName().equalsIgnoreCase(requestDto.getName()) &&
                officeRepository.existsByNameIgnoreCase(requestDto.getName())) {
            throw new DuplicateFieldException("Name", ENTITY_NAME);
        }
        if (!existingOffice.getShortName().equalsIgnoreCase(requestDto.getShortName()) &&
                officeRepository.existsByShortNameIgnoreCase(requestDto.getShortName())) {
            throw new DuplicateFieldException("ShortName", ENTITY_NAME);
        }
    }

}
