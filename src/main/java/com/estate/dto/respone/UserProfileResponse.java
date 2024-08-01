package com.estate.dto.respone;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserProfileResponse implements Serializable {

    private Long id;

    private String userName;

    private String fullName;

    private String password;

    private String phone;

    private String email;

    private Integer status;

    private String roleCode;
}
