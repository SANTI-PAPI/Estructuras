package com.Clases.Servidor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.Clases.Articulo;
import com.Clases.Estructuras.linkedlist.ListaArticulos;
import com.Clases.Estructuras.linkedlist.ListaPedidos;
import com.Clases.Estructuras.queue.ColaPrioridad;

public class Servicio extends UnicastRemoteObject implements DatosJSON {
  private static final long serialVersionUID = 123L;
  private ListaPedidos listaPedidosActivos = new ListaPedidos();
  private ColaPrioridad<Articulo> colaPedidos = new ColaPrioridad<>(0);

  protected Servicio() throws RemoteException {
    super();
  }

  @Override
  public byte[] desencolarArticulo() throws IOException {
    if (!colaPedidos.isEmpty()) {
      Articulo articulo = colaPedidos.extract();
      ByteArrayOutputStream bs = new ByteArrayOutputStream();
      ObjectOutputStream os = new ObjectOutputStream(bs);
      os.writeObject(articulo);
      os.close();
      bs.close();
      return bs.toByteArray();
    }
    return null;
  }

  @Override
  public void sendArticulo(Articulo articulo) {
    listaPedidosActivos.restarArticulo(articulo);
  }
}
