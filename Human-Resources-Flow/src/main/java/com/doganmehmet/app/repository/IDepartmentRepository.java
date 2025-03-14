package com.doganmehmet.app.repository;

import com.doganmehmet.app.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByDepartmentName(String departmentName);

    Optional<Department> findByDepartmentId(long departmentId);
}
