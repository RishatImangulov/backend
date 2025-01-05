package com.richard.backend.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AuditorAwareImpl implements AuditorAware<UUID> {
    //TODO get real user then
    @Override
    public Optional<UUID> getCurrentAuditor() {
        return Optional.of(UUID.fromString("a77f2225-8f4b-463d-8214-f6950e1d8b41"
        ));
    }
}
