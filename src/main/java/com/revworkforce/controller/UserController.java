package com.revworkforce.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import com.revworkforce.entity.User;
import com.revworkforce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // @GetMapping
    // public List<User> getAllUsers() {
    //     return userRepository.findAll();
    // }
     // ✅ ADMIN → sab users
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
      // ✅ EMPLOYEE → sirf apni profile
    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/me")
    public User getMyProfile(Authentication authentication) {
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow();
    }
      // ✅ MANAGER → sirf apni team
    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/my-team")
    public List<User> getMyTeam(Authentication authentication) {
        String email = authentication.getName();
        User manager = userRepository.findByEmail(email).orElseThrow();
        return manager.getReportees();
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser,
            @RequestParam(required = false) Long managerId) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
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
        // ✅ IMPORTANT LINE
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}