package de.internship.server.controller;


import de.internship.server.helper.Validator;
import de.internship.server.model.UserProfile;
import de.internship.server.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/registration.html")
    public String registerHTML(Model model) {
        model.addAttribute("userprofile", new UserProfile());
        return "registration";
    }

    @PostMapping(value="/register", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String registerJSON(@RequestBody UserProfile userprofile) {
        return registerUser(userprofile.getUsername(),userprofile.getPassword(),userprofile.getFirstName(),userprofile.getLastName(),userprofile.getGender(),userprofile.getYearOfBirth());
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String gender, @RequestParam int yearOfBirth) {

        if (Validator.validateString(username, 2, 16, false, false, false)
                == 1) {
            return "INTERNAL_ERR_USERNAME_INV_ARG";
        }
        if (Validator.validateString(username, 2, 16, false, false, false)
                == 2) {
            return "ERR_USERNAME_LENGTH_INADEQUATE";
        }
        /*if(Validator.validateString(username, 2, 16, false, false, false)
                == 3) {
            return "ERR_USERNAME_NOT_ALPHANUMERIC";
        }*/
        System.out.println("fn validator " + Validator.validateString(firstName, 2, 20, false, true, false));
        if (Validator.validateString(password, 8, 32, true, false, false)
                == 1) {
            return "INTERNAL_ERR_PASSWORD_INV_ARG";
        }
        if (Validator.validateString(password, 8, 32, true, false, false)
                == 2) {
            return "ERR_PASSWORD_LENGTH_INADEQUATE";
        }
        if (Validator.validateString(password, 8, 32, true, false, false)
                == 3) {
            return "ERR_PASSWORD_NOT_ALPHANUMERIC";
        }
        if (Validator.validateString(firstName, 2, 20, false, true, false)
                == 1) {
            return "INTERNAL_ERR_FIRST_NAME_INV_ARG";
        }
        if (Validator.validateString(firstName, 2, 20, false, true, false)
                == 2) {
            return "ERR_FIRST_NAME_LENGTH_INADEQUATE";
        }
        if (Validator.validateString(firstName, 2, 20, false, true, false)
                == 4) {
            return "ERR_FIRST_NAME_NOT_ALPHABETIC";
        }
        if (Validator.validateString(lastName, 2, 20, false, true, false)
                == 1) {
            return "INTERNAL_ERR_LAST_NAME_INV_ARG";
        }
        if (Validator.validateString(lastName, 2, 20, false, true, false)
                == 2) {
            return "ERR_LAST_NAME_LENGTH_INADEQUATE";
        }
        if (Validator.validateString(lastName, 2, 20, false, true, false)
                == 4) {
            return "ERR_LAST_NAME_NOT_ALPHABETIC";
        }
        if (Validator.validateString(gender, 4, 7, false, false, true)
                == 1) {
            return "INTERNAL_ERR_GENDER_INV_ARG";
        }
        if (Validator.validateString(gender, 4, 7, false, false, true)
                == 5) {
            return "ERR_INV_GENDER";
        }
        if (Validator.validateInt(yearOfBirth)
                == 1) {
            return "ERR_YEAR_OF_BIRTH_TOO_LOW";
        } else if (Validator.validateInt(yearOfBirth)
                == 2) {
            return "ERR_YEAR_OF_BIRTH_TOO_HIGH";
        }

        UserProfile tempUserProfile = new UserProfile(username, password, firstName, lastName, gender, yearOfBirth);

        //check for existing user
        List<UserProfile> userProfileList = userProfileRepository.findAll();
        for (int i = 0; i < userProfileList.size(); i++) {
            if (userProfileList.get(i).getUsername().equals(username)) {
                return "ERR_USERNAME_ALREADY_EXISTS";
            }
        }
        userProfileRepository.save(tempUserProfile);

        return "SUCCESSFULLY CREATED";
    }

    @GetMapping(value = "/login")
    @ResponseBody
    public String verifyUserLogin(@RequestParam String username, @RequestParam String password) {
        List<UserProfile> userProfileList = userProfileRepository.findAll();
        for (int i = 0; i < userProfileList.size(); i++) {
            if (userProfileList.get(i).getUsername().equals(username)) {
                if (userProfileList.get(i).getPassword().equals(password)) {
                    return "LOGIN_SUCCESSFUL";
                } else {
                    return "ERR_INVALID_PASSWORD";
                }
            } else {
                return "ERR_INVALID_USERNAME";
            }
        }
        return "INTERNAL_ERR_FUNCTION_CONTROL_BRIDGING";
    }
}