
package com.vamsi.employeeleavemanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.vamsi.employeeleavemanagement.entity.LeaveRequest;
import com.vamsi.employeeleavemanagement.service.LeaveRequestService;

@RestController
@RequestMapping("/api/leaves")
@CrossOrigin(origins = "http://localhost:5173")
public class LeaveRequestController {

    private final LeaveRequestService leaveService;

    public LeaveRequestController(LeaveRequestService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping
    public LeaveRequest applyForLeave(
            @RequestBody LeaveRequest request) {
        return leaveService.applyForLeave(request);
    }

    @GetMapping
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveService.getAllLeaveRequests();
    }

    @GetMapping("/{id}")
    public LeaveRequest getLeaveRequestById(@PathVariable Long id) {
        return leaveService.getLeaveRequestById(id);
    }

    @PutMapping("/{id}/status")
    public LeaveRequest updateLeaveStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return leaveService.updateLeaveStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public String deleteLeaveRequest(@PathVariable Long id) {
        leaveService.deleteLeaveRequest(id);
        return "Leave request deleted successfully";
    }
}
