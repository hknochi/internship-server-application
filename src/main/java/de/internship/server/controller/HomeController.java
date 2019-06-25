package de.internship.server.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @RequestMapping("/")
    String index() {
        return "redirect:user/login.html";
    }

    @GetMapping("/login.html")
    public String loginHTML(Model model) {
        return "login";
    }

    @GetMapping("/registration.html")
    public String registrationHTML(Model model) {
        return "registration";
    }
}
