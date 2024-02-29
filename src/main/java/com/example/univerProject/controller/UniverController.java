package com.example.univerProject.controller;

import com.example.univerProject.service.UniverService;
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