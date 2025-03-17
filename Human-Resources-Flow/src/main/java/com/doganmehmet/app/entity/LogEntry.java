package com.doganmehmet.app.entity;

import com.doganmehmet.app.enums.LogType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@Table(name = "log_entry")
@NoArgsConstructor
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_entry_id")
    private long logEntryId;

    private String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private String message;
    @Column(name = "performed_by")
    private String performedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "log_type")
    private LogType logType;

    public LogEntry(String message, String performedBy, LogType logType)
    {
        this.message = message;
        this.performedBy = performedBy;
        this.logType = logType;
    }
}
