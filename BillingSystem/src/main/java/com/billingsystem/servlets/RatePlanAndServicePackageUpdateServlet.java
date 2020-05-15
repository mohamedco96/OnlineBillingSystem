/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingsystem.servlets;

import com.billingsystem.daos.RatePlanDAO;
import com.billingsystem.daos.ServiceDAO;
import com.billingsystem.entities.RatePlan;
import com.billingsystem.entities.Service;
import com.billingsystem.entities.ServicePackage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import static java.util.Collections.list;
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

    RatePlanDAO rpd = new RatePlanDAO();
    List<ServicePackage> spList = new ArrayList<>();
    RatePlan rp = new RatePlan();
    ServicePackage voiceServicePackage=new ServicePackage();
    ServicePackage smsServicePackage=new ServicePackage();
    ServicePackage dataServicePackage=new ServicePackage();
    
     @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        rp.setName(req.getParameter("rpName"));
        rp.setMonthlyFees(Float.parseFloat(req.getParameter("MFess")));
         resp.getWriter().println( req.getParameterMap());
//       
//         voiceServicePackage.getService().setId(Integer.parseInt(req.getParameter("VoiceChecked")));
//         voiceServicePackage.getTimePackage().setId(Integer.parseInt(req.getParameter("VoiceTimePack")));
//         voiceServicePackage.getTarrifZone().setId(Integer.parseInt(req.getParameter("VoiceTarrif")));
//         voiceServicePackage.setFree_units(Integer.parseInt(req.getParameter("VoiceFreeUnit")));
//         voiceServicePackage.setRate(Float.parseFloat(req.getParameter("VoiceRate")));
//         spList.add(voiceServicePackage);
//         
//         smsServicePackage.getService().setId(Integer.parseInt(req.getParameter("SmsChecked")));
//         smsServicePackage.getTimePackage().setId(Integer.parseInt(req.getParameter("SmsTimePack")));
//         smsServicePackage.getTarrifZone().setId(Integer.parseInt(req.getParameter("SmsTarrif")));
//         smsServicePackage.setFree_units(Integer.parseInt(req.getParameter("SmsFreeUnit")));
//         smsServicePackage.setRate(Float.parseFloat(req.getParameter("SmsRate")));
//         spList.add(smsServicePackage);
//         
//         dataServicePackage.getService().setId(Integer.parseInt(req.getParameter("DataChecked")));
//         dataServicePackage.getTimePackage().setId(Integer.parseInt(req.getParameter("DataTimePack")));
//         dataServicePackage.getTarrifZone().setId(Integer.parseInt(req.getParameter("DataTarrif")));
//         dataServicePackage.setFree_units(Integer.parseInt(req.getParameter("DataFreeUnit")));
//         dataServicePackage.setRate(Float.parseFloat(req.getParameter("DataRate")));
//         spList.add(dataServicePackage);
         
         
//        sp.set(0, element).setId(Integer.parseInt(req.getParameter("serviceId")));
//        sp.getTimePackage().setId(Integer.parseInt(req.getParameter("timePackageID")));
//        sp.getTarrifZone().setId(Integer.parseInt(req.getParameter("tarriffZoneId")));
//        sp.setFree_units(Integer.parseInt(req.getParameter("FUints")));
//        sp.setFree_units(Integer.parseInt(req.getParameter("rate")));
        
         
//        rpd.saveAndReturnRatePlanId(rp);
    }
}
