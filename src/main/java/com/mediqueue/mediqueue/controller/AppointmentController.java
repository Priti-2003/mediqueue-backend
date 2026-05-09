package com.mediqueue.mediqueue.controller;

import com.mediqueue.mediqueue.model.Appointment;
import com.mediqueue.mediqueue.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

    private final AppointmentService appointmentService;

    // Appointment book karo
    @PostMapping("/book")
    public ResponseEntity<?> book(
            @RequestBody Map<String, String> request) {
        try {
            Appointment apt = appointmentService
                    .bookAppointment(
                            Long.parseLong(request.get("patientId")),
                            Long.parseLong(request.get("doctorId")),
                            LocalDate.parse(request.get("date")),
                            request.get("notes")
                    );
            return ResponseEntity.ok(Map.of(
                    "message", "Appointment booked!",
                    "tokenNumber", apt.getTokenNumber(),
                    "appointmentId", apt.getId()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Patient ki appointments
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>>
    getPatientAppointments(
            @PathVariable Long patientId) {
        return ResponseEntity.ok(
                appointmentService
                        .getPatientAppointments(patientId));
    }

    // Doctor ki queue
    @GetMapping("/doctor/{doctorId}/queue")
    public ResponseEntity<List<Appointment>>
    getDoctorQueue(
            @PathVariable Long doctorId) {
        return ResponseEntity.ok(
                appointmentService.getDoctorQueue(doctorId));
    }

    // Cancel appointment
    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancel(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                appointmentService.cancelAppointment(id));
    }

    // Mark consulted
    @PutMapping("/consulted/{id}")
    public ResponseEntity<?> markConsulted(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                appointmentService.markConsulted(id));
    }
}
