package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model, @AuthenticationPrincipal User currentUser) {

        model.addAttribute("createUser", new User());
        model.addAttribute("users", userService.getListUsers());
        model.addAttribute("listRoles", roleService.getAllRoles());
        model.addAttribute("currentUser", currentUser);
        return "admin";
    }

    @GetMapping("/admin/create")
    public String saveUser(User user, Model model) {
        model.addAttribute("user",user);
        model.addAttribute("listRoles", roleService.getAllRoles());
        return "create";
    }

    @PostMapping("/admin/create")
    public String saveUser(User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }
    @GetMapping("/admin/edit/{id}")
    public String update(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("listRoles", roleService.getAllRoles());
        return "edit";
    }

    @PostMapping("/admin/edit")
    public String update(User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }
    @GetMapping("/admin/user/{id}")
    public String findUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "admin";
    }

}