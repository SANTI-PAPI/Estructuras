package com.Clases;

public class Cliente {
    private String nombre, apellido;
    private String numeroTelefono;
    private TipoDireccion tipoDireccion;
    private String direccion1, direccion2, direccionAdicional;
    private String municipio, barrio;
    private String comuna;
    private boolean premium = false;
    
    public Cliente(String numero, String nombre, String apellido,
    TipoDireccion tipo, String direccion1, String direccion2, 
    String direccionAdicional, String municipio, String comuna, String barrio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numero;
        this.tipoDireccion = tipo;
        this.direccion1 = direccion1;
        this.direccion2 = direccion2;
        this.direccionAdicional = direccionAdicional;
        this.municipio = municipio;
        this.comuna = comuna;
        this.barrio = barrio;
    }

    public void setNuevaDireccion(TipoDireccion tipo, String dir1, 
    String dir2, String dirAd, String municipio, String comuna, String barrio) {
        this.tipoDireccion = tipo;
        this.direccion1 = dir1;
        this.direccion2 = dir2;
        this.direccionAdicional = dirAd;
        this.municipio = municipio;
        this.comuna = comuna;
        this.barrio = barrio;
    }

    public TipoDireccion getTipoDireccion() {
        return tipoDireccion;
    }

    public String getDireccion1() {
        return direccion1;
    }

    public String getDireccion2() {
        return direccion2;
    }

    public String getDireccionAdicional() {
        return direccionAdicional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getBarrio() {
        return barrio;
    }

    public boolean isPremium() {
        return premium;
    }

    public String getComuna() {
        return comuna;
    }
}
