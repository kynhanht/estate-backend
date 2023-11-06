package com.estate.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest extends PaginationRequest {

    private String code;

    private Long customerId;

    private String note;
}
