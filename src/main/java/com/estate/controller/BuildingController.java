package com.estate.controller;

import com.estate.dto.BuildingDTO;
import com.estate.dto.request.AssignmentBuildingRequest;
import com.estate.dto.request.BuildingSearchRequest;
import com.estate.dto.respone.BuildingSearchResponse;
import com.estate.dto.respone.PaginationResponse;
import com.estate.service.IBuildingService;
import com.estate.service.IMapValidationErrorService;
import com.estate.utils.SystemUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/buildings")
@RequiredArgsConstructor
public class BuildingController {


    private final IBuildingService buildingService;

    private final IMapValidationErrorService mapValidationErrorService;

    @GetMapping("{id}")
    public ResponseEntity<BuildingDTO> getBuildingById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(buildingService.findBuildingById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<PaginationResponse<BuildingSearchResponse>> searchBuildings(@RequestBody  BuildingSearchRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(buildingService.searchBuildings(request));
    }

    @GetMapping("/districts")
    public ResponseEntity<Map<String, String>> getDistricts() {

        return ResponseEntity.status(HttpStatus.OK).body(SystemUtils.getDistricts());
    }

    @GetMapping("/types")
    public ResponseEntity<Map<String, String>> getBuildingTypes() {
        return ResponseEntity.status(HttpStatus.OK).body(SystemUtils.getBuildingTypes());
    }

    @PostMapping
    public ResponseEntity<?> createBuilding(@Valid @RequestBody BuildingDTO buildingDTO, BindingResult bindingResult) {
        ResponseEntity<?> responseEntity = mapValidationErrorService.mapValidationFields(bindingResult);
        if (responseEntity != null) {
            return responseEntity;
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(buildingService.createBuilding(buildingDTO));

    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateBuilding(@PathVariable Long id, @Valid @RequestBody BuildingDTO buildingDTO, BindingResult bindingResult) {
        ResponseEntity<?> responseEntity = mapValidationErrorService.mapValidationFields(bindingResult);
        if (responseEntity != null) {
            return responseEntity;
        }
        return ResponseEntity.ok(buildingService.updateBuilding(id, buildingDTO));
    }


    @DeleteMapping
    public ResponseEntity<Void> removeBuildings(@RequestBody List<Long> ids) {

        if (ids != null && !ids.isEmpty()) {
            buildingService.deleteBuildings(ids);
        }
        return ResponseEntity.ok().build();
    }


    @PostMapping("/assignment")
    public ResponseEntity<Void> assignBuilding(@RequestBody AssignmentBuildingRequest request) {
        buildingService.assignBuilding(request);
        return ResponseEntity.ok().build();
    }



}
