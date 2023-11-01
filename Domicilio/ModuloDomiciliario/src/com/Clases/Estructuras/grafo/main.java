package com.Clases.Estructuras.grafo;

import java.io.IOException;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.Clases.Estructuras.linkedlist.*;
import com.Clases.Estructuras.queue.Cola;

public class main {
    public static void main(String[] args) throws IOException {

        grafo grfMetropolitano = new grafo(46);

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
        switch (num) {
            case 1:
                comuna = "CALDAS";
                break;

            case 2:
                comuna = "BOSQUE";
                break;

            case 3:
                comuna = "La Cumbre";
                break;

            case 4:
                comuna = "CASCO_ANTIGUO";
                break;

            case 5:
                comuna = "LAGOS";
                break;

            case 6:
                comuna = "BUCARICA";
                break;

            case 7:
                comuna = "CAÑAVERAL";
                break;

            case 8:
                comuna = "VILLABEL";
                break;

            case 9:
                comuna = "RUITOQUE";
                break;

            case 10:
                comuna = "PROVENZA";
                break;

            case 11:
                comuna = "SUR";
                break;

            case 12:
                comuna = "PEDREGOSA";
                break;

            case 13:
                comuna = "MUTIS";
                break;

            case 14:
                comuna = "SUROCCIDENTE";
                break;

            case 15:
                comuna = "LAGOS";
                break;

            case 16:
                comuna = "CONCORDIA";
                break;

            case 17:
                comuna = "CIUDADELA";
                break;

            case 18:
                comuna = "CENTRO";
                break;

            case 19:
                comuna = "GARCIA_ROVIRA";
                break;

            case 20:
                comuna = "ORIENTAL";
                break;

            case 21:
                comuna = "MORRORICO";
                break;

            case 22:
                comuna = "OCCIDENTAL";
                break;

            case 23:
                comuna = "SAN_FRANCISCO";
                break;

            case 24:
                comuna = "NORORIENTAL";
                break;

            case 25:
                comuna = "NORTE";
                break;

            case 26:
                comuna = "ACAPULCO";
                break;

            case 27:
                comuna = "BARBOSA";
                break;

            case 28:
                comuna = "PALO_GORDO";
                break;

            case 29:
                comuna = "LLANADAS";
                break;

            case 30:
                comuna = "RIO_FRIO";
                break;

            case 31:
                comuna = "CASCO_URBANO";
                break;

            case 32:
                comuna = "CARRIZAL";
                break;

            case 33:
                comuna = "BOCAS";
                break;

            case 34:
                comuna = "LAGUNETAS";
                break;

            case 35:
                comuna = "LLANO_GRANDE";
                break;

            case 36:
                comuna = "PEÑA";
                break;

            case 37:
                comuna = "CHOCITA";
                break;

            case 38:
                comuna = "CHOCOA";
                break;

            case 39:
                comuna = "CANTALTA";
                break;

            case 40:
                comuna = "PANTANO";
                break;

            case 41:
                comuna = "MOTOSO";
                break;

            case 42:
                comuna = "CEDRO";
                break;

            case 43:
                comuna = "PARROQUIA";
                break;

            case 44:
                comuna = "SOGAMOSO";
                break;

            case 45:
                comuna = "MARTHA";
                break;

        }
    }
}