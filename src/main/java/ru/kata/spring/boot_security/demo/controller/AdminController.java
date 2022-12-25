package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Arrays;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping(value = "/")
    public String getAllUsers(@AuthenticationPrincipal User user, ModelMap modelMap) {
        modelMap.addAttribute("allUsers", userService.listUsers());
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("roles", user.getRoles());
        return "admin";
    }

    @GetMapping(value = "/add-user")
    public String addNewUser(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "new-user";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("newUser") User user, @RequestParam(required = false, value = "checkBoxRoles") String[] checkBoxRoles) {
        user.setRoles(roleService.checkRoles(checkBoxRoles));
        userService.addUser(user);
        return "redirect:/admin/";
    }

//    @GetMapping(value = "/{id}/delete-user")
//    public String deleteUserQuestion(ModelMap model, @PathVariable("id") Long id) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "redirect:/admin/";
//    }

    @DeleteMapping(value = "/{id}")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.deleteUser(user);
        return "redirect:/admin/";
    }

//    @GetMapping(value = "/{id}/edit")
//    public String editUser(ModelMap model, @PathVariable("id") Long id) {
//        model.addAttribute("user", userService.getUserById(id));
//        model.addAttribute("roles", roleService.getAllRoles());
//        return "edit";
//    }

    @PatchMapping(value = "/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(required = false, value = "checkBoxRolesEdit") String[] checkBoxRoles) {
        System.out.println(Arrays.toString(checkBoxRoles));
        user.setRoles(roleService.checkRoles(checkBoxRoles));
        userService.updateUser(user);
        return "redirect:/admin/";
    }

}