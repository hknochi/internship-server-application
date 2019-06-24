package de.internship.server.controller;

import de.internship.server.model.Message;
import de.internship.server.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/message")
public class MessageController {
    @Autowired
    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping(value = "", produces = "application/json")
    @ResponseBody
    public List<Message> getMessageListAsJson() {
        List<Message> messageList = messageRepository.findAll();
        return messageList;
    }

    @GetMapping(value = "/{msgId}", produces = "application/json")
    @ResponseBody
    public Optional<Message> getMessageAsJson(@PathVariable Long msgId) {
        return messageRepository.findById(msgId);
    }
}

