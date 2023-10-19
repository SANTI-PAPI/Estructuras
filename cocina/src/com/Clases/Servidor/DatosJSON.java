package com.Clases.Servidor;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import com.Clases.Articulo;

public interface DatosJSON extends Remote {

        public byte[] desencolarArticulo() throws IOException, RemoteException;

	public void sendArticulo(Articulo articulo) throws RemoteException;

}
