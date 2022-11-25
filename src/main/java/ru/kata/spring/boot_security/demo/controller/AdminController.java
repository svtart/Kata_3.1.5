package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

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

    @GetMapping("/admin/edit/{id}")
    public String findUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "admin";
    }

    @RequestMapping(value = "/admin/create", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    public String createUser(@ModelAttribute("user") User user, @RequestParam(value = "newRoles") String[] roles) {
        user.setRoles(getRoles(roles));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PutMapping("/admin/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(value = "rolesEdit", required = false) String[] rolesEdit) {
//        user.setRoles(getRoles(rolesEdit));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT} )
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        userService.removeUser(id);
        return "redirect:/admin";
    }

    public Set<Role> getRoles(String[] roles){
        Set<Role> userRole = new HashSet<>();
        for (String role : roles){
            String auth = role;
            userRole.add(roleService.getRoleByName(auth));
        }
        return userRole;
    }
}