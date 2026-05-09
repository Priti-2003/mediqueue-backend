package com.mediqueue.mediqueue.repository;

import com.mediqueue.mediqueue.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DoctorRepository
        extends JpaRepository<Doctor, Long> {

    List<Doctor> findByDepartmentId(Long deptId);
    List<Doctor> findByAvailableTrue();
}
