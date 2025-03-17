package com.doganmehmet.app.service;

import com.doganmehmet.app.entity.Admin;
import com.doganmehmet.app.enums.LogType;
import com.doganmehmet.app.enums.Role;
import com.doganmehmet.app.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class AdminInitializer implements CommandLineRunner {

    private final IUserRepository m_userRepository;
    private final BCryptPasswordEncoder m_bCryptPasswordEncoder;
    private final LogEntryService m_logEntryService;

    public AdminInitializer(IUserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, LogEntryService logEntryService)
    {
        m_userRepository = userRepository;
        m_bCryptPasswordEncoder = bCryptPasswordEncoder;
        m_logEntryService = logEntryService;
    }

    @Override
    public void run(String... args)
    {
        if (!m_userRepository.existsByRole(Role.ADMIN)) {
            var admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword(m_bCryptPasswordEncoder.encode("admin"));
            admin.setEmail("admin@gmail.com");
            admin.setFirstName("Admin");
            admin.setLastName("Admin");
            admin.setRole(Role.ADMIN);
            admin.setPhone("00000000000");
            m_userRepository.save(admin);
            m_logEntryService.logger(admin.getUsername(), "Admin created", LogType.REGISTER);
            System.out.printf("Admin has been created. Username:%s password:%s", admin.getUsername(), admin.getPassword());

        }
    }
}
