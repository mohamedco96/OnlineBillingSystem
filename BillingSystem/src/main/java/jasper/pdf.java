/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jasper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author moham
 */
public class pdf {

    private final String url = "jdbc:postgresql://localhost:5432/billing";
    private final String user = "postgres";
    private final String password = "1";

    private Connection connection = null;
    private PreparedStatement preparedStatment = null;
    private ResultSet result = null;
    private String sqlCommand;

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is made successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public  void main(String[] args) {
        
        try {
            connect();
            JasperDesign jdesign = JRXmlLoader.load("C:\\\\Users\\\\moham\\\\Documents\\\\NetBeansProjects\\\\BillingSystem\\\\src\\\\java\\\\javaPackage\\\\report1.jrxml");
            String query="select * from users";
            JRDesignQuery updateQuery = new JRDesignQuery();
            updateQuery.setText(query);
            jdesign.setQuery(updateQuery);
            JasperReport jasperReport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jasperReport, null,connection);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters)
//            JasperExportManager.exportReportToPdfFile(jasperReport,"C:\\\\Users\\\\moham\\\\Documents\\\\test.pdf");
//            JRDataSource dataSource = JR
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
