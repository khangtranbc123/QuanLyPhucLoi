package com.example.vmg.service;

import com.example.vmg.model.WelfareStaffEntity;
import com.example.vmg.model.WelfareStaffInterface;
import com.example.vmg.respository.WelfareStaffEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WelfareStaffEntityService {
    @Autowired
    private WelfareStaffEntityRepository welfareStaffEntityRepository;
    public void deleteByWelfareId(Long id){welfareStaffEntityRepository.deleteWelfareStaffEntitiesByIdWelfare(id);}
    public List<WelfareStaffInterface> getAllWelfareOfUser(Long id){return welfareStaffEntityRepository.getAllWelfareOfUser(id);}
    public List<WelfareStaffInterface> getAlsoWelfareOfUser(Long id){return welfareStaffEntityRepository.getWelfareOfUser(id);}
}
