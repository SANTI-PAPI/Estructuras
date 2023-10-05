package com.Clases;

public class Articulo {
    private String nombre;
    private int cantidad;
    private int precio;
    boolean complejo;

    public Articulo(String nombre, int precio, boolean complejo) {
        this.nombre = nombre;
        this.precio = precio;
        this.complejo = complejo;
    }

    public String getNombre() {
        return nombre;
    }
    public int getPrecio() {
        return precio;
    }
    public boolean isComplejo() {
        return complejo;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public int getCantidad() {
        return cantidad;
    }
}
