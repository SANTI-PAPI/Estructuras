package com.Datos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.Clases.Cliente;
import com.Clases.TipoDireccion;

public class JSONManager {
    public static void main(String[] args) throws IOException, ParseException {
        pruebaReadClientes();
        pruebaWriteClientes();
    }
    
    public static void pruebaWriteClientes() throws IOException {
        Cliente nuevoCliente = new Cliente("3123053971", "Jose", "Montero", 
        TipoDireccion.KILOMETRO, "7 Sur", "Via Piedecuesta", "Condominio Montearroyo casa 10",
        "Floridablanca", "Vereda Los Cauchos");
        JSONObject detallesCliente = new JSONObject();

        detallesCliente.put("nombre", nuevoCliente.getNombre());
        detallesCliente.put("apellido", nuevoCliente.getApellido());
        detallesCliente.put("numero", nuevoCliente.getNumeroTelefono());
        detallesCliente.put("tipoDireccion", nuevoCliente.getTipoDireccion().toString());
        detallesCliente.put("direccion1", nuevoCliente.getDireccion1());
        detallesCliente.put("direccion2", nuevoCliente.getDireccion2());
        detallesCliente.put("direccionAdicional", nuevoCliente.getDireccionAdicional());
        detallesCliente.put("municipio", nuevoCliente.getMunicipio());
        detallesCliente.put("barrio", nuevoCliente.getBarrio());

        JSONObject objetoCliente = new JSONObject();
        objetoCliente.put("cliente", detallesCliente);

        JSONArray listaClientes = new JSONArray();
        listaClientes.add(objetoCliente);

        try (FileWriter file = new FileWriter("clientes.json")) {
            file.write(listaClientes.toJSONString());
            file.flush();
        }
    }

    public static void pruebaReadClientes() throws FileNotFoundException, IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("clientes.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray listaClientes = (JSONArray) obj;
            System.out.println(listaClientes);

            listaClientes.forEach(cl -> parseClienteObject((JSONObject) cl));
        }
    }

    public static void parseClienteObject(JSONObject cliente) {
        JSONObject objetoCliente = (JSONObject) cliente.get("cliente");

        String nombre = (String) objetoCliente.get("nombre");
        TipoDireccion tipoDir = TipoDireccion.valueOf((String) objetoCliente.get("tipoDireccion"));
        System.out.println(tipoDir);
    }
}

