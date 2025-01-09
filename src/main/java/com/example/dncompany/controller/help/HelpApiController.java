package com.example.dncompany.controller.help;

import com.example.dncompany.dto.help.HelpRequestDTO;
import com.example.dncompany.service.help.HelpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/help")
@RequiredArgsConstructor
public class HelpApiController {
    private final HelpService helpService;


}
