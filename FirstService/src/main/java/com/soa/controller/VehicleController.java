package com.soa.controller;

import com.example.vehicleservice.catalog.*;
import com.soa.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class VehicleController {
    private VehicleService vehicleService;
    private static final String NAMESPACE_URI = "http://com/example/vehicleService/catalog";

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createVehicleDtoRequest")
    @ResponsePayload
    public CreateVehicleDtoResponse createVehicleRequest(@RequestPayload CreateVehicleDtoRequest request) {
        return vehicleService.createVehicle(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteVehicleRequest")
    @ResponsePayload
    public DeleteVehicleResponse deleteVehicleRequest(@RequestPayload DeleteVehicleRequest request) {
        return vehicleService.deleteVehicle(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getVehicleRequest")
    @ResponsePayload
    public GetVehicleResponse getVehicleRequest(@RequestPayload GetVehicleRequest request) {
        return vehicleService.getVehicle(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateVehicleDtoRequest")
    @ResponsePayload
    public UpdateVehicleDtoResponse getVehicleRequest(@RequestPayload UpdateVehicleDtoRequest request) {
        return vehicleService.updateVehicle(request);
    }

//    @GetMapping("/{vehicle-id}/add-wheels/{number_of_wheels}")
//    public ResponseEntity<VehicleDto> updateVehicle(@PathVariable(name = "vehicle-id") Integer id, @PathVariable(name = "number_of_wheels") Integer numberOfWheels) {
//        return ResponseEntity.status(200).body(vehicleService.addVehicleWheels(id, numberOfWheels));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<VehicleDto>> getVehicles(FilterQueryDto dto) {
//        System.out.println(dto.toString());
//        return ResponseEntity.status(200).body(vehicleService.getVehicles(dto));
//    }
//
//    @GetMapping("/new")
//    public ResponseEntity<VehiclesListDTO> getVehicles2(FilterQueryDto dto) {
//        System.out.println(dto.toString());
//        return ResponseEntity.status(200).body(vehicleService.getVehicles2(dto));
//    }
//
//    @DeleteMapping("/type/{type}")
//    public ResponseEntity<List<VehicleDto>> deleteVehiclesByType(@PathVariable(name = "type") String type) {
//        return ResponseEntity.status(200).body(vehicleService.deleteVehiclesByType(type));
//    }
}
