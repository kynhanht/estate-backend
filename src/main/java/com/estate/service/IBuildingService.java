package com.estate.service;

import com.estate.dto.BuildingDTO;
import com.estate.dto.request.AssignmentBuildingRequest;
import com.estate.dto.request.BuildingSearchRequest;
import com.estate.dto.respone.BuildingSearchResponse;
import com.estate.dto.respone.PaginationResponse;

import java.util.List;

public interface IBuildingService {
    PaginationResponse<BuildingSearchResponse> searchBuildings(BuildingSearchRequest request);

    BuildingDTO findBuildingById(Long id);

    BuildingDTO createBuilding(BuildingDTO buildingDTO);

    BuildingDTO updateBuilding(Long id, BuildingDTO buildingDTO);

    void deleteBuildings(List<Long> ids);

    void assignBuilding(AssignmentBuildingRequest request);

}
