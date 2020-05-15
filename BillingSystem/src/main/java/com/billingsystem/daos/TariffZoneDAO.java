/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.daos;

import com.billingsystem.database.Database;
import com.billingsystem.entities.TariffZone;
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
public class TariffZoneDAO implements DAO<TariffZone> {

    private final Connection conn = Database.getConnection();

    @Override
    public TariffZone get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<TariffZone> getAll() {
        ArrayList<TariffZone> alltarrifZone = new ArrayList<>();
        String customerJoinRatePlanQuery = "select * from tarrif_zone";

        try (
                Statement stmt1 = conn.createStatement();) {
            ResultSet rs1 = stmt1.executeQuery(customerJoinRatePlanQuery);
            while (rs1.next()) {
                TariffZone tz = new TariffZone();
                tz.setId(rs1.getInt("id"));
                tz.setName(rs1.getString("name"));
                tz.setSame_net(rs1.getBoolean("same_net"));
                tz.setLocal(rs1.getBoolean("local"));
//                tz.setRoaming(rs1.getBoolean("roaming"));

                alltarrifZone.add(tz);

            }
        } catch (SQLException ex) {
            System.out.println("##### tarrifZone get all faild: \n" + ex.getMessage());
        }
        return alltarrifZone;
    }

    @Override
    public boolean update(TariffZone t) {
        boolean operationSuccess = true;
        String sqlCommand = "update tarrif_zone set name = ?, same_net = ?, local = ?"
                + " where id = ?";
                

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setString(1, t.getName());
            preparedStatment.setBoolean(2, t.isSame_net());
            preparedStatment.setBoolean(3, t.isLocal());
            preparedStatment.setInt(4, t.getId());

            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### tarrif_zone insert faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }

    public boolean deleteTarrifZone(TariffZone t) {
        boolean operationSuccess = true;
        String sqlCommand = "delete from tarrif_zone where id=?";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setInt(1, t.getId());
            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### TarrifZone delete faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int saveAndReturnId(TariffZone t) {
        int newRecordId;
        String sqlCommand = "insert into tarrif_zone (name,same_net,local) values (?,?,?)";

        try (PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setString(1, t.getName());
            preparedStatment.setBoolean(2, t.isSame_net());
            preparedStatment.setBoolean(3, t.isLocal());

            preparedStatment.executeUpdate();
            ResultSet generatedKeys = preparedStatment.getGeneratedKeys();
            generatedKeys.next();
            newRecordId = generatedKeys.getInt(1);

        } catch (SQLException ex) {
            System.out.println("##### tarrifZone insert faild: \n" + ex.getMessage());
            newRecordId = 0;
        }
        return newRecordId;
    }

    @Override
    public boolean save(TariffZone t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
