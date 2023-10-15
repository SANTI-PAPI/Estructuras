package com.Datos;

import java.io.File;
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
import com.Clases.Estructuras.linkedlist.ListaPedidos;
import com.Clases.Estructuras.node.NodoListaEnlazada;

public class JSONManager {

    public static void pruebaWriteArticulos() throws IOException {
        Articulo articulo1 = new Articulo(1, "Hamburguesa", 10000, false);
        JSONObject detallesArticulo1 = new JSONObject();

        detallesArticulo1.put("id", Integer.toString(articulo1.getId()));
        detallesArticulo1.put("nombre", articulo1.getNombre());
        detallesArticulo1.put("precio", Integer.toString(articulo1.getPrecio()));
        detallesArticulo1.put("isComplejo", Boolean.toString(articulo1.isComplejo()));

        JSONObject objetoArticulo1 = new JSONObject();
        objetoArticulo1.put("articulo", detallesArticulo1);

        Articulo articulo2 = new Articulo(2, "Perro loco", 7500, false);
        JSONObject detallesArticulo2 = new JSONObject();

        detallesArticulo2.put("id", Integer.toString(articulo2.getId()));
        detallesArticulo2.put("nombre", articulo2.getNombre());
        detallesArticulo2.put("precio", Integer.toString(articulo2.getPrecio()));
        detallesArticulo2.put("isComplejo", Boolean.toString(articulo2.isComplejo()));

        JSONObject objetoArticulo2 = new JSONObject();
        objetoArticulo2.put("articulo", detallesArticulo2);

        Articulo articulo3 = new Articulo(3, "Filet mignon", 50000, true);
        JSONObject detallesArticulo3 = new JSONObject();

        detallesArticulo3.put("id", Integer.toString(articulo3.getId()));
        detallesArticulo3.put("nombre", articulo3.getNombre());
        detallesArticulo3.put("precio", Integer.toString(articulo3.getPrecio()));
        detallesArticulo3.put("isComplejo", Boolean.toString(articulo3.isComplejo()));

        JSONObject objetoArticulo3 = new JSONObject();
        objetoArticulo3.put("articulo", detallesArticulo3);

        Articulo articulo4 = new Articulo(4, "Ratatouille", 36500, true);
        JSONObject detallesArticulo4 = new JSONObject();

        detallesArticulo4.put("id", Integer.toString(articulo4.getId()));
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

        File archivo = new File("pom.xml");
        String dir = archivo.getCanonicalPath();
        dir = dir.substring(0, (dir.length() - 22));
        dir += "articulos.json";

        try (FileWriter file = new FileWriter(dir)) {
            file.write(listaArticulos.toJSONString());
            file.flush();
        }
    }

    public static Object[][] readArticulos() throws FileNotFoundException, IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        File archivo = new File("pom.xml");
        String dir = archivo.getCanonicalPath();
        dir = dir.substring(0, (dir.length() - 22));
        dir += "articulos.json";

