package com.estate.dto.respone;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
public class CustomerSearchResponse implements Serializable {

    private Long id;

    private String fullName;

    private String phone;

    private String email;

    private String demand;

    private String modifiedBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedDate;

    private String status;

}
