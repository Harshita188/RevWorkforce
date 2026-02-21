package com.revworkforce.controller;

import com.revworkforce.entity.User;
import com.revworkforce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
    return userRepository.findAll();
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser,
            @RequestParam(required = false) Long managerId) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setRoles(updatedUser.getRoles());
        user.setDepartment(updatedUser.getDepartment());
        user.setDesignation(updatedUser.getDesignation());

        if (managerId != null) {
            User manager = userRepository.findById(managerId)
                    .orElseThrow(() -> new RuntimeException("Manager not found with id: " + managerId));
            user.setManager(manager);
        }

        return userRepository.save(user);
    }

    // @PostMapping
    // public User createUser(@RequestBody User user) {
    // return userRepository.save(user);
    // }
    @PostMapping
    public User createUser(@RequestBody User user, @RequestParam(required = false) Long managerId) {
        if (managerId != null) {
            User manager = userRepository.findById(managerId)
                    .orElseThrow(() -> new RuntimeException("Manager not found with id: " + managerId));
            user.setManager(manager);
        }
        return userRepository.save(user);
    }
}