package com.example.demo.controller;

import com.example.demo.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerAdviser {

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("exception_view");
        modelAndView.addObject("message", "Пользователь или курс не найдены");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

    @ExceptionHandler(NotPossibleDeleteException.class)
    public ModelAndView notPossibleDeleteExceptionHandler(NotPossibleDeleteException ex) {
        ModelAndView modelAndView = new ModelAndView("exception_view");
        modelAndView.addObject("message", "Невозможно удалить пользователя, так как на него назначены курсы");
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        return modelAndView;
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ModelAndView notPossibleDeleteExceptionHandler(UserAlreadyExistsException ex) {
        ModelAndView modelAndView = new ModelAndView("exception_view");
        modelAndView.addObject("message", "Такой логин уже создан, придумайте другой");
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        return modelAndView;
    }

    @ExceptionHandler(InternalServerError.class)
    public ModelAndView InternalServerErrorExceptionHandler(InternalServerError ex) {
        ModelAndView modelAndView = new ModelAndView("exception_view");
        modelAndView.addObject("message", "Произошла ошибка на сервере, попробуйте еще раз");
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return modelAndView;
    }

    @ExceptionHandler
    public ResponseEntity<Void> notFoundExceptionHandler(ImageNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }


}
