package com.URLShortner.URLShortnerService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/")
public class HealthController {

    @GetMapping(value = "/health", produces = "application/json")
    public String root() {
        return "{\"status\":\"up\"}";
    }
}
