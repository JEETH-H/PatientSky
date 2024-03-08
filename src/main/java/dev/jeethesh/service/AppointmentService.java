package dev.jeethesh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.jeethesh.entites.Appointment;
import dev.jeethesh.repository.AppointmentRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void saveAppointments(List<Appointment> appointments) {
        appointmentRepository.saveAll(appointments);
    }
    
    public List<Map<String, String>> findAvailableSlots(String calendarId, Integer duration, LocalDateTime startTime, LocalDateTime endTime) {
        List<Appointment> appointments = appointmentRepository.findAllByCalendarIdAndStartTimeBetween(
                calendarId, startTime, endTime);

        List<Map<String, String>> availableSlots = new ArrayList<>();

        // Assuming the appointments list is sorted by start time
        LocalDateTime currentSlotStart = startTime;

        for (Appointment appointment : appointments) {
            LocalDateTime appointmentStart = appointment.getStartTime();
            LocalDateTime appointmentEnd = appointment.getEndTime();

            // Check for a gap between appointments
            if (Duration.between(currentSlotStart, appointmentStart).toMinutes() >= duration) {
                Map<String, String> slot = new HashMap<>();
                slot.put("startTime", currentSlotStart.toString());
                slot.put("endTime", currentSlotStart.plusMinutes(duration).toString());
                availableSlots.add(slot);
            }

            currentSlotStart = appointmentEnd;
        }

        // Check for a gap after the last appointment
        if (Duration.between(currentSlotStart, endTime).toMinutes() >= duration) {
            Map<String, String> slot = new HashMap<>();
            slot.put("startTime", currentSlotStart.toString());
            slot.put("endTime", currentSlotStart.plusMinutes(duration).toString());
            availableSlots.add(slot);
        }

        return availableSlots;
    }

}
