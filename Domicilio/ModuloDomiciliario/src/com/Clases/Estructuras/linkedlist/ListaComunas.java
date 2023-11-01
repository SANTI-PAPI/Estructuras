package com.Clases.Estructuras.linkedlist;

import com.Clases.Comuna;
import com.Clases.Estructuras.node.NodoListaEnlazada;

public class ListaComunas extends ListaEnlazada<Comuna> {
    public boolean sort() {
        if (size() == 1) {
            return true;
        }
        if (size() == 0) {
            return false;
        }
        Comuna[] comunas = toArray();
        for (int gap = comunas.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < comunas.length; i++) {
                Comuna actual = comunas[i];
                int j;
                for (j = i; j >= gap && comunas[j - gap].getValorRecorrido() > actual.getValorRecorrido(); j -= gap) {
                    comunas[j] = comunas[j - gap];
                }
                comunas[j] = actual;
            }
        }
        clear();
        add(comunas);
        return true;
    }

    @Override
    public Comuna[] toArray() {
        NodoListaEnlazada<Comuna> nodo = cabeza;
        Comuna[] arreglo = new Comuna[tamano];
        for (int i = 0; i < tamano; i++) {
            arreglo[i] = nodo.getObject();
            nodo = nodo.getSiguiente();
        }
        return arreglo;
    }

}
