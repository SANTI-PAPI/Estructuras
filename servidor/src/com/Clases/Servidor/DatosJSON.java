package com.Clases.Servidor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import org.json.simple.parser.ParseException;

import com.Clases.Articulo;
import com.Clases.Cliente;
import com.Clases.Estructuras.linkedlist.ListaArticulos;

public interface DatosJSON extends Remote {

    public Object[][] readArticulos() throws RemoteException, IOException, ParseException;

    public byte[] getListaArticulos() throws RemoteException, FileNotFoundException, IOException, ParseException;

    public byte[] readClientes() throws RemoteException, IOException, FileNotFoundException, ParseException;

    public byte[] getListaPedidos(String numeroTelefono) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException;

    public void writeClientes(Cliente cliente) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException;

    public byte[] desencolarArticulo() throws RemoteException, IOException;

    public void addNuevoPedido(ListaArticulos pedido) throws RemoteException;

    public void sendArticulo(Articulo articulo) throws RemoteException, FileNotFoundException, ClassNotFoundException, IOException, ParseException;

    public Object[][] readPedidos() throws RemoteException, IOException, ParseException;

    public byte[] getListaPedidosActivos() throws RemoteException, IOException;

    public void modificarPedido(ListaArticulos pedido) throws RemoteException, FileNotFoundException, ClassNotFoundException, IOException, ParseException;

    public String getUsuarioOperador(String text, String password) throws RemoteException, IOException, ParseException;

    public String getUsuarioAdmin(String nombre, String password) throws RemoteException, IOException, ParseException;

    public void writeAdministrador(String id, String nombre, String password) throws RemoteException, IOException;

    public void writeOperador(String id, String nombre, String password) throws RemoteException, IOException;

}
