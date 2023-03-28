package com.example.eduproject.service;

import com.example.eduproject.entity.Group;
import com.example.eduproject.entity.User;
import com.example.eduproject.repos.GroupRepository;
import com.example.eduproject.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    public Page<Group> findByUserEmail(String email, Pageable pageable){
    Optional<User> userOptional = userRepository.findByEmail(email);
     User user = userOptional.orElseThrow(()->new IllegalArgumentException("Email not found"));
     return groupRepository.findGroupByUser(user,pageable);
    }
}
