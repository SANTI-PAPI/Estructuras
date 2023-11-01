package com.Clases.Servidor;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;

import com.Clases.Articulo;
import com.Clases.Estructuras.linkedlist.ListaPedidos;

public interface DatosJSON extends Remote {

    public void getListaPedidos(ListaPedidos listaPedidos);

    public Object[][] readArticulos() throws RemoteException, IOException, ParseException;

    public byte[] desencolarArticuloDomiciliario() throws RemoteException, IOException;

}
