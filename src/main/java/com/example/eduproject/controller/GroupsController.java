package com.example.eduproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.print.Pageable;

@Controller
public class GroupsController {
    @Autowired
    private GroupService groupService;
    @GetMapping("/groups")
    public String groups(Pageable pageable, HttpServletRequest request) {
    String email = request.getRemoteUser();

    }
}
