package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class WelcomeController {
    private String msg;

    public WelcomeController(@Value("${welcome_message}")  String message) {
        msg = message;
    }

    @GetMapping("/")
    public String sayHello(){
        return msg;
    }
}
