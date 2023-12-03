package com.estate.service.impl;

import com.estate.constant.ErrorMessageConstants;
import com.estate.converter.BuildingConverter;
import com.estate.dto.BuildingDTO;
import com.estate.dto.request.AssignmentBuildingRequest;
import com.estate.dto.request.BuildingSearchRequest;
import com.estate.dto.respone.BuildingSearchResponse;
import com.estate.dto.respone.PaginationResponse;
import com.estate.entity.BuildingEntity;
import com.estate.entity.BuildingEntity_;
import com.estate.enums.SearchOperationEnum;
import com.estate.exception.NotFoundException;
import com.estate.repository.BuildingRepository;
import com.estate.repository.UserRepository;
import com.estate.service.IBuildingService;
import com.estate.specifications.BuildingSpecification;
import com.estate.specifications.SearchCriteria;
import com.estate.utils.FileUploadUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuildingService implements IBuildingService {

    private final BuildingRepository buildingRepository;

    private final UserRepository userRepository;

    private final BuildingConverter buildingConverter;

    @Override
    public PaginationResponse<BuildingSearchResponse> searchBuildings(BuildingSearchRequest request) {


        BuildingSpecification buildingSpecification = new BuildingSpecification();

        Specification<BuildingEntity> specification = Specification
                .where(buildingSpecification.byCommon(new SearchCriteria(BuildingEntity_.BUILDING_NAME, request.getBuildingName(), SearchOperationEnum.CONTAINING)))
                .and(buildingSpecification.byCommon(new SearchCriteria(BuildingEntity_.FLOOR_AREA, request.getFloorArea(), SearchOperationEnum.EQUAL)))
                .and(buildingSpecification.byCommon(new SearchCriteria(BuildingEntity_.DISTRICT_CODE, request.getDistrictCode(), SearchOperationEnum.CONTAINING)))
                .and(buildingSpecification.byCommon(new SearchCriteria(BuildingEntity_.WARD, request.getWard(), SearchOperationEnum.CONTAINING)))
                .and(buildingSpecification.byCommon(new SearchCriteria(BuildingEntity_.STREET, request.getStreet(), SearchOperationEnum.CONTAINING)))
                .and(buildingSpecification.byCommon(new SearchCriteria(BuildingEntity_.NUMBER_OF_BASEMENT, request.getNumberOfBasement(), SearchOperationEnum.EQUAL)))
                .and(buildingSpecification.byCommon(new SearchCriteria(BuildingEntity_.DIRECTION, request.getDirection(), SearchOperationEnum.CONTAINING)))
                .and(buildingSpecification.byCommon(new SearchCriteria(BuildingEntity_.LEVEL, request.getLevel(), SearchOperationEnum.CONTAINING)))
                .and(buildingSpecification.byCommon(new SearchCriteria(BuildingEntity_.RENT_PRICE, request.getRentPriceFrom(), SearchOperationEnum.GREATER_THAN_EQUAL)))
                .and(buildingSpecification.byCommon(new SearchCriteria(BuildingEntity_.RENT_PRICE, request.getRentPriceTo(), SearchOperationEnum.LESS_THAN_EQUAL)))
                .and(buildingSpecification.byCommon(new SearchCriteria(BuildingEntity_.MANAGER_NAME, request.getManagerName(), SearchOperationEnum.CONTAINING)))
                .and(buildingSpecification.byCommon(new SearchCriteria(BuildingEntity_.MANAGER_PHONE, request.getManagerPhone(), SearchOperationEnum.CONTAINING)))
                .and(buildingSpecification.byBuildingTypes(request.getBuildingTypes()))
                .and(buildingSpecification.byStaffId(request.getStaffId()))
                .and(buildingSpecification.byRentArea(request.getRentAreaFrom(), request.getRentAreaTo()))
                .and(buildingSpecification.groupBy(BuildingEntity_.ID))
                .and(buildingSpecification.orderBy(request.getSortColumnName(), request.getSortDirection()));


        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getPageSize());
        Page<BuildingEntity> page = buildingRepository.findAll(specification, pageable);
        List<BuildingSearchResponse> responses = page
                .getContent()
                .stream()
                .map(buildingConverter::convertToBuildingSearchResponse)
                .collect(Collectors.toList());
        PaginationResponse<BuildingSearchResponse> paginationResponse = new PaginationResponse<>();
        paginationResponse.setPage(request.getPage());
        paginationResponse.setTotalPages(page.getTotalPages());
        paginationResponse.setPageSize(request.getPageSize());
        paginationResponse.setTotalItems((int) page.getTotalElements());
        paginationResponse.setListResult(responses);
        return paginationResponse;
    }

    @Override
    public BuildingDTO findBuildingById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.BUILDING_NOT_FOUND));
        return buildingConverter.convertToDTO(buildingEntity);
    }

    @Override
    @Transactional
    public BuildingDTO createBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        String imageUrl = FileUploadUtils.getFileName(buildingDTO.getImage());
        buildingEntity.setImageUrl(imageUrl);
        BuildingDTO newBuilding = buildingConverter.convertToDTO(buildingRepository.save(buildingEntity));
        FileUploadUtils.uploadFile(buildingDTO.getImage());
        return newBuilding;
    }

    @Override
    public BuildingDTO updateBuilding(Long id, BuildingDTO buildingDTO) {
        BuildingEntity oldBuildingEntity = buildingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.BUILDING_NOT_FOUND));
        BuildingEntity newBuildingEntity = buildingConverter.convertToEntity(buildingDTO);
        newBuildingEntity.setUsers(oldBuildingEntity.getUsers());
        newBuildingEntity.setCreatedBy(oldBuildingEntity.getCreatedBy());
        newBuildingEntity.setCreatedDate(oldBuildingEntity.getCreatedDate());
        String fileName = FileUploadUtils.getFileName(buildingDTO.getImage());
        if (fileName == null) {
            newBuildingEntity.setImageUrl(oldBuildingEntity.getImageUrl());
        } else {
            newBuildingEntity.setImageUrl(fileName);
        }
        BuildingEntity _buildingEntity = buildingRepository.save(newBuildingEntity);
        FileUploadUtils.uploadFile(buildingDTO.getImage());
        return buildingConverter.convertToDTO(_buildingEntity);
    }

    @Override
    @Transactional
    public void deleteBuildings(List<Long> ids) {
        ids.forEach(id -> {
            BuildingEntity buildingEntity = buildingRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.BUILDING_NOT_FOUND));
            buildingRepository.delete(buildingEntity);
        });
    }

    @Override
    @Transactional
    public void assignBuilding(AssignmentBuildingRequest request) {
        BuildingEntity buildingEntity = buildingRepository.findById(request.getBuildingId())
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.BUILDING_NOT_FOUND));
        List<Long> ids = request.getStaffIds();
        Long count = userRepository.countByIdIn(ids);
        if (count != ids.size()) {
            throw new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND);
        } else {
            buildingEntity.getUsers().clear();
            buildingEntity.getUsers().addAll(userRepository.findByIdIn(ids));
        }
    }

}
