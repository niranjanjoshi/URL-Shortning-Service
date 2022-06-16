package com.URLShortner.URLShortnerService.algorithms;

import com.URLShortner.URLShortnerService.utility.Constants;

import java.util.HashMap;

public class Base62Encoding implements KeyGeneration {

    private HashMap<String, Long> ltos;
    private HashMap<Long, String> stol;
    private static long COUNTER = Constants.BASE_62_COUNTER;
    private final String elements;

    public Base62Encoding() {
        ltos = new HashMap<String, Long>();
        stol = new HashMap<Long, String>();
        elements = Constants.ALLOWED_BASE_62_ELEMENTS;
    }

    @Override
    public String longToShort(String url) {
        String shorturl = base10ToBase62(COUNTER);
        ltos.put(url, COUNTER);
        stol.put(COUNTER, url);
        COUNTER++;
        return Constants.SHORTNING_BASE_URL + shorturl;
    }

    @Override
    public String shortToLong(String url) {
        url = url.substring(Constants.SHORTNING_BASE_URL.length());
        int n = base62ToBase10(url);
        return stol.get(n);
    }

    public int base62ToBase10(String s) {
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            n = n * 62 + convert(s.charAt(i));
        }
        return n;

    }

    public int convert(char c) {
        if (c >= '0' && c <= '9')
            return c - '0';
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        }
        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 36;
        }
        return -1;
    }

    public String base10ToBase62(long n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.insert(0, elements.charAt((int) (n % 62)));
            n /= 62;
        }
        while (sb.length() != 7) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }

}
