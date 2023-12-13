package com.soa.converter;

import com.example.vehicleservice.catalog.*;
import com.soa.dto.FuelType;
import com.soa.dto.VehicleType;
import com.soa.entity.VehicleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;

@Component
@AllArgsConstructor
public class VehicleConverter {
    private CoordinatesConverter coordinatesConverter;

    public VehicleEntity convertToEntity(CreateVehicleDtoRequest dto) {
        VehicleEntity entity = new VehicleEntity();
        entity
                .setName(dto.getName())
                .setCoordinates(coordinatesConverter.convertToEntity(dto.getCoordinates()))
                .setCreationDate(Instant.parse(dto.getCreationDate()))
                .setEnginePower(BigDecimal.valueOf(dto.getEnginePower()))
                .setType(VehicleType.valueOf(dto.getType().toUpperCase()))
                .setFuelType(FuelType.valueOf(dto.getFuelType().toUpperCase()));
        return entity;
    }

    public VehicleEntity convertToUpdateEntity(UpdateVehicleDtoRequest dto) {
        VehicleEntity entity = new VehicleEntity();
        entity
                .setId(Math.toIntExact(dto.getId()))
                .setName(dto.getName())
                .setCoordinates(coordinatesConverter.convertToEntity(dto.getCoordinates()))
                .setCreationDate(Instant.parse(dto.getCreationDate()))
                .setEnginePower(BigDecimal.valueOf(dto.getEnginePower()))
                .setType(VehicleType.valueOf(dto.getType().toUpperCase()))
                .setFuelType(FuelType.valueOf(dto.getFuelType().toUpperCase()))
                .setNumberOfWheels(Math.toIntExact(dto.getNumberOfWheels()));
        return entity;
    }

    public CreateVehicleDtoResponse convertToDto(VehicleEntity entity) {
        CreateVehicleDtoResponse dto = new CreateVehicleDtoResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCoordinates(coordinatesConverter.convertToDto(entity.getCoordinates()));
        dto.setCreationDate(entity.getCreationDate().toString());
        dto.setEnginePower(entity.getEnginePower().doubleValue());
        dto.setType(String.valueOf(entity.getType()));
        dto.setFuelType(String.valueOf(entity.getFuelType()));
        dto.setNumberOfWheels(entity.getNumberOfWheels());
        return dto;
    }

    public UpdateVehicleDtoResponse convertToUpdateDto(VehicleEntity entity) {
        UpdateVehicleDtoResponse dto = new UpdateVehicleDtoResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCoordinates(coordinatesConverter.convertToDto(entity.getCoordinates()));
        dto.setCreationDate(entity.getCreationDate().toString());
        dto.setEnginePower(entity.getEnginePower().doubleValue());
        dto.setType(String.valueOf(entity.getType()));
        dto.setFuelType(String.valueOf(entity.getFuelType()));
        dto.setNumberOfWheels(entity.getNumberOfWheels());
        return dto;
    }

    public DeleteVehicleResponse convertToDeleteDto(VehicleEntity entity) {
        DeleteVehicleResponse dto = new DeleteVehicleResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCoordinates(coordinatesConverter.convertToDto(entity.getCoordinates()));
        dto.setCreationDate(entity.getCreationDate().toString());
        dto.setEnginePower(entity.getEnginePower().doubleValue());
        dto.setType(String.valueOf(entity.getType()));
        dto.setFuelType(String.valueOf(entity.getFuelType()));
        dto.setNumberOfWheels(entity.getNumberOfWheels());
        return dto;
    }

    public GetVehicleResponse convertToGetDto(VehicleEntity entity) {
        GetVehicleResponse dto = new GetVehicleResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCoordinates(coordinatesConverter.convertToDto(entity.getCoordinates()));
        dto.setCreationDate(entity.getCreationDate().toString());
        dto.setEnginePower(entity.getEnginePower().doubleValue());
        dto.setType(String.valueOf(entity.getType()));
        dto.setFuelType(String.valueOf(entity.getFuelType()));
        dto.setNumberOfWheels(entity.getNumberOfWheels());
        return dto;
    }
}

