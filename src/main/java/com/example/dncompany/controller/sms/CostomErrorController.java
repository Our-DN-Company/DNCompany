package com.example.dncompany.controller.sms;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CostomErrorController implements ErrorController {
    @GetMapping("/error")
    public String error(HttpServletRequest req) {
        req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        return "fragment/error";
    }

}
