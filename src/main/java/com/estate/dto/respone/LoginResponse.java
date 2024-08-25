package com.estate.dto.respone;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginResponse implements Serializable {

    private String token;
    private String roleCode;
    private String fullName;
    private String username;

    public LoginResponse(String token, String roleCode, String fullName, String username) {
        this.token = token;
        this.roleCode = roleCode;
        this.fullName = fullName;
        this.username = username;
    }
}
