package com.example.dncompany.service.openAichat;

import com.example.dncompany.dto.openAiChat.GeminiRequest;
import com.example.dncompany.dto.openAiChat.GeminiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
@Slf4j
public class GeminiService {
    private static final String BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models";
    private final RestTemplate restTemplate;
    private final String apiKey;
    @Autowired
    public GeminiService(@Value("${GOOGLE_API_KEY}") String apiKey) {
        this.restTemplate = new RestTemplate();
        this.apiKey = apiKey;
    }

    public GeminiResponse generateText(String prompt) {
        String url = BASE_URL + "/gemini-pro:generateContent?key=" + apiKey;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        GeminiRequest request = createTextRequest(prompt);

        HttpEntity<GeminiRequest> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<GeminiResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    GeminiResponse.class
            );

            return response.getBody();
        } catch (Exception e) {
            log.error("Gemini API 호출 중 오류 발생: ", e);
            throw new RuntimeException("Gemini API 호출 실패", e);
        }
    }



    private GeminiRequest createTextRequest(String prompt) {
        return GeminiRequest.builder()
                .contents(List.of(
                        GeminiRequest.Content.builder()
                                .parts(List.of(
                                        GeminiRequest.Part.builder()
                                                .text(prompt)
                                                .build()
                                ))
                                .build()
                ))
                .build();
    }

}