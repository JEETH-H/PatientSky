package dev.jeethesh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.jeethesh.entites.Appointment;
import dev.jeethesh.service.AppointmentService;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    	 try {
             appointmentService.saveAppointments(appointments);
             return ResponseEntity.ok("Appointments saved successfully");
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
         }
    }

    // End point to check the available slots
    @GetMapping("/available-slots")
    public ResponseEntity<List<Map<String, String>>> getAvailableSlots(
            @RequestParam String calendarId,
            @RequestParam Integer duration,
            @RequestParam String startTime,
            @RequestParam String endTime
    ) {
        try {
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
        } catch (Exception e) {
            List<Map<String, String>> errorResponse = new ArrayList<>();
            Map<String, String> errorResult = new HashMap<>();
            errorResult.put("error", "An error occurred: " + e.getMessage());
            errorResponse.add(errorResult);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
