package com.soa.converter;

import com.example.vehicleservice.catalog.CoordinatesRequestDto;
import com.example.vehicleservice.catalog.CoordinatesResponseDto;
import com.soa.entity.CoordinatesEntity;
import org.springframework.stereotype.Component;

@Component
public class CoordinatesConverter {
    public CoordinatesEntity convertToEntity(CoordinatesRequestDto dto) {
        CoordinatesEntity entity = new CoordinatesEntity();
        entity
                .setX(Double.valueOf(dto.getX()))
                .setY(Double.valueOf(dto.getY()));
        return entity;
    }

    public CoordinatesResponseDto convertToDto(CoordinatesEntity entity) {
        CoordinatesResponseDto dto = new CoordinatesResponseDto();
        dto.setX(entity.getX().longValue());
        dto.setY(entity.getY());
        return dto;
    }
}
