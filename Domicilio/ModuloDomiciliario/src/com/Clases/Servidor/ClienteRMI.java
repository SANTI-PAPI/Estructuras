package com.Clases.Servidor;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
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

    public void getListaPedidos(ListaPedidos listaPedidos) {
        try {
            service = (DatosJSON) Naming.lookup(uri);
            service.getListaPedidos(listaPedidos);
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

    public Articulo desencolarArticuloDomiiciliario() throws RemoteException, IOException {
        try {
            service = (DatosJSON) Naming.lookup(uri);
            ByteArrayInputStream bs = new ByteArrayInputStream(service.desencolarArticuloDomiciliario());
            ObjectInputStream is = new ObjectInputStream(bs);
            Articulo articulo = (Articulo) is.readObject();
            System.out.println(articulo.getNombre());
            is.close();
            bs.close();
            return articulo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

}
