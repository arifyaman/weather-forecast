package com.example.weathercheck.domain.forecast.api.v1.response;

import java.util.UUID;

public record PlaceResponse(
        UUID id,
        String name
) {
}
