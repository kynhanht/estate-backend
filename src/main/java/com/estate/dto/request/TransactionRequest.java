package com.estate.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class TransactionRequest implements Serializable {

    private String code;

    private Long customerId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date appointmentDate;

    private String note;
}
