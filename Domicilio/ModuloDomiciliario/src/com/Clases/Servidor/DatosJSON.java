package com.Clases.Servidor;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import com.Clases.Articulo;
import com.Clases.Estructuras.linkedlist.ListaPedidos;

public interface DatosJSON extends Remote {

    public void getListaPedidos(ListaPedidos listaPedidos);

}
