/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.daos;

import com.billingsystem.database.Database;
import com.billingsystem.entities.Bills;
import com.billingsystem.entities.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author omega
 */
public class BillsDAO implements DAO{
   private final Connection conn = Database.getConnection();

    
    @Override
    public Object get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public ArrayList<Bills> getAll(String phone) {
        ArrayList<Bills> allBills = new ArrayList<>();
        String customerJoinRatePlanQuery = " select   sumtotal('"+phone+"',bill_id) as total,bill_id as billid , cdr.paid as paid , billdate as billdate from cdr where right(origin,11) ='"+phone+"' group by billdate ,bill_id ,cdr.paid;";
//        String customerJoinServiceQuery = "SELECT cs.*, s.* FROM cust_svc_addons cs, service s"
//                + "WHERE s.id = cs.service_id";
        try (
                Statement stmt1 = conn.createStatement();
//                Statement stmt2 = conn.createStatement()
            ) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
//            ResultSet rs2 = stmt2.executeQuery(customerJoinServiceQuery);
            while (rs1.next()) {
                Bills bill = new Bills();
                bill.setId(rs1.getInt("billid"));
                bill.setPaid(rs1.getBoolean("paid"));
                bill.setTotal(rs1.getFloat("total"));
                bill.setBill_date(rs1.getObject("billdate",LocalDateTime.class));
                System.out.println(rs1.getFloat("total"));
                allBills.add(bill);

            }
        } catch (SQLException ex) {
            System.out.println("##### Bills get all faild: \n" + ex.getMessage());
        }
        return allBills;
    }
    @Override
    public boolean save(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int saveAndReturnId(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    
    
    
}
