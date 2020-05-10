/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdr;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


public class data implements DAO<Cdr>{
    
    private final Connection conn = Database.getConnection();

    @Override
    public Cdr get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Cdr> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public boolean update(Cdr t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(Cdr t) {
        boolean operationSuccess = true;
        String sqlCommand = "insert into cdr (dial_a  ,dial_b  , service_id  , duration  , start_date  , start_time, external_charges ) values (?,?,?,?,?,?,?);";

        try(PreparedStatement preparedStatment = conn.prepareStatement(sqlCommand)) {
            preparedStatment.setString(1, t.getDial_a());
            preparedStatment.setString(2, t.getDial_b());
            preparedStatment.setInt(3, t.getService_id());
            preparedStatment.setInt(4, t.getDuration());
            preparedStatment.setString(5, t.getStart_date());
            preparedStatment.setString(6, t.getStart_time());
            preparedStatment.setInt(7, t.getExternal_charges());
            preparedStatment.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("##### Cdr insert faild: \n" + ex.getMessage());
            operationSuccess = false;
        }
        return operationSuccess;
    }

    
}
