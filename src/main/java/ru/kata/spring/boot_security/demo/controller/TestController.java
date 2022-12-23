//package ru.kata.spring.boot_security.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.RoleService;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//@Controller
//public class TestController {
//
//    private final UserService userService;
//    private final RoleService roleService;
//
//    @Autowired
//    public TestController(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }
//
//    @GetMapping("/admin2")
//    public String testController(@AuthenticationPrincipal User user, ModelMap modelMap) {
//        modelMap.addAttribute("allUsers", userService.listUsers());
//        modelMap.addAttribute("user", user);
//        modelMap.addAttribute("roles", user.getRoles());
//        return "admin";
//    }
//}
