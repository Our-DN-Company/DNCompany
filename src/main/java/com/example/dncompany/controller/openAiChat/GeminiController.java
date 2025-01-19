package com.example.dncompany.controller.openAiChat;

import com.example.dncompany.dto.openAiChat.GeminiResponse;
import com.example.dncompany.service.openAichat.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {
    private final GeminiService geminiService;

    @Autowired
    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/generate-text")
    public ResponseEntity<GeminiResponse> generateText(@RequestBody String prompt) {
        GeminiResponse response = geminiService.generateText(prompt);
        return ResponseEntity.ok(response);
    }


}