package com.estate.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PaginationRequest implements Serializable {

    private Integer page = 1;

    private Integer pageSize = 4;

    private String sortColumnName;

    private String sortDirection;

}
