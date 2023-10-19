package com.Clases.Servidor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import org.json.simple.parser.ParseException;

import com.Clases.Cliente;
import com.Clases.Estructuras.linkedlist.ListaArticulos;
import com.Clases.Estructuras.linkedlist.ListaPedidos;

public interface DatosJSON extends Remote {

    public int suma(int i1, int i2) throws RemoteException;

    public Object[][] readArticulos() throws RemoteException, IOException, ParseException;

    public byte[] getListaArticulos() throws RemoteException, FileNotFoundException, IOException, ParseException;

    public byte[] readClientes() throws RemoteException, IOException, FileNotFoundException, ParseException;

    public ListaPedidos getListaPedidos(String numeroTelefono) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException;

    public void writeClientes(Cliente cliente) throws RemoteException, IOException, FileNotFoundException, ParseException, ClassNotFoundException;

    public void addNuevoPedido(ListaArticulos pedido) throws RemoteException;

    public Object[][] readPedidos() throws RemoteException, IOException, ParseException;

    public byte[] getListaPedidosActivos() throws RemoteException, IOException;

    public void modificarPedido(ListaArticulos pedido) throws RemoteException;

    public String getUsuario(String text, String password) throws RemoteException, IOException, ParseException;

    public String getUsuarioAdmin(String nombre, String password) throws RemoteException, IOException, ParseException;

    public void writeAdministrador(String id, String nombre, String password) throws IOException;

    public void writeOperador(String id, String nombre, String password) throws IOException;

}
