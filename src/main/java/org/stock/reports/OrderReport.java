package org.stock.reports;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class OrderReport {

    private Integer OrderId;

    private Connection mySQLConnection ;
    private JasperPrint jasperPrint ;

    public OrderReport(Integer orderId) throws ClassNotFoundException, SQLException, JRException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.mySQLConnection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock","ayoub","123456") ;
        this.mySQLConnection.setAutoCommit(false);
        this.OrderId = orderId;
        HashMap parameters = new HashMap();
        parameters.put("OrderId",this.OrderId);
       var resource =  OrderReport.class.getResource("order_confirmation.jasper").getFile() ;
       System.out.println(resource);
        this.jasperPrint =  JasperFillManager.fillReport(resource,parameters,this.mySQLConnection);
    }

    public void showReport(){

        JasperViewer viewer = new JasperViewer(this.jasperPrint,false);

        viewer.setVisible(true);
    }
}
