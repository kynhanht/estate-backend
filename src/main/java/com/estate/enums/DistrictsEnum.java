package com.estate.enums;

public enum DistrictsEnum {

    Q1("Quận 1"),
    Q2("Quận 2"),
    Q3("Quận 3"),
    Q4("Quận 4");
    private final String districtValue;

    DistrictsEnum(String districtValue) {
        this.districtValue = districtValue;
    }

    public String getDistrictValue() {
        return districtValue;
    }

    public static String findDistrictValue(String name) {
        for (DistrictsEnum district : DistrictsEnum.values()) {
            if (district.name().equals(name)) {
                return district.getDistrictValue();
            }
        }
        return "";
    }
}
