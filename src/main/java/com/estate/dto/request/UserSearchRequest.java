package com.estate.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserSearchRequest implements Serializable {

    private String username;

}
