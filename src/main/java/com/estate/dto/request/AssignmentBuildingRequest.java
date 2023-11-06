package com.estate.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class AssignmentBuildingRequest implements Serializable {

    private List<Long> staffIds;

    private Long buildingId;

}
