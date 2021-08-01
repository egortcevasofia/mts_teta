package com.example.demo.controller;

import com.example.demo.dao.RoleRepository;
import com.example.demo.domain.Role;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    private RoleRepository roleRepository;
    private UserService userService;
    private UserMapper userMapper;


    @Autowired
    public UserController(RoleRepository roleRepository, UserService userService, UserMapper userMapper) {
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @Transactional
    @GetMapping("/admin/{id}")
    public String userForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "user_form";
    }

    @GetMapping("/findUser")
    public String userTable(Model model, HttpServletRequest request) {

        model.addAttribute("users", this.userService.findAll());
        return "users";
    }

    @GetMapping
    public String userForm(Model model, HttpServletRequest request) {
        model.addAttribute("user", this.userService.findUserByUsername(request.getRemoteUser()));
        return "user_form";
    }


    @PostMapping("/registration")
    public String submitUserForm(@Valid @ModelAttribute("user") UserDto user,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration_form";
        }
        userService.saveStudent(user);
        return "requestFormUserRegistration";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration_form";
    }

    @DeleteMapping({"/{id}"})
    public String deleteUser(HttpServletRequest request, @PathVariable("id") Long id) {
            this.userService.deleteById(id);
            return "redirect:/user/findUser";
    }

    @ModelAttribute("roles")
    public List<Role> rolesAttribute() {
        return roleRepository.findAll();
    }





}
