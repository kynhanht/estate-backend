package com.estate.dto.respone;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class CustomerSearchResponse implements Serializable {

    private Long id;

    private String fullName;

    private String phone;

    private String email;

    private String demand;

    private String modifiedBy;

    private String modifiedDate;

    private String status;

}
