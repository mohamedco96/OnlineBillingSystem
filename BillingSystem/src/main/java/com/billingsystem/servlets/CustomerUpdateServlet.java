/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.servlets;

//import com.billingsystem.entities.Users;
//import com.billingsystem.database.OldDatabase;
import com.billingsystem.daos.*;
import com.billingsystem.entities.*;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moham
 */
@WebServlet(value = "/addCustomer")
public class CustomerUpdateServlet extends HttpServlet {

//    Users user;
//    OldDatabase db = new OldDatabase();
    CustomerDAO cd = new CustomerDAO();
    Customer c = new Customer();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        
//        user = new Users(req.getParameter("name"), req.getParameter("nid"), req.getParameter("dnum"),
//                 req.getParameter("addr"), req.getParameter("email"), req.getParameter("profile"));
//                      
//        boolean CustomerUpdateServlet = db.CustomerUpdateServlet(user);
        c.setName(req.getParameter("name"));
        c.setNid(req.getParameter("nid"));
        c.setPhone(req.getParameter("dnum"));
        c.setAddress(req.getParameter("addr"));
        c.setEmail(req.getParameter("email"));
//        String string = req.getParameter("billing_date");
//        System.out.println(string);
//        Date date1 = null;
//        try {
//             date1 = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(string);
//        } catch (ParseException ex) {
//            Logger.getLogger(CustomerUpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        c.setBillingDate(date1);
        c.getRatePlan().setId(Integer.parseInt(req.getParameter("profile")));
        cd.save(c);
        resp.sendRedirect("./dashboard.jsp");

    }

}
