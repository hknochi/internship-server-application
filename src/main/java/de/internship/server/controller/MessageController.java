package de.internship.server.controller;

import de.internship.server.helper.Utils;
import de.internship.server.model.Message;
import de.internship.server.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/registerMessage")
    @ResponseBody
    public String registerMessage(
            @RequestParam String msgTransmitter,
            @RequestParam String msgReceiver,
            @RequestParam String msgContent,
            @RequestParam Long sendTime
    ) {
        Message tempMessageProfile = new Message(msgContent, msgTransmitter, msgReceiver, sendTime);

        messageRepository.save(tempMessageProfile);
        return Utils.generateJson(1, "MSG_REG_SUCCESSFUL");
    }

}
