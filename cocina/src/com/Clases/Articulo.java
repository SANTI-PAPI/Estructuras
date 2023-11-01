package com.Clases;

import java.io.Serializable;

public class Articulo implements Serializable {
    private static final long serialVersionUID = 328L;
    private String nombre;
    private int id;
    private int cantidad;
    private int precio;
    private String idPedido;
    private boolean complejo;
    private Cliente clienteAsociado;

    public Articulo(int id, String nombre, int precio, boolean complejo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.complejo = complejo;
    }

    public Cliente getClienteAsociado() {
        return clienteAsociado;
    }

    public void setCliente(Cliente cliente) {
        this.clienteAsociado = cliente;
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

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public void restarUnidad() {
        cantidad--;
    }
}
