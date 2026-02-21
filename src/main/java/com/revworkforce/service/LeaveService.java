package com.revworkforce.service;

import com.revworkforce.entity.Leave;
import com.revworkforce.entity.User;
import com.revworkforce.repository.LeaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    // Employee applies leave
    public Leave applyLeave(Leave leave) {
        return leaveRepository.save(leave);
    }

    // Manager fetches pending leaves
    public List<Leave> getPendingLeaves(User manager) {
        return leaveRepository.findByManagerAndStatus(manager, "PENDING");
    }

    // Approve leave
    public Leave approveLeave(Long leaveId) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found with id: " + leaveId));
        leave.setStatus("APPROVED");
        return leaveRepository.save(leave);
    }

    // Reject leave
    public Leave rejectLeave(Long leaveId) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found with id: " + leaveId));
        leave.setStatus("REJECTED");
        return leaveRepository.save(leave);
    }
}