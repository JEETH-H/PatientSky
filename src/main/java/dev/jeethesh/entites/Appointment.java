package dev.jeethesh.entites;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.UUID)
	 private String appointmentId;
	 
	 private String calendarId;

	 @Nonnull
	 @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	 private LocalDateTime startTime;
 
	 @Nonnull
	 @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	 private LocalDateTime endTime;
}
