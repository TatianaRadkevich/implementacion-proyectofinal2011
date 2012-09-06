/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio.Produccion.Planificacion.Recursos;

import Negocio.Compras.Material;

/**
 *
 * @author Dicsys
 */
public class MaterialPlan {

    private Material mat;
    private int stock;


    public MaterialPlan(Material material) {
       this.mat=material;
       this.stock=material.getStockActual();
    }

    public int getStock() {
        return stock;
    }

    public int addStock(int cant) {
        return stock=stock+cant;
    }
    public int removeStock(int cant) {
       return stock=stock-cant;
    }
}
