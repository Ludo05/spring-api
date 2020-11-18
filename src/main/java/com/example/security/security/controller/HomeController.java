package com.example.security.security.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
public class HomeController {

    @GetMapping(path = "/")
    public ResponseEntity<String> getStudent(@RequestHeader(value = "Connection") String authorization,
                                             HttpServletRequest request) {

        String requestResponse = request.getHeader("X-Header");
        if (requestResponse == null) {
            requestResponse = UUID.randomUUID().toString();
        }
        //Will return the new header back to the user or frontend for use
        HttpHeaders response = new HttpHeaders();
        response.set("X-Test-Header", requestResponse);


        return new ResponseEntity<>(authorization, response, HttpStatus.OK);
    }
}
