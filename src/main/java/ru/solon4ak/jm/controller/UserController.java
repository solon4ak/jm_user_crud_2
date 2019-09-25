package ru.solon4ak.jm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import ru.solon4ak.jm.model.User;
import ru.solon4ak.jm.service.UserService;

/**
 *
 * @author solon4ak
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public String getAll(Map<String, Object> model) {
        model.put("users", userService.listAll());
        return "list";
    }

    @GetMapping("view/{userId}")
    public String viewUser(Map<String, Object> model,
            @PathVariable("userId") long userId) {
        User user = userService.get(userId);

        model.put("user", user);
        return "view";
    }

    @GetMapping(value = "add")
    public String addUser(Map<String, Object> model, UserForm userForm) {
        List<String> userRoles = new ArrayList<>(
                Arrays.asList(new String[]{"user", "admin"})
        );
        model.put("roles", userRoles);
        model.put("userForm", userForm);
        return "add";
    }
    
    @PostMapping(value = "add")
    public View addUser(UserForm userForm) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.forLanguageTag("ru_RU"));

        User user = null;
        try {
            user = new User(
                    userForm.getFirstName(),
                    userForm.getLastName(),
                    userForm.getEmail(),
                    userForm.getAddress(),
                    userForm.getPhoneNumber(),
                    formatter.parse(userForm.getBirthDate()),
                    userForm.getNickName(),
                    userForm.getPassword()
            );
            user.setRole(userForm.getRole());
        } catch (ParseException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        userService.save(user);

        return new RedirectView("/list", true, false);
    }
    
    @GetMapping(value = "edit/{userId}")
    public String editUser(Map<String, Object> model,
            @PathVariable("userId") long userId) {

        User user = userService.get(userId);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.forLanguageTag("ru_RU"));

        List<String> userRoles = new ArrayList<>(
                Arrays.asList(new String[]{"user", "admin"})
        );

        UserForm userForm = new UserForm();

        userForm.setNickName(user.getNickName());
        userForm.setPassword(user.getPassword());
        userForm.setFirstName(user.getFirstName());
        userForm.setLastName(user.getLastName());
        userForm.setEmail(user.getEmail());
        userForm.setAddress(user.getAddress());
        userForm.setPhoneNumber(user.getPhoneNumber());
        userForm.setRole(user.getRole());
        userForm.setBirthDate(formatter.format(user.getBirthDate()));

        model.put("user", user);
        model.put("roles", userRoles);
        model.put("userForm", userForm);
        return "edit";
    }

    @PostMapping(value = "edit/{userId}")
    public View editUser(UserForm userForm, @PathVariable("userId") long userId) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.forLanguageTag("ru_RU"));

        User user = userService.get(userId);
        System.out.println(user);
        try {
            user.setFirstName(userForm.getFirstName());
            user.setLastName(userForm.getLastName());
            user.setEmail(userForm.getEmail());
            user.setAddress(userForm.getAddress());
            user.setPhoneNumber(userForm.getPhoneNumber());
            user.setBirthDate(formatter.parse(userForm.getBirthDate()));
            user.setPassword(userForm.getPassword());
            user.setRole(userForm.getRole());
            System.out.println(user);
            userService.update(user);
        } catch (ParseException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new RedirectView("/view/" + userId, true, false);
    }
    
    @GetMapping(value = "delete/{userId}")
    public View deleteUser(@PathVariable("userId") long userId) {
        userService.delete(userId);
        return new RedirectView("/list", true, false);
    }

    public static class UserForm {

        private String nickName;
        private String password;
        private String firstName;
        private String lastName;
        private String email;
        private String address;
        private String phoneNumber;
        private String birthDate;
        private String role;

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

    }
}
