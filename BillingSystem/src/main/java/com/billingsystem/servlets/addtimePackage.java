/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.servlets;

import com.billingsystem.daos.ServiceDao;
import com.billingsystem.daos.timePackageDao;
import com.billingsystem.entities.Service;
import com.billingsystem.entities.timePackage;
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
public class addtimePackage extends HttpServlet {
    timePackageDao tpd = new timePackageDao();
    timePackage tp = new timePackage();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        tp.setName(req.getParameter("pkg_name"));
        tp.setStart(req.getParameter("start"));
        tp.setFinish(req.getParameter("finish"));
        tp.setDay(req.getParameter("day"));
        tpd.save(tp);

    }

}
