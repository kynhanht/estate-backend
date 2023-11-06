package com.estate.dto.respone;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PaginationResponse<T> implements Serializable {

    private List<T> listResult = new ArrayList<>();
    private Integer page = 1;
    private Integer totalPages;
    private Integer totalPageItems = 4;
    private Integer totalItems;


}
