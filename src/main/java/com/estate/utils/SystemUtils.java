package com.estate.utils;

import com.estate.entity.RentAreaEntity;
import com.estate.enums.BuildingTypesEnum;
import com.estate.enums.DistrictsEnum;
import com.estate.enums.TransactionEnum;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class SystemUtils {

    public static Map<String, String> getDistricts() {
        Map<String, String> districts = new HashMap<>();
        for (DistrictsEnum item : DistrictsEnum.values()) {
            districts.put(item.toString(), item.getDistrictValue());
        }
        return districts;
    }

    public static Map<String, String> getBuildingTypes() {
        Map<String, String> buildingTypes = new HashMap<>();
        for (BuildingTypesEnum item : BuildingTypesEnum.values()) {
            buildingTypes.put(item.toString(), item.getBuildingTypeValue());
        }
        return buildingTypes;
    }

    public static Map<String, String> getTransactions() {
        Map<String, String> transactions = new HashMap<>();
        for (TransactionEnum item : TransactionEnum.values()) {
            transactions.put(item.toString(), item.getTransactionValue());
        }
        return transactions;
    }

    public static String convertToAddress(String street, String ward, String districtCode) {
        if (StringUtils.isNotBlank(street) || StringUtils.isNotBlank(ward) || StringUtils.isNotBlank(districtCode)) {
            List<String> address = new ArrayList<>();
            if (StringUtils.isNotBlank(street)) {
                address.add(street);
            }
            if (StringUtils.isNotBlank(ward)) {
                address.add(ward);
            }
            if (StringUtils.isNotBlank(districtCode)) {
                address.add(DistrictsEnum.findDistrictValue(districtCode));
            }
            return String.join(", ", address);
        }
        return "";
    }

    public static String convertBuildingTypes(List<String> buildingTypeList) {
        if (buildingTypeList != null && !buildingTypeList.isEmpty()) {
            return String.join(",", buildingTypeList);
        }
        return null;

    }

    public static List<String> convertBuildingTypeList(String buildingTypes) {
        if (StringUtils.isNotBlank(buildingTypes)) {
            return Arrays.asList(buildingTypes.split(","));
        }
        return null;
    }

    public static String convertToRentArea(List<RentAreaEntity> rentAreaList) {
        return rentAreaList
                .stream()
                .map(rentArea -> String.valueOf(rentArea.getValue()))
                .collect(Collectors.joining(","));
    }
}
