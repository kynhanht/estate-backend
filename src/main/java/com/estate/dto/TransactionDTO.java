package com.estate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class TransactionDTO implements Serializable {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date appointmentDate;

    private String note;

}
