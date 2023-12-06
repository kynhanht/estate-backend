package com.estate.service;

import com.estate.dto.BuildingDTO;
import com.estate.dto.request.AssignmentBuildingRequest;
import com.estate.dto.request.BuildingSearchRequest;
import com.estate.dto.respone.BuildingSearchResponse;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBuildingService {
    PageImpl<BuildingSearchResponse> searchBuildings(BuildingSearchRequest request, Pageable pageable);

    BuildingDTO findBuildingById(Long id);

    BuildingDTO createBuilding(BuildingDTO buildingDTO);

    BuildingDTO updateBuilding(Long id, BuildingDTO buildingDTO);

    void deleteBuildings(List<Long> ids);

    void assignBuilding(AssignmentBuildingRequest request);

}
