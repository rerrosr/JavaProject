package com.example.univerProject.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@AllArgsConstructor
public class UniverService {
    private final RestTemplate restTemplate;

    private static String universApiUrl = "http://universities.hipolabs.com/search";
    public String getUnivers(String country){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(universApiUrl)
                .queryParam("country", country);
        String url = builder.toUriString();
        return restTemplate.getForObject(url, String.class);
    }
}
