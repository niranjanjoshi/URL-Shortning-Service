package com.URLShortner.URLShortnerService.services;


import com.URLShortner.URLShortnerService.model.Url;
import com.URLShortner.URLShortnerService.repository.UrlRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {

    @Mock
    private UrlRepository repository;

    @InjectMocks
    private UrlService service;

    @Test
    public void saveShouldReturnShortUrlWhenRequested() throws Exception {
        Url productInfo = getMockUrlInstance();
        when(repository.save(productInfo)).thenReturn(productInfo);
        Url result = service.save(productInfo);
        assertEquals(productInfo,result);
    }

    @Test
    public void saveShouldReturnShortUrlIfAlreadyExists() throws Exception {
        Url productInfo = getMockUrlInstance();
        when(repository.findByLongURL(productInfo.getLongURL())).thenReturn(Optional.of(productInfo));
        Url result = service.save(productInfo);
        assertEquals(productInfo,result);
    }

    @Test
    public void saveShouldReturnShortUrlIfAliasIsProvided() throws Exception {
        Url productInfo = getMockUrlInstanceWithValidAlias();
        when(repository.findByLongURL(productInfo.getLongURL())).thenReturn(Optional.empty());
        when(repository.save(productInfo)).thenReturn(productInfo);
        Url result = service.save(productInfo);
        assertNotNull(result);
    }



    private Url getMockUrlInstance() {
        return new Url("234234222", "http://tiny.url/1L9zO9O", "https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Introduction.html", "");
    }


    private Url getMockUrlInstanceWithValidAlias() {
        return new Url("234234222", "http://tiny.url/1L9zO9O", "https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Introduction.html", "alias");
    }


}
