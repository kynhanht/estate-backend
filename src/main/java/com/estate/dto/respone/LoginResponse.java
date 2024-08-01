package com.estate.dto.respone;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginResponse implements Serializable {

    private String token;
    private String roleCode;

    public LoginResponse(String token, String roleCode) {
        this.token = token;
        this.roleCode = roleCode;
    }


}
