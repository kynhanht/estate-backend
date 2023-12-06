package com.estate.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TransactionRequest implements Serializable {

    private String code;

    private Long customerId;

    private String note;
}
