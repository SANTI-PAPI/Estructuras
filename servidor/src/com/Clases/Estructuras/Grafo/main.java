package com.Clases.Estructuras.Grafo;

import java.io.IOException;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.Clases.Estructuras.linkedlist.*;
import com.Clases.Estructuras.queue.Cola;

public class main {
    public static void main(String[] args) throws IOException {

        Grafo grfMetropolitanoFlorida = new Grafo(8);

        // Floridablnca
        grfMetropolitanoFlorida.addConexion(1, 3);
        grfMetropolitanoFlorida.addConexion(1, 2);
        grfMetropolitanoFlorida.addConexion(1, 4);
        grfMetropolitanoFlorida.addConexion(2, 7);
        grfMetropolitanoFlorida.addConexion(2, 5);
        grfMetropolitanoFlorida.addConexion(4, 6);
        grfMetropolitanoFlorida.addConexion(4, 8);

        Grafo grfMetropolitanoBucaramanga = new Grafo(16);

        // Bucaramanga
        grfMetropolitanoBucaramanga.addConexion(1, 2);
        grfMetropolitanoBucaramanga.addConexion(1, 3);
        grfMetropolitanoBucaramanga.addConexion(2, 4);
        grfMetropolitanoBucaramanga.addConexion(2, 5);
        grfMetropolitanoBucaramanga.addConexion(3, 10);
        grfMetropolitanoBucaramanga.addConexion(3, 8);
        grfMetropolitanoBucaramanga.addConexion(4, 8);
        grfMetropolitanoBucaramanga.addConexion(4, 6);
        grfMetropolitanoBucaramanga.addConexion(5, 7);
        grfMetropolitanoBucaramanga.addConexion(6, 11);
        grfMetropolitanoBucaramanga.addConexion(6, 12);
        grfMetropolitanoBucaramanga.addConexion(8, 11);
        grfMetropolitanoBucaramanga.addConexion(8, 9);
        grfMetropolitanoBucaramanga.addConexion(9, 13);
        grfMetropolitanoBucaramanga.addConexion(11, 12);
        grfMetropolitanoBucaramanga.addConexion(11, 15);
        grfMetropolitanoBucaramanga.addConexion(12, 15);
        grfMetropolitanoBucaramanga.addConexion(13, 14);
        grfMetropolitanoBucaramanga.addConexion(15, 16);
        grfMetropolitanoBucaramanga.addConexion(16, 17);

        Grafo grfMetropolitanoGiron = new Grafo(21);

        // Giron
        grfMetropolitanoGiron.addConexion(1, 2);
        grfMetropolitanoGiron.addConexion(1, 3);
        grfMetropolitanoGiron.addConexion(2, 13);
        grfMetropolitanoGiron.addConexion(2, 14);
        grfMetropolitanoGiron.addConexion(4, 5);
        grfMetropolitanoGiron.addConexion(4, 6);
        grfMetropolitanoGiron.addConexion(5, 6);
        grfMetropolitanoGiron.addConexion(5, 8);
        grfMetropolitanoGiron.addConexion(6, 7);
        grfMetropolitanoGiron.addConexion(7, 8);
        grfMetropolitanoGiron.addConexion(7, 9);
        grfMetropolitanoGiron.addConexion(8, 9);
        grfMetropolitanoGiron.addConexion(8, 11);
        grfMetropolitanoGiron.addConexion(9, 10);
        grfMetropolitanoGiron.addConexion(11, 16);
        grfMetropolitanoGiron.addConexion(11, 1);
        grfMetropolitanoGiron.addConexion(12, 1);
        grfMetropolitanoGiron.addConexion(12, 21);
        grfMetropolitanoGiron.addConexion(13, 15);
        grfMetropolitanoGiron.addConexion(13, 12);
        grfMetropolitanoGiron.addConexion(14, 12);
        grfMetropolitanoGiron.addConexion(14, 13);
        grfMetropolitanoGiron.addConexion(15, 1);
        grfMetropolitanoGiron.addConexion(16, 17);
        grfMetropolitanoGiron.addConexion(16, 12);
        grfMetropolitanoGiron.addConexion(17, 18);
        grfMetropolitanoGiron.addConexion(17, 19);
        
        

        // Piedecuesta
        // grfMetropolitano.addConexion(8, 45);
        
        ListaEnlazada<Integer> rec2 = grfMetropolitanoBucaramanga.recorrido(9, 7); 
        grfMetropolitanoBucaramanga.imprimir(rec2);
    }
}