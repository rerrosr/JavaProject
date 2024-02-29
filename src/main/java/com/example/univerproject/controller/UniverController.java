package com.example.univerproject.controller;

import com.example.univerproject.service.UniverService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UniverController {
    private final UniverService univerService;

    @GetMapping("/search")
    public String getCountry(@RequestParam("country") String country){
        return univerService.getUnivers(country);
    }
}