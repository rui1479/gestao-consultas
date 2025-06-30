package com.Natixis.gestao_consultas.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex, HttpServletRequest request, Model model) {
        model.addAttribute("status", 500);
        model.addAttribute("error", "Erro interno");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("path", request.getRequestURI());
        return "erro";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFound(NoHandlerFoundException ex, HttpServletRequest request, Model model) {
        model.addAttribute("status", 404);
        model.addAttribute("error", "Página não encontrada");
        model.addAttribute("message", "O recurso solicitado não existe.");
        model.addAttribute("path", request.getRequestURI());
        return "erro";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDenied(AccessDeniedException ex, HttpServletRequest request, Model model) {
        model.addAttribute("status", 403);
        model.addAttribute("error", "Acesso negado");
        model.addAttribute("message", "Não tem permissões para aceder a este recurso.");
        model.addAttribute("path", request.getRequestURI());
        return "erro";
    }
}
