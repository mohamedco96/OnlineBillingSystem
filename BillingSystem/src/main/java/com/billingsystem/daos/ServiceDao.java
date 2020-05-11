/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.daos;

import com.billingsystem.database.Database;
import com.billingsystem.entities.Customer;
import com.billingsystem.entities.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author moham
 */
public class ServiceDao implements DAO<Service> {

    private final Connection conn = Database.getConnection();

    @Override
    public Service get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Service> getAll() {
        ArrayList<Service> allService = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from service";

        try (
                Statement stmt1 = conn.createStatement();) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            while (rs1.next()) {
                Service s = new Service();
                s.setId(rs1.getInt("id"));
                s.setName(rs1.getString("name"));
                s.setRated(rs1.getBoolean("is_rated"));
                s.setType(rs1.getString("type"));

                allService.add(s);

            }
        } catch (SQLException ex) {
            System.out.println("##### Service get all faild: \n" + ex.getMessage());
        }
        return allService;
    }

    public ArrayList<Service> getAllRecurringServices() {
        ArrayList<Service> allService = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from service where type='Recurring Services'";

        try (
                Statement stmt1 = conn.createStatement();) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            while (rs1.next()) {
                Service s = new Service();
                s.setId(rs1.getInt("id"));
                s.setName(rs1.getString("name"));
                s.setRated(rs1.getBoolean("is_rated"));
                s.setType(rs1.getString("type"));

                allService.add(s);

            }
        } catch (SQLException ex) {
            System.out.println("##### RecurringServices get all faild: \n" + ex.getMessage());
        }
        return allService;
    }

    public ArrayList<Service> getAllOneTimeFee() {
        ArrayList<Service> allService = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from service where type='One time fee'";

        try (
                Statement stmt1 = conn.createStatement();) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            while (rs1.next()) {
                Service s = new Service();
                s.setId(rs1.getInt("id"));
                s.setName(rs1.getString("name"));
                s.setRated(rs1.getBoolean("is_rated"));
                s.setType(rs1.getString("type"));

                allService.add(s);

            }
        } catch (SQLException ex) {
            System.out.println("##### One time fee get all faild: \n" + ex.getMessage());
        }
        return allService;
    }

    @Override
    public boolean save(Service s) {
        boolean operationSuccess = true;
        String sqlCommand = "insert into service(name,is_rated,type) values (?,?,?)";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setString(1, s.getName());
            preparedStatment.setBoolean(2, s.isRated());
            preparedStatment.setString(3, s.getType());

            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### Service insert faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }

    @Override
    public boolean update(Service t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean deleteService(Service s) {
        boolean operationSuccess = true;
        String sqlCommand = "delete from service where id=?";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setInt(1, s.getId());
            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### Service delete faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
