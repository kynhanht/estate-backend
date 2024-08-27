package com.estate.dto.respone;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserSearchResponse implements Serializable {

    private Long id;

    private String username;

    private String fullName;

    private Integer status;

    private String roleCode;
}
