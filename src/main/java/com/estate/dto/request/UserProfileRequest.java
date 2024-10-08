package com.estate.dto.request;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserProfileRequest implements Serializable {

    private String fullname;

    private String phone;

    private String email;

}
