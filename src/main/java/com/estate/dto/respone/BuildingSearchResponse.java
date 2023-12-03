package com.estate.dto.respone;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class BuildingSearchResponse implements Serializable {

    private Long id;
    private String buildingName;
    private String address;
    private String managerName;
    private String managerPhone;
    private Integer numberOfBasement;
    private Double floorArea;
    private String rentAreaDescription;
    private Double rentPrice;
    private String serviceFee;
    private String brokerageFee;

}
