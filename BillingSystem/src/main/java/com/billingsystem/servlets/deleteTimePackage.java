/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.servlets;

import com.billingsystem.daos.timePackageDao;
import com.billingsystem.entities.timePackage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moham
 */
public class deleteTimePackage extends HttpServlet {

    timePackageDao tpd = new timePackageDao();
    timePackage tp = new timePackage();

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
