/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.daos;

import com.billingsystem.database.Database;
import com.billingsystem.entities.Customer;
import com.billingsystem.entities.*;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 *
 * @author THE PR!NCE
 */
public class CustomerDAO implements DAO<Customer> {

    private final Connection conn = Database.getConnection();

    @Override
    public ArrayList<Customer> getAll() {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        String customerJoinRatePlanQuery = "SELECT cust.*, cust.id AS cid, cust.name AS cname,"
                + " rp.name AS rpname, monthly_fees"
                + " FROM customer cust, rate_plan rp"
                + " WHERE cust.rate_plan_id = rp.id order by id";
//        String customerJoinServiceQuery = "SELECT cs.*, s.* FROM cust_svc_addons cs, service s"
//                + "WHERE s.id = cs.service_id";
        try (
                Statement stmt1 = conn.createStatement(); //                Statement stmt2 = conn.createStatement()
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
                customer.setBillingDate(rs1.getObject("billing_date", LocalDate.class));
                customer.getRatePlan().setId(rs1.getInt("rate_plan_id"));
                customer.getRatePlan().setName(rs1.getString("rpname"));
                customer.getRatePlan().setMonthlyFees(rs1.getFloat("monthly_fees"));
//                while(rs2.next()){
//                    if(customer.getId() == rs2.getInt("cust_id")){
//                        Service service = new Service();
//                        service.setId(rs2.getInt("service_id"));
//                        service.setName(rs2.getString("name"));
//                        service.setRated(rs2.getBoolean("rating"));
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
        boolean paid;
        int newRecordId, billId;
        LocalDate billdate;
        String sqlCommand = "INSERT INTO customer (name, nid, phone, address, email, rate_plan_id, billing_date) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatment.setString(1, customer.getName());
            preparedStatment.setString(2, customer.getNid());
            preparedStatment.setString(3, customer.getPhone());
            preparedStatment.setString(4, customer.getAddress());
            preparedStatment.setString(5, customer.getEmail());
            preparedStatment.setInt(6, customer.getRatePlan().getId());
            preparedStatment.setObject(7, customer.getBillingDate());
//            System.out.println(customer.getBillingDate());
            preparedStatment.executeUpdate();

            ResultSet generatedKeys = preparedStatment.getGeneratedKeys();
            generatedKeys.next();
            newRecordId = generatedKeys.getInt(1);
            billId = generatedKeys.getInt(7);
            paid = generatedKeys.getBoolean(8);
//            billdate = generatedKeys.getObject(10, LocalDate.class));
            saveServicePackage(newRecordId, customer.getAddOnServices().get(0), customer.getAddOnServices().get(2), billId, paid);
            saveServicePackage(newRecordId, customer.getAddOnServices().get(1), customer.getAddOnServices().get(3), billId, paid);
        } catch (SQLException ex) {
            System.out.println("##### Customer insert faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }

    public boolean saveServicePackage(int customerId, ExtraService es, ExtraService es2, int billId, boolean paid) {
        boolean operationSuccess = true;
        Customer c = new Customer();
        String sqlCommand = "insert into cust_svc_addons(cust_id,service_id,cost,bill_id,paid) values(?,?,?,?,?)";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setInt(1, customerId);
            preparedStatment.setInt(2, es.getService().getId());
            preparedStatment.setFloat(3, es2.getCost());
            preparedStatment.setFloat(4, billId);
            preparedStatment.setBoolean(5, paid);

            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### ServicePackage insert faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }

    public Customer searchByPhone(String phone) {
        Customer customer = new Customer();
//        String customerJoinRatePlanQuery="select * from customer where phone= '" + phone + "'";
        String customerJoinRatePlanQuery = "SELECT cust.*, cust.id AS cid, cust.name AS cname,"
                + " rp.name AS rpname, monthly_fees"
                + " FROM customer cust, rate_plan rp"
                + " WHERE cust.rate_plan_id = rp.id AND right(phone,11) = right('" + phone + "',11)";

        try (
                Statement stmt1 = conn.createStatement();
                Statement stmt2 = conn.createStatement();) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            if (rs1.next()) {
                customer.setId(rs1.getInt("id"));
                customer.setName(rs1.getString("cname"));
                customer.setPhone(rs1.getString("phone"));
                customer.setEmail(rs1.getString("email"));
                customer.setAddress(rs1.getString("address"));
                customer.setNid(rs1.getString("nid"));
                customer.setBillingDate(rs1.getObject("billing_date", LocalDate.class));
                customer.getRatePlan().setId(rs1.getInt("rate_plan_id"));
                customer.getRatePlan().setName(rs1.getString("rpname"));
                customer.getRatePlan().setMonthlyFees(rs1.getFloat("monthly_fees"));

                String customerJoinServiceQuery = "SELECT cs.*, s.* FROM cust_svc_addons cs, service s "
                        + " WHERE s.id = cs.service_id AND cust_id = " + customer.getId();
                ResultSet rs2 = stmt2.executeQuery(customerJoinServiceQuery);
                while (rs2.next()) {
                    ExtraService extraService = new ExtraService();
                    extraService.getService().setId(rs2.getInt("service_id"));
                    extraService.getService().setName(rs2.getString("name"));
                    extraService.getService().setRated(rs2.getBoolean("rating"));
                    extraService.getService().setType(rs2.getString("type"));
                    extraService.setCost(rs2.getFloat("cost"));
                    extraService.setPaid(rs2.getBoolean("paid"));
                    extraService.setBillDate(rs2.getObject("billdate", LocalDateTime.class));

                    customer.getAddOnServices().add(extraService);
                }
            }
        } catch (SQLException ex) {
            System.out.println("##### Customer search by phone faild: \n" + ex.getMessage());
        }
        return customer;
    }

    @Override
    public Customer get(int id) {
        Customer customer = new Customer();
        String customerJoinRatePlanQuery = "SELECT cust.*, cust.name AS cname,"
                + " rp.name AS rpname, monthly_fees"
                + " FROM customer cust, rate_plan rp"
                + " WHERE cust.rate_plan_id = rp.id AND cust.id = " + id;

        try (
                Statement stmt1 = conn.createStatement();
                Statement stmt2 = conn.createStatement()) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            if (rs1.next()) {
                customer.setId(rs1.getInt("id"));
                customer.setName(rs1.getString("cname"));
                customer.setPhone(rs1.getString("phone"));
                customer.setEmail(rs1.getString("email"));
                customer.setAddress(rs1.getString("address"));
                customer.setNid(rs1.getString("nid"));
                customer.setBillingDate(rs1.getObject("billing_date", LocalDate.class));
                customer.getRatePlan().setId(rs1.getInt("rate_plan_id"));
                customer.getRatePlan().setName(rs1.getString("rpname"));
                customer.getRatePlan().setMonthlyFees(rs1.getFloat("monthly_fees"));

                String customerJoinServiceQuery = "SELECT cust_svc_addons.*, service.* FROM cust_svc_addons , service "
                        + "WHERE service.id = cust_svc_addons.service_id AND cust_id = " + id;
                ResultSet rs2 = stmt2.executeQuery(customerJoinServiceQuery);
                while (rs2.next()) {
                    ExtraService extraService = new ExtraService();
                    extraService.getService().setId(rs2.getInt("service_id"));
                    extraService.getService().setName(rs2.getString("name"));
                    extraService.getService().setRated(rs2.getBoolean("rating"));
                    extraService.getService().setType(rs2.getString("type"));
                    extraService.setCost(rs2.getFloat("cost"));
                    extraService.setPaid(rs2.getBoolean("paid"));
                    extraService.setBillDate(rs2.getObject("billdate", LocalDateTime.class));
                    customer.getAddOnServices().add(extraService);
                }
            }
        } catch (SQLException ex) {
            System.out.println("##### Customer get by id faild: \n" + ex.getMessage());
        }
        return customer;
    }

    @Override
    public boolean update(Customer t) {
        boolean operationSuccess = true;
        String sqlCommand = "update customer set name=?, nid=?, phone=?, address=?, email=?, rate_plan_id=?, billing_date=? where id = ?";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setString(1, t.getName());
            preparedStatment.setString(2, t.getNid());
            preparedStatment.setString(3, t.getPhone());
            preparedStatment.setString(4, t.getAddress());
            preparedStatment.setString(5, t.getEmail());
            preparedStatment.setInt(6, t.getRatePlan().getId());
            preparedStatment.setObject(7, t.getBillingDate());
            preparedStatment.setInt(8, t.getId());
//            System.out.println(customer.getBillingDate());
            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### Customer Update faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }

    public boolean deleteCustomer(Customer t) {
        boolean operationSuccess = true;
        String sqlCommand = "delete from customer where id=?";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setInt(1, t.getId());
            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### Customer delete faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
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
