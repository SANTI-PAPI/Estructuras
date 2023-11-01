package com.Clases.Estructuras.grafo;

import java.io.IOException;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.Clases.Estructuras.linkedlist.*;
import com.Clases.Estructuras.queue.Cola;

public class main {
    public static void main(String[] args) throws IOException {

        Grafo grfMetropolitano = new Grafo(46);

        // Floridablnca
        grfMetropolitano.addConexion(1, 2);
        grfMetropolitano.addConexion(1, 4);
        grfMetropolitano.addConexion(2, 7);
        grfMetropolitano.addConexion(2, 5);
        grfMetropolitano.addConexion(4, 6);
        grfMetropolitano.addConexion(4, 8);

        // Bucaramanga
        grfMetropolitano.addConexion(1, 9);
        grfMetropolitano.addConexion(9, 10);
        grfMetropolitano.addConexion(9, 11);
        grfMetropolitano.addConexion(10, 12);
        grfMetropolitano.addConexion(10, 13);
        grfMetropolitano.addConexion(11, 14);
        grfMetropolitano.addConexion(11, 15);
        grfMetropolitano.addConexion(12, 16);
        grfMetropolitano.addConexion(12, 17);
        grfMetropolitano.addConexion(13, 18);
        grfMetropolitano.addConexion(15, 19);
        grfMetropolitano.addConexion(19, 20);
        grfMetropolitano.addConexion(20, 21);
        grfMetropolitano.addConexion(21, 22);
        grfMetropolitano.addConexion(22, 23);
        grfMetropolitano.addConexion(23, 24);

        // Giron
        grfMetropolitano.addConexion(5, 25);
        grfMetropolitano.addConexion(25, 26);
        grfMetropolitano.addConexion(26, 27);
        grfMetropolitano.addConexion(25, 28);
        grfMetropolitano.addConexion(28, 29);
        grfMetropolitano.addConexion(28, 30);
        grfMetropolitano.addConexion(29, 31);
        grfMetropolitano.addConexion(31, 32);
        grfMetropolitano.addConexion(32, 33);
        grfMetropolitano.addConexion(32, 34);
        grfMetropolitano.addConexion(34, 35);
        grfMetropolitano.addConexion(30, 36);
        grfMetropolitano.addConexion(36, 37);
        grfMetropolitano.addConexion(37, 38);
        grfMetropolitano.addConexion(36, 39);
        grfMetropolitano.addConexion(39, 40);
        grfMetropolitano.addConexion(40, 41);
        grfMetropolitano.addConexion(40, 42);
        grfMetropolitano.addConexion(42, 43);
        grfMetropolitano.addConexion(43, 44);

        // Piedecuesta
        // grfMetropolitano.addConexion(8, 45);

        ListaEnlazada rec2 = grfMetropolitano.recorrido(1, 15);
        grfMetropolitano.imprimir(rec2);

        int num = 0;
        String comuna;
        
    }
}