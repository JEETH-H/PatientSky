package dev.jeethesh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.jeethesh.entites.Appointment;
import dev.jeethesh.service.AppointmentService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    
    // End point to save a list of appointments
    @PostMapping("/appointments")
    public ResponseEntity<String> saveAppointments(@RequestBody List<Appointment> appointments) {
        appointmentService.saveAppointments(appointments);
        return ResponseEntity.ok("Appointments saved successfully");
    }

    // End point to check the available slots
    @GetMapping("/available-slots")
    public ResponseEntity<List<Map<String, String>>> getAvailableSlots(
            @RequestParam String calendarId,
            @RequestParam Integer duration,
            @RequestParam String startTime,
            @RequestParam String endTime
    ) {
        LocalDateTime startDateTime = LocalDateTime.parse(startTime);
        LocalDateTime endDateTime = LocalDateTime.parse(endTime);

        List<Map<String, String>> availableSlots = appointmentService.findAvailableSlots(calendarId, duration, startDateTime, endDateTime);

        if (availableSlots.isEmpty()) {
            Map<String, String> result = new HashMap<>();
            result.put("message", "No slots available");
            availableSlots.add(result);
        } else {
            Map<String, String> result = new HashMap<>();
            result.put("message", "Requested time frame is available");
            availableSlots.add(result);
        }

        return ResponseEntity.ok(availableSlots);
    }
}
