/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.servlets;

import com.billingsystem.daos.RatePlanDao;
import com.billingsystem.daos.ServiceDao;
import com.billingsystem.entities.RatePlan;
import com.billingsystem.entities.Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moham
 */
public class AddRatePlan extends HttpServlet {

    RatePlanDao rpd = new RatePlanDao();
    RatePlan rp = new RatePlan();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        rp.setName(req.getParameter("RatePlanName"));
        rp.setMonthlyFees(Float.parseFloat(req.getParameter("monthly_fees")));
        rpd.save(rp);
        resp.getWriter().println("Done");

    }

}
