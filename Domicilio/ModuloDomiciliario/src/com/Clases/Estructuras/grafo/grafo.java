package com.Clases.Estructuras.grafo;

import java.util.Iterator;

import com.Clases.Estructuras.interfaces.node.NodeInterface;
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

    public ListaEnlazada<Integer> recorrido(int inicial, int fin) {
        PilaArray<Integer> colita = new PilaArray<>();
        boolean[] visitados = new boolean[numeroVertices];
        ListaEnlazada<Integer> record = new ListaEnlazada<>();
        colita.push(inicial);
        int pasos = -1;
        while (!colita.isEmpty()) {
            visitados[(int) inicial] = true;
            int valid = (int) colita.pop();
            pasos++;
            record.add(valid);
            if (valid == fin) {
                record.add(pasos);
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
                            pasos++;
                            record.add(pasos);
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

    public int comunaToInteger(String comuna) {
        switch (comuna) {
            case "CALDAS":
                return 1;

            case "BOSQUE":
                return 2;

            case "CUMBRE":
                return 3;

            case "CASCO_ANTIGUO":
                return 4;

            case "LAGOS":
                return 5;

            case "BUCARICA":
                return 6;

            case "CANAVERAL":
                return 7;

            case "VILLABEL":
                return 8;

            case "PROVENZA":
                return 9;

            case "SUR":
                return 10;

            case "PEDREGOSA":
                return 11;

            case "MUTIS":
                return 12;

            case "SUROCCIDENTE":
                return 13;

            case "CONCORDIA":
                return 14;

            case "CIUDADELA":
                return 16;

            case "CENTRO":
                return 17;

            case "GARCIA_ROVIRA":
                return 18;

            case "ORIENTAL":
                return 19;

            case "MORRORRICO":
                return 20;

            case "OCCIDENTAL":
                return 21;

            case "SAN_FRANCISCO":
                return 22;

            case "NORORIENTAL":
                return 23;

            case "NORTE":
                return 24;

            case "RUITOQUE":
                return 25;

            case "ACAPULCO":
                return 26;

            case "BARBOSA":
                return 27;
            case "PALO_GORDO":
                return 28;

            case "LLANADAS":
                return 29;

            case "RIO_FRIO":
                return 30;

            case "CASCO_URBANO":
                return 31;

            case "CARRIZAL":
                return 32;

            case "BOCAS":
                return 33;

            case "LAGUNETAS":
                return 34;

            case "LLANO_GRANDE":
                return 35;

            case "PENAS":
                return 36;

            case "CHOCOITA":
                return 37;

            case "CHOCOA":
                return 38;

            case "CANTALTA":
                return 39;

            case "PANTANO":
                return 40;

            case "MOTOSO":
                return 41;

            case "CEDRO":
                return 42;

            case "PARROQUIA":
                return 43;

            case "SOGAMOSO":
                return 44;

            case "MARTHA":
                return 45;
        }

        return -1;
    }

}
