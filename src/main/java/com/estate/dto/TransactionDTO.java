package com.estate.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TransactionDTO implements Serializable {

    private Long id;

    private String createdBy;

    private String note;

}
