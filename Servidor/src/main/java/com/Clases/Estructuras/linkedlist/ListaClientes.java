package com.Clases.Estructuras.linkedlist;

import java.util.Iterator;

import com.Clases.Cliente;
import com.Clases.Estructuras.interfaces.node.NodeInterface;
import com.Clases.Estructuras.node.NodoListaEnlazada;

public class ListaClientes extends ListaEnlazada<Cliente> {
    private static final long serialVersionUID = 1525693509033756841L;
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
        }

        return false;
    }

    public boolean replace(Cliente cliente) {
        try {
            if (isEmpty()) {
                return false;
            } else {
                NodoListaEnlazada<Cliente> nodo = cabeza;
                while (nodo != null) {
                    if (nodo.getObject().getNumeroTelefono().equals(cliente.getNumeroTelefono())) {
                        nodo.setObject(cliente);
                        return true;
                    }
                    nodo = nodo.getSiguiente();
                }
            }
        } catch (Exception e) {
        }
        return false;
    }
}
