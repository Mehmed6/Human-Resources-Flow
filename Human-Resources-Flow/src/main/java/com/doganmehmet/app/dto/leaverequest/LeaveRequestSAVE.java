package com.doganmehmet.app.dto.leaverequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LeaveRequestSAVE {

    @NotBlank(message = "Username cannot be empty!")
    private String username;
    @NotBlank(message = "Leave Reason cannot be empty!")
    private String leaveReason;
    @NotNull(message = "Start Date cannot be empty!")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @NotNull(message = "End Date cannot be empty!")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
