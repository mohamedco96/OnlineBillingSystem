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
public class ServiceDAO implements DAO<Service> {

    private final Connection conn = Database.getConnection();

    @Override
    public Service get(int id) {
        String serviceByIdQuery = "select * from service where id=" + id;
        Service s = new Service();
        try (Statement stmt1 = conn.createStatement()) {
            ResultSet rs1 = stmt1.executeQuery(serviceByIdQuery);

            if (rs1.next()) {
                s.setId(rs1.getInt("id"));
                s.setName(rs1.getString("name"));
                s.setRated(rs1.getBoolean("rating"));
                s.setType(rs1.getString("type"));
            }
        } catch (SQLException ex) {
            System.out.println("##### service By Id faild: \n" + ex.getMessage());
        }
        return s;
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
                s.setRated(rs1.getBoolean("rating"));
                s.setType(rs1.getString("type"));

                allService.add(s);

            }
        } catch (SQLException ex) {
            System.out.println("##### Service get all faild: \n" + ex.getMessage());
        }
        return allService;
    }
    
    public ArrayList<Service> getAllNormal() {
        ArrayList<Service> allService = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from service where type='network'";

        try (
                Statement stmt1 = conn.createStatement();) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            while (rs1.next()) {
                Service s = new Service();
                s.setId(rs1.getInt("id"));
                s.setName(rs1.getString("name"));
                s.setRated(rs1.getBoolean("rating"));
                s.setType(rs1.getString("type"));

                allService.add(s);

            }
        } catch (SQLException ex) {
            System.out.println("##### Normal get all faild: \n" + ex.getMessage());
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
                s.setRated(rs1.getBoolean("rating"));
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
                s.setRated(rs1.getBoolean("rating"));
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
        String sqlCommand = "insert into service(name,rating,type) values (?,?,?)";

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
    public int saveAndReturnId(Service s) {
        int newRecordId;
        String sqlCommand = "insert into service(name,rating,type) values (?,?,?)";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatment.setString(1, s.getName());
            preparedStatment.setBoolean(2, s.isRated());
            preparedStatment.setString(3, s.getType());

            preparedStatment.executeUpdate();
            ResultSet generatedKeys = preparedStatment.getGeneratedKeys();
            generatedKeys.next();
            newRecordId = generatedKeys.getInt(1);

        } catch (SQLException ex) {
            newRecordId = 0;
            System.out.println("##### Service insert & return id faild: \n" + ex.getMessage());
        }
        return newRecordId;
    }

    @Override
    public boolean update(Service s) {
        boolean operationSuccess = true;
        String sqlCommand = "update service set name = ?, rating = ?, type = ?"
                + " where id = ?";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setString(1, s.getName());
            preparedStatment.setBoolean(2, s.isRated());
            preparedStatment.setString(3, s.getType());
            preparedStatment.setInt(4, s.getId());

            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### Service insert faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
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
