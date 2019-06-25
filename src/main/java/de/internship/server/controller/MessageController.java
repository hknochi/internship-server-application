package de.internship.server.controller;

import de.internship.server.helper.Utils;
import de.internship.server.helper.Validator;
import de.internship.server.model.Message;
import de.internship.server.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        int statusValue=Validator.validateMsg(msgContent, msgTransmitter, msgReceiver, sendTime);
        if(statusValue==1) {
            return Utils.generateJson(0, "ERR_INV_ID");
        } else if(statusValue==2) {
            return Utils.generateJson(0, "ERR_INV_ID_ID_CLONED");
        } else if(statusValue==3) {
            return Utils.generateJson(0, "ERR_TRANSMITTER_EQUALS_RECEIVER");
        } else if(statusValue==4) {
            return Utils.generateJson(0, "ERR_MSG_EMPTY");
        } else if(statusValue==5) {
            return Utils.generateJson(0, "ERR_TIME_TRAVEL");
        } else {
            Message tempMessageProfile = new Message(msgContent, msgTransmitter, msgReceiver, sendTime);

            messageRepository.save(tempMessageProfile);
            return Utils.generateJson(1, "MSG_REG_SUCCESSFUL");        }

    }

    @GetMapping("/messages.html")
    public String messagesHTML(Model model) {
        model.addAttribute("users", new UserController().getAllUsers());
        return "messages";
    }
}
