
package com.vamsi.employeeleavemanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vamsi.employeeleavemanagement.entity.Employee;
import com.vamsi.employeeleavemanagement.entity.LeaveRequest;
import com.vamsi.employeeleavemanagement.repository.EmployeeRepository;
import com.vamsi.employeeleavemanagement.repository.LeaveRequestRepository;

@Service
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveRequestService(
            LeaveRequestRepository leaveRepository,
            EmployeeRepository employeeRepository) {
        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
    }

    public LeaveRequest applyForLeave(LeaveRequest request) {

        if (request.getEmployeeId() == null) {
            throw new IllegalArgumentException("Employee ID is required");
        }

        Employee employee = employeeRepository
                .findById(request.getEmployeeId())
                .orElseThrow(() ->
                    new RuntimeException("Employee not found"));

        if (request.getStartDate() == null
                || request.getEndDate() == null) {
            throw new IllegalArgumentException(
                    "Start date and end date are required");
        }

        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new IllegalArgumentException(
                    "End date cannot be before start date");
        }

        if (request.getReason() == null
                || request.getReason().isBlank()) {
            throw new IllegalArgumentException("Leave reason is required");
        }

        request.setId(null);
        request.setStatus("PENDING");

        return leaveRepository.save(request);
    }

    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRepository.findAll();
    }

    public LeaveRequest getLeaveRequestById(Long id) {
        return leaveRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Leave request not found with ID: " + id));
    }

    @Transactional
    public LeaveRequest updateLeaveStatus(Long id, String status) {

        if (status == null
                || (!status.equalsIgnoreCase("APPROVED")
                && !status.equalsIgnoreCase("REJECTED"))) {
            throw new IllegalArgumentException(
                    "Status must be APPROVED or REJECTED");
        }

        LeaveRequest request = getLeaveRequestById(id);
        request.setStatus(status.toUpperCase());

        return leaveRepository.save(request);
    }

    public void deleteLeaveRequest(Long id) {
        LeaveRequest request = getLeaveRequestById(id);
        leaveRepository.delete(request);
    }
}
