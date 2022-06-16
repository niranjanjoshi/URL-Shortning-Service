package com.URLShortner.URLShortnerService.controller;

import com.URLShortner.URLShortnerService.exception.GenericException;
import com.URLShortner.URLShortnerService.exception.InvalidURLException;
import com.URLShortner.URLShortnerService.model.Url;
import com.URLShortner.URLShortnerService.services.UrlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class URLControllerTest {


    @InjectMocks
    private UrlController controller;

    @Mock
    private UrlService service;

    @Test
    public void createShortUrlAndReturnItTest() throws Exception {

        Url urlInfo = getMockUrlInstance();
        when(service.save(urlInfo)).thenReturn(urlInfo);
        ResponseEntity<Url> result = controller.createShortUrl(urlInfo);
        Assert.assertEquals(result.getStatusCode(), ResponseEntity.ok().build().getStatusCode());
    }

    @Test(expected = InvalidURLException.class)
    public void invalidLongUrlThrowsInvalidURLExceptionTest() throws Exception {

        Url invalidUrlInfo = getInvalidMockUrlInstance();
        ResponseEntity<Url> result = controller.createShortUrl(invalidUrlInfo);
        assertNull(result);
    }

    @Test(expected = GenericException.class)
    public void genericExceptionTest() throws Exception {
        Url urlInfo = getMockUrlInstance();
        when(service.save(urlInfo)).thenReturn(null);
        ResponseEntity<Url> result = controller.createShortUrl(urlInfo);
        assertNull(result);
    }

    @Test
    public void redirectToOriginalUrlShouldReturnOriginalUrlIfurlExists() throws Exception {

        Url urlInfo = getMockUrlInstance();
        when(service.getOriginalUrl(urlInfo.getShortURL())).thenReturn(urlInfo);
        ResponseEntity<?> result = controller.redirectToOriginalUrl(urlInfo.getShortURL());
        assertNotNull(result);
    }

    @Test(expected = InvalidURLException.class)
    public void emptyLongUrlReturnsInvalidURLExceptionTest() throws Exception {
        Url urlInfo = getInvalidMockUrlInstance();
        Url validMockInfo = getMockUrlInstance();
        when(service.getOriginalUrl(validMockInfo.getShortURL())).thenReturn(urlInfo);
        ResponseEntity<?> result = controller.redirectToOriginalUrl(validMockInfo.getShortURL());
        assertNull(result);
    }

    @Test(expected = InvalidURLException.class)
    public void emptyShortUrlReturnsInvalidURLExceptionTest() throws Exception {
        Url urlInfo = getInvalidMockUrlInstance();
        ResponseEntity<?> result = controller.redirectToOriginalUrl(urlInfo.getShortURL());
        assertNull(result);
    }

    private Url getMockUrlInstance() {
        return new Url("234234222", "http://tiny.url/1L9zO9O", "https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Introduction.html", "");
    }

    private Url getInvalidMockUrlInstance() {
        return new Url("", "", "", "");
    }


}
