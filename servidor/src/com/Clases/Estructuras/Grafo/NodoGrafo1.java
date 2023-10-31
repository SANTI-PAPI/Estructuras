package com.Clases.Estructuras.Grafo;

import com.Clases.Estructuras.linkedlist.*;
import com.Clases.Estructuras.node.Nodo;

public class NodoGrafo1<T> extends ListaEnlazada {

    int numeroConexiones;

    public ListaEnlazada<T> getNodo() {
        return nodo;
    }

    ListaEnlazada<T> nodo;
    int nombre;

    public NodoGrafo1(int nombre) {
        this.nombre = nombre;
        this.nodo = new ListaEnlazada<>();
    }

    public int getNumeroConexiones() {
        return numeroConexiones;
    }

    public void setNumeroConexiones(int numeroConexiones) {
        this.numeroConexiones = numeroConexiones;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
    /*
     * public boolean add(T object) {
     * 
     * return nodo.add(object); }
     * 
     * public boolean remove(T object) { return nodo.remove(object); }
     */
}
