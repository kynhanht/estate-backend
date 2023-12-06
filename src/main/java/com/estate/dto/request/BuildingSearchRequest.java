package com.estate.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BuildingSearchRequest implements Serializable {


    private String buildingName;
    private Double floorArea;
    private String districtCode;
    private String ward;
    private String street;
    private Integer numberOfBasement;
    private String direction;
    private String level;
    private Double rentPriceFrom;
    private Double rentPriceTo;
    private Double rentAreaFrom;
    private Double rentAreaTo;
    private String managerName;
    private String managerPhone;
    private Long staffId;
    private List<String> buildingTypes = new ArrayList<>();

}
