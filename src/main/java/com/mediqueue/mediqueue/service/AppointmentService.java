package com.mediqueue.mediqueue.service;

import com.mediqueue.mediqueue.model.*;
import com.mediqueue.mediqueue.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    // Token number generate karo
    public int generateToken(Long doctorId,
                             LocalDate date) {
        int count = appointmentRepository
                .countByDoctorIdAndAppointmentDate(
                        doctorId, date);
        return count + 1;
    }

    // Appointment book karo
    public Appointment bookAppointment(Long patientId,
                                       Long doctorId,
                                       LocalDate date,
                                       String notes) {
        User patient = userRepository.findById(patientId)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found!"));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new RuntimeException("Doctor not found!"));

        int token = generateToken(doctorId, date);

        Appointment appointment = Appointment.builder()
                .patient(patient)
                .doctor(doctor)
                .appointmentDate(date)
                .tokenNumber(token)
                .patientNotes(notes)
                .status(Appointment.AppointmentStatus.BOOKED)
                .build();

        return appointmentRepository.save(appointment);
    }

    // Patient ki appointments dekho
    public List<Appointment> getPatientAppointments(
            Long patientId) {
        return appointmentRepository
                .findByPatientId(patientId);
    }

    // Doctor ki aaj ki queue dekho
    public List<Appointment> getDoctorQueue(
            Long doctorId) {
        return appointmentRepository
                .findByDoctorIdAndAppointmentDate(
                        doctorId, LocalDate.now());
    }

    // Appointment cancel karo
    public Appointment cancelAppointment(Long id) {
        Appointment apt = appointmentRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Not found!"));
        apt.setStatus(
                Appointment.AppointmentStatus.CANCELLED);
        return appointmentRepository.save(apt);
    }

    // Doctor next patient ko consulted mark kare
    public Appointment markConsulted(Long id) {
        Appointment apt = appointmentRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Not found!"));
        apt.setStatus(
                Appointment.AppointmentStatus.CONSULTED);
        return appointmentRepository.save(apt);
    }
}
