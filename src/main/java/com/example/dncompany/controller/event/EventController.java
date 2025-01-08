package com.example.dncompany.controller.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventBoard {

    @GetMapping("/eventlist")
    public String eventlist () { return "event/eventlist"; }

}
