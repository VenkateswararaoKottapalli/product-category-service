package com.myproject.application.config;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@AllArgsConstructor
public class MessageResourceConfig {

    private final MessageSource messageSource;

//    public MessageResourceConfig(MessageSource messageSource){
//        this.messageSource = messageSource;
//    }

    public String getMessage(String code){
        return messageSource.getMessage(code,null, Locale.US);
    }
}
