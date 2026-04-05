package com.whatsapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping({"/"})
    public ResponseEntity<String> rootHealthCheck() {
        return ResponseEntity.ok("Elastic Beanstalk app is healthy");
    }

    @GetMapping({"/health"})
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }
}
