package com.doganmehmet.app.entity;

import com.doganmehmet.app.enums.LogType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "log_entry")
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_entry_id")
    private long logEntryId;

    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    @Column(name = "performed_by")
    private String performedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "log_type")
    private LogType logType;
}
