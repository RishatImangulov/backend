package com.richard.backend.service;

import com.richard.backend.dto.AdvRequestDto;
import com.richard.backend.dto.AdvResponseDto;
import com.richard.backend.entity.Advertisement;
import com.richard.backend.exception.DuplicateFieldException;
import com.richard.backend.exception.FieldIsBlank;
import com.richard.backend.exception.NotFoundEntityByUuid;
import com.richard.backend.mapper.AdvertisementMapper;
import com.richard.backend.repository.AdvertisementRepository;
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
public class AdvertisementService {

    private final String ENTITY_NAME = "Advertisement";

    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementMapper advertisementMapper;
    private final CacheManager cacheManager;

    @Cacheable(value = "advertisements")
    public List<AdvResponseDto> getAll() {
        return advertisementRepository.findAllByIsDeletedFalse()
                .stream()
                .map(advertisementMapper::toAdvResponseDto)
                .toList();
    }

    public List<AdvResponseDto> getAllWithDeleted() {
        var advertisements = advertisementRepository.findAll();
        advertisements.forEach(advertisement -> Objects.requireNonNull(cacheManager.getCache("advertisement"))
                .put(advertisement.getId(), advertisementMapper.toAdvResponseDto(advertisement)));

        return advertisements.stream()
                .map(advertisementMapper::toAdvResponseDto)
                .toList();
    }


    @Cacheable(value = "advertisement", key = "#id")
    public AdvResponseDto getById(UUID id) {
        return advertisementRepository.findById(id)
                .map(advertisementMapper::toAdvResponseDto)
                .orElseThrow(() -> new NotFoundEntityByUuid(ENTITY_NAME, id.toString()));
    }

    public List<AdvResponseDto> findAllByFragment(String fragment) {
        return advertisementRepository.searchByFragment(fragment)
                .stream()
                .map(advertisementMapper::toAdvResponseDto)
                .toList();
    }

    @Transactional
    @CacheEvict(value = "advertisements", allEntries = true)
    public AdvResponseDto create(AdvRequestDto requestDto) {
        if (requestDto.getTitle() == null || requestDto.getTitle().isBlank()) {
            throw new FieldIsBlank("Short name", ENTITY_NAME);
        }

        requestDto.setId(null);
        requestDto.setTitle(requestDto.getTitle().trim());
        requestDto.setDescription(requestDto.getDescription().trim());


        if (advertisementRepository.existsByTitleIgnoreCase(requestDto.getTitle())) {
            throw new DuplicateFieldException("Title", ENTITY_NAME);
        }
        if (advertisementRepository.existsByDescriptionIgnoreCase(requestDto.getTitle())) {
            throw new DuplicateFieldException("Description", ENTITY_NAME);
        }

        Advertisement advertisement = advertisementMapper.toEntity(requestDto);

        var saved = advertisementRepository.save(advertisement);
        return advertisementMapper.toAdvResponseDto(saved);

    }

    @Transactional
    @CacheEvict(value = "advertisements", allEntries = true)
    @CachePut(value = "advertisement", key = "#id")
    public AdvResponseDto update(UUID id, AdvRequestDto requestDto) {

        var advertisement = advertisementRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityByUuid(ENTITY_NAME, id.toString()));

        requestDto.setId(id);
        requestDto.setTitle(requestDto.getTitle() == null ? null : requestDto.getTitle().trim());
        requestDto.setDescription(requestDto.getDescription() == null ? null : requestDto.getDescription().trim());

        validateUniqueness(requestDto, advertisement);

        var saved = advertisementMapper.updateWithNull(requestDto, advertisement);

        return advertisementMapper.toAdvResponseDto(saved);
    }

    @Transactional
    @CacheEvict(value = "advertisements", allEntries = true)
    @CachePut(value = "advertisement", key = "#id")
    public void delete(UUID id) {
        var advertisement = advertisementRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityByUuid(ENTITY_NAME, id.toString()));
        if (advertisement != null) {
            advertisementRepository.delete(advertisement);
        }
    }


    public void validateUniqueness(AdvRequestDto requestDto, Advertisement existingAdvertisement) {
        if (!existingAdvertisement.getDescription().equalsIgnoreCase(requestDto.getDescription()) &&
                advertisementRepository.existsByDescriptionIgnoreCase(requestDto.getDescription())) {
            throw new DuplicateFieldException("Description", ENTITY_NAME);
        }
        if (!existingAdvertisement.getTitle().equalsIgnoreCase(requestDto.getTitle()) &&
                advertisementRepository.existsByTitleIgnoreCase(requestDto.getTitle())) {
            throw new DuplicateFieldException("Title", ENTITY_NAME);
        }
    }

}
