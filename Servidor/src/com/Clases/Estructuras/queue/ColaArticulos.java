package com.Clases.Estructuras.queue;

import com.Clases.Articulo;
import com.Clases.Estructuras.linkedlist.ListaArticulos;

public class ColaArticulos extends Cola<Articulo> {
    public ColaArticulos() {
        lista = new ListaArticulos();
    }

    public boolean contains(int id, String idPedido) {
        return ((ListaArticulos) lista).contains(id, idPedido);
    }

    public void remove(Articulo articuloActual) {
        for (int i = 0; i < articuloActual.getCantidad(); i++) {
            ((ListaArticulos) lista).remove(articuloActual.getId(), articuloActual.getIdPedido());
        }
    }
}
