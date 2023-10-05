package com.Datos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.Clases.Articulo;
import com.Clases.Cliente;
import com.Clases.TipoDireccion;
import com.Clases.Estructuras.interfaces.node.NodeInterface;
import com.Clases.Estructuras.linkedlist.ListaArticulos;
import com.Clases.Estructuras.linkedlist.ListaClientes;
import com.Clases.Estructuras.linkedlist.ListaEnlazada;
import com.Clases.Estructuras.node.NodoListaEnlazada;

public class JSONManager {
    public static void main(String[] args) throws IOException, ParseException {
        pruebaWriteClientes();
        pruebaReadClientes();
        pruebaWriteArticulos();
        pruebaReadArticulos();
    }

    public static void pruebaWriteArticulos() throws IOException {
        Articulo articulo1 = new Articulo("Hamburguesa", 10000, false);
        JSONObject detallesArticulo1 = new JSONObject();

        detallesArticulo1.put("nombre", articulo1.getNombre());
        detallesArticulo1.put("precio", Integer.toString(articulo1.getPrecio()));
        detallesArticulo1.put("isComplejo", Boolean.toString(articulo1.isComplejo()));

        JSONObject objetoArticulo1 = new JSONObject();
        objetoArticulo1.put("articulo", detallesArticulo1);

        Articulo articulo2 = new Articulo("Perro loco", 7500, false);
        JSONObject detallesArticulo2 = new JSONObject();

        detallesArticulo2.put("nombre", articulo2.getNombre());
        detallesArticulo2.put("precio", Integer.toString(articulo2.getPrecio()));
        detallesArticulo2.put("isComplejo", Boolean.toString(articulo2.isComplejo()));

        JSONObject objetoArticulo2 = new JSONObject();
        objetoArticulo2.put("articulo", detallesArticulo2);

        Articulo articulo3 = new Articulo("Filet mignon", 50000, true);
        JSONObject detallesArticulo3 = new JSONObject();

        detallesArticulo3.put("nombre", articulo3.getNombre());
        detallesArticulo3.put("precio", Integer.toString(articulo3.getPrecio()));
        detallesArticulo3.put("isComplejo", Boolean.toString(articulo3.isComplejo()));

        JSONObject objetoArticulo3 = new JSONObject();
        objetoArticulo3.put("articulo", detallesArticulo3);

        Articulo articulo4 = new Articulo("Ratatouille", 36500, true);
        JSONObject detallesArticulo4 = new JSONObject();

        detallesArticulo4.put("nombre", articulo4.getNombre());
        detallesArticulo4.put("precio", Integer.toString(articulo4.getPrecio()));
        detallesArticulo4.put("isComplejo", Boolean.toString(articulo4.isComplejo()));

        JSONObject objetoArticulo4 = new JSONObject();
        objetoArticulo4.put("articulo", detallesArticulo4);

        JSONArray listaArticulos = new JSONArray();
        listaArticulos.add(objetoArticulo1);
        listaArticulos.add(objetoArticulo2);
        listaArticulos.add(objetoArticulo3);
        listaArticulos.add(objetoArticulo4);

        try (FileWriter file = new FileWriter("articulos.json")) {
            file.write(listaArticulos.toJSONString());
            file.flush();
        }
    }

    public static Object[][] pruebaReadArticulos() throws FileNotFoundException, IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("articulos.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray listaArticulos = (JSONArray) obj;
            ListaEnlazada<Articulo> linkedArticulos = new ListaEnlazada<>();

            listaArticulos.forEach(ar -> linkedArticulos.add(parseArticuloObject((JSONObject) ar)));

            Iterator<NodeInterface<Articulo>> iterador = linkedArticulos.iterator();
            Object[][] tablaArticulos = new Object[linkedArticulos.size()][2];
            int pos = 0;
            while(iterador.hasNext()) {
                NodoListaEnlazada<Articulo> nodo = (NodoListaEnlazada<Articulo>) iterador.next();
                tablaArticulos[pos][0] = nodo.getObject().getNombre();
                tablaArticulos[pos][1] = nodo.getObject().getPrecio();
                pos++;
            }
            
            return tablaArticulos;
        }
    }

    public static ListaArticulos getListaArticulos() throws FileNotFoundException, IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("articulos.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray listaArticulos = (JSONArray) obj;
            ListaArticulos linkedArticulos = new ListaArticulos();
            listaArticulos.forEach(ar -> linkedArticulos.add(parseArticuloObject((JSONObject) ar)));

            return linkedArticulos;
        }
    }

    public static Articulo parseArticuloObject(JSONObject articulo) {
        JSONObject objetoArticulo = (JSONObject) articulo.get("articulo");
        
        Articulo nuevoArticulo = new Articulo((String) objetoArticulo.get("nombre"), 
        Integer.parseInt((String) objetoArticulo.get("precio")), 
        Boolean.parseBoolean((String) objetoArticulo.get("isComplejo")));

        return nuevoArticulo;
    }

    public static void pruebaWriteClientes() throws IOException {
        Cliente nuevoCliente = new Cliente("3123053971", "Jose", "Montero",
                TipoDireccion.KILOMETRO, "7 Sur", "Via Piedecuesta", "Universidad Pontificia Bolivariana",
                "Floridablanca", "RUITOQUE", "El Propio");
        JSONObject detallesCliente = new JSONObject();

        detallesCliente.put("nombre", nuevoCliente.getNombre());
        detallesCliente.put("apellido", nuevoCliente.getApellido());
        detallesCliente.put("numero", nuevoCliente.getNumeroTelefono());
        detallesCliente.put("tipoDireccion", nuevoCliente.getTipoDireccion().toString());
        detallesCliente.put("direccion1", nuevoCliente.getDireccion1());
        detallesCliente.put("direccion2", nuevoCliente.getDireccion2());
        detallesCliente.put("direccionAdicional", nuevoCliente.getDireccionAdicional());
        detallesCliente.put("municipio", nuevoCliente.getMunicipio());
        detallesCliente.put("comuna", nuevoCliente.getComuna());
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

    public static ListaClientes pruebaReadClientes() throws FileNotFoundException, IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("clientes.json")) {
            Object obj = jsonParser.parse(reader);

            JSONArray listaClientes = (JSONArray) obj;
            ListaClientes linkedClientes = new ListaClientes();

            listaClientes.forEach(cl -> linkedClientes.add(parseClienteObject((JSONObject) cl)));
            return linkedClientes;
        }
    }

    public static Cliente parseClienteObject(JSONObject cliente) {
        JSONObject objetoCliente = (JSONObject) cliente.get("cliente");

        String telefono = (String) objetoCliente.get("numero");
        String nombre = (String) objetoCliente.get("nombre");
        String apellido = (String) objetoCliente.get("apellido");
        TipoDireccion tipoDir = TipoDireccion.valueOf((String) objetoCliente.get("tipoDireccion"));
        String dir1 = (String) objetoCliente.get("direccion1");
        String dir2 = (String) objetoCliente.get("direccion2");
        String dirAd = (String) objetoCliente.get("direccionAdicional");
        String municipio = (String) objetoCliente.get("municipio");
        String comuna = (String) objetoCliente.get("comuna");
        String barrio = (String) objetoCliente.get("barrio");

        return new Cliente(telefono, nombre, apellido, tipoDir, dir1, dir2, dirAd, municipio, comuna, barrio);
    }
}
