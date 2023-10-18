package com.Clases.Estructuras.linkedlist;

import java.util.Iterator;

import com.Clases.Estructuras.interfaces.node.NodeInterface;
import com.Clases.Estructuras.node.NodoListaEnlazada;
import com.Clases.Articulo;
import com.Clases.Cliente;

public class ListaArticulos extends ListaEnlazada<Articulo> {
    private static final long serialVersionUID = 295L;
    private int cantidad; // Para organizar la cantidad de veces que se ha pedido una lista de artículos
                          // por un Cliente
    private Cliente cliente; // Para asignar el pedido a nombre de un cliente
    private String idPedido;

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

    public void sumarDiferenciaPedido(ListaArticulos listaDiferencias) {
        NodoListaEnlazada<Articulo> nodoActual = cabeza;
        while (nodoActual != null) {
            Articulo articuloActual = nodoActual.getObject();
            Articulo articuloDiferencia = listaDiferencias.contains(articuloActual.getId());
            if (articuloDiferencia != null) {
                int diferencia = articuloActual.getCantidad() + articuloDiferencia.getCantidad();
                if (diferencia < 0) {
                    remove(articuloActual.getId());
                } else {
                    articuloActual.setCantidad(diferencia);
                }
            }
            nodoActual = nodoActual.getSiguiente();
        }
        Iterator<NodeInterface<Articulo>> iterador = listaDiferencias.iterator();
        while (iterador.hasNext()) {
            Articulo articuloActual = iterador.next().getObject();
            if (contains(articuloActual.getId()) == null) {
                add(articuloActual);
            }
        }
    }

    private void remove(int id) {
        NodoListaEnlazada<Articulo> nodoActual = cabeza;
        NodoListaEnlazada<Articulo> nodoAnterior = null;
        while (nodoActual != null) {
            if (nodoActual.getObject().getId() == id) {
                if (nodoAnterior == null) {
                    cabeza = cabeza.getSiguiente();
                } else {
                    nodoAnterior.setSiguiente(nodoActual.getSiguiente());
                }
                tamano--;
                return;
            }
        }
    }

    public boolean contains(int id, String idPedido) {
        Iterator<NodeInterface<Articulo>> iterador = this.iterator();
        while (iterador.hasNext()) {
            Articulo articuloActual = iterador.next().getObject();
            if (articuloActual.getId() == id && articuloActual.getIdPedido().equals(idPedido)) {
                return true;
            }
        }
        return false;
    }

    public void remove(int id, String idPedido) {
        NodoListaEnlazada<Articulo> nodoActual = cabeza;
        NodoListaEnlazada<Articulo> nodoAnterior = null;
        while (nodoActual != null) {
            if (nodoActual.getObject().getId() == id && nodoActual.getObject().getIdPedido().equals(idPedido)) {
                if (nodoAnterior == null) {
                    cabeza = cabeza.getSiguiente();
                } else {
                    nodoAnterior.setSiguiente(nodoActual.getSiguiente());
                }
                tamano--;
                return;
            }
        }
    }
}
