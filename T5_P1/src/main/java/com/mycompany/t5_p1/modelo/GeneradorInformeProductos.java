/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.t5_p1.modelo;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author David
 */
public class GeneradorInformeProductos {
    public static void leerInforme(List<Positions> listaProductos){
    try {
            JasperPrint print;
            HashMap parametro = new HashMap();
          
            JRDataSource dataSource = new JRBeanArrayDataSource(listaProductos.toArray());
            String report = "T5_P1 Facturas_subreportProductos.jasper";
            print = JasperFillManager.fillReport(report, parametro,dataSource);
            JasperExportManager.exportReportToPdfFile(print, "ListaProductos.pdf");
            JasperViewer.viewReport(print);
        } catch (JRException ex) {
            Logger.getLogger(GeneradorInformeClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
