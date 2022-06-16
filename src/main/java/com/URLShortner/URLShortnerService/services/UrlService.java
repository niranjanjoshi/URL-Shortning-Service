package com.URLShortner.URLShortnerService.services;

import com.URLShortner.URLShortnerService.algorithms.AlgorithmFactory;
import com.URLShortner.URLShortnerService.algorithms.KeyGeneration;
import com.URLShortner.URLShortnerService.config.Logging;
import com.URLShortner.URLShortnerService.enums.ErrorCodes;
import com.URLShortner.URLShortnerService.exception.AliasFieldException;
import com.URLShortner.URLShortnerService.model.Url;
import com.URLShortner.URLShortnerService.repository.UrlRepository;
import com.URLShortner.URLShortnerService.utility.Constants;
import com.URLShortner.URLShortnerService.utility.UtilityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Optional;

@Service
public class UrlService {

    @Autowired
    UrlRepository urlRepository;

    final Logging logger = Logging.getInstance();

    public Url save(@NonNull final Url url) throws IOException, AliasFieldException {
        String shortURl = null;
        // Check if long URL is already present in the DB
        Optional<Url> longURL = urlRepository.findByLongURL(url.getLongURL());
        if (longURL.isPresent()) {
            logger.log("The URL is already shortened. Returning the existing short URL.");
            return longURL.get();
        }

        // Verify if short URL with given alias already present
        if (url.getAlias() != null && !url.getAlias().isEmpty()) {
            Optional<Url> aliasUrl = urlRepository.findByShortURL(UtilityFactory.getURLwithAlias(url.getAlias()));
            if (aliasUrl.isPresent()) {
                throw  new AliasFieldException(ErrorCodes.INVALID_SHORT_URL.getErrorCode(),ErrorCodes.ALIAS_NOT_AVAILABLE.getMessage());
            } else {
                shortURl = UtilityFactory.getURLwithAlias(url.getAlias());
            }
        } else {
            KeyGeneration encode = AlgorithmFactory.getInstance(Constants.ENCODING_BASE_62);
            if (null != encode) {
                shortURl = encode.longToShort(url.getLongURL());
            }
        }
        url.setShortURL(shortURl);
        return urlRepository.save(url);
    }

    public Url getOriginalUrl(@NonNull final String url) throws IOException {
        Optional<Url> urlToRet = urlRepository.findByShortURL(url);
        if (urlToRet.isPresent()) {
            logger.log("The original URL is available. Returning the original URL.");
            return urlToRet.get();
        }
        return null;
    }
}
