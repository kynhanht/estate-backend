package com.estate.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "buildings")
@Getter
@Setter
public class BuildingEntity extends BaseEntity {


    @Column(name = "building_name")
    private String buildingName;

    @Column(name = "number_of_basement")
    private Integer numberOfBasement;

    @Column(name = "district_code")
    private String districtCode;

    @Column(name = "building_types")
    private String buildingTypes;

    @Column(name = "street")
    private String street;

    @Column(name = "ward")
    private String ward;

    @Column(name = "structure")
    private String structure;

    @Column(name = "floor_area")
    private Double floorArea;

    @Column(name = "direction")
    private String direction;

    @Column(name = "level")
    private String level;

    @Column(name = "rent_price")
    private Double rentPrice;

    @Column(name = "rent_price_description", columnDefinition = "TEXT")
    private String rentPriceDescription;

    @Column(name = "service_fee")
    private String serviceFee;

    @Column(name = "car_fee")
    private String carFee;

    @Column(name = "motorbike_fee")
    private String motorbikeFee;

    @Column(name = "overtime_fee")
    private String overtimeFee;

    @Column(name = "water_fee")
    private String waterFee;

    @Column(name = "electricity_fee")
    private String electricityFee;

    @Column(name = "deposit")
    private String deposit;

    @Column(name = "payment")
    private String payment;

    @Column(name = "rent_time")
    private String rentTime;

    @Column(name = "decoration_time")
    private String decorationTime;

    @Column(name = "brokerage_fee")
    private String brokerageFee;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "link_of_building")
    private String linkOfBuilding;

    @Column(name = "map")
    private String map;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "manager_phone")
    private String managerPhone;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "rent_area_description", columnDefinition = "TEXT")
    private String rentAreaDescription;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<RentAreaEntity> rentAreas = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignment_building",
            joinColumns = @JoinColumn(name = "building_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false))
    private List<UserEntity> users = new ArrayList<>();


}
