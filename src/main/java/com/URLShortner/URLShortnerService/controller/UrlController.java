package com.URLShortner.URLShortnerService.controller;

import com.URLShortner.URLShortnerService.config.Logging;
import com.URLShortner.URLShortnerService.enums.ErrorCodes;
import com.URLShortner.URLShortnerService.exception.GenericException;
import com.URLShortner.URLShortnerService.exception.InvalidURLException;
import com.URLShortner.URLShortnerService.model.Url;
import com.URLShortner.URLShortnerService.services.UrlService;
import com.URLShortner.URLShortnerService.utility.CustomUrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/v1")
public class UrlController {

    @Autowired
    private UrlService urlService;
    final Logging logger = Logging.getInstance();

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Url> createShortUrl(@RequestBody final Url url) throws Exception {
        logger.log("Long URL: " + url.getLongURL());
        if (!CustomUrlValidator.isValidUrl(url.getLongURL())) {
            throw new InvalidURLException(ErrorCodes.INVALID_LONG_URL.getErrorCode(), ErrorCodes.INVALID_LONG_URL.getMessage());
        }
        Url saveUrl = urlService.save(url);
        if (saveUrl == null) {
            throw new GenericException(ErrorCodes.GENERIC_ERROR.getErrorCode(), ErrorCodes.GENERIC_ERROR.getMessage());
        }
        return ResponseEntity.ok(saveUrl);
    }

    @GetMapping("/redirect")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<?> redirectToOriginalUrl(@NonNull @RequestParam final String shortLink) throws InvalidURLException, IOException {
        if (shortLink == null || shortLink.isEmpty()) {
            throw new InvalidURLException(ErrorCodes.INVALID_SHORT_URL.getErrorCode(), ErrorCodes.INVALID_SHORT_URL.getMessage());
        }
        logger.log("Short URL: " + shortLink);
        Url urlToRet = urlService.getOriginalUrl(shortLink);
        if (urlToRet.getLongURL() == null || urlToRet.getLongURL().isEmpty()) {
            throw new InvalidURLException(ErrorCodes.URL_NOT_AVAILABLE.getErrorCode(), ErrorCodes.URL_NOT_AVAILABLE.getMessage());
        }
        logger.log("Redirected long URL: " + urlToRet.getLongURL());
        //Redirects to the original URL
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(urlToRet.getLongURL())).build();
    }
    // Returns the original URL
    //return ResponseEntity.ok(urlToRet.getLongURL());

}
