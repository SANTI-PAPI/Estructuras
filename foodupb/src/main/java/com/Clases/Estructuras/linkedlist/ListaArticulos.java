package com.Clases.Estructuras.linkedlist;

import java.util.Iterator;

import com.Clases.Estructuras.interfaces.node.NodeInterface;
import com.Clases.Estructuras.node.NodoListaEnlazada;
import com.Clases.Articulo;

public class ListaArticulos extends ListaEnlazada<Articulo> {
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
}
