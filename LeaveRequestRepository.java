package com.vamsi.employeeleavemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vamsi.employeeleavemanagement.entity.LeaveRequest;

public interface LeaveRequestRepository
        extends JpaRepository<LeaveRequest, Long> {
}