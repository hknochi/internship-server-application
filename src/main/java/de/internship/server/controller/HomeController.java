package de.internship.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping(path = "/")
public class HomeController {

    @GetMapping("")
    public String index() {
        return "redirect:login.html";
    }

    @GetMapping("login.html")
    public String loginHTML() {
        return "login";
    }

    @GetMapping("registration.html")
    public String registrationHTML() {
        return "registration";
    }
}
