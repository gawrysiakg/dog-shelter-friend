package com.example.dogshelter.api.dog_ninja;

import com.example.dogshelter.domain.api.dog_ninja_info.DogInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DogNinjaClient {

    private final DogNinjaConfig dogNinjaConfig;
    private final RestTemplate restTemplate;

    public DogInfo getDogInfo(String name) {
        String url = dogNinjaConfig.getDogNinjaNameApiEndpoint()+name;
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("X-RapidAPI-Key", dogNinjaConfig.getHeaderApiKey());
        headers.add("X-RapidAPI-Host", dogNinjaConfig.getHeaderApiHost());
        HttpEntity<?> entity = new HttpEntity<Object>(body, headers);
        ResponseEntity<DogInfo[]> res = restTemplate.exchange(
                url, HttpMethod.GET, entity, DogInfo[].class);
        return  Arrays.asList(Objects.requireNonNull(res.getBody())).get(0);
    }
}
