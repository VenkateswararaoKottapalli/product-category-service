package com.myproject.common.utility;

import com.myproject.interfaces.rest.response.ResponseTemplate;
import com.myproject.interfaces.rest.response.StatusResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseUtility {
    public static <T> ResponseTemplate<T> generateResponse(T responseData, Integer statusCode, String message){
        return new ResponseTemplate<>(responseData,new StatusResponse(statusCode,message));
    }
}
