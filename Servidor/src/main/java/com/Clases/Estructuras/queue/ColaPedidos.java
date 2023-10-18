package com.Clases.Estructuras.queue;

import java.util.Iterator;

import com.Clases.Articulo;
import com.Clases.Estructuras.interfaces.node.NodeInterface;
import com.Clases.Estructuras.linkedlist.ListaArticulos;

public class ColaPedidos extends ColaPrioridad<Articulo> {

    public ColaPedidos(int cantidadPrioridad) {
        super(cantidadPrioridad);
        colaPrioridad = new ColaArticulos[cantidadPrioridad];
        for (int i = 0; i < cantidadPrioridad; i++) {
            colaPrioridad[i] = new ColaArticulos();
        }
    }

    public void alterarCola(ListaArticulos listaDiferencias) {
        Iterator<NodeInterface<Articulo>> iterador = listaDiferencias.iterator();
        while (iterador.hasNext()) {
            Articulo articuloActual = iterador.next().getObject();
            int cantidadActual = articuloActual.getCantidad();
            if (cantidadActual > 0) {
                for (int i = 0; i < cantidadActual; i++) {
                    insert(articuloActual);
                }
            } else if (cantidadActual < 0) {
                cantidadActual = (-cantidadActual);
                articuloActual.setCantidad(cantidadActual);
                forceRemove(articuloActual);
            }
        }
        int nuevoTamano = 0;
        for (int i = 0; i < cantidad; i++) {
            ColaArticulos colaActual = (ColaArticulos) colaPrioridad[i];
            if (!colaActual.isEmpty()) {
                nuevoTamano = colaActual.size();
            }
        }
        tamano = nuevoTamano;
    }

    private void forceRemove(Articulo articuloActual) {
        for (int i = 0; i < cantidad; i++) {
            ColaArticulos colaActual = (ColaArticulos) colaPrioridad[i];
            if (!colaActual.isEmpty()) {
                if (colaActual.contains(articuloActual.getId(), articuloActual.getIdPedido())) {
                    colaActual.remove(articuloActual);
                    return;
                }
            }
        }
    }

}