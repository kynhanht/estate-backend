package com.estate.converter;

import com.estate.dto.BuildingDTO;
import com.estate.dto.respone.BuildingSearchResponse;
import com.estate.entity.BuildingEntity;
import com.estate.entity.RentAreaEntity;
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

    public BuildingDTO convertToDTO(BuildingEntity entity) {

        BuildingDTO dto = modelMapper.map(entity, BuildingDTO.class);
        dto.setBuildingTypes(SystemUtils.convertBuildingTypeList(entity.getBuildingTypes()));
        dto.setRentArea(SystemUtils.convertToRentArea(entity.getRentAreas()));
        return dto;
    }

    public BuildingEntity convertToEntity(BuildingDTO dto) {

        BuildingEntity entity = modelMapper.map(dto, BuildingEntity.class);
        entity.setBuildingTypes(SystemUtils.convertBuildingTypes(dto.getBuildingTypes()));
        // Convert String rentArea to RentAreaEntity
        if (StringUtils.isNotBlank(dto.getRentArea())) {
            List<RentAreaEntity> rentAreas = new ArrayList<>();
            String[] rentAreaStringArray = dto.getRentArea().split(",");
            for (String rentArea : rentAreaStringArray) {
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setValue(Double.valueOf(rentArea));
                rentAreaEntity.setBuilding(entity);
                rentAreas.add(rentAreaEntity);
            }
            entity.setRentAreas(rentAreas);
        }
        return entity;
    }

    public BuildingSearchResponse convertToBuildingSearchResponse(BuildingEntity entity) {

        BuildingSearchResponse response = modelMapper.map(entity, BuildingSearchResponse.class);
        response.setAddress(SystemUtils.convertToAddress(entity.getStreet(), entity.getWard(), entity.getDistrictCode()));
        return response;
    }

}
