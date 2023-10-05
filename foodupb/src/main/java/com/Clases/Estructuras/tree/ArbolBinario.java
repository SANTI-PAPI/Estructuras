package com.Clases.Estructuras.tree;

import com.Clases.Estructuras.linkedlist.ListaEnlazada;
import com.Clases.Estructuras.node.NodoArbolBinario;

public class ArbolBinario<T> {
    NodoArbolBinario<T> raiz = null;

    public ArbolBinario(T objeto) {
        raiz = new NodoArbolBinario<T>(objeto);
    }

    public ArbolBinario(NodoArbolBinario<T> raiz) {
        this.raiz = raiz;
    }

    public ListaEnlazada<T> ordenPrefijo() {
        ListaEnlazada<T> listaPrefijo = new ListaEnlazada<>();
        return ordenPrefijo(raiz, listaPrefijo);
    }

    public ListaEnlazada<T> ordenPrefijo(NodoArbolBinario<T> nodoActual, ListaEnlazada<T> listaActual) {
        if (nodoActual != null) {
            listaActual.add(raiz.getObject());
            listaActual = ordenPrefijo(nodoActual.getSubIzquierda(), listaActual);
            listaActual = ordenPrefijo(nodoActual.getSubDerecha(), listaActual);
        }
        return listaActual;
    }

    public ListaEnlazada<T> ordenInfijo() {
        ListaEnlazada<T> listaInfijo = new ListaEnlazada<>();
        return ordenInfijo(raiz, listaInfijo);
    }

    public ListaEnlazada<T> ordenInfijo(NodoArbolBinario<T> nodoActual, ListaEnlazada<T> listaActual) {
        if (nodoActual != null) {
            listaActual = ordenInfijo(nodoActual.getSubIzquierda(), listaActual);
            listaActual.add(raiz.getObject());
            listaActual = ordenInfijo(nodoActual.getSubDerecha(), listaActual);
        }
        return listaActual;
    }

    public ListaEnlazada<T> ordenPosfijo() {
        ListaEnlazada<T> listaPosfijo = new ListaEnlazada<>();
        return ordenPosfijo(raiz, listaPosfijo);
    }

    public ListaEnlazada<T> ordenPosfijo(NodoArbolBinario<T> nodoActual, ListaEnlazada<T> listaActual) {
        if (nodoActual != null) {
            listaActual = ordenPosfijo(nodoActual.getSubIzquierda(), listaActual);
            listaActual = ordenPosfijo(nodoActual.getSubDerecha(), listaActual);
            listaActual.add(raiz.getObject());
        }
        return listaActual;
    }
}
