package com.example.demo.controller;

import com.example.demo.dao.RoleRepository;
import com.example.demo.domain.Role;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("admin/user")
public class UserController {
    RoleRepository roleRepository;
    UserService userService;


    @Autowired
    public UserController(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }
//    @GetMapping
//    public String courseTable( Model model, @RequestParam(name = "id", required = false) Long id) {
//        //if(id == null) return  "redirect:/user";
//        model.addAttribute("user", userService.findUserById(id));
//        return "user_form";
//    }


    @Transactional
    @RequestMapping("/{id}")
    public String userForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "user_form";
    }

    @ModelAttribute("roles")
    public List<Role> rolesAttribute() {
        return roleRepository.findAll();
    }

    @PostMapping
    public String submitUserForm(@Valid @ModelAttribute("user") UserDto user,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_form";
        }

        userService.save(user);
        return "redirect:/user";
    }
}
