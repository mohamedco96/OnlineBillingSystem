/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.daos;

import com.billingsystem.database.Database;
import com.billingsystem.entities.RatePlan;
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
public class RatePlanDAO implements DAO<RatePlan> {
    
  

    private final Connection conn = Database.getConnection();

    @Override
    public RatePlan get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<RatePlan> getAll() {
        ArrayList<RatePlan> allRatePlan = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from rate_plan";

        try (
                Statement stmt1 = conn.createStatement();) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            while (rs1.next()) {
                RatePlan s = new RatePlan();
                s.setId(rs1.getInt("id"));
                s.setName(rs1.getString("name"));
                s.setMonthlyFees(rs1.getFloat("monthly_fees"));

                allRatePlan.add(s);

            }
        } catch (SQLException ex) {
            System.out.println("##### RatePlan get all faild: \n" + ex.getMessage());
        }
        return allRatePlan;
    }

    @Override
    public boolean save(RatePlan t) {
        boolean operationSuccess = true;
        String sqlCommand = "insert into rate_plan (name,monthly_fees) values (?,?)";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setString(1, t.getName());
            preparedStatment.setFloat(2, t.getMonthlyFees());

            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### RatePlan insert faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }

    @Override
    public boolean update(RatePlan t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean deleteRatePlan(RatePlan t) {
        boolean operationSuccess = true;
        String sqlCommand = "delete from rate_plan where id=?";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setInt(1, t.getId());
            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### RatePlan delete faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }

    @Override
    public int saveAndReturnId(RatePlan t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
