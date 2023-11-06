package com.estate.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSearchRequest extends PaginationRequest {

    private String fullName;

    private String phone;

    private String email;

    private Long staffId;
}
