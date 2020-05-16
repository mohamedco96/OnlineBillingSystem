/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.daos;

import com.billingsystem.database.Database;
import com.billingsystem.entities.Customer;
import com.billingsystem.entities.DetailedBill;
import com.billingsystem.entities.DetailedBill.BillServices;
import com.billingsystem.entities.DetailedBill.RecService;
import com.billingsystem.entities.RatePlan;
import com.billingsystem.entities.Service;
import com.billingsystem.entities.ServicePackage;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author omega
 */
public class DetailedBillDao implements DAO{
DetailedBill detailedBill ;
    private final Connection conn = Database.getConnection();

    /**
     *
     * @param id
     * @return
     */
    public DetailedBill get(String id,int bill_id) {
     detailedBill = new DetailedBill()   ;
     ServiceDAO svc_dao = new ServiceDAO();
     CustomerDAO customerDAO = new CustomerDAO();
     ServicePackageDAO svc_pkg_dao = new ServicePackageDAO();
     RatePlanDAO ratePlanDao = new RatePlanDAO();
     Vector <BillServices> vec = new Vector<>(); 
     Vector <RecService> vec2 = new Vector<>();
     detailedBill.setCustomer(customerDAO.searchByPhone(id));
     
     String sql = "select * from (select cdr.service_id, cdr.svc_pkg_id,cdr.rate ,sum(volumespent) as \"volume spent\",(select sum(volumespent)-free_units from svc_pkg where svc_pkg.id =cdr.svc_pkg_id) as \"after rate plan\" ,(select( sum (volumespent)-free_units)* rate from  svc_pkg where svc_pkg.id =cdr.svc_pkg_id ) as  cost  , (select free_units from svc_pkg where id = cdr.svc_pkg_id) as \"free units\" ,billdate ,cdr.rate_plan_id , cdr.paid , bill_id  from cdr  where right(origin,11) ='"+id+"' and bill_id="+bill_id+"  group by svc_pkg_id ,cdr.service_id ,cdr.rate , billdate  , cdr.rate_plan_id, cdr.paid ,cdr.bill_id ) as invoice where cost>0  ;";
     
     
     String sql2=" select service_id,cost,paid,billdate from cust_svc_addons where cust_id  =(select id from customer where right(phone,11 )='"+ id+"' ) and bill_id="+bill_id+" ;";
     
     String sql3="{ ? = call sumtax(?,?) }";
     
     String sql4="{ ? = call sumtotal(?,?) }";
     
     
   
    try {
        Statement stmt1 = conn.createStatement();
        Statement stmt2 = conn.createStatement();
       
        ResultSet rs= stmt1.executeQuery(sql);
        ResultSet rs2= stmt2.executeQuery(sql2);
       
        CallableStatement callableStatement = conn.prepareCall(sql3) ;
           callableStatement.registerOutParameter(1, Types.FLOAT);
           callableStatement.setString(2, id);
           callableStatement.setInt(3, bill_id);
           callableStatement.executeUpdate();
           Double result = callableStatement.getDouble(1);
            detailedBill.setTax(result);
         CallableStatement callableStatement2 = conn.prepareCall(sql4) ;
           callableStatement2.registerOutParameter(1, Types.FLOAT);
           callableStatement2.setString(2, id);
           callableStatement2.setInt(3, bill_id);
           callableStatement2.executeUpdate();
           Double result2 = callableStatement2.getDouble(1);
          
           detailedBill.setTotal(result2);
        
        while(rs.next())
        {
        BillServices bill = detailedBill.new BillServices();
        bill.setBilldate(rs.getDate(8));
        bill.setCost(rs.getFloat(6));
        bill.setFree_units(rs.getInt(7));
        bill.setRate(rs.getFloat(3));
        bill.setSvc(svc_dao.get(rs.getInt(1)));
        System.out.println(bill.getSvc().getName());
        bill.setSvc_pkg(svc_pkg_dao.get(rs.getInt(2)));
        bill.setUsed(rs.getFloat(5));
        bill.setVolume_spent(rs.getFloat(4));
        bill.setRatePlan(ratePlanDao.get(rs.getInt(9)));
        bill.setPaid(rs.getBoolean(10));
       
        detailedBill.setId(rs.getInt(11));
        vec.add(bill);
        }
         while(rs2.next()){
         RecService recService = detailedBill.new RecService();
         recService.setSvc(svc_dao.get(rs2.getInt(1)));
         recService.setBilldate(rs2.getDate(4));
         recService.setCost(rs2.getFloat(2));
         recService.setPaid(rs2.getBoolean(3));
         vec2.add(recService);
         }
         
         
    } catch (SQLException ex) {
        Logger.getLogger(DetailedBillDao.class.getName()).log(Level.SEVERE, null, ex);
    }
     detailedBill.setVec2(vec2);
     detailedBill.setVec(vec);
     
     
     return detailedBill;
    }

    @Override
    public ArrayList getAll() {
        
       ArrayList<DetailedBill> arrayList = new ArrayList<>();
        
       return arrayList; 
    }

    @Override
    public boolean save(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void pay(String phone , int id){
    
        
        String sql1 = "update customer set paid = true where right (origin,11) ='" + phone+"' ";
        String sql2=  "update cdr set paid = true where right (origin,11) ='" + phone+"' and bill_id="+id+";";
        String sql3=  "update cust_svc_addons set paid = true where cust_id =(select id from customer where right(id,12 )='"+ phone+"' ) and bill_id="+id+";";
        
    try {
        PreparedStatement pst1 = conn.prepareStatement(sql1);
        PreparedStatement pst2 = conn.prepareStatement(sql2);
        PreparedStatement pst3 = conn.prepareStatement(sql3);
        pst1.executeUpdate();
        pst2.executeUpdate();
        pst3.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(DetailedBillDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
    
    
    @Override
    public boolean update(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int saveAndReturnId(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
