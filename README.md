
# PatientSky

This is the application for Booking the available slot for Doctor Appointment. It is developed using Java and the Spring Boot framework.


## Usage
Patient can check the available slot for booking Doctor Appointment.

If the Slot for Appointment is available then Patient can book that slot, else Application will show the message slot not available for requested time frame

### Checking Available Slots

To check the available slot Application uses /available-slots endpoint.

This endpoint is accessed via `@GetMapping`, and User must pass the parameters
(calanderId, Duration in minutes, startTime, endTime)

Note: Here startTime must be less than the endTime

### Saving Appointments

To save the Appointment Application uses /appointments endpoint.

This endpoint is accessed via `@PostMapping`, and User must pass doctorId, startTime, endTime

Note: startTime must be less than the endTime
