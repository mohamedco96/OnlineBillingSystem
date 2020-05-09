/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.daos;

import com.billingsystem.database.Database;
import com.billingsystem.entities.Service;
import com.billingsystem.entities.timePackage;
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
public class timePackageDao implements DAO<timePackage> {

    private final Connection conn = Database.getConnection();

    @Override
    public timePackage get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<timePackage> getAll() {
        ArrayList<timePackage> alltimePackage = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from time_pkg";

        try (
                Statement stmt1 = conn.createStatement();) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            while (rs1.next()) {
                timePackage tp = new timePackage();
                tp.setId(rs1.getInt("id"));
                tp.setName(rs1.getString("name"));
                tp.setStart(rs1.getString("start"));
                tp.setFinish(rs1.getString("finish"));
                tp.setDay(rs1.getString("day"));

                alltimePackage.add(tp);

            }
        } catch (SQLException ex) {
            System.out.println("##### timePackage get all faild: \n" + ex.getMessage());
        }
        return alltimePackage;
    }

    @Override
    public boolean save(timePackage t) {
        boolean operationSuccess = true;
        String sqlCommand = "insert into time_pkg(name,day) values (?,?)";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setString(1, t.getName());
//            preparedStatment.setString(2, t.getStart());
//            preparedStatment.setString(3, t.getFinish());
            preparedStatment.setString(2, t.getDay());

            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### timePackage insert faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }

    @Override
    public boolean update(timePackage t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean deletetimePackage(timePackage t) {
         boolean operationSuccess = true;
        String sqlCommand = "delete from time_pkg where id=?";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setInt(1, t.getId());
            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### timePackage delete faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
