package com.mycompany.t5_p1.controlador;

import com.mycompany.t5_p1.ClaseGenerada;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.view.JasperViewer;

public class GeneradorVentasTotales {
    
    public static InputStream imgInputStream = GeneradorVentasTotales.class.getResourceAsStream("/Imagenes/LOGO.jpg");
    
    public static Date date = new Date();
    
    public static ClaseGenerada claseGenerada;
    public static void leerInforme(List<ClaseGenerada> listaGenerada){
        try{
            JasperPrint print;
            HashMap<String, Object> param = new HashMap<>();
            param.put("FECHA", date);
            param.put("LOGO", imgInputStream);

            JRDataSource productoDataSource = new JRBeanArrayDataSource(listaGenerada.toArray());

            String report = "T5_Ventas_Totales.jasper";
            print = JasperFillManager.fillReport(report, param, productoDataSource);

            JasperExportManager.exportReportToPdfFile(print, "InformeVentasTotales.pdf");
            JasperViewer.viewReport(print);
            
        } catch (JRException ex) {
            Logger.getLogger(GeneradorVentasTotales.class.getName()).log(Level.SEVERE, null, ex);
        }               
    }
}
