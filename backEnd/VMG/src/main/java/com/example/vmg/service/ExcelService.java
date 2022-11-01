package com.example.vmg.service;

import com.example.vmg.helper.ExcelHelper;
import com.example.vmg.model.Staff;
import com.example.vmg.respository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    @Autowired private StaffRepository repository;
    ExcelHelper excelHelper = new ExcelHelper();

    public void save(MultipartFile file) {
        try {
            List<Staff> staff = excelHelper.excelToTutorials(file.getInputStream());
            repository.saveAll(staff);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<Staff> getAllTutorials() {
        return repository.findAll();
    }
}
