package com.Clases.Estructuras.linkedlist;

import java.util.Iterator;

import com.Clases.Estructuras.interfaces.node.NodeInterface;
import com.Clases.Estructuras.node.NodoListaEnlazada;
import com.Clases.Articulo;
import com.Clases.Cliente;

public class ListaArticulos extends ListaEnlazada<Articulo> {
    private int cantidad; // Para organizar la cantidad de veces que se ha pedido una lista de artículos
                          // por un Cliente
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

    public boolean remove(int idArticulo) {
        try {
            if (isEmpty()) {
                return false;
            } else {
                Iterator<NodeInterface<Articulo>> iterador = this.iterator();
                NodoListaEnlazada<Articulo> nodo = null;
                while (iterador.hasNext()) {
                    NodoListaEnlazada<Articulo> nodoAnterior = nodo;
                    nodo = (NodoListaEnlazada<Articulo>) iterador.next();
                    if (nodo.getObject().getId() == idArticulo) {
                        if (nodoAnterior == null) {
                            cabeza = nodo.getSiguiente();
                        } else {
                            nodoAnterior.setSiguiente(nodo.getSiguiente());
                        }
                        tamano--;
                        return true;
                    }
                }
            }
        } catch (Exception e) {

        }

        return false;
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
