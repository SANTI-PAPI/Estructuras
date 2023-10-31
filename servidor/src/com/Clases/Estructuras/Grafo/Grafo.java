package com.Clases.Estructuras.Grafo;

import java.util.Iterator;

import com.Clases.Estructuras.linkedlist.*;
import com.Clases.Estructuras.node.NodoListaEnlazada;
import com.Clases.Estructuras.stack.*;
import com.Clases.Estructuras.stack.PilaArray;

public class Grafo {

    private ListaEnlazada<ListaEnlazada<Integer>> grafo;
    int numeroVertices;

    public Grafo(int numVertices) {
        this.numeroVertices = numVertices;
        grafo = new ListaEnlazada<>();
        for (int i = 0; i < numVertices; i++) {

            ListaEnlazada<Integer> ffe = new ListaEnlazada();
            ffe.add(i + 1);
            grafo.add(ffe);
        }
    }

    public boolean addConexion(int vertice, int verticeConect) {

        Iterator iterador = grafo.iterator();
        NodoListaEnlazada<ListaEnlazada> nodo = null;

        while (iterador.hasNext()) {

            nodo = (NodoListaEnlazada<ListaEnlazada>) iterador.next();

            if ((int) nodo.getObject().get() == vertice) {

                nodo.getObject().add(verticeConect);

                return true;

            }

        }

        return false;
    }

    public <T> boolean removeConexion(int vertice, T verticeConect) {

        Iterator iterador = grafo.iterator();
        NodoListaEnlazada<ListaEnlazada> nodo = null;

        while (iterador.hasNext()) {

            nodo = (NodoListaEnlazada<ListaEnlazada>) iterador.next();
            if ((int) nodo.getObject().get() == vertice) {

                nodo.getObject().remove(verticeConect);
                return true;

            }

        }

        return false;
    }

    public ListaEnlazada recorrido(int inicial, int fin) {
        PilaArray<Integer> colita = new PilaArray<>();
        boolean[] visitados = new boolean[numeroVertices];
        ListaEnlazada record = new ListaEnlazada<>();
        colita.push(inicial);
        while (!colita.isEmpty()) {
            visitados[(int) inicial] = true;
            int valid = (int) colita.pop();
            System.out.println(valid);
            record.add(valid);
            if (valid == fin) {
                return record;
            }
            // System.out.println("vea");
            Iterator iter = grafo.iterator();
            NodoListaEnlazada<ListaEnlazada> indexgrafo = null;
            // System.out.println("vea2");
            while (iter.hasNext()) {
                // System.out.println("glls");
                indexgrafo = (NodoListaEnlazada<ListaEnlazada>) iter.next();

                Iterator iterador = indexgrafo.getObject().iterator();

                if ((int) indexgrafo.getObject().cabeza.getObject() == valid) {

                    NodoListaEnlazada indexlista = null;

                    while (iterador.hasNext()) {
                        // System.out.println("flag");
                        indexlista = (NodoListaEnlazada) iterador.next();
                        if ((int) indexlista.getObject() == fin) {
                            System.out.println(indexlista.getObject());
                            record.add(indexlista.getObject());
                            return record;
                        }
                        colita.push((int) indexlista.getObject());
                    }
                }

            }

        }

        return record;
    }

    public void imprimir(ListaEnlazada<Integer> rec) {
        Iterator iterador = rec.iterator();
        NodoListaEnlazada<Integer> bb = null;
        while (iterador.hasNext()) {
            bb = (NodoListaEnlazada<Integer>) iterador.next();
            System.out.println(bb.getObject());
        }
    }

}
