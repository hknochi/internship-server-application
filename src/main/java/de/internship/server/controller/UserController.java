package de.internship.server.controller;

import de.internship.server.model.UserProfile;
import de.internship.server.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserProfileRepository userProfileRepository;

    @GetMapping(value = "", produces = "application/json")
    @ResponseBody
    public List<UserProfile> getUserListAsJson() {
        List<UserProfile> userProfileList = userProfileRepository.findAll();
        for (int i = 0; i < userProfileList.size(); i++) {
            userProfileList.get(i).setPassword("<ausgeblendet>");
        }
        return userProfileList;
    }

    @GetMapping(value = "/{username}", produces = "application/json")
    @ResponseBody
    public Optional<UserProfile> getUserAsJson(@PathVariable String username) {
        List<UserProfile> userProfileList = userProfileRepository.findAll();
        for (int i = 0; i < userProfileList.size(); i++) {
            if (userProfileList.get(i).getUsername().equals(username)) {
                userProfileList.get(i).setPassword("<ausgeblendet>");
                return Optional.of(userProfileList.get(i));
            }
        }
        return Optional.empty();
    }

    @GetMapping(value = "/register")
    @ResponseBody
    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String gender, @RequestParam int yearOfBirth) {
        UserProfile tempUserProfile = new UserProfile(username, password, firstName, lastName, gender, yearOfBirth);
        userProfileRepository.save(tempUserProfile);
    }


}
