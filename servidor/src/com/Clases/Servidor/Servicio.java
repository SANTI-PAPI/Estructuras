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

import org.apache.commons.codec.digest.DigestUtils;
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
import com.Clases.Estructuras.queue.ColaPedidos;
import com.Clases.Estructuras.queue.ColaPrioridad;

public class Servicio extends UnicastRemoteObject implements DatosJSON {
    private static final long serialVersionUID = 123L;
    private ListaPedidos listaPedidosActivos = new ListaPedidos();
    private ListaPedidos copiaListaPedidos = new ListaPedidos();
    private ColaPedidos colaPedidos = new ColaPedidos(10);
    private ColaPrioridad<Articulo> colaEntregas = new ColaPrioridad<>(5);

    protected Servicio() throws RemoteException {
        super();
    }

    @Override
    public byte[] getListaArticulos() throws RemoteException, FileNotFoundException, IOException, org.json.simple.parser.ParseException {
        JSONParser jsonParser = new JSONParser();
        File archivo = new File("datos/articulos.json");
        String dir = archivo.getCanonicalPath();

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

    public ListaArticulos getObjetoListaArticulos() throws RemoteException, FileNotFoundException, IOException, org.json.simple.parser.ParseException {
        JSONParser jsonParser = new JSONParser();
        File archivo = new File("datos/articulos.json");
        String dir = archivo.getCanonicalPath();

        try (FileReader reader = new FileReader(dir)) {
            Object obj = jsonParser.parse(reader);

            JSONArray listaArticulos = (JSONArray) obj;
            ListaArticulos linkedArticulos = new ListaArticulos();
            listaArticulos.forEach(ar -> linkedArticulos.add(parseArticuloObject((JSONObject) ar)));
            return linkedArticulos;
        }
    }

    public Articulo parseArticuloObject(JSONObject articulo) {
        JSONObject objetoArticulo = (JSONObject) articulo.get("articulo");

        Articulo nuevoArticulo = new Articulo(Integer.parseInt((String) objetoArticulo.get("id")), (String) objetoArticulo.get("nombre"), Integer.parseInt((String) objetoArticulo.get("precio")),
                Boolean.parseBoolean((String) objetoArticulo.get("isComplejo")));

        return nuevoArticulo;
    }

    @Override
    public byte[] readClientes() throws RemoteException, IOException, FileNotFoundException, org.json.simple.parser.ParseException {
        JSONParser jsonParser = new JSONParser();
        File archivo = new File("datos/clientes.json");
        String dir = archivo.getCanonicalPath();

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

    public ListaClientes readListaClientes() throws RemoteException, IOException, FileNotFoundException, org.json.simple.parser.ParseException {
        JSONParser jsonParser = new JSONParser();
        File archivo = new File("datos/clientes.json");
        String dir = archivo.getCanonicalPath();

        try (FileReader reader = new FileReader(dir)) {
            Object obj = jsonParser.parse(reader);

            JSONArray listaClientes = (JSONArray) obj;
            ListaClientes linkedClientes = new ListaClientes();
            listaClientes.forEach(cl -> linkedClientes.add(parseClienteObject((JSONObject) cl)));

            return linkedClientes;
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
    public byte[] getListaPedidos(String numeroTelefono) throws RemoteException, IOException, FileNotFoundException, org.json.simple.parser.ParseException, ClassNotFoundException {
        JSONParser jsonParser = new JSONParser();
        ListaPedidos listaPedidos = new ListaPedidos();
        File archivo = new File("clientes/c" + numeroTelefono + ".json");
        String dir = archivo.getCanonicalPath();

        try (FileReader reader = new FileReader(dir)) {
            ListaArticulos listaArticulos = getObjetoListaArticulos();
            Object obj = jsonParser.parse(reader);
            JSONArray arrayPedidos = (JSONArray) obj;
            arrayPedidos.forEach(pe -> {
                try {
                    listaPedidos.add(parsePedidosObject((JSONObject) pe, listaArticulos));
                } catch (RemoteException e) {
                }
            });
            ByteArrayOutputStream outputPedidos = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputPedidos = new ObjectOutputStream(outputPedidos);
            objectOutputPedidos.writeObject(listaPedidos);
            objectOutputPedidos.close();
            return outputPedidos.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }

    public ListaPedidos getObjetoListaPedidos(String numeroTelefono) throws RemoteException, IOException, FileNotFoundException, org.json.simple.parser.ParseException, ClassNotFoundException {
        JSONParser jsonParser = new JSONParser();
        ListaPedidos listaPedidos = new ListaPedidos();
        File archivo = new File("clientes/c" + numeroTelefono + ".json");
        String dir = archivo.getCanonicalPath();

        try (FileReader reader = new FileReader(dir)) {
            ListaArticulos listaArticulos = getObjetoListaArticulos();
            Object obj = jsonParser.parse(reader);
            JSONArray arrayPedidos = (JSONArray) obj;
            arrayPedidos.forEach(pe -> {
                try {
                    listaPedidos.add(parsePedidosObject((JSONObject) pe, listaArticulos));
                } catch (RemoteException e) {
                }
            });
            return listaPedidos;
        } catch (Exception e) {
            return null;
        }
    }

    public ListaArticulos parsePedidosObject(JSONObject pe, ListaArticulos listaArticulos) throws RemoteException {
        JSONArray arrayArticulos = (JSONArray) pe.get("pedido");
        int cantidadVecesPedido = Integer.parseInt((String) pe.get("cantidad"));
        ListaArticulos listaPedido = new ListaArticulos();
        listaPedido.setCantidad(cantidadVecesPedido);
        arrayArticulos.forEach(ar -> {
            try {
                listaPedido.add(parseArticuloObject(listaArticulos, (JSONObject) ar));
            } catch (RemoteException e) {
            }
        });

        return listaPedido;
    }

    public Articulo parseArticuloObject(ListaArticulos listaArticulos, JSONObject objetoPedido) throws RemoteException {
        String stringId = (String) objetoPedido.get("id");
        Articulo nuevoArticulo = listaArticulos.contains(Integer.parseInt(stringId));
        if (nuevoArticulo != null) {
            nuevoArticulo.setCantidad(Integer.parseInt((String) objetoPedido.get("cantidad")));
        }
        return nuevoArticulo;
    }

    @Override
    public void writeClientes(Cliente cliente) throws RemoteException, IOException, FileNotFoundException, org.json.simple.parser.ParseException, ClassNotFoundException {
        File archivo = new File("datos/clientes.json");
        String dir = archivo.getCanonicalPath();

        ListaClientes lista = readListaClientes();

        if (lista.contains(cliente.getNumeroTelefono()) != null) {
            lista.replace(cliente);
        } else {
            lista.add(cliente);
        }

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

    @Override
    public Object[][] readArticulos() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        File archivo = new File("datos/articulos.json");
        String dir = archivo.getCanonicalPath();

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
    public byte[] desencolarArticulo() throws IOException {
        if (!colaPedidos.isEmpty()) {
            Articulo articulo = colaPedidos.extract();
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bs);
            os.writeObject(articulo);
            os.close();
            bs.close();
            return bs.toByteArray();
        }
        return null;
    }

    @Override
    public void addNuevoPedido(ListaArticulos pedido) {
        listaPedidosActivos.add(pedido);
        ListaArticulos copiaPedido = new ListaArticulos();
        copiaPedido.copiar(pedido);
        copiaListaPedidos.add(copiaPedido);
        Iterator<NodeInterface<Articulo>> iterador = pedido.iterator();
        if (pedido.getCliente().isPremium()) {
            while (iterador.hasNext()) {
                Articulo articuloActual = iterador.next().getObject();
                articuloActual.setIdPedido(pedido.getIdPedido());
                int cantidad = articuloActual.getCantidad();
                for (int i = 0; i < cantidad; i++) {
                    colaPedidos.insert(articuloActual, 0);
                }
            }
        } else {
            while (iterador.hasNext()) {
                Articulo articuloActual = iterador.next().getObject();
                articuloActual.setIdPedido(pedido.getIdPedido());
                int cantidad = articuloActual.getCantidad();
                for (int i = 0; i < cantidad; i++) {
                    colaPedidos.insert(articuloActual, 5);
                }
            }
        }
    }

    @Override
    public void sendArticulo(Articulo articulo) throws RemoteException, FileNotFoundException, ClassNotFoundException, IOException, ParseException {
        listaPedidosActivos.restarArticulo(articulo);
        colaEntregas.insert(articulo, 0);
        revisarListaPedidos();
    }

    @Override
    public Object[][] readPedidos() throws IOException, ParseException {
        Iterator<NodeInterface<ListaArticulos>> iterador = listaPedidosActivos.iterator();
        Object[][] tablaPedidos = new Object[listaPedidosActivos.size()][2];
        int pos = 0;
        while (iterador.hasNext()) {
            NodoListaEnlazada<ListaArticulos> nodo = (NodoListaEnlazada<ListaArticulos>) iterador.next();
            tablaPedidos[pos][0] = nodo.getObject().getCliente().getNumeroTelefono();
            tablaPedidos[pos][1] = nodo.getObject().getIdPedido();
            pos++;
        }
        return tablaPedidos;
    }

    @Override
    public byte[] getListaPedidosActivos() throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bs);
        os.writeObject(copiaListaPedidos);
        os.close();
        bs.close();
        return bs.toByteArray();
    }

    @Override
    public void modificarPedido(ListaArticulos pedido) throws FileNotFoundException, ClassNotFoundException, IOException, ParseException {
        Iterator<NodeInterface<ListaArticulos>> iterador = copiaListaPedidos.iterator();
        while (iterador.hasNext()) {
            ListaArticulos listaActual = iterador.next().getObject();
            if (listaActual.getIdPedido().equals(pedido.getIdPedido())) { // Encuentra el pedido a modificar
                ListaArticulos listaDiferencias = new ListaArticulos();
                listaDiferencias.setIdPedido(pedido.getIdPedido());
                Iterator<NodeInterface<Articulo>> iteradorArticulos = pedido.iterator();
                while (iteradorArticulos.hasNext()) {
                    Articulo articuloNuevo = iteradorArticulos.next().getObject();
                    Articulo articuloActual = listaActual.contains(articuloNuevo.getId());
                    if (articuloActual != null) {
                        if (articuloNuevo.getCantidad() != articuloActual.getCantidad()) {
                            int diferencia = articuloNuevo.getCantidad() - articuloActual.getCantidad();
                            Articulo articuloDiferencia = new Articulo(articuloActual.getId(), articuloActual.getNombre(), articuloActual.getPrecio(), articuloActual.isComplejo());
                            articuloDiferencia.setCantidad(diferencia);
                            articuloDiferencia.setIdPedido(pedido.getIdPedido());
                            listaDiferencias.add(articuloDiferencia);
                        }
                    } else {
                        listaDiferencias.add(articuloNuevo);
                    }
                }
                copiaListaPedidos.replace(pedido); // Reemplaza la Lista del pedido con la nueva Lista
                listaPedidosActivos.sumarDiferenciaPedido(listaDiferencias); // Suma la diferencia que falta por preparar de cada Articulo
                revisarListaPedidos();
                colaPedidos.alterarCola(listaDiferencias);
            }
        }
    }

    private void revisarListaPedidos() throws RemoteException, FileNotFoundException, ClassNotFoundException, IOException, ParseException {
        Iterator<NodeInterface<ListaArticulos>> iterador = listaPedidosActivos.iterator();
        while (iterador.hasNext()) {
            ListaArticulos listaActual = iterador.next().getObject();
            boolean preparado = true;
            if (!listaActual.isEmpty()) {
                Iterator<NodeInterface<Articulo>> iteradorArticulos = listaActual.iterator();
                while (iteradorArticulos.hasNext()) {
                    if (iteradorArticulos.next().getObject().getCantidad() > 0) {
                        preparado = false;
                    }
                }
            }
            if (preparado) {
                listaPedidosActivos.remove(listaActual.getIdPedido());
                ListaArticulos listaPedido = copiaListaPedidos.contains(listaActual.getIdPedido());
                almacenarPedido(listaPedido);
                copiaListaPedidos.remove(listaActual.getIdPedido());
            }
        }
    }

    private void almacenarPedido(ListaArticulos listaActual) throws RemoteException, FileNotFoundException, ClassNotFoundException, IOException, ParseException {
        ListaPedidos listaPedidosCliente = getObjetoListaPedidos(listaActual.getCliente().getNumeroTelefono());
        if (listaPedidosCliente != null) {
            listaPedidosCliente.buscarPedido(listaActual);
        } else {
            listaPedidosCliente = new ListaPedidos();
            listaActual.setCantidad(1);
            listaPedidosCliente.add(listaActual);
        }
        listaPedidosCliente.setCliente(listaActual.getCliente());
        writeListaPedidos(listaPedidosCliente);
    }

    private void writeListaPedidos(ListaPedidos listaPedidosCliente) throws IOException {
        Iterator<NodeInterface<ListaArticulos>> iterador = listaPedidosCliente.iterator();
        JSONArray arrayPedidos = new JSONArray();

        while (iterador.hasNext()) {
            ListaArticulos pedidoActual = iterador.next().getObject();
            JSONArray detallesPedido = new JSONArray();

            Iterator<NodeInterface<Articulo>> iteradorPedido = pedidoActual.iterator();
            while (iteradorPedido.hasNext()) {
                Articulo articuloActual = iteradorPedido.next().getObject();
                JSONObject objetoArticulo = new JSONObject();
                objetoArticulo.put("id", String.valueOf(articuloActual.getId()));
                objetoArticulo.put("cantidad", String.valueOf(articuloActual.getCantidad()));
                detallesPedido.add(objetoArticulo);
            }

            JSONObject objetoPedido = new JSONObject();
            objetoPedido.put("pedido", detallesPedido);
            objetoPedido.put("cantidad", String.valueOf(pedidoActual.getCantidad()));
            arrayPedidos.add(objetoPedido);
        }

        File archivo = new File("clientes/c" + listaPedidosCliente.getCliente().getNumeroTelefono() + ".json");
        String dir = archivo.getCanonicalPath();

        try (FileWriter file = new FileWriter(dir)) {
            file.write(arrayPedidos.toJSONString());
            file.flush();
        } catch (Exception e) {
        }
    }

    @Override
    public String getUsuarioOperador(String nombre, String password) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        password = DigestUtils.sha1Hex(password);

        File archivo = new File("usuarios/operador/u-" + nombre + ".json");
        String dir = archivo.getCanonicalPath();

        try (FileReader reader = new FileReader(dir)) {
            Object obj = jsonParser.parse(reader);

            JSONObject usuario = (JSONObject) obj;
            String filePass = (String) usuario.get("password");
            if (filePass != null && filePass.equals(password)) {
                return (String) usuario.get("nombre");
            }
        }
        return "";
    }

    @Override
    public String getUsuarioAdmin(String nombre, String password) throws RemoteException, IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        password = DigestUtils.sha1Hex(password);

        File archivo = new File("usuarios/admin/u-" + nombre + ".json");
        String dir = archivo.getCanonicalPath();

        try (FileReader reader = new FileReader(dir)) {
            Object obj = jsonParser.parse(reader);

            JSONObject usuario = (JSONObject) obj;
            String filePass = (String) usuario.get("password");
            if (filePass != null && filePass.equals(password)) {
                return (String) usuario.get("nombre");
            }
        }
        return "";
    }

    @Override
    public void writeAdministrador(String id, String nombre, String password) throws IOException {
        password = DigestUtils.sha1Hex(password);

        File archivo = new File("usuarios/admin/u-" + id + ".json");
        String dir = archivo.getCanonicalPath();

        JSONObject userObject = new JSONObject();
        userObject.put("nombre", nombre);
        userObject.put("password", password);

        try (FileWriter file = new FileWriter(dir)) {
            file.write(userObject.toJSONString());
            file.flush();
        }
    }

    @Override
    public void writeOperador(String id, String nombre, String password) throws IOException {
        password = DigestUtils.sha1Hex(password);

        File archivo = new File("usuarios/operador/u-" + id + ".json");
        String dir = archivo.getCanonicalPath();

        JSONObject userObject = new JSONObject();
        userObject.put("nombre", nombre);
        userObject.put("password", password);

        try (FileWriter file = new FileWriter(dir)) {
            file.write(userObject.toJSONString());
            file.flush();
        }
    }

    @Override
    public void writeArticulo(String nombre, int precio, boolean isComplejo) throws IOException, ParseException {
        ListaArticulos listaArticulos = getObjetoListaArticulos();
        if (listaArticulos.contains(nombre) != null) {
            Articulo reemplazo = new Articulo(listaArticulos.contains(nombre).getId(), nombre, precio, isComplejo);
            listaArticulos.replace(reemplazo);
        } else {
            int ultimoId = listaArticulos.getLatestId();
            Articulo nuevoArticulo = new Articulo(ultimoId + 1, nombre, precio, isComplejo);
            listaArticulos.add(nuevoArticulo);
        }

        JSONArray arrayArticulos = new JSONArray();
        Iterator<NodeInterface<Articulo>> iterador = listaArticulos.iterator();
        while (iterador.hasNext()) {
            Articulo articuloActual = iterador.next().getObject();
            JSONObject objetoDetalles = new JSONObject();
            objetoDetalles.put("id", Integer.toString(articuloActual.getId()));
            objetoDetalles.put("nombre", articuloActual.getNombre());
            objetoDetalles.put("precio", Integer.toString(articuloActual.getPrecio()));
            objetoDetalles.put("isComplejo", Boolean.toString(articuloActual.isComplejo()));
            JSONObject objetoArticulo = new JSONObject();
            objetoArticulo.put("articulo", objetoDetalles);
            arrayArticulos.add(objetoArticulo);
        }
        File archivo = new File("datos/articulos.json");
        String dir = archivo.getCanonicalPath();
        System.out.println(dir);

        try (FileWriter file = new FileWriter(dir)) {
            file.write(arrayArticulos.toJSONString());
            file.flush();
        }
    }
}
