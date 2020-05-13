/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.servlets;

import com.billingsystem.daos.TimePackageDAO;
import com.billingsystem.entities.TimePackage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moham
 */
@WebServlet(value = "/deleteTimePackage")
public class TimePackageDeleteServlet extends HttpServlet {

    TimePackageDAO tpd = new TimePackageDAO();
    TimePackage tp = new TimePackage();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            tp.setId(Integer.parseInt(req.getParameter("TimePackage_id")));
            tpd.deletetimePackage(tp);
            resp.setContentType("success");
        } catch (Exception e) {
            e.getMessage();
        }

    }

}
