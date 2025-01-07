package com.richard.backend.controller;

import com.richard.backend.dto.OfficeRequestDto;
import com.richard.backend.dto.OfficeResponseDto;
import com.richard.backend.service.OfficeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/offices")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // Разрешённый домен
public class OfficeResource {

    private final OfficeService officeService;

    @GetMapping
    public ResponseEntity<List<OfficeResponseDto>> getAll() {
        List<OfficeResponseDto> offices = officeService.getAll();
        return ResponseEntity.ok(offices);
    }

    @GetMapping("/search")
    public ResponseEntity<List<OfficeResponseDto>> search(@RequestParam String fragment) {
        List<OfficeResponseDto> results = officeService.findAllByFragment(fragment);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfficeResponseDto> getById(@PathVariable UUID id) {
        OfficeResponseDto OfficeResponseDto = officeService.getById(id);
        return ResponseEntity.ok(OfficeResponseDto);
    }

    @PostMapping
    public ResponseEntity<OfficeResponseDto> create(@Valid @RequestBody OfficeRequestDto officeRequestDto) {
        var officeResponseDto = officeService.create(officeRequestDto);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(officeResponseDto.id())
                .toUri();
        return ResponseEntity.created(location).body(officeResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfficeResponseDto> update(@PathVariable UUID id,
                                                    @Valid @RequestBody OfficeRequestDto officeRequestDto) {
        var updatedAd = officeService.update(id, officeRequestDto);
        return ResponseEntity.ok(updatedAd);
    }

    @DeleteMapping("/{id}")
    public void deleteOffice(@PathVariable UUID id) {
        officeService.delete(id);
    }


}
