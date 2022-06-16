package com.URLShortner.URLShortnerService.algorithms;

import com.URLShortner.URLShortnerService.utility.Constants;

import java.util.Objects;

// Factory Design Pattern
public class AlgorithmFactory {
    public static KeyGeneration getInstance(String algorithmType){
        if(Objects.equals(algorithmType, Constants.ENCODING_BASE_62)){
            return new Base62Encoding();
        }else if(Objects.equals(algorithmType, Constants.ENCODING_RANDOM)){
            return new RandomNumber();
        }
        return null;
    }
}
