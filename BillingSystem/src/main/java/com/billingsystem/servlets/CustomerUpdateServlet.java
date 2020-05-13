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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author moham
 */
@WebServlet(value = "/addCustomer")
public class CustomerUpdateServlet extends HttpServlet {

    CustomerDAO cd = new CustomerDAO();
    Customer c = new Customer();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        c.setName(req.getParameter("name"));
        c.setNid(req.getParameter("nid"));
        c.setPhone(req.getParameter("dnum"));
        c.setAddress(req.getParameter("addr"));
        c.setEmail(req.getParameter("email"));
        String string = req.getParameter("billing_date");
        System.out.println(string);
        LocalDate date1 = null;
        try {
            date1 = LocalDate.parse(string, DateTimeFormatter.ISO_DATE);
            System.out.println("#########" + date1);
            c.setBillingDate(date1);
            c.getRatePlan().setId(Integer.parseInt(req.getParameter("profile")));
            cd.save(c);
            resp.sendRedirect("./index.jsp");
        } catch (Exception ex) {
            System.out.println("##### Parsing date faild\n" + ex.getMessage());
        }
    }
}
