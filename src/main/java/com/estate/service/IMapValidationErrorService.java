package com.estate.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface IMapValidationErrorService {

    ResponseEntity<?> mapValidationFields(BindingResult bindingResult);

}
