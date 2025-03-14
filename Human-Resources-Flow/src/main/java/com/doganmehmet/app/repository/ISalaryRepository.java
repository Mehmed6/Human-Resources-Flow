package com.doganmehmet.app.repository;

import com.doganmehmet.app.entity.Employee;
import com.doganmehmet.app.entity.Salary;
import com.doganmehmet.app.enums.SalaryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISalaryRepository extends JpaRepository<Salary, Long> {
    List<Salary> findAllByEmployee(Employee employee);

    List<Salary> findAllBySalaryType(SalaryType salaryType);

    List<Salary> findAllBySalaryBetween(double salaryAfter, double salaryBefore);

    List<Salary> findAllBySalaryGreaterThan(double salaryIsGreaterThan);

    List<Salary> findAllBySalaryLessThan(double salaryIsLessThan);
}