        try (FileReader reader = new FileReader(dir)) {
            Object obj = jsonParser.parse(reader);

            JSONArray listaArticulos = (JSONArray) obj;
            ListaEnlazada<Articulo> linkedArticulos = new ListaEnlazada<>();

            listaArticulos.forEach(ar -> linkedArticulos.add(parseArticuloObject((JSONObject) ar)));

            Iterator<NodeInterface<Articulo>> iterador = linkedArticulos.iterator();
            Object[][] tablaArticulos = new Object[linkedArticulos.size()][2];
            int pos = 0;
            while (iterador.hasNext()) {
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
        File archivo = new File("pom.xml");
        String dir = archivo.getCanonicalPath();
        dir = dir.substring(0, (dir.length() - 22));
        dir += "articulos.json";

        try (FileReader reader = new FileReader(dir)) {
            Object obj = jsonParser.parse(reader);

            JSONArray listaArticulos = (JSONArray) obj;
            ListaArticulos linkedArticulos = new ListaArticulos();
            listaArticulos.forEach(ar -> linkedArticulos.add(parseArticuloObject((JSONObject) ar)));

            return linkedArticulos;
        }
    }

    public static Articulo parseArticuloObject(JSONObject articulo) {
        JSONObject objetoArticulo = (JSONObject) articulo.get("articulo");

        Articulo nuevoArticulo = new Articulo(Integer.parseInt((String) objetoArticulo.get("id")),
                (String) objetoArticulo.get("nombre"),
                Integer.parseInt((String) objetoArticulo.get("precio")),
                Boolean.parseBoolean((String) objetoArticulo.get("isComplejo")));

        return nuevoArticulo;
    }

    public static void pruebaWriteClientes() throws IOException {
        Cliente nuevoCliente1 = new Cliente("3123053971", "Jose", "Montero",
                TipoDireccion.KILOMETRO, "7 Sur", "Via Piedecuesta", "Universidad Pontificia Bolivariana",
                "Floridablanca", "RUITOQUE", "El Propio");
        JSONObject detallesCliente1 = new JSONObject();

        detallesCliente1.put("nombre", nuevoCliente1.getNombre());
        detallesCliente1.put("apellido", nuevoCliente1.getApellido());
        detallesCliente1.put("numero", nuevoCliente1.getNumeroTelefono());
        detallesCliente1.put("tipoDireccion", nuevoCliente1.getTipoDireccion().toString());
        detallesCliente1.put("direccion1", nuevoCliente1.getDireccion1());
        detallesCliente1.put("direccion2", nuevoCliente1.getDireccion2());
        detallesCliente1.put("direccionAdicional", nuevoCliente1.getDireccionAdicional());
        detallesCliente1.put("municipio", nuevoCliente1.getMunicipio());
        detallesCliente1.put("comuna", nuevoCliente1.getComuna());
        detallesCliente1.put("barrio", nuevoCliente1.getBarrio());

        JSONObject objetoCliente1 = new JSONObject();
        objetoCliente1.put("cliente", detallesCliente1);

        Cliente nuevoCliente2 = new Cliente("3216031698", "Santiago", "Nino",
                TipoDireccion.CALLE, "48", "25-34", "",
                "Piedecuesta", "PIEDECUESTA_NORTE", "No se");
        JSONObject detallesCliente2 = new JSONObject();

        detallesCliente2.put("nombre", nuevoCliente2.getNombre());
        detallesCliente2.put("apellido", nuevoCliente2.getApellido());
        detallesCliente2.put("numero", nuevoCliente2.getNumeroTelefono());
        detallesCliente2.put("tipoDireccion", nuevoCliente2.getTipoDireccion().toString());
        detallesCliente2.put("direccion1", nuevoCliente2.getDireccion1());
        detallesCliente2.put("direccion2", nuevoCliente2.getDireccion2());
        detallesCliente2.put("direccionAdicional", nuevoCliente2.getDireccionAdicional());
        detallesCliente2.put("municipio", nuevoCliente2.getMunicipio());
        detallesCliente2.put("comuna", nuevoCliente2.getComuna());
        detallesCliente2.put("barrio", nuevoCliente2.getBarrio());

        JSONObject objetoCliente2 = new JSONObject();
        objetoCliente2.put("cliente", detallesCliente2);

        Cliente nuevoCliente3 = new Cliente("3245019284", "Sergio", "Mesa",
                TipoDireccion.CALLE, "44", "41-11", "Apartamento 2901",
                "Bucaramanga", "CABECERA", "La Calle del Majestic");
        JSONObject detallesCliente3 = new JSONObject();

        detallesCliente3.put("nombre", nuevoCliente3.getNombre());
        detallesCliente3.put("apellido", nuevoCliente3.getApellido());
        detallesCliente3.put("numero", nuevoCliente3.getNumeroTelefono());
        detallesCliente3.put("tipoDireccion", nuevoCliente3.getTipoDireccion().toString());
        detallesCliente3.put("direccion1", nuevoCliente3.getDireccion1());
        detallesCliente3.put("direccion2", nuevoCliente3.getDireccion2());
        detallesCliente3.put("direccionAdicional", nuevoCliente3.getDireccionAdicional());
        detallesCliente3.put("municipio", nuevoCliente3.getMunicipio());
        detallesCliente3.put("comuna", nuevoCliente3.getComuna());
        detallesCliente3.put("barrio", nuevoCliente3.getBarrio());

        JSONObject objetoCliente3 = new JSONObject();
        objetoCliente3.put("cliente", detallesCliente3);

        JSONArray listaClientes = new JSONArray();
        listaClientes.add(objetoCliente1);
        listaClientes.add(objetoCliente2);
        listaClientes.add(objetoCliente3);

        File archivo = new File("pom.xml");
        String dir = archivo.getCanonicalPath();
        dir = dir.substring(0, (dir.length() - 22));
        dir += "clientes.json";

        try (FileWriter file = new FileWriter(dir)) {
            file.write(listaClientes.toJSONString());
            file.flush();
        }
    }

    public static ListaClientes readClientes() throws FileNotFoundException, IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        File archivo = new File("pom.xml");
        String dir = archivo.getCanonicalPath();
        dir = dir.substring(0, (dir.length() - 22));
        dir += "clientes.json";

        try (FileReader reader = new FileReader(dir)) {
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

    public static ListaPedidos getListaPedidos(String numeroTelefono)throws FileNotFoundException, IOException, ParseException {
        ListaArticulos listaArticulos = getListaArticulos();
        JSONParser jsonParser = new JSONParser();
        ListaPedidos listaPedidos = new ListaPedidos();
        
        String fileDir = "clientes//c" + String.valueOf(numeroTelefono);

        try (FileReader reader = new FileReader(fileDir)) {
            Object obj = jsonParser.parse(reader);

            JSONArray arrayPedidos = (JSONArray) obj;
            arrayPedidos.forEach(pe -> listaPedidos.add(parsePedidoObject((JSONObject) pe, listaArticulos)));
        }
        listaPedidos.sort();
        return listaPedidos;
    }

    private static ListaArticulos parsePedidoObject(JSONObject pe, ListaArticulos listaArticulos) {
        JSONObject objetoPedido = (JSONObject) pe.get("pedido");
        JSONArray arrayArticulos = (JSONArray) objetoPedido.get("articulos");
        ListaArticulos listaPedido = new ListaArticulos();
        arrayArticulos.forEach(ar -> listaPedido.add(parseArticuloObject(listaArticulos, (JSONObject) ar)));

        return listaPedido;
    }

    private static Articulo parseArticuloObject(ListaArticulos listaArticulos, JSONObject objetoPedido) {
        JSONObject objetoArticulo = (JSONObject) objetoPedido.get("articulo");
        Articulo nuevoArticulo = listaArticulos.contains(Integer.parseInt((String) objetoArticulo.get("id")));
        return nuevoArticulo;
    }

    public static void writeClientes(Cliente cliente) throws FileNotFoundException, IOException, ParseException {
        File archivo = new File("pom.xml");
        String dir = archivo.getCanonicalPath();
        dir = dir.substring(0, (dir.length() - 22));
        dir += "clientes.json";
        ListaClientes lista = readClientes();

        if (lista.contains(cliente.getNumeroTelefono()) != null) {
            lista.remove(cliente.getNumeroTelefono());
        }
        lista.add(cliente);

        Iterator<NodeInterface<Cliente>> iterador = lista.iterator();
        JSONArray listaClientes = new JSONArray();

        while (iterador.hasNext()) {
            Cliente clienteActual = iterador.next().getObject();
            JSONObject detallesCliente = new JSONObject();

            detallesCliente.put("nombre", clienteActual.getNombre());
            detallesCliente.put("apellido", clienteActual.getApellido());
            detallesCliente.put("numero", clienteActual.getNumeroTelefono());
            detallesCliente.put("tipoDireccion", clienteActual.getTipoDireccion().toString());
            detallesCliente.put("direccion1", clienteActual.getDireccion1());
            detallesCliente.put("direccion2", clienteActual.getDireccion2());
            detallesCliente.put("direccionAdicional", clienteActual.getDireccionAdicional());
            detallesCliente.put("municipio", clienteActual.getMunicipio());
            detallesCliente.put("comuna", clienteActual.getComuna());
            detallesCliente.put("barrio", clienteActual.getBarrio());

            JSONObject objetoCliente = new JSONObject();
            objetoCliente.put("cliente", detallesCliente);
            listaClientes.add(objetoCliente);
        }

        try (FileWriter file = new FileWriter(dir)) {
            file.write(listaClientes.toJSONString());
            file.flush();
        }
    }
}
