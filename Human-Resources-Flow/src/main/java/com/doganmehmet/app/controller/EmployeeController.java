package com.doganmehmet.app.controller;

import com.doganmehmet.app.dto.employee.EmployeeDTO;
import com.doganmehmet.app.dto.employee.EmployeeLeaveDateRequest;
import com.doganmehmet.app.dto.employee.EmployeeRequest;
import com.doganmehmet.app.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService m_employeeService;

    public EmployeeController(EmployeeService employeeService)
    {
        m_employeeService = employeeService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeDTO save(@Valid @RequestBody EmployeeRequest employeeRequest)
    {
        return m_employeeService.saveEmployee(employeeRequest);
    }

    @GetMapping("/find/id/{id}")
    public EmployeeDTO findById(@PathVariable long id)
    {
        return m_employeeService.findByEmployeeId(id);
    }

    @GetMapping("/find/username/{username}")
    public EmployeeDTO findByUsername(@PathVariable String username)
    {
        return m_employeeService.findByEmployeeUsername(username);
    }

    @GetMapping("/find/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<EmployeeDTO> findAll()
    {
        return m_employeeService.findAll();
    }

    @GetMapping("/find/department/{departmentId}")
    public List<EmployeeDTO> findByDepartmentId(@PathVariable long departmentId)
    {
        return m_employeeService.findAllEmployeesByDepartment(departmentId);
    }

    @GetMapping("/find/position/{positionId}")
    public List<EmployeeDTO> findByPositionId(@PathVariable long positionId)
    {
        return m_employeeService.findAllEmployeesByPosition(positionId);
    }

    @PostMapping("/find/leave/date")
    @PreAuthorize("hasRole('ADMIN')")
    public List<EmployeeDTO> findEmployeesOnLeaveByDayOfWeek(@Valid @RequestBody EmployeeLeaveDateRequest dateRequest)
    {
        return m_employeeService.findEmployeesOnLeaveByDayOfWeek(dateRequest);
    }
}
