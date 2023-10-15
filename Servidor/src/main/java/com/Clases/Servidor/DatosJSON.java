package com.Clases.Servidor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.Clases.Articulo;
import com.Clases.Cliente;
import com.Clases.Estructuras.linkedlist.ListaArticulos;
import com.Clases.Estructuras.linkedlist.ListaClientes;
import com.Clases.Estructuras.linkedlist.ListaPedidos;

public interface DatosJSON extends Remote {

        public int suma(int i1, int i2) throws RemoteException;

        public Object[][] readArticulos() throws RemoteException, IOException, ParseException;

        public ListaArticulos getListaArticulos() throws RemoteException, FileNotFoundException, IOException, ParseException;

        public ListaClientes readClientes() throws RemoteException, IOException, FileNotFoundException, ParseException;

        public ListaPedidos getListaPedidos(String numeroTelefono) throws RemoteException, IOException, FileNotFoundException, ParseException;

        public void writeClientes(Cliente cliente) throws RemoteException, IOException, FileNotFoundException, ParseException;

}
