/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.scifi.trabajo2;

/**
 *
 * @author samantha
 */
public class Producto {
   private String referencia; 
   private String nombre; 
   private int stock; 
   private double pvp; 

    public Producto(String referencia, String nombre, int stock, double pvp) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.stock = stock;
        this.pvp = pvp;
    }

    public Producto() {
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPvp() {
        return pvp;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }
   
   
    
}
