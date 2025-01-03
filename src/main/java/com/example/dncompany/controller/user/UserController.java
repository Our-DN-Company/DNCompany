package com.example.dncompany.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/signup")
    public String signup() {
        return "user/signup";
    }
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
}
