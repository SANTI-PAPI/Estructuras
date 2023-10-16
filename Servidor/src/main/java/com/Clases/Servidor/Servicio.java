package com.Clases.Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import com.Clases.Estructuras.queue.ColaPrioridad;

public class Servicio extends UnicastRemoteObject implements DatosJSON {
  private static final long serialVersionUID = 123L;
  // private ColaPrioridad<> colaPedidos = new ColaPrioridad<>(0);

  protected Servicio() throws RemoteException {
    super();
  }

  @Override
  public byte[] getListaArticulos() throws RemoteException, FileNotFoundException, IOException, org.json.simple.parser.ParseException {
    JSONParser jsonParser = new JSONParser();
    File archivo = new File("pom.xml");
    String dir = archivo.getCanonicalPath();
    dir = dir.substring(0, (dir.length() - 7));
    dir += "datos\\articulos.json";

    try (FileReader reader = new FileReader(dir)) {
      Object obj = jsonParser.parse(reader);

      JSONArray listaArticulos = (JSONArray) obj;
      ListaArticulos linkedArticulos = new ListaArticulos();
      listaArticulos.forEach(ar -> linkedArticulos.add(parseArticuloObject((JSONObject) ar)));

      ByteArrayOutputStream listaArt = new ByteArrayOutputStream();
      ObjectOutputStream rescribir = new ObjectOutputStream(listaArt);
      rescribir.writeObject(linkedArticulos);
      rescribir.close();
      return listaArt.toByteArray();
    }
  }

  public Articulo parseArticuloObject(JSONObject articulo) {
    JSONObject objetoArticulo = (JSONObject) articulo.get("articulo");

    Articulo nuevoArticulo = new Articulo(Integer.parseInt((String) objetoArticulo.get("id")), (String) objetoArticulo.get("nombre"), Integer.parseInt((String) objetoArticulo.get("precio")),
        Boolean.parseBoolean((String) objetoArticulo.get("isComplejo")));

    return nuevoArticulo;
  }

  // RETORNA UN OBJETO TIPO ListaClientes EN ARRAY DE BYTES
  @Override
  public byte[] readClientes() throws RemoteException, IOException, FileNotFoundException, org.json.simple.parser.ParseException {
    JSONParser jsonParser = new JSONParser();
    File archivo = new File("pom.xml");
    String dir = archivo.getCanonicalPath();
    dir = dir.substring(0, (dir.length() - 7));
    dir += "datos\\clientes.json";

    try (FileReader reader = new FileReader(dir)) {
      Object obj = jsonParser.parse(reader);

      JSONArray listaClientes = (JSONArray) obj;
      ListaClientes linkedClientes = new ListaClientes();
      listaClientes.forEach(cl -> linkedClientes.add(parseClienteObject((JSONObject) cl)));

      ByteArrayOutputStream listaCli = new ByteArrayOutputStream();
      ObjectOutputStream rescribir = new ObjectOutputStream(listaCli);
      rescribir.writeObject(linkedClientes);
      rescribir.close();
      listaCli.close();

      reader.close();
      return listaCli.toByteArray();
    }
  }

  public Cliente parseClienteObject(JSONObject cliente) {
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

  @Override
  public ListaPedidos getListaPedidos(String numeroTelefono) throws RemoteException, IOException, FileNotFoundException, org.json.simple.parser.ParseException, ClassNotFoundException {

    ByteArrayInputStream bs = new ByteArrayInputStream(getListaArticulos());
    ObjectInputStream is = new ObjectInputStream(bs);
    ListaArticulos listaArticulos = (ListaArticulos) is.readObject();
    is.close();

    JSONParser jsonParser = new JSONParser();
    ListaPedidos listaPedidos = new ListaPedidos();

    String fileDir = "clientes//c" + String.valueOf(numeroTelefono);

    try (FileReader reader = new FileReader(fileDir)) {
      Object obj = jsonParser.parse(reader);

      JSONArray arrayPedidos = (JSONArray) obj;
      arrayPedidos.forEach(pe -> {
        try {
          listaPedidos.add(parsePedidosObject((JSONObject) pe, listaArticulos));
        } catch (RemoteException e) {
        }
      });
    }
    listaPedidos.sort();
    return listaPedidos;
  }

  public ListaArticulos parsePedidosObject(JSONObject pe, ListaArticulos listaArticulos) throws RemoteException {
    JSONObject objetoPedido = (JSONObject) pe.get("pedido");
    JSONArray arrayArticulos = (JSONArray) objetoPedido.get("articulos");
    ListaArticulos listaPedido = new ListaArticulos();
    arrayArticulos.forEach(ar -> {
      try {
        listaPedido.add(parseArticuloObject(listaArticulos, (JSONObject) ar));
      } catch (RemoteException e) {
      }
    });

    return listaPedido;
  }

  public Articulo parseArticuloObject(ListaArticulos listaArticulos, JSONObject objetoPedido) throws RemoteException {
    JSONObject objetoArticulo = (JSONObject) objetoPedido.get("articulo");
    Articulo nuevoArticulo = listaArticulos.contains(Integer.parseInt((String) objetoArticulo.get("id")));
    return nuevoArticulo;
  }

  @Override
  public void writeClientes(Cliente cliente) throws RemoteException, IOException, FileNotFoundException, org.json.simple.parser.ParseException, ClassNotFoundException {
    File archivo = new File("pom.xml");
    String dir = archivo.getCanonicalPath();
    dir = dir.substring(0, (dir.length() - 7));
    dir += "datos\\clientes.json";

    ByteArrayInputStream bs = new ByteArrayInputStream(readClientes());
    ObjectInputStream is = new ObjectInputStream(bs);
    ListaClientes lista = (ListaClientes) is.readObject();
    is.close();
    bs.close();

    if (lista.contains(cliente.getNumeroTelefono()) != null) {
      System.out.println("1" + lista.remove(cliente.getNumeroTelefono()));
    }
    System.out.println("2" + lista.add(cliente));
    System.out.println("Último cliente: " + lista.getFromEnd().getNombre());

    Iterator<NodeInterface<Cliente>> iterador = lista.iterator();
    JSONArray listaClientes = new JSONArray();

    while (iterador.hasNext()) {
      Cliente clienteActual = iterador.next().getObject();
      System.out.println("Cliente actual: " + clienteActual.getNombre());
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

  @Override
  public Object[][] readArticulos() throws IOException, ParseException {
    JSONParser jsonParser = new JSONParser();
    File archivo = new File("pom.xml");
    String dir = archivo.getCanonicalPath();
    dir = dir.substring(0, (dir.length() - 7));
    dir += "datos\\articulos.json";

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

  @Override
  public int suma(int i1, int i2) throws RemoteException {
    return i1 + i2;
  }
}
