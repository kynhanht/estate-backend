package com.estate.dto.respone;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StaffResponse implements Serializable {

    private String fullName;

    private Long staffId;

    private boolean isChecked;

}
