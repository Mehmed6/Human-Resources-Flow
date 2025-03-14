package com.doganmehmet.app.repository;

import com.doganmehmet.app.entity.Department;
import com.doganmehmet.app.entity.Employee;
import com.doganmehmet.app.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<Employee> findByUsername(String username);

    List<Employee> findAllByDepartment(Department department);

    List<Employee> findAllByPosition(Position position);

    @Query("SELECT DISTINCT lr.employee FROM LeaveRequest lr WHERE :date BETWEEN lr.startDate AND lr.endDate")
    List<Employee> findEmployeesOnLeaveOnDate(@Param("date") LocalDate date);


}
