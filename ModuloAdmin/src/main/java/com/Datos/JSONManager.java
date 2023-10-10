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
import org.apache.commons.codec.digest.DigestUtils;

public class JSONManager {

    public static void writeAdministrador(String id, String nombre, String pass) throws IOException, ParseException {
        pass = DigestUtils.sha1Hex(pass);

        File archivo = new File("pom.xml");
        String dir = archivo.getCanonicalPath();
        dir = dir.substring(0, (dir.length() - 19));
        dir += "usuarios\\admin\\u-" + id + ".json";

        JSONObject userObject = new JSONObject();
        userObject.put("nombre", nombre);
        userObject.put("password", pass);

        try (FileWriter file = new FileWriter(dir)) {
            file.write(userObject.toJSONString());
            file.flush();
        }
    }

    public static void writeOperador(String id, String nombre, String pass) throws IOException, ParseException {
        pass = DigestUtils.sha1Hex(pass);

        File archivo = new File("pom.xml");
        String dir = archivo.getCanonicalPath();
        dir = dir.substring(0, (dir.length() - 19));
        dir += "usuarios\\operador\\u-" + id + ".json";

        JSONObject userObject = new JSONObject();
        userObject.put("nombre", nombre);
        userObject.put("password", pass);

        try (FileWriter file = new FileWriter(dir)) {
            file.write(userObject.toJSONString());
            file.flush();
        }
    }

    public static void writeDomiciliario(String id, String nombre, String pass) throws IOException, ParseException {
        pass = DigestUtils.sha1Hex(pass);

        File archivo = new File("pom.xml");
        String dir = archivo.getCanonicalPath();
        dir = dir.substring(0, (dir.length() - 19));
        dir += "usuarios\\domiciliario\\u-" + id + ".json";

        JSONObject userObject = new JSONObject();
        userObject.put("nombre", nombre);
        userObject.put("password", pass);

        try (FileWriter file = new FileWriter(dir)) {
            file.write(userObject.toJSONString());
            file.flush();
        }
    }

    public static String getUsuario(String id, String pass) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        pass = DigestUtils.sha1Hex(pass);

        File archivo = new File("pom.xml");
        String dir = archivo.getCanonicalPath();
        dir = dir.substring(0, (dir.length() - 19));
        dir += "usuarios\\admin\\u-" + id + ".json";

        try (FileReader reader = new FileReader(dir)) {
            Object obj = jsonParser.parse(reader);

            JSONObject usuario = (JSONObject) obj;
            String filePass = (String) usuario.get("password");
            if (filePass != null && filePass.equals(pass)) {
                return (String) usuario.get("nombre");
            }
        }
        return "";
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

    public static void writeArticulos(Articulo articulo) throws FileNotFoundException, IOException, ParseException {
        File archivo = new File("pom.xml");
        String dir = archivo.getCanonicalPath();
        dir = dir.substring(0, (dir.length() - 22));
        dir += "articulos.json";
        ListaArticulos lista = getListaArticulos();

        if (lista.contains(articulo.getId()) != null) {
            lista.remove(articulo.getId());
        }
        lista.add(articulo);

        Iterator<NodeInterface<Articulo>> iterador = lista.iterator();
        JSONArray listaArticulos = new JSONArray();

        while (iterador.hasNext()) {
            Articulo articuloActual = iterador.next().getObject();
            JSONObject detallesArticulo = new JSONObject();

            detallesArticulo.put("id", Integer.toString(articuloActual.getId()));
            detallesArticulo.put("nombre", articuloActual.getNombre());
            detallesArticulo.put("precio", Integer.toString(articuloActual.getPrecio()));
            detallesArticulo.put("isComplejo", Boolean.toString(articuloActual.isComplejo()));

            JSONObject objetoArticulo = new JSONObject();
            objetoArticulo.put("cliente", detallesArticulo);
            listaArticulos.add(objetoArticulo);
        }

        try (FileWriter file = new FileWriter(dir)) {
            file.write(listaArticulos.toJSONString());
            file.flush();
        }
    }
}
