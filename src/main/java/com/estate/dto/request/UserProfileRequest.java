package com.estate.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileRequest {

    private String fullName;

    private String phone;

    private String email;

}
