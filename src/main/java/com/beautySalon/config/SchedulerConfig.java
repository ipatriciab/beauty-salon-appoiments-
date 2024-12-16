package com.beautySalon.config;

import com.beautySalon.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    private final AppointmentService appointmentService;

    @Autowired
    public SchedulerConfig(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * Programare zilnică pentru a trimite mementouri despre programări.
     */
    @Scheduled(cron = "0 0 9 * * ?") // La ora 9:00 zilnic
    public void sendReminders() {
        appointmentService.sendDailyReminders();
    }
}
