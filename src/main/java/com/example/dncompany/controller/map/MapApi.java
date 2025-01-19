package com.example.dncompany.controller.map;

import com.example.dncompany.dto.map.api.PetServiceResponse;
import com.example.dncompany.service.map.PetServiceApi;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MapApi {
    private final PetServiceApi petServiceApi;

    @GetMapping("/v1/pet-service")
    public PetServiceResponse getPetService() {
        log.info("MapApi: Received request for /v1/pet-service");
        return petServiceApi.getPetServiceAPI();
    }
}
