package com.estate.controller;

import com.estate.dto.BuildingDTO;
import com.estate.dto.request.AssignmentBuildingRequest;
import com.estate.dto.request.BuildingSearchRequest;
import com.estate.exception.FileStorageException;
import com.estate.service.IBuildingService;
import com.estate.service.IFileStorageService;
import com.estate.service.IMapValidationErrorService;
import com.estate.utils.SystemUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/buildings")
@RequiredArgsConstructor
public class BuildingController {


    private final IBuildingService buildingService;

    private final IMapValidationErrorService mapValidationErrorService;

    private final IFileStorageService fileStorageService;

    @GetMapping("{id}")
    public ResponseEntity<BuildingDTO> getBuildingById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(buildingService.findBuildingById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchBuildings(@RequestBody BuildingSearchRequest request,
                                             @PageableDefault(size = 4, sort = "buildingName", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(buildingService.searchBuildings(request, pageable));
    }

    @GetMapping("/districts")
    public ResponseEntity<?> getDistricts() {

        return ResponseEntity.status(HttpStatus.OK).body(SystemUtils.getDistricts());
    }

    @GetMapping("/types")
    public ResponseEntity<?> getBuildingTypes() {
        return ResponseEntity.status(HttpStatus.OK).body(SystemUtils.getBuildingTypes());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> createBuilding(@Valid @ModelAttribute BuildingDTO buildingDTO, BindingResult bindingResult) {
        ResponseEntity<?> responseEntity = mapValidationErrorService.mapValidationFields(bindingResult);
        if (responseEntity != null) {
            return responseEntity;
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(buildingService.createBuilding(buildingDTO));

    }


    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateBuilding(@PathVariable Long id, @Valid @ModelAttribute BuildingDTO buildingDTO, BindingResult bindingResult) {
        ResponseEntity<?> responseEntity = mapValidationErrorService.mapValidationFields(bindingResult);
        if (responseEntity != null) {
            return responseEntity;
        }
        return ResponseEntity.ok(buildingService.updateBuilding(id, buildingDTO));
    }


    @DeleteMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> removeBuildings(@RequestBody List<Long> ids) {

        if (ids != null && !ids.isEmpty()) {
            buildingService.deleteBuildings(ids);
        }
        return ResponseEntity.ok().build();
    }


    @PostMapping("/assignment-building")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> assignBuilding(@RequestBody AssignmentBuildingRequest request) {
        buildingService.assignBuilding(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/image/{filename:.+}")
    public ResponseEntity<?> downloadImage(@PathVariable String filename, HttpServletRequest request) {
        Resource resource = fileStorageService.loadImageFileAsResource(filename);
        String contentType;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception ex) {
            throw new FileStorageException("Could not determine file type", ex);
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"")
                .body(resource);
    }


}
