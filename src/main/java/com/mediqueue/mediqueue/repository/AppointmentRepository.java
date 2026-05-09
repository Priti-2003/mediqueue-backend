package com.mediqueue.mediqueue.repository;

import com.mediqueue.mediqueue.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorIdAndAppointmentDate(
            Long doctorId, LocalDate date);
    int countByDoctorIdAndAppointmentDate(
            Long doctorId, LocalDate date);
}
