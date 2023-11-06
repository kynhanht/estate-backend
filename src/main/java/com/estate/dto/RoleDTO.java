package com.estate.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RoleDTO implements Serializable {

    private Long id;

    private String name;

    private String code;

}
