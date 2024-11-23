package com.myproject.interfaces.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseTemplate<T> {
    private T data;
    private StatusResponse statusResponse;
}
