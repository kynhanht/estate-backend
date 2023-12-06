package com.estate.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CustomerSearchRequest implements Serializable {

    private String fullName;

    private String phone;

    private String email;

    private Long staffId;
}
