package com.URLShortner.URLShortnerService.config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Date;

// Singleton design pattern
public class Logging {
    private static  Logging logger;
    private Logging(){
    }

    public static synchronized Logging getInstance(){
        if(logger == null){
            logger = new Logging();
        }
        return logger;
    }

    public void log(String logMessage) throws IOException {
        Instant currentTimeStamp = Instant.now();
        System.out.println("URL SHOTNING SERVICE LOG:  "+currentTimeStamp+" Message: "+logMessage);
    }
}
