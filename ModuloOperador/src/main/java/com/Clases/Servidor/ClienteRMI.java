package com.Clases.Servidor;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.Naming;
import java.rmi.NotBoundException;
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

public class ClienteRMI /* implements DatosJSON */ {
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

    // @Override
    public int suma(int i1, int i2) throws RemoteException {
        try {
            service = (DatosJSON) Naming.lookup(uri);
            return service.suma(i1, i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // @Override
    public ListaArticulos getListaArticulos() throws RemoteException, FileNotFoundException, IOException, ParseException {
        try {
            service = (DatosJSON) Naming.lookup(uri);
            ByteArrayInputStream bs = new ByteArrayInputStream(service.getListaArticulos());
            ObjectInputStream is = new ObjectInputStream(bs);
            ListaArticulos listaArt = (ListaArticulos) is.readObject();
            Iterator<NodeInterface<Articulo>> iterador = listaArt.iterator();
            
            while (iterador.hasNext()) {
                System.out.println("Articulo: " + iterador.next().getObject().getNombre());
            }

            is.close();
            return listaArt;

        } catch (RemoteException rE) {
            System.out.println("RemoteException");
        } catch (IOException iE) {
            System.out.println("IOException");
        } catch (ParseException pE) {
            System.out.println("ParseException");
        } catch (NotBoundException e) {
            System.out.println("NotBoundException");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        }
        return new ListaArticulos();
    }

    // @Override
    public ListaClientes readClientes() throws RemoteException, IOException, FileNotFoundException, ParseException {
        try {
            service = (DatosJSON) Naming.lookup(uri);
            ByteArrayInputStream bs = new ByteArrayInputStream(service.readClientes());
            ObjectInputStream is = new ObjectInputStream(bs);
            ListaClientes listaClient = (ListaClientes) is.readObject();
            is.close();
            return listaClient;
        } catch (Exception e) {
        }
        return new ListaClientes();
    }

    // @Override
    public ListaPedidos getListaPedidos(String numeroTelefono) throws RemoteException, IOException, FileNotFoundException, ParseException {
        try {
            service = (DatosJSON) Naming.lookup(uri);
            return service.getListaPedidos(numeroTelefono);
        } catch (Exception e) {
        }
        return new ListaPedidos();
    }

    // @Override
    public void writeClientes(Cliente cliente) throws RemoteException, IOException, FileNotFoundException, ParseException {
        try {
            service = (DatosJSON) Naming.lookup(uri);
            service.writeClientes(cliente);
        } catch (Exception e) {
        }
    }

    // @Override
    public Object[][] readArticulos() throws RemoteException {
        try {
            service = (DatosJSON) Naming.lookup(uri);
            return service.readArticulos();
        } catch (Exception e) {
        }
        return new Object[0][0];
    }
}
