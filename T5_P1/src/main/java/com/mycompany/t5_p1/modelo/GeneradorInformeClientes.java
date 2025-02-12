/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.t5_p1.modelo;

import java.io.InputStream;
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
public class GeneradorInformeClientes {
    private static InputStream logo;
    private static InputStream torre;
    public static void leerInforme(List<Address> listaClientes){
    try {
            JasperPrint print;
            HashMap parametro = new HashMap();
            logo = GeneradorInformeClientes.class.getResourceAsStream("/Imagenes/logo_gestor.png");
            torre = GeneradorInformeClientes.class.getResourceAsStream("/Imagenes/torre.jpg");
            parametro.put("fecha", "04/02/2025");
            parametro.put("titulo", "LISTADO DE CLIENTES");
            parametro.put("logo", logo);
            parametro.put("torre", torre);
            
            JRDataSource dataSource = new JRBeanArrayDataSource(listaClientes.toArray());
            String report = "T5_P1 Facturas.jasper";
            print = JasperFillManager.fillReport(report, parametro,dataSource);
            JasperExportManager.exportReportToPdfFile(print, "ListaClientes.pdf");
            JasperViewer.viewReport(print);
        } catch (JRException ex) {
            Logger.getLogger(GeneradorInformeClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
