package com.Clases.Servidor;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Iterator;

import org.json.simple.parser.ParseException;

import com.Clases.Articulo;
import com.Clases.Cliente;
import com.Clases.Estructuras.interfaces.node.NodeInterface;
import com.Clases.Estructuras.linkedlist.ListaArticulos;
import com.Clases.Estructuras.linkedlist.ListaClientes;
import com.Clases.Estructuras.linkedlist.ListaPedidos;

public class ClienteRMI {
    private DatosJSON service;
    private String ip;
    private String port;
    private String serviceName;
    private String uri;

    public ClienteRMI(String ip, String port, String serviceName) {
        this.ip = ip;
        this.port = port;
        this.serviceName = serviceName;
        this.uri = "//" + this.ip + ":" + this.port + "/" + this.serviceName;
    }

    public boolean deployDatosJSON() {
        try {
            System.setProperty("java.rmi.server.hostname", ip);
            DatosJSON service = new Servicio();
            LocateRegistry.createRegistry(Integer.parseInt(port));
            Naming.rebind(uri, service);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int suma(int i1, int i2) throws RemoteException {
        try {
            service = (DatosJSON) Naming.lookup(uri);
            return service.suma(i1, i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ListaArticulos getListaArticulos() throws RemoteException, FileNotFoundException, IOException, ParseException {
        try {
            service = (DatosJSON) Naming.lookup(uri);
            ByteArrayInputStream bs = new ByteArrayInputStream(service.getListaArticulos());
            ObjectInputStream is = new ObjectInputStream(bs);
            ListaArticulos listaArt = (ListaArticulos) is.readObject();
            is.close();
            return listaArt;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ListaArticulos();
    }

    public ListaClientes readClientes() throws RemoteException, IOException, FileNotFoundException, ParseException {
        try {
            service = (DatosJSON) Naming.lookup(uri);
            ByteArrayInputStream bs = new ByteArrayInputStream(service.readClientes());
            ObjectInputStream is = new ObjectInputStream(bs);
            ListaClientes listaClient = (ListaClientes) is.readObject();
            is.close();
            return listaClient;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ListaClientes();
    }

    public ListaPedidos getListaPedidos(String numeroTelefono) throws RemoteException, IOException, FileNotFoundException, ParseException {
        try {
            service = (DatosJSON) Naming.lookup(uri);
            ByteArrayInputStream bs = new ByteArrayInputStream(service.getListaPedidos(numeroTelefono));
            ObjectInputStream is = new ObjectInputStream(bs);
            ListaPedidos listaPedidos = (ListaPedidos) is.readObject();
            is.close();
            return listaPedidos;
        } catch (Exception e) {
        }
        return new ListaPedidos();
    }

    public void writeClientes(Cliente cliente) throws RemoteException, IOException, FileNotFoundException, ParseException {
        try {
            service = (DatosJSON) Naming.lookup(uri);
            service.writeClientes(cliente);
        } catch (Exception e) {
        }
    }

    public Object[][] readArticulos() throws RemoteException {
        try {
            service = (DatosJSON) Naming.lookup(uri);
            return service.readArticulos();
        } catch (Exception e) {
        }
        return new Object[0][0];
    }

    public void addNuevoPedido(ListaArticulos pedido) {
        try {
            service = (DatosJSON) Naming.lookup(uri);
            service.addNuevoPedido(pedido);
        } catch (Exception e) {
        }
    }

    public ListaPedidos getListaPedidosActivos() {
        try {
            service = (DatosJSON) Naming.lookup(uri);
            ByteArrayInputStream bs = new ByteArrayInputStream(service.getListaPedidosActivos());
            ObjectInputStream is = new ObjectInputStream(bs);
            ListaPedidos listaPedidos = (ListaPedidos) is.readObject();
            Iterator<NodeInterface<ListaArticulos>> iterador = listaPedidos.iterator();
            while (iterador.hasNext()) {
                ListaArticulos listaActual = iterador.next().getObject();
                Iterator<NodeInterface<Articulo>> iteradorArticulos = listaActual.iterator();
                while (iteradorArticulos.hasNext()) {
                    System.out.println(iteradorArticulos.next().getObject().getCantidad());
                }
            }
            is.close();
            return listaPedidos;
        } catch (Exception e) {
        }
        return new ListaPedidos();
    }

    public Object[][] readPedidos() {
        try {
            service = (DatosJSON) Naming.lookup(uri);
            return service.readPedidos();
        } catch (Exception e) {
        }
        return new Object[0][0];
    }

    public void modificarPedido(ListaArticulos pedido) {
        try {
            service = (DatosJSON) Naming.lookup(uri);
            service.modificarPedido(pedido);
        } catch (Exception e) {
        }
    }

    public String getUsuarioOperador(String nombre, String password) {
        try {
            service = (DatosJSON) Naming.lookup(uri);
            return service.getUsuarioOperador(nombre, password);
        } catch (Exception e) {
        }
        return "";
    }
}
