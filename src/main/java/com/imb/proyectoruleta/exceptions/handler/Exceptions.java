package com.imb.proyectoruleta.exceptions.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.imb.proyectoruleta.exceptions.RuletaInexistente;

@RestControllerAdvice
public class Exceptions {

	@ExceptionHandler(value = RuletaInexistente.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> ruletaNoExiste(RuletaInexistente ex){
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return response;

    }
}
