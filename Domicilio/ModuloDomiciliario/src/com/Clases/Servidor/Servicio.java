package com.Clases.Servidor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;

import com.Clases.Articulo;
import com.Clases.Estructuras.linkedlist.ListaPedidos;
import com.Clases.Estructuras.queue.ColaPrioridad;

public class Servicio extends UnicastRemoteObject implements DatosJSON {
    private static final long serialVersionUID = 123L;
    private ListaPedidos listaPedidosActivos = new ListaPedidos();

    protected Servicio() throws RemoteException {
        super();
    }

    public void getListaPedidos(ListaPedidos listaPedidos) {
        listaPedidosActivos = listaPedidos;
    }

    @Override
    public Object[][] readArticulos() throws RemoteException, IOException, ParseException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readArticulos'");
    }

    @Override
    public byte[] desencolarArticuloDomiciliario() throws RemoteException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'desencolarArticuloDomiciliario'");
    }

}
