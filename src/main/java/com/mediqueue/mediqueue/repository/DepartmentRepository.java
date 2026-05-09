package com.mediqueue.mediqueue.repository;

import com.mediqueue.mediqueue.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository
        extends JpaRepository<Department, Long> {
}
