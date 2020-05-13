/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.servlets;

import com.billingsystem.daos.RatePlanDAO;
import com.billingsystem.entities.RatePlan;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moham
 */
@WebServlet(value = "/DeleteRatePlan")
public class RatePlanDeleteServlet extends HttpServlet {

    RatePlanDAO rpd = new RatePlanDAO();
    RatePlan rp = new RatePlan();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        rp.setId(Integer.parseInt(req.getParameter("RatePlanId")));
        rpd.deleteRatePlan(rp);
        resp.getWriter().println("success");

    }

}
