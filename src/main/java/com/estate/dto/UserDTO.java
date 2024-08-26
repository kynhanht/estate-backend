package com.estate.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDTO implements Serializable {

    private Long id;

    private String username;

    private String fullName;

    private String password;

    private Integer status;

    private String roleCode;


}
