package com.swifthearty.libraryms.utility.mapper;

import com.swifthearty.libraryms.dto.response.BadRequestResponse;
import com.swifthearty.libraryms.dto.response.GeneralResponse;


import java.util.Map;

public class ExceptionMapper {

    public static GeneralResponse mapExceptionToResponse(String message){
        GeneralResponse response = new GeneralResponse();
        response.setMessage("exception");
//        response.setSuccess(true);
        return response;
    }
    public static BadRequestResponse mapBadRequestToResponse(Map<String,String> message){
        BadRequestResponse response = new BadRequestResponse();
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }
}
