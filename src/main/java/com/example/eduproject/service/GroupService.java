package com.example.eduproject.service;

import com.example.eduproject.entity.Group;
import com.example.eduproject.entity.GroupAccessibility;
import com.example.eduproject.entity.User;
import com.example.eduproject.repos.GroupRepository;
import com.example.eduproject.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<Group> findByUserEmail(String email, Pageable pageable) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional.orElseThrow(() -> new IllegalArgumentException("Email not found"));
        return groupRepository.findGroupByUser(user, pageable);
    }

    public Group createNewGroup(String email, String name, String desc, String password) {
        GroupAccessibility groupAccessibility = GroupAccessibility.accessibilityFromPassword(password);
        String code = StringUtils.randomAlphanumeric(6);
        while (groupRepository.findGroupByCode(code).isPresent()) {
            code = StringUtils.randomAlphanumeric(6);
        }
        password = "password";
        Group group = Group.builder()
                .teachers(List.of(userRepository.findByEmail(email)
                        .orElseThrow(() -> new IllegalArgumentException("Email not found"))))
                .code(code)
                .description(desc)
                .name(name)
                .accessibility(groupAccessibility)
                .password(passwordEncoder.encode(password))
                .build();

        return groupRepository.save(group);
    }

    public Group    addUserToGroup(String code, String password, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));
        Group group = groupRepository.findGroupByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Group not found"));
        if (group.getStudents().contains(user) || group.getTeachers().contains(user)) {
            throw new IllegalArgumentException("You are already in this group");
        }
        if (group.getAccessibility() == GroupAccessibility.PRIVATE) {
            if (StringUtils.isEmpty(password)) {
                throw new IllegalArgumentException("Group is private");
            }
            if (passwordEncoder.matches(password, group.getPassword())) {
                group.getStudents().add(user);
                return groupRepository.save(group);
            } else throw new IllegalArgumentException("Password is incorrect");
        }

        group.getStudents().add(user);
        return groupRepository.save(group);
    }


}
