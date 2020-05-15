/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.daos;

import com.billingsystem.database.Database;
import com.billingsystem.entities.RatePlan;
import com.billingsystem.entities.ServicePackage;
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

    private ResultSet result = null;

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
                RatePlan rp = new RatePlan();
                rp.setId(rs1.getInt("id"));
                rp.setName(rs1.getString("name"));
                rp.setMonthlyFees(rs1.getFloat("monthly_fees"));
                rp.setServicePackages(getAllServicePackage(rp.getId()));
                allRatePlan.add(rp);

            }
        } catch (SQLException ex) {
            System.out.println("##### RatePlan get all faild: \n" + ex.getMessage());
        }
        return allRatePlan;
    }

    public ArrayList<ServicePackage> getAllServicePackage(int ratePlanId) {
        ArrayList<ServicePackage> allServicePackage = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from svc_pkg where rate_plan_id=?";

        try (PreparedStatement preparedStatment = conn.prepareStatement(customerJoinRatePlanQuery)) {
            preparedStatment.setInt(1, ratePlanId);

            result = preparedStatment.executeQuery();
            while (result.next()) {
                ServicePackage sp = new ServicePackage();
                sp.setId(result.getInt("id"));
                sp.setService(new ServiceDAO().get(result.getInt("service_id")));
//                rp.getTimePackage().setId(result.getInt("time_id"));
//                rp.getTarrifZone().setId(result.getInt("tarrif_id"));
                sp.setFree_units(result.getInt("free_units"));
                sp.setRate(result.getInt("rate"));

//                sp.getRatePlan().setId(rs1.getInt("id"));
                allServicePackage.add(sp);
            }

        } catch (SQLException ex) {
            System.out.println("##### svc_pkg select faild: \n" + ex.getMessage());
        }

        return allServicePackage;
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

    public int saveAndReturnRatePlanId(RatePlan t) {
        int newRecordId;
        String sqlCommand = "insert into rate_plan (name,monthly_fees) values (?,?)";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatment.setString(1, t.getName());
            preparedStatment.setFloat(2, t.getMonthlyFees());

            preparedStatment.executeUpdate();
            ResultSet generatedKeys = preparedStatment.getGeneratedKeys();
            generatedKeys.next();
            newRecordId = generatedKeys.getInt(1);
            
            RatePlan rp = new RatePlan();
//            rp.setServicePackages(saveServicePackage(rp.getId()));
            
        } catch (SQLException ex) {
            newRecordId = 0;
            System.out.println("##### rate_plan insert & return id faild: \n" + ex.getMessage());
        }
        return newRecordId;
    }

    public int saveServicePackage(ServicePackage sp) {
        int newRecordId;
        String sqlCommand2 = "insert into svc_pkg(rate_plan_id,service_id,time_id,tarrif_id,free_units,rate) values (?,?,?,?,?,?)";
        
        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand2, Statement.RETURN_GENERATED_KEYS)) {
//            preparedStatment.setInt(1, newRecordId);
            preparedStatment.setInt(2, sp.getService().getId());
            preparedStatment.setInt(3, sp.getTimePackage().getId());
            preparedStatment.setInt(4, sp.getTarrifZone().getId());
            preparedStatment.setInt(5, sp.getFree_units());
            preparedStatment.setFloat(6, sp.getRate());

            preparedStatment.executeUpdate();
            ResultSet generatedKeys = preparedStatment.getGeneratedKeys();
            generatedKeys.next();
            newRecordId = generatedKeys.getInt(1);
        } catch (SQLException ex) {
            newRecordId = 0;
            System.out.println("##### svc_pkg insert id faild: \n" + ex.getMessage());
        }
        return newRecordId;
    }

    @Override
    public int saveAndReturnId(RatePlan t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
