package com.estate.dto.respone;

import com.estate.dto.TransactionDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TransactionResponse implements Serializable {

    private String code;

    private String transactionValue;

    private List<TransactionDTO> transactions = new ArrayList<>();

}
