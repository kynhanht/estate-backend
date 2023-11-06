package com.estate.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchRequest extends PaginationRequest {

    private String name;

}
