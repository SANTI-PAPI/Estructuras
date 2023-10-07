package com.Clases.Estructuras.linkedlist;

import java.util.Iterator;

import com.Clases.Estructuras.interfaces.node.NodeInterface;
import com.Clases.Estructuras.node.NodoListaEnlazada;
import com.Clases.Articulo;
import com.Clases.Cliente;

public class ListaArticulos extends ListaEnlazada<Articulo> {
    private int cantidad; // Para organizar la cantidad de veces que se ha pedido una lista de artículos por un Cliente
    private Cliente cliente; // Para asignar el pedido a nombre de un cliente

    public Articulo contains(String nombreArticulo) {
        Iterator<NodeInterface<Articulo>> iterador = this.iterator();
        while (iterador.hasNext()) {
            NodoListaEnlazada<Articulo> nodo = (NodoListaEnlazada<Articulo>) iterador.next();
            if (nodo.getObject().getNombre().equals(nombreArticulo)) {
                return nodo.getObject();
            }
        }
        return null;
    }

    public Articulo contains(int idArticulo) {
        Iterator<NodeInterface<Articulo>> iterador = this.iterator();
        while (iterador.hasNext()) {
            NodoListaEnlazada<Articulo> nodo = (NodoListaEnlazada<Articulo>) iterador.next();
            if (nodo.getObject().getId() == idArticulo) {
                return nodo.getObject();
            }
        }
        return null;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
