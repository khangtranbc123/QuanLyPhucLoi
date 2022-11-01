package com.example.vmg.respository;

import com.example.vmg.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Department getDepartmentByName(String name);
}
