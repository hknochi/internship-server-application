package de.internship.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @RequestMapping("/")
    String index() {
        return "Greetings from Spring Boot!";
    }
}
