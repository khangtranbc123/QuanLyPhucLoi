package com.example.vmg.respository;

import com.example.vmg.model.WelfareStaffEntity;
import com.example.vmg.model.WelfareStaffInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface WelfareStaffEntityRepository extends JpaRepository<WelfareStaffEntity, Integer> {
    @Transactional
    @Modifying
   @Query(value = "delete from welfare_staff where id_welfare = :id",nativeQuery = true)
    void deleteWelfareStaffEntitiesByIdWelfare(@Param("id") Long id);

    @Query(value = "select ws.id_welfare as idWelfare,ws.id_staff as idUser,ws.status,w.price,w.name,w.text,sum(ws.quantity) as quantity from welfare_staff ws,welfare w where w.id = ws.id_welfare and ws.status = 0 and ws.id_staff = ? group by ws.id_welfare,ws.id_staff,ws.status,w.price,w.name,w.text",nativeQuery = true)
    List<WelfareStaffInterface> getAllWelfareOfUser(@Param("id") Long id);
    //select 21,idWelfare,idStaff,status,sum(quantity) as quantity from WelfareStaffEntity where status = 0 and idStaff = :id group by idWelfare,idStaff,status
    @Query(value = "select ws.id_welfare as idWelfare,ws.id_staff as idUser,ws.status,w.price,w.name,w.text,sum(ws.quantity) as quantity from welfare_staff ws,welfare w where w.id = ws.id_welfare and ws.status != 1 and ws.id_staff = ? group by ws.id_welfare,ws.id_staff,ws.status,w.price,w.name,w.text",nativeQuery = true)
    List<WelfareStaffInterface> getWelfareOfUser(@Param("id") Long id);
}