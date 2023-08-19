package com.bookshelf.gateway.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.result.view.RedirectView;

@ControllerAdvice
public class MethodArgumentExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    public RedirectView parameterExceptionHandler(WebExchangeBindException e) {
        BindingResult exceptions = e.getBindingResult();
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                FieldError fieldError = (FieldError) errors.get(0);
                return new RedirectView("/register?error="
                        + Optional.ofNullable(fieldError.getDefaultMessage())
                                .orElse(StringUtils.EMPTY)
                                .replaceAll(StringUtils.SPACE, "+"));
            }
        }
        return new RedirectView("/register?error=Zle+Dane");
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class ErrorResult implements Serializable {
        private long code;
        private String msg;
    }
}
