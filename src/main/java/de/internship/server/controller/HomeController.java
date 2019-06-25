package de.internship.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String loginHTML(@ModelAttribute("username") String usernameAttr, Model model) {
        model.addAttribute("username", usernameAttr);
        return "login";
    }

    @GetMapping("registration.html")
    public String registrationHTML(@ModelAttribute("sender") String sender, @ModelAttribute("receiver") String receiver,  Model model) {
        model.addAttribute("sender", sender);
        model.addAttribute("receiver", receiver);
        return "registration";
    }
}
