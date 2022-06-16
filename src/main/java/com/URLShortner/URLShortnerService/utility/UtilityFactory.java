package com.URLShortner.URLShortnerService.utility;

import org.apache.commons.validator.routines.UrlValidator;

public class UtilityFactory {

    public static String getURLwithAlias(String alias){

        return Constants.SHORTNING_BASE_URL+alias;
    }
}
