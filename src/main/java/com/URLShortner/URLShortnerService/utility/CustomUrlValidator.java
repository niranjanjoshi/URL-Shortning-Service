package com.URLShortner.URLShortnerService.utility;

import org.apache.commons.validator.routines.UrlValidator;

public class CustomUrlValidator {

    public static boolean isValidUrl(String url){
        UrlValidator urlValidator = new UrlValidator();
       return urlValidator.isValid(url);
    }
}
