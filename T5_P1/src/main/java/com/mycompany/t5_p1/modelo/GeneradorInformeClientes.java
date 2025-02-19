/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.t5_p1.modelo;

import java.io.InputStream;
import java.util.ArrayList;
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
    private static int id;
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
            //parametro.put("SUBREPORT_DIR", "\\T5_P1\\T5_P1\\");+
            
            JRDataSource dataSource = new JRBeanArrayDataSource(listaClientes.toArray());
            String report = "T5_P1 Facturas.jasper";
            print = JasperFillManager.fillReport(report, parametro,dataSource);
            JasperExportManager.exportReportToPdfFile(print, "ListaClientes.pdf");
            JasperViewer.viewReport(print);
        } catch (JRException ex) {
            Logger.getLogger(GeneradorInformeClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void informePorCliente(Address buscado){
        try {
            JasperPrint print;
            HashMap parametro = new HashMap();
            parametro.put("fecha", "04/02/2025");
            parametro.put("titulo", "Facturas por Usuario");
            parametro.put("id", buscado.getId());
            parametro.put("nombre", buscado.getFirstname());
            //parametro.put("SUBREPORT_DIR", "\\T5_P1\\T5_P1\\");+
            
            JRDataSource dataSource = new JRBeanArrayDataSource(buscado.getDocumentCollection().toArray());
            String report = "ListadoPorClientes.jasper";
            print = JasperFillManager.fillReport(report, parametro,dataSource);
            JasperExportManager.exportReportToPdfFile(print, "FacturasPorCliente.pdf");
            JasperViewer.viewReport(print);
        } catch (JRException ex) {
            Logger.getLogger(GeneradorInformeClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
