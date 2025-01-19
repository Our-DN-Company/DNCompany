package com.example.dncompany.service.map;

import com.example.dncompany.dto.map.api.PetServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class PetServiceApi {

    @Value("${api.pet-service.key}")
    private String petServiceApiKey;

    public PetServiceResponse getPetServiceAPI() {
        // Base URL
        String baseUrl = "https://api.odcloud.kr/api/15111389/v1/uddi:41944402-8249-4e45-9e9d-a52d0a7db1cc";

        // Build URI with query parameters
        String finalUrl = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("page", 1)
                .queryParam("perPage", 10)
                .queryParam("returnType", "JSON") // 선택 사항
                .toUriString();

        // Call API using RestClient
        PetServiceResponse petServiceResponse = getPetServiceRestClient().get()
                .uri(finalUrl) // Pass final URL with query parameters
                .headers(httpHeaders -> {
                    httpHeaders.set("Authorization", "Infuser " + petServiceApiKey);
                    httpHeaders.set("Content-Type", "application/json");
                })
                .retrieve()
                .body(PetServiceResponse.class);

        System.out.println("petServiceResponse = " + petServiceResponse);
        return petServiceResponse;
    }

    public RestClient getPetServiceRestClient() {
        return RestClient.builder()
                .baseUrl("") // Base URL is not needed here because we provide the full URL
                .build();
    }
}
