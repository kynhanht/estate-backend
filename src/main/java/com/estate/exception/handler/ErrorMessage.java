package com.estate.exception.handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


@Getter
@Setter
public class ErrorMessage implements Serializable {

    private HttpStatus status;

    private List<String> messages;


    public ErrorMessage() {
    }

    public ErrorMessage(HttpStatus status, List<String> messages) {
        this.status = status;
        this.messages = messages;
    }

    public ErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.messages = Arrays.asList(message);
    }

}
