package com.Clases.Estructuras.linkedlist;

import java.io.Serializable;

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
}
