package com.example.demo.controller;

import com.example.demo.dao.RoleRepository;
import com.example.demo.domain.Role;
import com.example.demo.dto.UserDto;
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
    private final RoleRepository roleRepository;
    private final UserService userService;



    @Autowired
    public UserController(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;

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


    @Transactional
    @GetMapping("/admin/{id}")
    public String userForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "user_form";
    }


    @PostMapping//апдэйт, Если есть с таким же айди то смердживаетб если нет то создает новый
    public String updateUserForm(@Valid @ModelAttribute("user") UserDto userDto,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_form";
        }
        userService.updateUser(userDto);
        return "redirect:/user/findUser";
    }


    @PostMapping("/registration")
    public String submitUserForm(@Valid @ModelAttribute("user") UserDto userDto,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration_form";
        }
        userService.saveStudent(userDto);
        return "requestFormUserRegistration";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration_form";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        this.userService.deleteById(id);
        return "redirect:/user/findUser";
    }

    @ModelAttribute("roles")
    public List<Role> rolesAttribute() {
        return roleRepository.findAll();
    }


}
