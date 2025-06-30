package com.Natixis.gestao_consultas.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import java.util.Set;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthViewController {

    @GetMapping("/login")
    public String loginPage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()
            && !(authentication instanceof AnonymousAuthenticationToken)) {

            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

            if (roles.contains("ROLE_ADMIN")) {
                return "redirect:/admin";
            } else if (roles.contains("ROLE_MEDICO")) {
                return "redirect:/medico";
            } else if (roles.contains("ROLE_PACIENTE")) {
                return "redirect:/paciente";
            } else {
                return "redirect:/";
            }
        }
        return "login"; 
    }

}
