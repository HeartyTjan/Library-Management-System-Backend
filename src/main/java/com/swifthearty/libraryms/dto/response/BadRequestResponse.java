package com.swifthearty.libraryms.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class BadRequestResponse {
    private Map<String,String>message;
    private boolean success;
}
