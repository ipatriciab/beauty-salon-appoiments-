package com.beautySalon.config;

import com.beautySalon.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    // == fields ==
    private final AppointmentService appointmentService;

    // == constructor ==
    @Autowired
    public SchedulerConfig(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // will send reminders for today's appointments every 24 hours
    @Scheduled(cron = "0 0 9 * * ?") // Executes every day at 9:00 AM
    public void dailyReminderJob() {
        appointmentService.sendDailyReminders(); // Custom logic to send reminders
    }
}
