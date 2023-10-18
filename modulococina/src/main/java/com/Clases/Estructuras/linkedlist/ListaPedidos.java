package com.Clases.Estructuras.linkedlist;

import java.io.Serializable;

import com.Clases.Articulo;
import com.Clases.Estructuras.node.NodoListaEnlazada;

public class ListaPedidos extends ListaEnlazada<ListaArticulos> implements Serializable {
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
}
