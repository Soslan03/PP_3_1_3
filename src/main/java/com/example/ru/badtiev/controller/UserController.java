package com.example.ru.badtiev.controller;

import com.example.ru.badtiev.service.RoleService;
import com.example.ru.badtiev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    /**
     * Информаци о зарегистрированном пользователе.
     * @param principal
     * @param model
     * @return
     */
    @GetMapping()
    public String pageUser(Principal principal, Model model) {
        model.addAttribute("user", userService.findByEmail(principal.getName()));

        return "/user";
    }

    /**
     * Информация о пользователе по id.
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public String pageUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("roles", roleService.listRoles());
        model.addAttribute("user", userService.findById(id));
        return "/user";
    }


}

