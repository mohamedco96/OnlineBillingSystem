/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.servlets;

import com.billingsystem.daos.ServiceDAO;
import com.billingsystem.entities.Service;
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
@WebServlet(value = "/deleteService")
public class ServiceDeleteServlet extends HttpServlet {

    ServiceDAO sd = new ServiceDAO();
    Service s = new Service();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        try {
            s.setId(Integer.parseInt(req.getParameter("service_id")));
            sd.deleteService(s);
            resp.getWriter().print("success");
//            resp.setContentType("success");
//        } catch (Exception e) {
//            e.getMessage();
//        }

    }

}
