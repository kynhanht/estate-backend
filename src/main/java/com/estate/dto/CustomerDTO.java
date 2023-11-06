package com.estate.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CustomerDTO implements Serializable {

    private Long id;

    private String fullName;

    private String phone;

    private String email;

    private String company;

    private String demand;

    private String note;


}
