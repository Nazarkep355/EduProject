package com.example.eduproject.controller;

import com.example.eduproject.service.GroupService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
@RequestMapping("/groups")
public class GroupsController {
    @Autowired
    private GroupService groupService;
    @GetMapping
    public String groups(Pageable pageable, HttpServletRequest request, Model model) {
    String email = request.getRemoteUser();
    if(email==null){
        return "redirect:/";
    }
    model.addAttribute("groups",groupService.findByUserEmail(email,pageable));
        return "groups.html";
    }

    @GetMapping("/create")
    public String createGroupPage(){
        return "create_group_page.html";
    }
    @PostMapping("/create")
    public String createGroup(String name, String description, Optional<String> password, HttpServletRequest request){
        String email = request.getRemoteUser();
        groupService.createNewGroup(email,name,description,password.orElse(null));
        return "redirect:/";
    }
    @GetMapping("/join")
    public String joinPage(Optional<String> error,Model model){
        error.ifPresent((er)->model.addAttribute("error",er));
        return "join_group.html";
    }
    @PostMapping("/join")
    public String joinRoom(String code,Optional<String> password,HttpServletRequest request){
        String email = request.getRemoteUser();
        try{
            groupService.addUserToGroup(code,password.get(),email);
        }catch (IllegalArgumentException e){
            return "redirect:/groups/join?error="+e.getMessage();
        }
        return "redirect:/groups";
    }

    }
