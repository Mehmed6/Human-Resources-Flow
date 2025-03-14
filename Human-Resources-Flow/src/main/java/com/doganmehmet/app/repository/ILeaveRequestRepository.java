package com.doganmehmet.app.repository;

import com.doganmehmet.app.entity.Employee;
import com.doganmehmet.app.entity.LeaveRequest;
import com.doganmehmet.app.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ILeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    boolean existsByEmployeeAndStartDateAndEndDate(Employee employee, LocalDate startDate, LocalDate endDate);

    List<LeaveRequest> findAllByEmployee(Employee employee);

    List<LeaveRequest> findAllByLeaveStatus(LeaveStatus leaveStatus);
}
