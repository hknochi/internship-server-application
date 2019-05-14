package de.internship.server.controller;

import de.internship.server.model.Book;
import de.internship.server.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping(path = "/books")
public class BookController {

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookRepository bookRepository;


    @GetMapping("")
    public String getbooks(Model model) {
        List books = (List<Book>) bookRepository.findAll();

        model.addAttribute("books", books);
        return "showBooks";
    }

}
