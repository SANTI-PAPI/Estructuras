package com.Json;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario los valores
        System.out.print("Ingrese el número: ");
        String numero = scanner.nextLine();

        System.out.print("Ingrese el pedido: ");
        String pedido = scanner.nextLine();

        System.out.print("Ingrese la dirección: ");
        String direccion = scanner.nextLine();

        System.out.print("Ingrese el valor: ");
        double valor = scanner.nextDouble();

        // Crear un objeto que contenga los valores
        DatosPedido datosPedido = new DatosPedido(numero, pedido, direccion, valor);

        // Escribir el objeto en un archivo JSON
        ArchivosJson.escribirJson(datosPedido);

        System.out.println("");
        System.out.println("¿Desea leer el archivo JSON?");
        System.out.println("1. Si");
        System.out.println("2. No");
        int respuesta = scanner.nextInt();
        switch(respuesta){
            case 1:
                // Leer el archivo JSON y mostrar los datos en la consola
                DatosPedido datosLeidos = ArchivosJson.leerJson();

                if (datosLeidos != null) {
                    System.out.println("Datos del pedido leído desde el archivo JSON:");
                    System.out.println("Número: " + datosLeidos.getNumero());
                    System.out.println("Pedido: " + datosLeidos.getPedido());
                    System.out.println("Dirección: " + datosLeidos.getDireccion());
                    System.out.println("Valor: " + datosLeidos.getValor());
                }
                break;

            case 2:
                break;
            
            default:
                System.out.println("Ingrese una opción valida");
                return;
        }
        scanner.close();
    }
}