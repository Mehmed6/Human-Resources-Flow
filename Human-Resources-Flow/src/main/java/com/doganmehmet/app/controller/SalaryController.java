package com.doganmehmet.app.controller;

import com.doganmehmet.app.dto.salary.SalaryDTO;
import com.doganmehmet.app.dto.salary.SalaryRequest;
import com.doganmehmet.app.enums.SalaryType;
import com.doganmehmet.app.service.SalaryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salary")
@PreAuthorize("hasRole('ADMIN')")
public class SalaryController {
    private final SalaryService m_salaryService;

    public SalaryController(SalaryService salaryService)
    {
        m_salaryService = salaryService;
    }

    @PostMapping("/save")
    public SalaryDTO save(@RequestBody SalaryRequest salaryRequest)
    {
        return m_salaryService.saveSalary(salaryRequest);
    }

    @GetMapping("/find/all")
    public List<SalaryDTO> findAll()
    {
        return m_salaryService.findAllSalary();
    }

    @GetMapping("/find/employee/salaries/{employeeId}")
    public List<SalaryDTO> findByEmployeeId(@PathVariable long employeeId)
    {
        return m_salaryService.findSalaryByEmployeeId(employeeId);
    }

    @GetMapping("/find/type/{salaryType}")
    public List<SalaryDTO> findBySalaryType(@PathVariable SalaryType salaryType)
    {
        return m_salaryService.findSalaryBySalaryType(salaryType);
    }

    @GetMapping("/find/between")
    public List<SalaryDTO> findBetween(@RequestParam double min,@RequestParam double max)
    {
        return m_salaryService.findSalaryBetween(min, max);
    }

    @GetMapping("/find/greater")
    public List<SalaryDTO> findGreaterThan(@RequestParam double value)
    {
        return m_salaryService.findSalaryGreaterThan(value);
    }

    @GetMapping("/find/less")
    public List<SalaryDTO> findLessThan(@RequestParam double value)
    {
        return m_salaryService.findSalaryLessThan(value);
    }
}
