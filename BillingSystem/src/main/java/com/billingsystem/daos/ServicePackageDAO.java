/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.daos;

import com.billingsystem.database.Database;
import com.billingsystem.entities.Customer;
import com.billingsystem.entities.Service;
import com.billingsystem.entities.ServicePackage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author moham
 */
public class ServicePackageDAO implements DAO<ServicePackage> {

    private final Connection conn = Database.getConnection();

    @Override
    public ServicePackage get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ServicePackage> getAll() {
        ArrayList<ServicePackage> allServicePackage = new ArrayList<>();
        String customerJoinRatePlanQuery = "SELECT cust.*, cust.id AS cid, cust.name AS cname,"
                + " rp.name AS rpname, monthly_fees"
                + " FROM customer cust, rate_plan rp"
                + " WHERE cust.rate_plan_id = rp.id";
//        String customerJoinServiceQuery = "SELECT cs.*, s.* FROM cust_svc cs, service s"
//                + "WHERE s.id = cs.service_id";
        try (
                Statement stmt1 = conn.createStatement(); //                Statement stmt2 = conn.createStatement()
                ) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
//            ResultSet rs2 = stmt2.executeQuery(customerJoinServiceQuery);
            while (rs1.next()) {
                ServicePackage sp = new ServicePackage();
                sp.setId(rs1.getInt("id"));
//                customer.setName(rs1.getString("cname"));
//                customer.setEmail(rs1.getString("email"));
//                customer.setAddress(rs1.getString("address"));
//                customer.setPhone(rs1.getString("phone"));
//                customer.setNid(rs1.getString("nid"));
//                customer.setBillingDate(rs1.getDate("billing_date"));
//                customer.getRatePlan().setId(rs1.getInt("rate_plan_id"));
//                customer.getRatePlan().setName(rs1.getString("rpname"));
//                customer.getRatePlan().setMonthlyFees(rs1.getFloat("monthly_fees"));
//                while(rs2.next()){
//                    if(customer.getId() == rs2.getInt("cust_id")){
//                        Service service = new Service();
//                        service.setId(rs2.getInt("service_id"));
//                        service.setName(rs2.getString("name"));
//                        service.setRated(rs2.getBoolean("is_rated"));
//                        service.setType(rs2.getString("type"));
//                        rs2.deleteRow();
//                        customer.getAddOnServices().add(service);
//                    }
//                }
                allServicePackage.add(sp);

            }
        } catch (SQLException ex) {
            System.out.println("##### Customer get all faild: \n" + ex.getMessage());
        }
        return allServicePackage;
    }

    public ArrayList<ServicePackage> getAllServicePackage() {
        ArrayList<ServicePackage> allServicePackage = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from service";

        try (
                Statement stmt1 = conn.createStatement();) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            while (rs1.next()) {
                ServicePackage sp = new ServicePackage();
                sp.setId(rs1.getInt("id"));
                sp.getRatePlan().setId(rs1.getInt("rate_plan_id"));
                sp.getRatePlan().setName(rs1.getString("rpname"));
                sp.getService().setId(rs1.getInt("rate_plan_id"));
                sp.getService().setName(rs1.getString("rpname"));
                sp.getTimePackage().setId(rs1.getInt("rate_plan_id"));
                sp.getTimePackage().setName(rs1.getString("rpname"));
                sp.getTarrifZone().setId(rs1.getInt("rate_plan_id"));
                sp.getTarrifZone().setName(rs1.getString("rpname"));
                sp.setFree_units(rs1.getInt("id"));
                sp.setRate(rs1.getFloat("id"));
                

                allServicePackage.add(sp);

            }
        } catch (SQLException ex) {
            System.out.println("##### Service get all faild: \n" + ex.getMessage());
        }
        return allServicePackage;
    }

    @Override
    public boolean save(ServicePackage t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ServicePackage t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int saveAndReturnId(ServicePackage t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
