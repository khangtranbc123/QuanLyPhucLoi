package com.example.vmg.StaffRole;

import com.example.vmg.form.WelfareForm;
import com.example.vmg.model.Welfare;
import com.example.vmg.model.WelfareStaffEntity;
import com.example.vmg.respository.WelfareRepository;
import com.example.vmg.respository.WelfareStaffEntityRepository;
import com.example.vmg.security.jwt.JwtProvider;
import com.example.vmg.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class StaffRoleController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private WelfareService welfareService;
    @Autowired
    private GeneralWelfareService generalWelfareService;
    @Autowired private WelfareStaffEntityService welfareStaffEntityService;
    @Autowired
    private WelfareStaffEntityRepository welfareStaffEntityRepository;
    @PostMapping("/regis_welfare")
    public ResponseEntity<Void> createTutorial(@RequestBody AddWelfare addWelfareRequest) {
        try {
            BigDecimal sum = BigDecimal.valueOf(Long.valueOf("0"));
            for(AddWelfareQuantity wid: addWelfareRequest.getList()){

                //System.out.println(welfareService.getById(wid.getId()).getPrice().multiply(BigDecimal.valueOf(wid.getQuantity())));
                sum = sum.add(welfareService.getById(wid.getId()).getPrice().multiply(BigDecimal.valueOf(wid.getQuantity())));
                System.out.println(sum);
            }
            System.out.println(BigDecimal.valueOf(generalWelfareService.getAllMoneyGeneral()));
            sum = sum.add(BigDecimal.valueOf(generalWelfareService.getAllMoneyGeneral()));
            System.out.println(sum);
            System.out.println(welfareService.getAllMoney((long) addWelfareRequest.getId()));
            for(int i = 0;i < welfareStaffEntityService.getAlsoWelfareOfUser((long) addWelfareRequest.getId()).size(); i++){
                BigDecimal quantity = BigDecimal.valueOf(welfareStaffEntityService.getAlsoWelfareOfUser((long) addWelfareRequest.getId()).get(i).getQuantity());
                sum = sum.add(welfareStaffEntityService.getAlsoWelfareOfUser((long) addWelfareRequest.getId()).get(i).getPrice().multiply(quantity));
             }
            System.out.println(sum);
            if(sum.compareTo(welfareService.getAllMoney((long) addWelfareRequest.getId())) == 1){
                return new ResponseEntity<>(HttpStatus.OK);
            }

            for (AddWelfareQuantity wid: addWelfareRequest.getList()) {
                welfareStaffEntityRepository.save(new WelfareStaffEntity(2, Long.valueOf(wid.getId()),Long.valueOf(addWelfareRequest.getId()),Long.valueOf(wid.getQuantity())));
            }
            return new ResponseEntity<>( HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/find_id/{username}")
    public Long getIdOfAccount(@PathVariable String username){
        return staffService.getByEmail(username).getId();
    }

    @GetMapping("/find_money/{username}")
    public BigDecimal getMoneyOfAccount(@PathVariable String username){
        return staffService.getByEmail(username).getWelfareMoney();
    }


}
