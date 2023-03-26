package com.example.eduproject.controller;

import com.example.eduproject.entity.User;
import com.example.eduproject.service.RegisterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Controller
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/register")
    public String registerUser(HttpServletRequest request, String email, String password, String name) {
        try {
            User user = registerService.RegisterUserByForm(email, password, name);
            UsernamePasswordAuthenticationToken authReq
                    = new UsernamePasswordAuthenticationToken(user.getEmail(),
                    user.getPassword());
            Authentication auth = authManager.authenticate(authReq);

            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
            HttpSession session = request.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
        } catch (IllegalArgumentException e) {
            return "redirect:/register?error="+e.getMessage();
        }
        return "redirect:/";
    }
}
