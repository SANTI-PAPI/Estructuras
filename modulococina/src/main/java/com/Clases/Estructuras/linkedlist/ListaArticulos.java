package com.Clases.Estructuras.linkedlist;

import java.util.Iterator;

import com.Clases.Estructuras.interfaces.node.NodeInterface;
import com.Clases.Estructuras.node.NodoListaEnlazada;
import com.Clases.Articulo;
import com.Clases.Cliente;

public class ListaArticulos extends ListaEnlazada<Articulo> {
    private int cantidad; // Para organizar la cantidad de veces que se ha pedido una lista de artículos por un Cliente
    private Cliente cliente; // Para asignar el pedido a nombre de un cliente
    private String idPedido;
    private boolean preparado;

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

    public boolean isPreparado() {
        return preparado;
    }

    public void setPreparado(boolean preparado) {
        this.preparado = preparado;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public boolean restarArticulo(Articulo articulo) {
        NodoListaEnlazada<Articulo> nodoActual = cabeza;
        while (nodoActual != null) {
            if (nodoActual.getObject().getId() == articulo.getId()) {
                if (nodoActual.getObject().getCantidad() > 0) {
                    nodoActual.getObject().restarUnidad();
                    return true;
                }
            }
            nodoActual = nodoActual.getSiguiente();
        }
        return false;
    }
}
