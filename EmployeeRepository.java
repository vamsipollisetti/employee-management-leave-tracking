package com.vamsi.employeeleavemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vamsi.employeeleavemanagement.entity.Employee;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {
}