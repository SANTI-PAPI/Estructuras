package com.Clases.Estructuras.linkedlist;

import java.util.Iterator;

import com.Clases.Articulo;
import com.Clases.Estructuras.interfaces.node.NodeInterface;
import com.Clases.Estructuras.node.NodoListaEnlazada;

public class ListaPedidos extends ListaEnlazada<ListaArticulos> {
    private static final long serialVersionUID = 2901L;

    public boolean sort() {
        if (size() == 1) {
            return true;
        }
        if (size() == 0) {
            return false;
        }
        ListaArticulos[] objects = toArray();
        for (int gap = objects.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < objects.length; i++) {
                ListaArticulos actual = objects[i];
                int j;
                for (j = i; j >= gap && objects[j - gap].getCantidad() > actual.getCantidad(); j -= gap) {
                    objects[j] = objects[j - gap];
                }
                objects[j] = actual;
            }
        }
        clear();
        add(objects);
        return true;
    }

    public boolean restarArticulo(Articulo articulo) {
        NodoListaEnlazada<ListaArticulos> nodoActual = cabeza;
        while (nodoActual != null) {
            ListaArticulos pedidoActual = nodoActual.getObject();
            if (pedidoActual.getIdPedido().equals(articulo.getIdPedido())) {
                return pedidoActual.restarArticulo(articulo);
            }
            nodoActual = nodoActual.getSiguiente();
        }
        return false;
    }

    public void remove(String idPedido) {
        try {
            if (!isEmpty()) {
                NodoListaEnlazada<ListaArticulos> nodoActual = cabeza;
                NodoListaEnlazada<ListaArticulos> nodoAnterior = null;
                while (nodoActual != null) {
                    if (nodoActual.getObject().getIdPedido().equals(idPedido)) {
                        if (nodoAnterior == null) {
                            cabeza = cabeza.getSiguiente();
                        } else {
                            nodoAnterior.setSiguiente(nodoActual.getSiguiente());
                        }
                        tamano--;
                        return;
                    }
                    nodoAnterior = nodoActual;
                    nodoActual = nodoActual.getSiguiente();
                }
            }
        } catch (Exception e) {
        }
    }

    public void sumarDiferenciaPedido(ListaArticulos listaDiferencias) {
        NodeInterface<ListaArticulos> nodoActual = cabeza;
        while (nodoActual != null) {
            if (nodoActual.getObject().getIdPedido().equals(listaDiferencias.getIdPedido())) {
                nodoActual.getObject().sumarDiferenciaPedido(listaDiferencias);
            }
        }
    }

    public void replace(ListaArticulos pedido) {
        NodoListaEnlazada<ListaArticulos> nodo = cabeza;
        while (nodo != null) {
            if (nodo.getObject().getIdPedido().equals(pedido.getIdPedido())) {
                nodo.setObject(pedido);
                return;
            }
            nodo = nodo.getSiguiente();
        }
    }

    public ListaArticulos contains(String idPedido) {
        Iterator<NodeInterface<ListaArticulos>> iterador = this.iterator();
        while (iterador.hasNext()) {
            NodoListaEnlazada<ListaArticulos> nodo = (NodoListaEnlazada<ListaArticulos>) iterador.next();
            if (nodo.getObject().getIdPedido().equals(idPedido)) {
                return nodo.getObject();
            }
        }
        return null;
    }
}
