package com.doganmehmet.app.service;

import com.doganmehmet.app.dto.leaverequest.LeaveRequestDTO;
import com.doganmehmet.app.dto.leaverequest.LeaveRequestSAVE;
import com.doganmehmet.app.enums.LeaveStatus;
import com.doganmehmet.app.exception.ApiException;
import com.doganmehmet.app.exception.MyError;
import com.doganmehmet.app.mapper.IEmployeeMapper;
import com.doganmehmet.app.mapper.ILeaveRequestMapper;
import com.doganmehmet.app.repository.IEmployeeRepository;
import com.doganmehmet.app.repository.ILeaveRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestService {
    private final ILeaveRequestRepository m_leaveRequestRepository;
    private final ILeaveRequestMapper m_leaveRequestMapper;
    private final IEmployeeRepository m_employeeRepository;
    private final IEmployeeMapper m_employeeMapper;

    public LeaveRequestService(ILeaveRequestRepository leaveRequestRepository, ILeaveRequestMapper leaveRequestMapper, IEmployeeRepository employeeRepository, IEmployeeMapper employeeMapper)
    {
        m_leaveRequestRepository = leaveRequestRepository;
        m_leaveRequestMapper = leaveRequestMapper;
        m_employeeRepository = employeeRepository;
        m_employeeMapper = employeeMapper;
    }

    private boolean isValidLeaveStatus(String leaveStatus)
    {
        for (var status : LeaveStatus.values())
            if (status.name().equalsIgnoreCase(leaveStatus))
                return true;

        return false;
    }

    public LeaveRequestDTO saveLeaveRequest(LeaveRequestSAVE saveRequest)
    {
        var employee = m_employeeRepository.findByUsername(saveRequest.getUsername())
                .orElseThrow(() -> new ApiException(MyError.USER_NOT_FOUND));

        var startDate = saveRequest.getStartDate();
        var endDate = saveRequest.getEndDate();

        var check = m_leaveRequestRepository.existsByEmployeeAndStartDateAndEndDate(employee, startDate, endDate);

        if (check)
            throw new ApiException(MyError.LEAVE_REQUEST_ALREADY_EXISTS);

        var leaveRequest = m_leaveRequestMapper.toLeaveRequest(saveRequest);
        leaveRequest.setEmployee(employee);
        leaveRequest.setLeaveStatus(LeaveStatus.PENDING);

        return m_leaveRequestMapper.toLeaveRequestDTO(m_leaveRequestRepository.save(leaveRequest));
    }

    public List<LeaveRequestDTO> findAllLeaveRequestByUser(String username)
    {
        var employee = m_employeeRepository.findByUsername(username)
                .orElseThrow(() -> new ApiException(MyError.USER_NOT_FOUND));

        return m_leaveRequestMapper.toLeaveRequestDTOList(m_leaveRequestRepository.findAllByEmployee(employee));
    }

    public List<LeaveRequestDTO> findAllLeaveRequest()
    {
        return m_leaveRequestMapper.toLeaveRequestDTOList(m_leaveRequestRepository.findAll());
    }

    public List<LeaveRequestDTO> findAllByLeaveStatus(String leaveStatus)
    {
        if (!isValidLeaveStatus(leaveStatus))
            throw new ApiException(MyError.LEAVE_REQUEST_NOT_FOUND);

        var status = LeaveStatus.valueOf(leaveStatus.toUpperCase());

        return m_leaveRequestMapper.toLeaveRequestDTOList(m_leaveRequestRepository.findAllByLeaveStatus(status));
    }

    public LeaveRequestDTO approvedLeaveRequestId(long leaveRequestId)
    {
        var leaveRequest = m_leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new ApiException(MyError.LEAVE_REQUEST_NOT_FOUND));

        leaveRequest.setLeaveStatus(LeaveStatus.APPROVED);
        m_leaveRequestRepository.save(leaveRequest);
        return m_leaveRequestMapper.toLeaveRequestDTO(leaveRequest);
    }

    public LeaveRequestDTO rejectedLeaveRequestId(long leaveRequestId)
    {
        var leaveRequest = m_leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new ApiException(MyError.LEAVE_REQUEST_NOT_FOUND));

        leaveRequest.setLeaveStatus(LeaveStatus.REJECTED);
        m_leaveRequestRepository.save(leaveRequest);
        return m_leaveRequestMapper.toLeaveRequestDTO(leaveRequest);
    }
}
