package ru.javawebinar.topjava.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import ru.javawebinar.topjava.to.UserTo;

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:meals";
    }

//    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String getUsers(ModelMap model) {
    	model.addAttribute("userForm", new UserTo());
        return "users";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/meals")
    public String getMeals() {
        return "meals";
    }
}
