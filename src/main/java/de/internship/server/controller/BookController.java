package de.internship.server.controller;

import de.internship.server.model.Book;
import de.internship.server.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/books")
public class BookController {

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(value="")
    public String getbooks(Model model) {
        List books = (List<Book>) bookRepository.findAll();
        model.addAttribute("books", books);
        return "showBooks";
    }

    @GetMapping(value="", consumes="application/json", produces="application/json")
    @ResponseBody
    public List<Book> getBooksAsJson() {
        return (List<Book>) bookRepository.findAll();
    }


    @GetMapping("/{id}")
    public String getBook(@PathVariable long id,  Model model) {
        model.addAttribute("book", bookRepository.getOne(id));
        return "addBook";
    }

    @GetMapping(value="/{id}", consumes="application/json", produces="application/json")
    @ResponseBody
    public Optional<Book> getBookAsJson(@PathVariable long id) {
        return bookRepository.findById(id);
    }

    @GetMapping("/add")
    public String getBook(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping(value="/add")
    public String updateBook(@ModelAttribute Book book, Model model) {
        bookRepository.save(book);

        List books = (List<Book>) bookRepository.findAll();
        model.addAttribute("books", books);
        return "redirect:";
    }



}
