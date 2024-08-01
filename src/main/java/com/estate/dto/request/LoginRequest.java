package com.estate.dto.request;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginRequest implements Serializable {

    private String userName;
    private String password;

}
