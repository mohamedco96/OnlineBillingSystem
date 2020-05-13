/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.daos;

import com.billingsystem.database.Database;
import com.billingsystem.entities.Customer;
import com.billingsystem.entities.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author THE PR!NCE
 */
public class CustomerDAO implements DAO<Customer>{
    
    private final Connection conn = Database.getConnection();

    @Override
    public ArrayList<Customer> getAll() {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        String customerJoinRatePlanQuery = "SELECT cust.*, cust.id AS cid, cust.name AS cname,"
                + " rp.name AS rpname, monthly_fees"
                + " FROM customer cust, rate_plan rp"
                + " WHERE cust.rate_plan_id = rp.id";
//        String customerJoinServiceQuery = "SELECT cs.*, s.* FROM cust_svc cs, service s"
//                + "WHERE s.id = cs.service_id";
        try (
                Statement stmt1 = conn.createStatement();
//                Statement stmt2 = conn.createStatement()
            ) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
//            ResultSet rs2 = stmt2.executeQuery(customerJoinServiceQuery);
            while (rs1.next()) {
                Customer customer = new Customer();
                customer.setId(rs1.getInt("id"));
                customer.setName(rs1.getString("cname"));
                customer.setEmail(rs1.getString("email"));
                customer.setAddress(rs1.getString("address"));
                customer.setPhone(rs1.getString("phone"));
                customer.setNid(rs1.getString("nid"));
                customer.setBillingDate(rs1.getDate("billing_date"));
                customer.getRatePlan().setId(rs1.getInt("rate_plan_id"));
                customer.getRatePlan().setName(rs1.getString("rpname"));
                customer.getRatePlan().setMonthlyFees(rs1.getFloat("monthly_fees"));
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
                allCustomers.add(customer);

            }
        } catch (SQLException ex) {
            System.out.println("##### Customer get all faild: \n" + ex.getMessage());
        }
        return allCustomers;
    }
    
    @Override
    public boolean save(Customer customer) {
        boolean operationSuccess = true;
        String sqlCommand = "INSERT INTO customer (name, nid, phone, address, email, rate_plan_id) VALUES(?, ?, ?, ?, ?, ?)";

        try(PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setString(1, customer.getName());
            preparedStatment.setString(2, customer.getNid());
            preparedStatment.setString(3, customer.getPhone());
            preparedStatment.setString(4, customer.getAddress());
            preparedStatment.setString(5, customer.getEmail());
            preparedStatment.setInt(6, customer.getRatePlan().getId());
//            preparedStatment.setDate(7, (Date) customer.getBillingDate());
//            System.out.println(customer.getBillingDate());
            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### Customer insert faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }
    
    public Customer searchByPhone(String phone){
        Customer customer = new Customer();
//        String customerJoinRatePlanQuery="select * from customer where phone= '" + phone + "'";
        String customerJoinRatePlanQuery = "SELECT cust.*, cust.id AS cid, cust.name AS cname,"
                + " rp.name AS rpname, monthly_fees"
                + " FROM customer cust, rate_plan rp"
                + " WHERE cust.rate_plan_id = rp.id AND phone = '" + phone + "'";

        try (
                Statement stmt1 = conn.createStatement();
                Statement stmt2 = conn.createStatement();
            ) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            if (rs1.next()) {
                customer.setId(rs1.getInt("id"));
                customer.setName(rs1.getString("cname"));
                customer.setPhone(rs1.getString("phone"));
                customer.setEmail(rs1.getString("email"));
                customer.setAddress(rs1.getString("address"));
                customer.setNid(rs1.getString("nid"));
                customer.setBillingDate(rs1.getDate("billing_date"));
                customer.getRatePlan().setId(rs1.getInt("rate_plan_id"));
                customer.getRatePlan().setName(rs1.getString("rpname"));
                customer.getRatePlan().setMonthlyFees(rs1.getFloat("monthly_fees"));
                
                String customerJoinServiceQuery = "SELECT cs.*, s.* FROM cust_svc cs, service s "
                + " WHERE s.id = cs.service_id AND cust_id = " + customer.getId();
                ResultSet rs2 = stmt2.executeQuery(customerJoinServiceQuery);
                while(rs2.next()){
                    Service service = new Service();
                    service.setId(rs2.getInt("service_id"));
                    service.setName(rs2.getString("name"));
                    service.setRated(rs2.getBoolean("is_rated"));
                    service.setType(rs2.getString("type"));
                    customer.getAddOnServices().add(service);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("##### Customer search by phone faild: \n" + ex.getMessage());
        }
        return customer;
    }

    @Override
    public Customer get(int id) {
        Customer customer = new Customer();
        String customerJoinRatePlanQuery = "SELECT cust.*, cust.id AS cid, cust.name AS cname,"
                + " rp.name AS rpname, monthly_fees"
                + " FROM customer cust, rate_plan rp"
                + " WHERE cust.rate_plan_id = rp.id AND cid = " + id;

        try (
                Statement stmt1 = conn.createStatement();
                Statement stmt2 = conn.createStatement()
            ) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            if (rs1.next()) {
                customer.setId(rs1.getInt("id"));
                customer.setName(rs1.getString("cname"));
                customer.setEmail(rs1.getString("email"));
                customer.setAddress(rs1.getString("address"));
                customer.setNid(rs1.getString("nid"));
                customer.setBillingDate(rs1.getDate("address"));
                customer.getRatePlan().setId(rs1.getInt("rate_plan_id"));
                customer.getRatePlan().setName(rs1.getString("rpname"));
                customer.getRatePlan().setMonthlyFees(rs1.getFloat("monthly_fees"));
                
                String customerJoinServiceQuery = "SELECT cs.*, s.* FROM cust_svc cs, service s"
                + "WHERE s.id = cs.service_id AND cust_id = " + id;
                ResultSet rs2 = stmt2.executeQuery(customerJoinServiceQuery);
                while(rs2.next()){
                    Service service = new Service();
                    service.setId(rs2.getInt("service_id"));
                    service.setName(rs2.getString("name"));
                    service.setRated(rs2.getBoolean("is_rated"));
                    service.setType(rs2.getString("type"));
                    customer.getAddOnServices().add(service);
                }
            }
        } catch (SQLException ex) {
            System.out.println("##### Customer get by id faild: \n" + ex.getMessage());
        }
        return customer;    }
    
    @Override
    public boolean update(Customer t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int saveAndReturnId(Customer t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
