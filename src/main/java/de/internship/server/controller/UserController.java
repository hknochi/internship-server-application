package de.internship.server.controller;

import de.internship.server.helper.Utils;
import de.internship.server.helper.Validator;
import de.internship.server.model.Login;
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

    public static final String LOGIN_SUCCESSFUL = "LOGIN_SUCCESSFUL";
    public static final String ERROR_INVALID_PASSWORD = "ERR_INV_PASSWORD";
    public static final String ERROR_INVALID_USERNAME = "ERR_INV_USERNAME";
    public static final String REGISTER_SUCCESSFUL = "REGISTER_SUCCESSFUL";


    @Autowired
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserProfileRepository userProfileRepository;

    @GetMapping(value = "", produces = "application/json")
    @ResponseBody
    public List<UserProfile> getUserListAsJson() {
        return getAllUsers();
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

    @PostMapping("/register")
    public String registerHTML(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String gender, @RequestParam int yearOfBirth) {
        String loginStatus = validateUser(username, password, firstName, lastName, gender, yearOfBirth);

        if (loginStatus.equals(REGISTER_SUCCESSFUL))
        {
            UserProfile tempUserProfile = new UserProfile(username, password, firstName, lastName, gender, yearOfBirth);
            userProfileRepository.save(tempUserProfile);

            return "redirect:/message/messages.html";
        }
        else
        {
            return "redirect:/registration.html";
        }
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String registerJSON(@RequestBody UserProfile userprofile) {

        String loginStatus = validateUser(userprofile.getUsername(), userprofile.getPassword(), userprofile.getFirstName(), userprofile.getLastName(), userprofile.getGender(), userprofile.getYearOfBirth());

        if (loginStatus.equals(REGISTER_SUCCESSFUL))
        {
            UserProfile tempUserProfile = new UserProfile(userprofile.getUsername(), userprofile.getPassword(), userprofile.getFirstName(), userprofile.getLastName(), userprofile.getGender(), userprofile.getYearOfBirth());
            userProfileRepository.save(tempUserProfile);

            return Utils.generateJson(1, REGISTER_SUCCESSFUL);
        }
        else
        {
            return Utils.generateJson(0, loginStatus);
        }
    }




    @PostMapping(value = "/login")
    public String verifyUserLogin(@RequestParam String username, @RequestParam String password) {
        String loginStatus = getLoginStatus(username, password);

        if (loginStatus.equals(LOGIN_SUCCESSFUL))
        {
            return "redirect:/message/messages.html";
        }
        else
        {
            return "redirect:/login.html";
        }
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String loginJson(@RequestBody Login login) {
        String loginStatus = getLoginStatus(login.getUsername(), login.getPassword());

        if (loginStatus.equals(LOGIN_SUCCESSFUL))
        {
            return Utils.generateJson(1, LOGIN_SUCCESSFUL);
        }
        else
        {
            return Utils.generateJson(0, loginStatus);
        }
    }

    private String getLoginStatus(String username, String password)
    {
        List<UserProfile> userProfileList = userProfileRepository.findAll();
        for (int i = 0; i < userProfileList.size(); i++) {
            if (userProfileList.get(i).getUsername().equals(username)) {
                if (userProfileList.get(i).getPassword().equals(password)) {
                    return LOGIN_SUCCESSFUL;
                } else {
                    return ERROR_INVALID_PASSWORD;
                }
            }
        }
        return ERROR_INVALID_USERNAME;
    }

    public List<UserProfile> getAllUsers() {
        List<UserProfile> userProfileList = userProfileRepository.findAll();
        for (int i = 0; i < userProfileList.size(); i++) {
            userProfileList.get(i).setPassword("<ausgeblendet>");
        }
        return userProfileList;
    }

    private String validateUser(String username, String password, String firstName, String lastName, String gender, int yearOfBirth) {

        if (Validator.validateString(username, 2, 16, false, false, false)
                == 1) {
            return "INTERNAL_ERROR_USERNAME_INV_ARG";
        }
        if (Validator.validateString(username, 2, 16, false, false, false)
                == 2) {
            return"ERR_USERNAME_LENGTH_INADEQUATE";
        }
        /*if(Validator.validateString(username, 2, 16, false, false, false)
                == 3) {
            return "ERR_USERNAME_NOT_ALPHANUMERIC";
        }*/
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

        //check for existing user
        List<UserProfile> userProfileList = userProfileRepository.findAll();
        for (int i = 0; i < userProfileList.size(); i++) {
            if (userProfileList.get(i).getUsername().equals(username)) {
                return "ERR_USERNAME_ALREADY_EXISTS";
            }
        }
        return REGISTER_SUCCESSFUL;
    }
}
