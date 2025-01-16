package com.example.dncompany.controller.openAiChat;


import com.example.dncompany.dto.openAiChat.req.ChatHistory;
import com.example.dncompany.dto.openAiChat.req.ClientChatRequest;
import com.example.dncompany.service.openAichat.AiChatService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class openAiChatRestController {
    private final AiChatService aiChatService;

    @PostMapping("/chat2")
    public String chat2(@RequestBody ClientChatRequest chatRequest,
                        HttpSession session) {

        String assistantContent = aiChatService.sendMessage2(session, chatRequest.getMessage());

        ChatHistory chatHistory = (ChatHistory) session.getAttribute("chatHistory");
        System.out.println("chatHistory = " + chatHistory);

        return assistantContent;
    }



}
