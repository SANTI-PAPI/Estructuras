package com.Clases.Estructuras.linkedlist;

import com.Clases.Estructuras.interfaces.linkedlist.LinkedListInterface;
import com.Clases.Estructuras.interfaces.node.NodeInterface;
import com.Clases.Estructuras.node.NodoDobleEnlazado;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListaDobleEnlazadaCircular<T> implements LinkedListInterface<T> {
    private NodoDobleEnlazado<T> head;
    private NodoDobleEnlazado<T> tail;
    private NodoDobleEnlazado<T> inode;
    private int size;

    @Override
    public boolean add(T object) {
        if (object != null) {
            try {
                if (isEmpty()) {
                    tail = head = new NodoDobleEnlazado<T>(object);
                    head.setPrevious(tail);
                    tail.setNext(head);
                } else { 
                    tail.setNext(new NodoDobleEnlazado<T>(object));
                    tail.getNext().setPrevious(tail);
                    tail = tail.getNext();
                    head.setPrevious(tail);
                    tail.setNext(head);
                }
                size++;
                return true;
            } catch (Exception e) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
            }
        }
        return false;
    }

    @Override
    public boolean add(NodeInterface<T> node, T object) {
        Iterator iterator = iterator();
        NodoDobleEnlazado nodeTemp;
        if (object != null) {
            try {
                if (isEmpty()) {
                    tail = head = new NodoDobleEnlazado<>(object);
                    head.setPrevious(tail);
                    tail.setNext(head);
                }
                if (tail.equals(node)) {
                    tail.setNext(new NodoDobleEnlazado<>(object));
                    tail.getNext().setPrevious(tail);
                    tail = tail.getNext();
                    head.setPrevious(tail);
                    tail.setNext(tail);
                } else {
                    while (iterator.hasNext()) {
                        nodeTemp = (NodoDobleEnlazado) iterator.next();
                        if (nodeTemp.isEquals(node.getObject())) {

                            NodoDobleEnlazado nodeToAdd = new NodoDobleEnlazado<T>(object);
                            if (nodeTemp.isEquals(tail.getObject())) {
                                tail = nodeToAdd;
                                head.setPrevious(tail);
                                tail.setNext(head);
                            }
                            nodeToAdd.setNext(nodeTemp.getNext());
                            nodeTemp.getNext().setPrevious(nodeToAdd);
                            nodeTemp.setNext(nodeToAdd);
                            nodeToAdd.setPrevious(nodeTemp);
                        }
                    }
                }
                size++;
                return true;
            } catch (Exception e) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
            }
        }
        return false;
    }

    @Override
    public boolean add(NodeInterface<T> node, NodeInterface<T> next) {
        // add(node , next.getObject());
        Iterator iter = iterator();
        NodoDobleEnlazado nodeTemp;
        if (node != null && next != null) {
            tail.setNext((NodoDobleEnlazado<T>) next);
            tail.getNext().setPrevious(tail);
            tail = tail.getNext();
            head.setPrevious(tail);
            tail.setNext(head);
        }
        try {
            while (iter.hasNext()) {
                nodeTemp = (NodoDobleEnlazado) iter.next();
                if (nodeTemp.isEquals(node.getObject())) {

                    NodoDobleEnlazado newNode = (NodoDobleEnlazado) next;
                    if (nodeTemp.isEquals(tail.getObject())) {
                        tail = newNode;
                        head.setPrevious(tail);
                        tail.setNext(head);
                    }
                    newNode.setNext(nodeTemp.getNext());
                    nodeTemp.getNext().setPrevious(newNode);
                    nodeTemp.setNext(newNode);
                    newNode.setPrevious(nodeTemp);
                }
            }
            size++;
            return true;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public boolean add(T[] objects) {
        Iterator iter = iterator();
        if (objects != null) {
            try {
                for (T object : objects) {
                    add(object);
                }
                return true;
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return false;
    }

    @Override
    public boolean add(NodeInterface<T> node, T[] objects) {
        Iterator iter = iterator();
        NodoDobleEnlazado nodeTemp;
        if (objects.length > 0) {
            try {
                if (isEmpty()) {
                    for (int i = 0; i < objects.length; i++) {
                        if (head == null) {
                            head = tail = new NodoDobleEnlazado<>(objects[i]);
                            size++;
                        }
                    }
                } else {
                    while (iter.hasNext()) {
                        nodeTemp = (NodoDobleEnlazado) iter.next();
                        if (nodeTemp.isEquals(node.getObject())) {
                            for (int i = 0; i < objects.length; i++) {

                                NodoDobleEnlazado newNode = new NodoDobleEnlazado<T>(objects[i]);

                                newNode.setNext(nodeTemp.getNext());
                                nodeTemp.getNext().setPrevious(newNode);
                                nodeTemp.setNext(newNode);
                                newNode.setPrevious(nodeTemp);
                                size++;
                            }
                        }
                    }
                    return true;
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return false;
    }

    @Override
    public boolean addOnHead(T object) {
        if (object != null) {
            try {
                if (isEmpty()) {
                    head = tail = new NodoDobleEnlazado<T>(object);
                    head.setPrevious(tail);
                    tail.setNext(head);
                } else {
                    NodoDobleEnlazado newNode = new NodoDobleEnlazado(object);

                    newNode.setNext(head);
                    head.setPrevious(newNode);
                    head = newNode;
                    head.setPrevious(tail);
                    tail.setNext(head);
                }
                size++;
                return true;
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return false;
    }

    @Override
    public boolean addOnHead(T[] objects) {
        if (objects.length > 0) {
            try {
                for (T object : objects) {
                    addOnHead(object);
                }
                return true;
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return false;
    }

    @Override
    public boolean clear() {
        head = null;
        size = 0;
        return true;
    }

    @Override
    public ListaDobleEnlazadaCircular<T> cloneList() {
        try {
            if (!isEmpty()) {
                Iterator<NodeInterface<T>> iter = iterator();
                ListaDobleEnlazadaCircular<T> cloneNode = new ListaDobleEnlazadaCircular<T>();
                while (iter.hasNext()) {
                    cloneNode.add(iter.next().getObject());
                }
                return cloneNode;
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    public boolean contains(T object) {
        Iterator iter = iterator();
        if (!isEmpty()) {
            if (object != null) {
                while (iter.hasNext()) {
                    NodoDobleEnlazado newNode = (NodoDobleEnlazado) iter.next();
                    if (newNode.isEquals(object)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean contains(T[] objects) {
        if (!isEmpty()) {
            try {
                int cont = 1;
                for (T object : objects) {
                    if (contains(object)) {
                        cont++;
                    }
                }
                if (cont == objects.length) {
                    return true;
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return false;
    }

    @Override
    public NodeInterface<T> nodeOf(T object) {
        Iterator iter = iterator();
        if (object != null) {
            try {
                while (iter.hasNext()) {
                    NodoDobleEnlazado newNode = (NodoDobleEnlazado) iter.next();
                    if (newNode.isEquals(object)) {
                        return newNode;
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return new NodoDobleEnlazado<>(null);
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public T get() {
        if (!isEmpty()) {
            return head.getObject();
        }
        return null;
    }

    @Override
    public T[] get(int n) {
        T[] arrRetur = (T[]) new Object[n];
        Iterator iter = iterator();
        if (!isEmpty()) {
            if (size() >= n) {
                for (int i = 0; i < n; i++) {
                    NodoDobleEnlazado<T> nodeArr = (NodoDobleEnlazado<T>) iter.next();
                    arrRetur[i] = nodeArr.getObject();
                }
            } else {
                System.out.println("te pasas");
            }
        }

        return arrRetur;
    }

    @Override
    public T getPrevious(NodeInterface<T> node) {
        Iterator iter = iterator();
        T objReturn = null;
        try {
            if (!isEmpty()) {
                node = (NodeInterface<T>) nodeOf(node.getObject());
                objReturn = ((NodoDobleEnlazado<T>) node).getPrevious().getObject();
                return objReturn;
            }
        } catch (Exception ex) {
            objReturn = null;
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, ex.getMessage(), ex);
        }
        return objReturn;
    }

    private NodoDobleEnlazado<T> getPreviousNode(NodeInterface<T> node) {
        NodoDobleEnlazado<T> nodeTemp = (NodoDobleEnlazado<T>) node;
        if (!isEmpty()) {
            return nodeTemp.getPrevious();
        }
        return nodeTemp.getPrevious();
    }

    @Override
    public T getFromEnd() {
        if (!isEmpty()) {
            return tail.getObject();
        }
        return null;
    }

    @Override
    public T[] getFromEnd(int n) {
        T[] arrTail = (T[]) new Object[n];
        NodoDobleEnlazado<T> nodeTemp;
        Iterator iter = iteratorToBack();
        if (size() > n) {
            for (int i = 0; i < n; i++) {
                nodeTemp = (NodoDobleEnlazado<T>) iter.next();
                arrTail[i] = nodeTemp.getObject();
                nodeTemp.getNext();
            }
        }
        return arrTail;
    }

    @Override
    public T pop() {
        if (!isEmpty()) {
            NodoDobleEnlazado<T> nodeTemp = head;
            head = head.getNext();
            head.setPrevious(tail);
            tail.setNext(head);
            size--;
            return nodeTemp.getObject();
        }
        return null;
    }

    @Override
    public boolean remove(T object) {// impecable
        Iterator iter;
        NodoDobleEnlazado<T> nodeTemp = null;
        if (object != null && (!isEmpty()) && (contains(object))) {
            try {
                if (head.isEquals(object)) {
                    head = head.getNext();
                    head.setPrevious(tail);
                    tail.setNext(head);
                    size--;
                    return true;
                } else if (tail.isEquals(object)) {
                    T nodeFin = tail.getPrevious().getObject();
                    tail = (NodoDobleEnlazado<T>) nodeOf(nodeFin);
                    head.setPrevious(tail);
                    tail.setNext(head);
                    size--;
                    return true;
                }
                NodoDobleEnlazado<T> nodeU;
                iter = iterator();
                while (iter.hasNext()) {
                    nodeU = nodeTemp;
                    nodeTemp = (NodoDobleEnlazado<T>) iter.next();
                    if (nodeTemp.isEquals(object)) {
                        nodeU.setNext(nodeTemp.getNext());
                        nodeTemp.getNext().setPrevious(nodeU);
                        size--;
                        return true;
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return false;
    }

    @Override
    public boolean remove(NodeInterface<T> node) {
        if (isEmpty()) {
            return false;
        } else {
            try {
                return remove(node.getObject());
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(T[] objects) {
        if (!isEmpty() && objects != null) {
            try {
                for (T objet : objects) {
                    if (objet != null) {
                        remove(objet);
                    }
                }
                return true;
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return false;
    }

    /*
     * private boolean retain(T[] objects) {
     * try {
     * for (T objet : objects) {
     * if (objet != null) {
     * if (!contains(objet)) {
     * remove(objet);
     * 
     * }
     * }
     * }
     * return true;
     * } catch (Exception ex) {
     * Logger.getLogger(this.getClass().getName()).log(Level.WARNING,
     * ex.getMessage(), ex);
     * }
     * return false;
     * }
     * 
     * 
     */
    @Override
    public boolean retainAll(T[] objects) {
        if (objects != null && !isEmpty()) {
            try {
                int cont = 0;
                Iterator iter = iterator();
                T[] arrTemp = (T[]) new Object[objects.length];
                NodoDobleEnlazado<T> nodeTemp;
                while (iter.hasNext()) {
                    nodeTemp = (NodoDobleEnlazado<T>) iter.next();
                    for (int i = 0; i < objects.length; i++) {
                        if (nodeTemp.isEquals(objects[i])) {
                            arrTemp[cont] = nodeTemp.getObject();
                            cont++;
                        }
                    }
                }
                clear();
                add(arrTemp);
                return true;
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public LinkedListInterface<T> subList(NodeInterface<T> from, NodeInterface<T> to) {
        if (validateSubList(from, to) && (!isEmpty())) {
            Iterator iter = iterator();
            NodoDobleEnlazado<T> ini;
            ListaDobleEnlazadaCircular subLista = new ListaDobleEnlazadaCircular();
            while (iter.hasNext()) {
                ini = (NodoDobleEnlazado<T>) iter.next();
                if (ini.isEquals(from.getObject())) {
                    subLista.add(ini.getObject());
                    ini = ini.getNext();
                    while (!ini.isEquals(to.getObject())) {
                        subLista.add(ini.getObject());
                        ini = ini.getNext();
                    }
                    subLista.add(ini.getObject());
                    return subLista;
                }
            }
            return subLista;
        } else {
            System.out.println("no entra");
        }
        return null;
    }

    private boolean validateSubList(NodeInterface<T> from, NodeInterface<T> to) {
        Iterator iter = iterator();
        NodoDobleEnlazado<T> nodeTemp;
        if (from != null && to != null) {
            while (iter.hasNext()) {
                nodeTemp = (NodoDobleEnlazado<T>) iter.next();
                if (nodeTemp.isEquals(from.getObject())) {
                    return true;
                } else if (nodeTemp.isEquals(to.getObject())) {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public T[] toArray() {
        NodeInterface<T> nodo = head;
        T[] arreglo = (T[]) new Object[size];
        Iterator iterator = iterator();
        int i = 0;
        while (iterator.hasNext()) {
            nodo = (NodeInterface<T>) iterator.next();
            arreglo[i++] = (T) nodo.getObject();
        }
        return arreglo;
    }
    /*
     * @Override
     * public T[] toArray() {
     * T[] objectArr ;
     * DoubleListNode nodo = head;
     * T[] arreglo = (T[]) new Object[size];
     * 
     * for (int i = 0; i < size; i++) {
     * arreglo[i] = (T) nodo.getObject();
     * nodo = nodo.getNext();
     * }
     * return arreglo;
     * }
     */

    @Override
    public boolean sortObjectsBySize() {
        // shell
        if (!isEmpty()) {
            try {
                T[] arryLis = toArray();
                for (int gap = arryLis.length / 2; gap > 0; gap /= 2) {
                    for (int i = gap; i < arryLis.length; i++) {
                        T tempVal = arryLis[i];
                        int j = i;
                        while (j >= gap && arryLis[j - gap].toString().length() > tempVal.toString().length()) {
                            arryLis[j] = arryLis[j - gap];
                            j -= gap;
                        }
                        arryLis[j] = tempVal;
                    }
                }
                clear();
                add(arryLis);
                return true;
            } catch (Exception e) {
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
            }
        }
        return false;
    }

    @Override
    public Iterator<NodeInterface<T>> iterator() {
        inode = head;
        return new Iterator<NodeInterface<T>>() {
            @Override
            public boolean hasNext() {
                return inode != null;
            }

            @Override
            public NodeInterface<T> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                NodeInterface<T> node = inode;
                inode = inode.getNext();
                if (inode == head) {
                    inode = null;
                }
                return node;
            }
        };
    }

    public Iterator<NodeInterface<T>> iteratorToBack() {
        inode = tail;
        return new Iterator<NodeInterface<T>>() {
            @Override
            public boolean hasNext() {
                return inode != null;
            }

            @Override
            public NodeInterface<T> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                NodeInterface<T> node = inode;
                inode = inode.getPrevious();
                if (inode == tail) {
                    inode = null;
                }
                return node;
            }
        };
    }

    public Iterator iteratorObj() {
        inode = head;
        return new Iterator() {
            @Override
            public boolean hasNext() {
                return inode != null;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                NodeInterface<T> node = inode;
                inode = inode.getNext();
                return node.getObject();
            }
        };
    }

    public void imprimir() {
        Iterator<NodeInterface<T>> iterator = iterator();
        for (int i = 0; i < size; i++) {
            System.out.println(iterator.next().getObject());
        }
    }

    public boolean invertir(int k) {
        if (k > 0 && !isEmpty()) {
            int tamaño = (size() / k);
            NodoDobleEnlazado<T> nodeIter;
            NodoDobleEnlazado<T> firstNode;
            NodoDobleEnlazado<T> kNode;
            NodoDobleEnlazado<T> prevNode;
            NodoDobleEnlazado<T> moveNode;
            NodoDobleEnlazado<T> nodeFor;
            NodoDobleEnlazado<T> nodeSis;
            nodeIter = head;
            for (int i = 0; i < tamaño; i++) {
                firstNode = nodeIter;
                kNode = firstNode;
                for (int j = 1; j < k; j++) {
                    kNode = kNode.getNext();
                }
                if (size() == k) {
                    nodeFor = getPreviousNode(kNode);
                    moveNode = firstNode.getNext();
                    nodeFor.setNext(firstNode);
                    tail = firstNode;
                    head = kNode;
                    kNode.setNext(moveNode);
                    return true;
                }
                if (kNode.isEquals(tail.getObject())) {
                    if (k == 2) {
                        prevNode = getPreviousNode(kNode);
                        moveNode = getPreviousNode(firstNode);
                        nodeSis = firstNode.getNext();
                        prevNode.setNext(firstNode);
                        firstNode.setNext(head);
                        moveNode.setNext(kNode);
                        kNode.setNext(firstNode);
                        tail = firstNode;
                    }
                    if (k > 2) {
                        prevNode = getPreviousNode(kNode);
                        moveNode = getPreviousNode(firstNode);
                        nodeSis = firstNode.getNext();
                        prevNode.setNext(firstNode);
                        firstNode.setNext(head);
                        moveNode.setNext(kNode);
                        kNode.setNext(nodeSis);
                        tail = firstNode;
                    }
                }
                if (firstNode.isEquals(head.getObject())) {
                    if (k == 2) {
                        prevNode = firstNode.getNext();
                        moveNode = getPreviousNode(kNode);
                        moveNode.setNext(firstNode);
                        firstNode.setNext(kNode.getNext());
                        kNode.setNext(firstNode);
                        head = kNode;
                        nodeIter = firstNode.getNext();
                    }
                    if (k > 2) {
                        prevNode = firstNode.getNext();
                        moveNode = getPreviousNode(kNode);
                        moveNode.setNext(firstNode);
                        firstNode.setNext(kNode.getNext());
                        kNode.setNext(prevNode);
                        head = kNode;
                        nodeIter = firstNode.getNext();
                    }
                }
                if ((firstNode != head) && (kNode != tail) && (kNode != head) && (firstNode != tail)) {
                    if (k == 2) {
                        nodeFor = kNode.getNext();
                        prevNode = getPreviousNode(kNode);
                        moveNode = getPreviousNode(firstNode);
                        nodeSis = firstNode.getNext();
                        moveNode.setNext(kNode);
                        kNode.setNext(firstNode);
                        prevNode.setNext(firstNode);
                        firstNode.setNext(nodeFor);
                        nodeIter = firstNode.getNext();
                    }
                    if (k > 2) {
                        nodeFor = kNode.getNext();
                        prevNode = getPreviousNode(kNode);
                        moveNode = getPreviousNode(firstNode);
                        nodeSis = firstNode.getNext();
                        moveNode.setNext(kNode);
                        kNode.setNext(nodeSis);
                        prevNode.setNext(firstNode);
                        firstNode.setNext(nodeFor);
                        nodeIter = firstNode.getNext();
                    }
                }
            }
            return true;
        }

        return false;
    }
}
