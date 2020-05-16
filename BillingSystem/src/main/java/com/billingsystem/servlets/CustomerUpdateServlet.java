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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moham
 */
@WebServlet(value = "/addCustomer")
public class CustomerUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDAO cd = new CustomerDAO();
        Customer c = new Customer();
        List<ExtraService> extraServiceList = new ArrayList<>();
        ExtraService recuringService = new ExtraService();
        ExtraService oneTimeFeeService = new ExtraService();
        ExtraService recuringCost = new ExtraService();
        ExtraService oneTimeFeeCost = new ExtraService();

        if (req.getParameter("customer").equals("addCustomer")) {
            recuringService.getService().setId(Integer.parseInt(req.getParameter("rs")));
            oneTimeFeeService.getService().setId(Integer.parseInt(req.getParameter("otf")));
            recuringCost.setCost(Integer.parseInt(req.getParameter("rsCost")));
            oneTimeFeeCost.setCost(Integer.parseInt(req.getParameter("otfCost")));
            System.out.println("############"+req.getParameter("rsCost")+"####"+req.getParameter("otfCost"));
            extraServiceList.add(recuringService);
            extraServiceList.add(oneTimeFeeService);
            extraServiceList.add(recuringCost);
            extraServiceList.add(oneTimeFeeCost);
            
            c.setAddOnServices(extraServiceList);
            
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
        } else if (req.getParameter("customer").equals("deleteCustomer")) {
            c.setId(Integer.parseInt(req.getParameter("customerId")));
            cd.deleteCustomer(c);
            resp.sendRedirect("./pages/customers.jsp");
        } else if (req.getParameter("customer").equals("updateCustomer")) {
            c.setName(req.getParameter("name"));
            c.setId(Integer.parseInt(req.getParameter("customerId")));
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
                cd.update(c);
                resp.sendRedirect("./pages/customers.jsp");
            } catch (Exception ex) {
                System.out.println("##### Parsing date faild\n" + ex.getMessage());
            }
        }

    }
}
