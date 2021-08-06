package com.example.demo.controller;

import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotPossibleDeleteException;
import com.example.demo.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerAdviser {

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

    @ExceptionHandler(NotPossibleDeleteException.class)
    public ModelAndView notPossibleDeleteExceptionHandler(NotPossibleDeleteException ex) {
        ModelAndView modelAndView = new ModelAndView("not_possible_delete");
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        return modelAndView;
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ModelAndView notPossibleDeleteExceptionHandler(UserAlreadyExistsException ex) {
        ModelAndView modelAndView = new ModelAndView("user_already_exists");
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        return modelAndView;
    }
}
