package com.estate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;

    private String userName;

    private String fullName;

    private String password;

    private Integer status;

    private String roleCode;


}
