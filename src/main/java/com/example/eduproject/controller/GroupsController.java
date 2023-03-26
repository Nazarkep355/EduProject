package com.example.eduproject.controller;

import com.example.eduproject.service.GroupService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.print.Pageable;

@Controller
public class GroupsController {
    @Autowired
    private GroupService groupService;
    @GetMapping("/groups")
    public String groups(Pageable pageable, HttpServletRequest request, Model model) {
    String email = request.getRemoteUser();
    model.addAttribute("email",email);
        return "groups.html";
    }
}
