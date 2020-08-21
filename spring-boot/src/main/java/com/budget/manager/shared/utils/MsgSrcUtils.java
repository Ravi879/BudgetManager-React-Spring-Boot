package com.budget.manager.shared.utils;

import org.springframework.context.MessageSource;

import java.util.Locale;

public class MsgSrcUtils {

    public static String getMessage(MessageSource messageSource, String messageCode){
        return messageSource.getMessage(messageCode, null, Locale.ENGLISH);
    }

}
