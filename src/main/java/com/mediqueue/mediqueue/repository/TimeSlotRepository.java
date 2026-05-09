package com.mediqueue.mediqueue.repository;

import com.mediqueue.mediqueue.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TimeSlotRepository
        extends JpaRepository<TimeSlot, Long> {

    List<TimeSlot> findByDoctorId(Long doctorId);
    List<TimeSlot> findByDoctorIdAndIsActiveTrue(
            Long doctorId);
}
