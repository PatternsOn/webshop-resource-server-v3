package com.patternson.webshopresourceserverv3.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String hello() {

        return "hello";
    }

    @GetMapping("/secret")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String showSecret(Principal principal) {
        return "Secret " + principal.getName();
    }

}
