package com.doganmehmet.app.mapper;

import com.doganmehmet.app.dto.leaverequest.LeaveRequestDTO;
import com.doganmehmet.app.dto.leaverequest.LeaveRequestSAVE;
import com.doganmehmet.app.entity.LeaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(implementationName = "LeaveRequestMapperImpl", componentModel = "spring")
public interface ILeaveRequestMapper {

    LeaveRequest toLeaveRequest(LeaveRequestSAVE leaveRequestSAVE);

    @Mapping(source = "employee.position.department", target = "employee.position.department", ignore = true)
    LeaveRequestDTO toLeaveRequestDTO(LeaveRequest leaveRequest);

    List<LeaveRequestDTO> toLeaveRequestDTOList(List<LeaveRequest> leaveRequestList);
}
