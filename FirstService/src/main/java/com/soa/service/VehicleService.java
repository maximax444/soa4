package com.soa.service;

import com.example.vehicleservice.catalog.*;
import com.soa.converter.VehicleConverter;
import com.soa.entity.VehicleEntity;
import com.soa.exception.EntityNotFoundException;
import com.soa.service.db.VehicleDbService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VehicleService {
    private VehicleConverter vehicleConverter;
    private VehicleDbService vehicleDbService;
    private PageService pageService;

    public CreateVehicleDtoResponse createVehicle(CreateVehicleDtoRequest vehicleDto) {
        VehicleEntity vehicle = vehicleConverter.convertToEntity(vehicleDto);
        vehicle = vehicleDbService.saveVehicle(vehicle);
        return vehicleConverter.convertToDto(vehicle);
    }

    public UpdateVehicleDtoResponse updateVehicle(UpdateVehicleDtoRequest vehicleDto) {
        VehicleEntity vehicle = vehicleConverter.convertToUpdateEntity(vehicleDto);
        vehicle = vehicleDbService.saveVehicle(vehicle);
        return vehicleConverter.convertToUpdateDto(vehicle);
    }

    //
//    public VehicleDto addVehicleWheels(Integer vehicleId, Integer numberOfWheels) {
//        if (numberOfWheels <= 0) {
//            throw new IllegalArgumentException("Количество добавляемых колес должно быть больше 0");
//        }
//        VehicleEntity vehicle = vehicleDbService.findById(vehicleId).orElseThrow(EntityNotFoundException::new);
//        vehicle.setNumberOfWheels(vehicle.getNumberOfWheels() + numberOfWheels);
//        vehicle = vehicleDbService.saveVehicle(vehicle);
//        return vehicleConverter.convertToDto(vehicle);
//    }

    public GetVehicleResponse getVehicle(GetVehicleRequest vehicleRequest) {
        VehicleEntity vehicle = vehicleDbService.findById(Math.toIntExact(vehicleRequest.getId())).orElseThrow(EntityNotFoundException::new);
        return vehicleConverter.convertToGetDto(vehicle);
    }

    public DeleteVehicleResponse deleteVehicle(DeleteVehicleRequest deleteVehicleRequest) {
        VehicleEntity vehicle = vehicleDbService.findById(Math.toIntExact(deleteVehicleRequest.getId())).orElseThrow(EntityNotFoundException::new);
        vehicleDbService.delete(vehicle);
        return vehicleConverter.convertToDeleteDto(vehicle);
    }
//
//    public List<VehicleDto> deleteVehiclesByType(String type) {
//        VehicleType vehicleType = VehicleType.valueOf(type.toUpperCase(Locale.ROOT));
//        List<VehicleEntity> vehicles = vehicleDbService.findAllByType(vehicleType);
//        if (vehicles.isEmpty()) {
//            throw new EntityNotFoundException();
//        }
//        vehicleDbService.removeAllByType(vehicleType);
//        return vehicles.stream().map(entity -> vehicleConverter.convertToDto(entity)).toList();
//    }
//
//
//    public List<VehicleDto> getVehicles(FilterQueryDto dto) {
//        FilterService.isValidRequestParams(dto);
//        PageRequest request = pageService.getPageRequest(dto.getLimit(), dto.getOffset(), dto.getSort());
//        return vehicleDbService.getVehicles(dto, request).stream().map(v -> vehicleConverter.convertToDto(v)).toList();
//    }
//
//    public VehiclesListDTO getVehicles2(FilterQueryDto dto) {
//        FilterService.isValidRequestParams(dto);
//        PageRequest request = pageService.getPageRequest(dto.getLimit(), dto.getOffset(), dto.getSort());
//        return new VehiclesListDTO(vehicleDbService.getVehicles(dto, request).stream().map(v -> vehicleConverter.convertToDto(v)).toList());
//    }
}
