package com.estate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class BuildingDTO implements Serializable {

    private Long id;

    @NotEmpty(message = "BuildingName is required")
    private String buildingName;

    private String districtCode;

    private String ward;

    private String street;

    private String structure;

    private Integer numberOfBasement;

    private Double floorArea;

    private String direction;

    private String level;

    private String rentArea;

    private String rentAreaDescription;

    private Double rentPrice;

    private String rentPriceDescription;

    private String serviceFee;

    private String carFee;

    private String motorbikeFee;

    private String overtimeFee;

    private String waterFee;

    private String electricityFee;

    private String deposit;

    private String payment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rentTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date decorationTime;

    private String managerName;

    private String managerPhone;

    private String brokerageFee;

    private List<String> buildingTypes = new ArrayList<>();

    private String note;

    private String linkOfBuilding;

    private String map;

    private MultipartFile imageFile;

    private String imageName;


}
