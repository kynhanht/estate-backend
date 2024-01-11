package com.estate.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserPasswordRequest implements Serializable {

    private String oldPassword;

    private String newPassword;

    private String confirmPassword;

}
