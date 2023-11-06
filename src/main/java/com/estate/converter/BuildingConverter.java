package com.estate.converter;

import com.estate.dto.BuildingDTO;
import com.estate.dto.respone.BuildingSearchResponse;
import com.estate.entity.BuildingEntity;
import com.estate.entity.RentAreaEntity;
import com.estate.utils.FileUploadUtils;
import com.estate.utils.SystemUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BuildingConverter {

    private final ModelMapper modelMapper;

    public BuildingDTO convertToDTO(BuildingEntity buildingEntity) {

        BuildingDTO entityDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
        entityDTO.setBuildingTypes(SystemUtils.convertBuildingTypeList(buildingEntity.getBuildingTypes()));
        entityDTO.setRentArea(SystemUtils.convertToRentArea(buildingEntity.getRentAreas()));
        entityDTO.setImageUrl(FileUploadUtils.loadPathFile(buildingEntity.getImageUrl()));
        return entityDTO;
    }

    public BuildingEntity convertToEntity(BuildingDTO buildingDTO) {

        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setBuildingTypes(SystemUtils.convertBuildingTypes(buildingDTO.getBuildingTypes()));
        // Convert String rentArea to RentAreaEntity
        if (StringUtils.isNotBlank(buildingDTO.getRentArea())) {
            List<RentAreaEntity> rentAreas = new ArrayList<>();
            String[] rentAreaStringArray = buildingDTO.getRentArea().split(",");
            for (String rentArea : rentAreaStringArray) {
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setValue(Double.valueOf(rentArea));
                rentAreaEntity.setBuilding(buildingEntity);
                rentAreas.add(rentAreaEntity);
            }
            buildingEntity.setRentAreas(rentAreas);
        }
        return buildingEntity;
    }

    public BuildingSearchResponse convertToBuildingSearchResponse(BuildingEntity buildingEntity) {

        BuildingSearchResponse response = modelMapper.map(buildingEntity, BuildingSearchResponse.class);
        response.setAddress(SystemUtils.convertToAddress(buildingEntity.getStreet(), buildingEntity.getWard(), buildingEntity.getDistrictCode()));
        return response;
    }

}
