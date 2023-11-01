package com.Clases.Estructuras.grafo;

import java.util.Iterator;

import com.Clases.Estructuras.interfaces.node.NodeInterface;
import com.Clases.Estructuras.linkedlist.*;
import com.Clases.Estructuras.node.NodoListaEnlazada;
import com.Clases.Estructuras.stack.*;
import com.Clases.Estructuras.stack.PilaArray;

public class grafo {

    private ListaEnlazada<ListaEnlazada<Integer>> grafo;
    int numeroVertices;

    public grafo(int numVertices) {
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

    public ListaEnlazada<Integer> recorrido(int inicial, int fin) {
        PilaArray<Integer> colita = new PilaArray<>();
        boolean[] visitados = new boolean[numeroVertices];
        ListaEnlazada<Integer> record = new ListaEnlazada<>();
        colita.push(inicial);
        while (!colita.isEmpty()) {
            visitados[(int) inicial] = true;
            int valid = (int) colita.pop();
            record.add(valid);
            if (valid == fin) {
                return record;
            }
            // System.out.println("vea");
            Iterator<NodeInterface<ListaEnlazada<Integer>>> iter = grafo.iterator();
            NodoListaEnlazada<ListaEnlazada<Integer>> indexgrafo = null;
            // System.out.println("vea2");
            while (iter.hasNext()) {
                // System.out.println("glls");
                indexgrafo = (NodoListaEnlazada<ListaEnlazada<Integer>>) iter.next();

                Iterator<NodeInterface<Integer>> iterador = indexgrafo.getObject().iterator();

                if ((int) indexgrafo.getObject().cabeza.getObject() == valid) {

                    NodoListaEnlazada<Integer> indexlista = null;

                    while (iterador.hasNext()) {
                        // System.out.println("flag");
                        indexlista = (NodoListaEnlazada) iterador.next();
                        if ((int) indexlista.getObject() == fin) {
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

    public void imprimir(ListaEnlazada rec) {
        Iterator iterador = rec.iterator();
        NodoListaEnlazada bb = null;
        while (iterador.hasNext()) {
            bb = (NodoListaEnlazada) iterador.next();
            System.out.println(bb.getObject());
        }
    }

}
