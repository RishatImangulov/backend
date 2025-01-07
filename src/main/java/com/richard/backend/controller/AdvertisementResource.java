package com.richard.backend.controller;

import com.richard.backend.dto.AdvRequestDto;
import com.richard.backend.dto.AdvResponseDto;
import com.richard.backend.service.AdvertisementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/advertisements")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // Разрешённый домен
public class AdvertisementResource {

    private final AdvertisementService advService;

    @GetMapping
    public ResponseEntity<List<AdvResponseDto>> getAll() {
        List<AdvResponseDto> advertisements = advService.getAll();
        return ResponseEntity.ok(advertisements);
    }

    @GetMapping("/search")
    public ResponseEntity<List<AdvResponseDto>> search(@RequestParam String fragment) {
        List<AdvResponseDto> results = advService.findAllByFragment(fragment);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvResponseDto> getById(@PathVariable UUID id) {
        AdvResponseDto AdvResponseDto = advService.getById(id);
        return ResponseEntity.ok(AdvResponseDto);
    }

    @PostMapping
    public ResponseEntity<AdvResponseDto> create(@Valid @RequestBody AdvRequestDto advRequestDto) {
        var advResponseDto = advService.create(advRequestDto);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(advResponseDto.id())
                .toUri();
        return ResponseEntity.created(location).body(advResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdvResponseDto> update(@PathVariable UUID id,
                                                 @Valid @RequestBody AdvRequestDto advRequestDto) {
        var updatedAd = advService.update(id, advRequestDto);
        return ResponseEntity.ok(updatedAd);
    }

    @DeleteMapping("/{id}")
    public void deleteAdvertisement(@PathVariable UUID id) {
        advService.delete(id);
    }


}
