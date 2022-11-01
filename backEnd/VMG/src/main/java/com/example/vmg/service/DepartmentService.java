package com.example.vmg.service;

import com.example.vmg.model.Department;
import com.example.vmg.respository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department getById(Long id) {
        return departmentRepository.findById(id).get();
    }
    public Department getByName(String name) {
        return departmentRepository.getDepartmentByName(name);
    }
}
