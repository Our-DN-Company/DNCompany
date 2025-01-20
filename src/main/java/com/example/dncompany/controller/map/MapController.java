package com.example.dncompany.controller.map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/map")
@RequiredArgsConstructor
public class MapController {
    @Value("${map.kakao-map.service.key}")
    private String kakaoMapKey;

    @GetMapping("/hospital")
    public String hospital() {
        return "map/hospital";
    }
    @GetMapping("/pet-service")
    public String petService(Model model) {
        model.addAttribute("kakaoMapKey", kakaoMapKey);
        return "map/pet-service";
    }
}
