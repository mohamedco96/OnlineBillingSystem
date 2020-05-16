/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.servlets;

import com.billingsystem.daos.RatePlanDAO;
import com.billingsystem.entities.RatePlan;
import com.billingsystem.entities.ServicePackage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author moham
 */
@WebServlet(value = "/add_RP_And_SP")
public class RatePlanAndServicePackageUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RatePlanDAO rpd = new RatePlanDAO();
        List<ServicePackage> spList = new ArrayList<>();
        RatePlan rp = new RatePlan();
        ServicePackage voiceServicePackage = new ServicePackage();
        ServicePackage smsServicePackage = new ServicePackage();
        ServicePackage dataServicePackage = new ServicePackage();

        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        rp.setName(req.getParameter("rpName"));
        rp.setMonthlyFees(Float.parseFloat(req.getParameter("MFess")));
//         resp.getWriter().println( Integer.parseInt(req.getParameter("voiceChecked")));
//       
        voiceServicePackage.getService().setId(Integer.parseInt(req.getParameter("voiceChecked")));
        voiceServicePackage.getTimePackage().setId(Integer.parseInt(req.getParameter("voiceTimePack")));
        voiceServicePackage.getTarrifZone().setId(Integer.parseInt(req.getParameter("voiceTarrif")));
        voiceServicePackage.setFree_units(Integer.parseInt(req.getParameter("voiceFreeUnit")));
        voiceServicePackage.setRate(Float.parseFloat(req.getParameter("voiceRate")));
        spList.add(voiceServicePackage);
//         
        smsServicePackage.getService().setId(Integer.parseInt(req.getParameter("smsChecked")));
        smsServicePackage.getTimePackage().setId(Integer.parseInt(req.getParameter("smsTimePack")));
        smsServicePackage.getTarrifZone().setId(Integer.parseInt(req.getParameter("smsTarrif")));
        smsServicePackage.setFree_units(Integer.parseInt(req.getParameter("smsFreeUnit")));
        smsServicePackage.setRate(Float.parseFloat(req.getParameter("smsRate")));
        spList.add(smsServicePackage);

        dataServicePackage.getService().setId(Integer.parseInt(req.getParameter("dataChecked")));
        dataServicePackage.getTimePackage().setId(Integer.parseInt(req.getParameter("dataTimePack")));
        dataServicePackage.getTarrifZone().setId(Integer.parseInt(req.getParameter("dataTarrif")));
        dataServicePackage.setFree_units(Integer.parseInt(req.getParameter("dataFreeUnit")));
        dataServicePackage.setRate(Float.parseFloat(req.getParameter("dataRate")));
        spList.add(dataServicePackage);

        rp.setServicePackages(spList);

//        sp.set(0, element).setId(Integer.parseInt(req.getParameter("serviceId")));
//        sp.getTimePackage().setId(Integer.parseInt(req.getParameter("timePackageID")));
//        sp.getTarrifZone().setId(Integer.parseInt(req.getParameter("tarriffZoneId")));
//        sp.setFree_units(Integer.parseInt(req.getParameter("FUints")));
//        sp.setFree_units(Integer.parseInt(req.getParameter("rate")));
        rpd.save(rp);
        resp.sendRedirect("./pages/ratePlan.jsp");
    }
}
