package com.Clases.Estructuras.linkedlist;

import java.util.Iterator;

import com.Clases.Cliente;
import com.Clases.Estructuras.interfaces.node.NodeInterface;
import com.Clases.Estructuras.node.NodoListaEnlazada;

public class ListaClientes extends ListaEnlazada<Cliente> {
    public Cliente contains(String numero) {
        Iterator<NodeInterface<Cliente>> iterador = this.iterator();
        while (iterador.hasNext()) {
            NodoListaEnlazada<Cliente> nodo = (NodoListaEnlazada<Cliente>) iterador.next();
            if (nodo.getObject().getNumeroTelefono().equals(numero)) {
                return nodo.getObject();
            }
        }
        return null;
    }

    public boolean remove(String numero) {
        try {
            if (isEmpty()) {
                return false;
            } else {
                Iterator<NodeInterface<Cliente>> iterador = this.iterator();
                NodoListaEnlazada<Cliente> nodo = null;
                while (iterador.hasNext()) {
                    NodoListaEnlazada<Cliente> nodoAnterior = nodo;
                    nodo = (NodoListaEnlazada<Cliente>) iterador.next();
                    if (nodo.getObject().getNumeroTelefono().equals(numero)) {
                        if (nodoAnterior == null) {
                            cabeza = nodo.getSiguiente();
                        } else {
                            nodoAnterior.setSiguiente(nodo.getSiguiente());
                        }
                        tamano--;
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("¿Excepción?");
        }

        return false;
    }
}
