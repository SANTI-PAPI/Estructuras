package com.Clases;

import java.io.Serializable;

public class Articulo implements Serializable {
    private String nombre;
    private int id;
    private int cantidad;
    private int precio;
    boolean complejo;

    public Articulo(int id, String nombre, int precio, boolean complejo) {
        this.id = id;
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

    public int getId() {
        return id;
    }
}
