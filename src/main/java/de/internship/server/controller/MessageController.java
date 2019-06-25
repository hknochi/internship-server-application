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

    public static final String MSG_REG_SUCCESSFUL = "MSG_REG_SUCCESSFUL";

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserController userController;

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

    @PostMapping(value = "/registerMessage", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String registerMessage(@RequestBody Message message) {
        message.setSendTime(Utils.getTimeInMs());
        String loginStatus = validateMessage(message.getMsgContent(), message.getTransmitterUsername(), message.getReceiverUsername(), message.getSendTime());

        if (loginStatus.equals(MSG_REG_SUCCESSFUL))
        {
            messageRepository.save(message);

            return Utils.generateJson(1, MSG_REG_SUCCESSFUL);
        }
        else
        {
            return Utils.generateJson(0, loginStatus);
        }

    }

    @PostMapping("/registerMessage")
    public String registerMessageHTML(Model model, @RequestParam String transmitterUsername, @RequestParam String receiverUsername, @RequestParam String msgContent) {

        long time = Utils.getTimeInMs();
        String loginStatus = validateMessage(msgContent, transmitterUsername, receiverUsername, time);

        if (loginStatus.equals(MSG_REG_SUCCESSFUL))
        {
            Message tempUserProfile = new Message(msgContent, transmitterUsername, receiverUsername, time);
            messageRepository.save(tempUserProfile);

            return "redirect:/message/messages.html";
        }
        else
        {
            return "redirect:/message/messages.html";
        }
    }

    @GetMapping("/messages.html")
    public String messagesHTML(Model model) {
        model.addAttribute("users", userController.getAllUsers());
        return "messages";
    }

    private String validateMessage(String msgContent, String transmitterUsername, String receiverUsername, long sendTime) {
        int statusValue=Validator.validateMsg(msgContent, transmitterUsername, receiverUsername, sendTime);
        // Here below a test for the actual existence of transmitter and receiver still's to add
        if(statusValue==1) {
            return "ERR_INV_ID";
        } else if(statusValue==2) {
            return "ERR_INV_ID_ID_CLONED";
        } else if(statusValue==3) {
            return "ERR_TRANSMITTER_EQUALS_RECEIVER";
        } else if(statusValue==4) {
            return "ERR_MSG_LENGTH_INADEQUATE_MSG_EMPTY";
        } else if(statusValue==5) {
            return "ERR_MSG_LENGTH_INADEQUATE_MSG_MAX_LENGTH_EXCEEDED";
        } else if(statusValue==6) {
            return "ERR_TIME_TRAVEL";
        } else {
            return MSG_REG_SUCCESSFUL;
        }
    }
}
