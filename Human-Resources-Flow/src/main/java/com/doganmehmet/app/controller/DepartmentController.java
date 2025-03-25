package com.doganmehmet.app.controller;

import com.doganmehmet.app.dto.department.DepartmentDTO;
import com.doganmehmet.app.dto.department.DepartmentRequest;
import com.doganmehmet.app.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/department")
public class DepartmentController {
    private final DepartmentService m_departmentService;

    public DepartmentController(DepartmentService departmentService)
    {
        m_departmentService = departmentService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public DepartmentDTO saveDepartment(@Valid @RequestBody DepartmentRequest request)
    {
        return m_departmentService.save(request);
    }

    @GetMapping("/find/id/{departmentId}")
    public DepartmentDTO findById(@PathVariable long departmentId)
    {
        return m_departmentService.findDepartmentById(departmentId);
    }

    @GetMapping("/find/name/{departmentName}")
    public DepartmentDTO findByName(@PathVariable String departmentName)
    {
        return m_departmentService.findDepartmentByName(departmentName);
    }

    @GetMapping("/find/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<DepartmentDTO> findAll()
    {
        return m_departmentService.findAllDepartment();
    }

    @DeleteMapping("/delete/{departmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable long departmentId)
    {
        m_departmentService.deleteDepartmentById(departmentId);
    }
}
