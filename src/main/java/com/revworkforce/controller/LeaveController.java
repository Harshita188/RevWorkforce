package com.revworkforce.controller;

import com.revworkforce.dto.LeaveDTO;
import com.revworkforce.entity.Leave;
import com.revworkforce.entity.User;
import com.revworkforce.repository.UserRepository;
import com.revworkforce.service.LeaveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final LeaveService leaveService;
    private final UserRepository userRepository;

    public LeaveController(LeaveService leaveService, UserRepository userRepository) {
        this.leaveService = leaveService;
        this.userRepository = userRepository;
    }

    // Employee applies leave
    // @PostMapping
    // public Leave applyLeave(@RequestBody Leave leave) {
    // // Optional: fetch employee & manager from DB to ensure they exist
    // leave.setEmployee(userRepository.findById(leave.getEmployee().getId())
    // .orElseThrow(() -> new RuntimeException("Employee not found")));
    // leave.setManager(userRepository.findById(leave.getManager().getId())
    // .orElseThrow(() -> new RuntimeException("Manager not found")));

    // return leaveService.applyLeave(leave);
    // }
    // Employee applies leave – return LeaveDTO instead of full Leave
    @PostMapping
    public LeaveDTO applyLeave(@RequestBody Leave leave) {
        // Set employee and manager from DB
        leave.setEmployee(userRepository.findById(leave.getEmployee().getId())
                .orElseThrow(() -> new RuntimeException("Employee not found")));
        leave.setManager(userRepository.findById(leave.getManager().getId())
                .orElseThrow(() -> new RuntimeException("Manager not found")));

        // Save leave
        Leave savedLeave = leaveService.applyLeave(leave);

        // Map to LeaveDTO
        return new LeaveDTO(
                savedLeave.getId(),
                savedLeave.getEmployee().getFirstName() + " " + savedLeave.getEmployee().getLastName(),
                savedLeave.getManager().getFirstName() + " " + savedLeave.getManager().getLastName(),
                savedLeave.getType(),
                savedLeave.getStatus(),
                savedLeave.getStartDate(),
                savedLeave.getEndDate(),
                savedLeave.getReason());
    }

    // Manager fetches pending leaves
    // @GetMapping("/pending")
    // public List<Leave> getPendingLeaves(@RequestParam Long managerId) {
    // User manager = userRepository.findById(managerId)
    // .orElseThrow(() -> new RuntimeException("Manager not found"));
    // return leaveService.getPendingLeaves(manager);
    // }
    // Manager fetches pending leaves (return clean DTO)
    @GetMapping("/pending")
    public List<LeaveDTO> getPendingLeaves(@RequestParam Long managerId) {
        User manager = userRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found"));
        List<Leave> pendingLeaves = leaveService.getPendingLeaves(manager);

        // Map Leave -> LeaveDTO
        return pendingLeaves.stream().map(leave -> new LeaveDTO(
                leave.getId(),
                leave.getEmployee().getFirstName() + " " + leave.getEmployee().getLastName(),
                leave.getManager().getFirstName() + " " + leave.getManager().getLastName(),
                leave.getType(),
                leave.getStatus(),
                leave.getStartDate(),
                leave.getEndDate(),
                leave.getReason())).collect(Collectors.toList());
    }

    // // Approve leave
    // @PutMapping("/{id}/approve")
    // public Leave approveLeave(@PathVariable Long id) {
    // return leaveService.approveLeave(id);
    // }
    // Approve leave – return LeaveDTO
    @PutMapping("/{id}/approve")
    public LeaveDTO approveLeave(@PathVariable Long id) {
        Leave savedLeave = leaveService.approveLeave(id);

        return new LeaveDTO(
                savedLeave.getId(),
                savedLeave.getEmployee().getFirstName() + " " + savedLeave.getEmployee().getLastName(),
                savedLeave.getManager().getFirstName() + " " + savedLeave.getManager().getLastName(),
                savedLeave.getType(),
                savedLeave.getStatus(),
                savedLeave.getStartDate(),
                savedLeave.getEndDate(),
                savedLeave.getReason());
    }

    // Reject leave
    // Reject leave – return LeaveDTO
    @PutMapping("/{id}/reject")
    public LeaveDTO rejectLeave(@PathVariable Long id) {
        Leave savedLeave = leaveService.rejectLeave(id);

        return new LeaveDTO(
                savedLeave.getId(),
                savedLeave.getEmployee().getFirstName() + " " + savedLeave.getEmployee().getLastName(),
                savedLeave.getManager().getFirstName() + " " + savedLeave.getManager().getLastName(),
                savedLeave.getType(),
                savedLeave.getStatus(),
                savedLeave.getStartDate(),
                savedLeave.getEndDate(),
                savedLeave.getReason());
    }
}