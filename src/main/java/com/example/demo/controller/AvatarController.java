package com.example.demo.controller;

import com.example.demo.exception.InternalServerError;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.AvatarStorageService;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/profile")
public class AvatarController {

    private final AvatarStorageService avatarStorageService;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public AvatarController(AvatarStorageService avatarStorageService, UserService userService) {
        this.avatarStorageService = avatarStorageService;
        this.userService = userService;
    }

    @GetMapping
    public String profile(Model model, HttpServletRequest request) {
        model.addAttribute("user", this.userService.findUserByUsername(request.getRemoteUser()));
        return "profile";
    }

    @PostMapping("/avatar")
    public String updateAvatarImage(Authentication auth,
                                    @RequestParam("avatar") MultipartFile avatar) {
        logger.info("File name {}, file content type {}, file size {}", avatar.getOriginalFilename(), avatar.getContentType(), avatar.getSize());
        try {
            avatarStorageService.save(auth.getName(), avatar.getContentType(), avatar.getInputStream());
        } catch (Exception ex) {
            logger.info("", ex);
            throw new InternalServerError();
        }
        return "redirect:/profile";
    }

    @GetMapping("/avatar")
    @ResponseBody
    public ResponseEntity<byte[]> avatarImage(Authentication auth) {
        String contentType = avatarStorageService.getContentTypeByUser(auth.getName())
                .orElseThrow(NotFoundException::new);
        byte[] data = avatarStorageService.getAvatarImageByUser(auth.getName())
                .orElseThrow(NotFoundException::new);
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(data);
    }

    @ExceptionHandler
    public ResponseEntity<Void> notFoundExceptionHandler(NotFoundException ex) {
        return ResponseEntity.notFound().build();
    }


}
