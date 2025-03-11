package com.doganmehmet.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "employees")
public class Employee extends User{

    private boolean blocked = false;
    @Column(name = "failed_login_attempts")
    private int failedLoginAttempts = 0;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Salary> salaries;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "leave_requests")
    private List<LeaveRequest> leaveRequests;
}
