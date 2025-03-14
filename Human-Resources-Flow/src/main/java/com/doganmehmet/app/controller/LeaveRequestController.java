package com.doganmehmet.app.controller;

import com.doganmehmet.app.dto.leaverequest.LeaveRequestDTO;
import com.doganmehmet.app.dto.leaverequest.LeaveRequestSAVE;
import com.doganmehmet.app.service.LeaveRequestService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee/leave/request")
public class LeaveRequestController {
    private final LeaveRequestService m_leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService)
    {
        m_leaveRequestService = leaveRequestService;
    }

    @PostMapping("/save")
    public LeaveRequestDTO save(@Valid @RequestBody LeaveRequestSAVE leaveRequestSAVE)
    {
        return m_leaveRequestService.saveLeaveRequest(leaveRequestSAVE);
    }

    @GetMapping("/find/all/username/{username}")
    public List<LeaveRequestDTO> findAllByUsername(@PathVariable String username)
    {
        return m_leaveRequestService.findAllLeaveRequestByUser(username);
    }

    @GetMapping("/find/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<LeaveRequestDTO> findAll()
    {
        return m_leaveRequestService.findAllLeaveRequest();
    }

    @GetMapping("/find/all/status/{status}")
    public List<LeaveRequestDTO> findAllByStatus(@PathVariable String status)
    {
        return m_leaveRequestService.findAllByLeaveStatus(status);
    }

    @PatchMapping("/approved/{leaveRequestId}")
    @PreAuthorize("hasRole('ADMIN')")
    public LeaveRequestDTO approvedStatus(@PathVariable long leaveRequestId)
    {
        return m_leaveRequestService.approvedLeaveRequestId(leaveRequestId);
    }

    @PatchMapping("/rejected/{leaveRequestId}")
    @PreAuthorize("hasRole('ADMIN')")
    public LeaveRequestDTO rejectedStatus(@PathVariable long leaveRequestId)
    {
        return m_leaveRequestService.rejectedLeaveRequestId(leaveRequestId);
    }
}
