package com.Clases.Servidor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.Clases.Articulo;
import com.Clases.Cliente;
import com.Clases.Estructuras.linkedlist.ListaArticulos;
import com.Clases.Estructuras.linkedlist.ListaClientes;
import com.Clases.Estructuras.linkedlist.ListaPedidos;

public class Servidor implements DatosJSON {

    private String ip;
    private String port;
    private String serviceName;
    private String uri;

    public Servidor(String ip, String port, String serviceName) {
        this.ip = ip;
        this.port = port;
        this.serviceName = serviceName;
        // "//92.168.0.1:1802/service"
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

    @Override
    public int suma(int i1, int i2) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'suma'");
    }

    @Override
    public ListaArticulos getListaArticulos() throws RemoteException, FileNotFoundException, IOException, ParseException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getListaArticulos'");
    }

    @Override
    public Articulo parseArticuloObject(JSONObject articulo) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseArticuloObject'");
    }

    @Override
    public ListaClientes readClientes() throws RemoteException, IOException, FileNotFoundException, ParseException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readClientes'");
    }

    @Override
    public Cliente ParseClienteObject(JSONObject Cliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ParseClienteObject'");
    }

    @Override
    public ListaPedidos getListaPedidos(String numeroTelefono) throws RemoteException, IOException, FileNotFoundException, ParseException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getListaPedidos'");
    }

    @Override
    public ListaArticulos ParsePedidosObject(JSONObject pe, ListaArticulos listaArticulos) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ParsePedidosObject'");
    }

    @Override
    public Articulo ParseArticuloObject(ListaArticulos ListaArticulos, JSONObject objetoPedido) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ParseArticuloObject'");
    }

    @Override
    public void WriteClientes(Cliente cliente) throws RemoteException, IOException, FileNotFoundException, ParseException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'WriteClientes'");
    }

}
