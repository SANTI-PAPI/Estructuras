package com.Json;

public class DatosPedido {
    private String numero;
    private String pedido;
    private String direccion;
    private double valor;

    public DatosPedido(String numero, String pedido, String direccion, double valor) {
        this.numero = numero;
        this.pedido = pedido;
        this.direccion = direccion;
        this.valor = valor;
    }

    public String getNumero() {
        return numero;
    }

    public String getPedido() {
        return pedido;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getValor() {
        return valor;
    }
}
