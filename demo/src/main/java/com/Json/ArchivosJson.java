package com.Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivosJson {

    private static final String nombreArchivo = "pedido.json";

    public static void escribirJson(DatosPedido datosPedido) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Convertir el objeto a formato JSON
        String json = gson.toJson(datosPedido);

        // Escribir el JSON en un archivo
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write(json);
            System.out.println("Archivo JSON creado y escrito exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DatosPedido leerJson() {
        DatosPedido datosPedido = null;
        Gson gson = new GsonBuilder().create();

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            String numero = jsonObject.get("numero").getAsString();
            String pedido = jsonObject.get("pedido").getAsString();
            String direccion = jsonObject.get("direccion").getAsString();
            double valor = jsonObject.get("valor").getAsDouble();

            datosPedido = new DatosPedido(numero, pedido, direccion, valor);
            System.out.println("Archivo JSON leído exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return datosPedido;
    }
}