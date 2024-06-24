package ru.gb.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String ListUsers(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }
    @GetMapping("/{id}")
    public String ListUsers(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUserByID(id));
        return "user";
    }
}

