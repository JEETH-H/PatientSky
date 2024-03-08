package dev.jeethesh.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.jeethesh.entites.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {
	List<Appointment> findAllByCalendarIdAndStartTimeBetween(String calendarId, LocalDateTime startTime, LocalDateTime endTime);
}