package com.bookstore.ai.controller;

import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstore")
public class BookstoreAssistantController {

    private final OpenAiChatClient chatClient;

    public BookstoreAssistantController(OpenAiChatClient chatClient) {
        this.chatClient = chatClient;
    }

    // http://localhost:8080/bookstore/informations
    @GetMapping("/informations")
    public String bookstoreChat(@RequestParam(value = "message", defaultValue = "Faça um resumo sobre Spring AI?") String message){
        return chatClient.call(message);
    }


    // http://localhost:8080/bookstore/chat2/informations
    @GetMapping("/chat2/informations")
    public ChatResponse bookstoreChatEx2(@RequestParam(value = "message", defaultValue = "Faça um resumo sobre Spring AI?") String message){
        return chatClient.call(new Prompt(message));
    }

}
