/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.t5_p1;

import com.mycompany.t5_p1.controlador.PositionsJpaController;
import com.mycompany.t5_p1.controlador.ProductJpaController;
import com.mycompany.t5_p1.modelo.Positions;
import com.mycompany.t5_p1.modelo.Product;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author auref
 */
public class ClaseGenerada {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
    ProductJpaController productJpaController = new ProductJpaController(emf);
    PositionsJpaController positionsJpaController = new PositionsJpaController(emf);

    List<Product> listaProducto;
    List<Positions> listaPosiciones;
    List<ClaseGenerada> resultado;

    int ID;
    String NAME;
    int QUANTITY;
    double PRICE;
    long TOTAL;

    public int getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public int getQUANTITY() {
        return QUANTITY;
    }

    public Double getPRICE() {
        return PRICE;
    }

    
    public ClaseGenerada(int ID, String NAME, int QUANTITY, double PRICE, long TOTAL) {
        this.ID = ID;
        this.NAME = NAME;
        this.QUANTITY = QUANTITY;
        this.PRICE = PRICE;
        this.TOTAL = TOTAL;
    }

    public Long getTOTAL() {
        return this.TOTAL;
    }
    
    public List<ClaseGenerada> conseguirDatos(){
        listaProducto = productJpaController.findProductEntities();
        listaPosiciones = positionsJpaController.findPositionsEntities();
        int cantidadtotal = 0;  
        boolean encontrado = false;
        resultado = new ArrayList<>();
        for (Product product : listaProducto) {
            for (Positions position : listaPosiciones) {
                if (position.getProduct().equals(product)) { 
                        ID = product.getId();
                        NAME = product.getName();
                        cantidadtotal += position.getQuantity();
                        PRICE = position.getPrice();
                        TOTAL = (long) (Math.round(QUANTITY * PRICE * 100.0) / 100.0); 
                        encontrado = true;
                }
            }
            if(encontrado){
                resultado.add(new ClaseGenerada(ID, NAME, cantidadtotal, PRICE, TOTAL));
            }
            cantidadtotal = 0;
            encontrado = false;
        }
        return resultado;
    }
}