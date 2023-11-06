package com.estate.enums;

public enum BuildingTypesEnum {

    TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyên căn"),
    NOI_THAT("Nội thất");

    private final String buildingTypeValue;

    BuildingTypesEnum(String buildingTypeValue) {
        this.buildingTypeValue = buildingTypeValue;
    }

    public String getBuildingTypeValue() {
        return buildingTypeValue;
    }

    public static String findBuildingTypeValue(String name) {
        for (BuildingTypesEnum buildingType : BuildingTypesEnum.values()) {
            if (buildingType.name().equals(name)) {
                return buildingType.getBuildingTypeValue();
            }
        }
        return "";
    }
}
