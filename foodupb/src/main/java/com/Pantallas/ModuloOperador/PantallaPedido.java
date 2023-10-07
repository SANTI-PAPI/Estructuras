package com.Pantallas.ModuloOperador;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import org.json.simple.parser.ParseException;

import com.Clases.Articulo;
import com.Clases.Cliente;
import com.Clases.Estructuras.interfaces.node.NodeInterface;
import com.Clases.Estructuras.linkedlist.ListaArticulos;
import com.Datos.JSONManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

class MyIntFilter extends DocumentFilter {
   @Override
   public void insertString(FilterBypass fb, int offset, String string,
         AttributeSet attr) throws BadLocationException {

      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.insert(offset, string);

      if (test(sb.toString())) {
         super.insertString(fb, offset, string, attr);
      }
   }

   private boolean test(String text) {
      try {
         Integer.parseInt(text);
         return true;
      } catch (NumberFormatException e) {
         return false;
      }
   }

   @Override
   public void replace(FilterBypass fb, int offset, int length, String text,
         AttributeSet attrs) throws BadLocationException {

      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.replace(offset, offset + length, text);

      if (test(sb.toString())) {
         super.replace(fb, offset, length, text, attrs);
      }
   }

   @Override
   public void remove(FilterBypass fb, int offset, int length)
         throws BadLocationException {
      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.delete(offset, offset + length);

      if (test(sb.toString())) {
         super.remove(fb, offset, length);
      }
   }
}

public class PantallaPedido extends JFrame {
   boolean flagEnd = false;
   String telefono;
   Cliente cliente;
   ListaArticulos listaArticulos;
   DefaultTableModel modelo;

   public PantallaPedido(String telefono) throws FileNotFoundException, IOException, ParseException {
      iniciarComponentes();
      this.telefono = telefono;
      setTitle("FoodUPB - Creación del pedido");
      setLocationRelativeTo(null);
      setResizable(false);
      setLayout(new GridLayout(1, 2));
      setMaximizedBounds(getBounds());
      listaArticulos = JSONManager.getListaArticulos();
   }

   public PantallaPedido(ListaArticulos listaPedido, String telefono) throws FileNotFoundException, IOException, ParseException {
      iniciarComponentes();
      this.telefono = telefono;
      setTitle("FoodUPB - Creación del pedido");
      setLocationRelativeTo(null);
      setResizable(false);
      setLayout(new GridLayout(1, 2));
      setMaximizedBounds(getBounds());
      listaArticulos = JSONManager.getListaArticulos();
      Iterator<NodeInterface<Articulo>> iteradorPedido = listaPedido.iterator();
      flagEnd = true;
      while (iteradorPedido.hasNext()) {
         Articulo articuloActual = iteradorPedido.next().getObject();
         String nombre = articuloActual.getNombre();
         int cantidad = articuloActual.getCantidad();
         Object[] nuevaFila = { nombre, cantidad };
         modelo.addRow(nuevaFila);
      }
      flagEnd = false;
   }

   public PantallaPedido(Cliente cliente) throws FileNotFoundException, IOException, ParseException {
      iniciarComponentes();
      this.cliente = cliente;
      setTitle("FoodUPB - Creación del pedido");
      setLocationRelativeTo(null);
      setResizable(false);
      setLayout(new GridLayout(1, 2));
      setMaximizedBounds(getBounds());
      listaArticulos = JSONManager.getListaArticulos();
   }

   public PantallaPedido(ListaArticulos listaPedido, Cliente cliente) throws FileNotFoundException, IOException, ParseException {
      iniciarComponentes();
      this.cliente = cliente;
      setTitle("FoodUPB - Creación del pedido");
      setLocationRelativeTo(null);
      setResizable(false);
      setLayout(new GridLayout(1, 2));
      setMaximizedBounds(getBounds());
      listaArticulos = JSONManager.getListaArticulos();
      Iterator<NodeInterface<Articulo>> iteradorPedido = listaPedido.iterator();
      flagEnd = true;
      while (iteradorPedido.hasNext()) {
         Articulo articuloActual = iteradorPedido.next().getObject();
         String nombre = articuloActual.getNombre();
         int cantidad = articuloActual.getCantidad();
         Object[] nuevaFila = { nombre, cantidad };
         modelo.addRow(nuevaFila);
      }
      flagEnd = false;
   }

   private void iniciarComponentes() throws FileNotFoundException, IOException, ParseException {

      JPanel mainPanel = new JPanel(); // Panel principal
      mainPanel.setLayout(new BorderLayout());
      getContentPane().add(mainPanel);

      JPanel panelIzquierda = new JPanel(null); // Utiliza un layout nulo
      panelIzquierda.setPreferredSize(new Dimension(500, 200));
      panelIzquierda.setBackground(Color.LIGHT_GRAY);
      mainPanel.add(panelIzquierda, BorderLayout.WEST);

      JButton buttonVolver = new JButton("VOLVER"); // Agrega el botón
      buttonVolver.setBounds(20, 20, 100, 30); // Posición del botón en la esquina superior izquierda
      panelIzquierda.add(buttonVolver);
      buttonVolver.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (cliente != null) {
               try {
                  new PantallaPedidoPrevio(cliente);
               } catch (IOException | ParseException e1) {
                  e1.printStackTrace();
               }
            } else {
               new ModuloOperador();
            }
            dispose();
         }
      });

      JTextField fieldBuscador = new JTextField();
      fieldBuscador.setBounds(80, 60, 330, 30); // Posición del buscador debajo del botón
      panelIzquierda.add(fieldBuscador);

      JLabel labelBuscador = new JLabel("  🔍  ");
      labelBuscador.setBounds(50, 60, 30, 30); // Posición de la etiqueta "CANTIDAD"
      labelBuscador.setHorizontalAlignment(JLabel.CENTER); // Centrar el texto
      panelIzquierda.add(labelBuscador);

      String[] columnas = { "Nombre del artículo", "Precio unitario" };
      Object[][] articulos = JSONManager.pruebaReadArticulos();
      JTable tablaArticulos = new JTable(articulos, columnas) {
         @Override
         public boolean isCellEditable(int row, int column) {
            return false;
         }
      };
      tablaArticulos.setSelectionMode(0);
      JScrollPane panelBusqueda = new JScrollPane(tablaArticulos);
      panelBusqueda.setBounds(60, 120, 370, 200); // Posición de cajasPanel debajo del buscador
      panelBusqueda.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      panelIzquierda.add(panelBusqueda);

      JTextField fieldCantidad = new JTextField("1", 2);
      PlainDocument doc = (PlainDocument) fieldCantidad.getDocument();
      doc.setDocumentFilter(new MyIntFilter());
      fieldCantidad.setBounds(115, 350, 75, 30); // Posición de la caja de texto debajo de "CANTIDAD"
      panelIzquierda.add(fieldCantidad);

      JLabel labelCantidad = new JLabel("CANTIDAD");
      labelCantidad.setBounds(115, 380, 75, 30); // Posición de la etiqueta "CANTIDAD"
      labelCantidad.setHorizontalAlignment(JLabel.CENTER); // Centrar el texto
      panelIzquierda.add(labelCantidad);

      modelo = new DefaultTableModel() {
         @Override
         public boolean isCellEditable(int row, int column) {
            if (column == 1) {
               return true;
            }
            return false;
         }
      };
      modelo.addColumn("Nombre del artículo");
      modelo.addColumn("Cantidad");

      JButton buttonAgregar = new JButton("AGREGAR");
      buttonAgregar.setBounds(265, 350, 100, 30); // Posición del botón "AGREGAR" al lado de la caja de texto
      panelIzquierda.add(buttonAgregar);

      JPanel panelDerecha = new JPanel(null); // Panel para poner caja y botones
      panelDerecha.setPreferredSize(new Dimension(300, 450));
      panelDerecha.setBackground(Color.GRAY);
      mainPanel.add(panelDerecha, BorderLayout.EAST);

      JTable tablaPedidos = new JTable(modelo);
      JScrollPane panelPedido = new JScrollPane(tablaPedidos); // Crear un caja donde luego se verá el pedido
      panelPedido.setBounds(30, 75, 260, 250);
      panelPedido.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      panelPedido.setBackground(Color.WHITE);
      panelDerecha.add(panelPedido);

      modelo.addTableModelListener(new TableModelListener() {

         public static boolean isNumeric(int strNum) {
            try {
               String.valueOf(strNum);
            } catch (NumberFormatException nfe) {
               return false;
            }
            return true;
         }

         @Override
         public void tableChanged(TableModelEvent e) {
            if (!flagEnd && (tablaPedidos.getSelectedRow() != -1 && tablaPedidos.getRowCount() > 0)) {
               try {
                  if (!(isNumeric(Integer.parseInt((String) modelo.getValueAt(tablaPedidos.getSelectedRow(), 1))))) {
                  }
               } catch (Exception exception) {
                  flagEnd = true;
                  modelo.setValueAt(1, tablaPedidos.getSelectedRow(), 1);
                  flagEnd = false;
               }
            }
         }
      });

      buttonAgregar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (tablaArticulos.getSelectedRow() != -1) {
               flagEnd = true;
               String nombre = String.valueOf(tablaArticulos.getValueAt(tablaArticulos.getSelectedRow(), 0));
               int fila = -1;
               for (int i = 0; i < tablaPedidos.getRowCount(); i++) {
                  if (tablaPedidos.getModel().getValueAt(i, 0).equals(nombre)) {
                     fila = i;
                  }
               }
               if (fila == -1) {
                  Object[] nuevaFila = { nombre, (fieldCantidad.getText()) };
                  modelo.addRow(nuevaFila);
               } else {
                  String cantidadActual = String.valueOf(tablaPedidos.getModel().getValueAt(fila, 1));
                  tablaPedidos.getModel().setValueAt(
                        String.valueOf(Integer.parseInt(cantidadActual) + Integer.parseInt(fieldCantidad.getText())),
                        fila, 1);
               }
               flagEnd = false;
            }
         }
      });

      JButton buttonQuitar = new JButton("QUITAR"); // Crea el botón Quitar
      buttonQuitar.setBounds(105, 345, 120, 30);
      panelDerecha.add(buttonQuitar);
      buttonQuitar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            flagEnd = true;
            if (tablaPedidos.getSelectedRow() != -1) {
               modelo.removeRow(tablaPedidos.getSelectedRow());
            }
            flagEnd = false;
         }
      });

      JButton buttonContinuar = new JButton("CONTINUAR"); // Crea el botón Continuar
      buttonContinuar.setBounds(105, 385, 120, 30);
      panelDerecha.add(buttonContinuar);
      buttonContinuar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            try {
               if (tablaPedidos.getRowCount() != 0) {
                  ListaArticulos listaPedido = new ListaArticulos();
                  for (int i = 0; i < tablaPedidos.getRowCount(); i++) {
                     String nombre = String.valueOf(tablaPedidos.getValueAt(i, 0));
                     int cantidad = Integer.parseInt(String.valueOf(tablaPedidos.getValueAt(i, 1)));
                     Articulo articuloActual = listaArticulos.contains(nombre);
                     if (articuloActual != null) {
                        articuloActual.setCantidad(cantidad);
                        listaPedido.add(articuloActual);
                     }
                  }
                  if (telefono != null) {
                     new PantallaRegistroDireccion(listaPedido, telefono);
                  } if (cliente != null) {
                     new PantallaRegistroDireccion(listaPedido, cliente);
                  }
                  dispose();
               }
            } catch (IOException | ParseException e) {
            }
         }
      });

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(0, 0, 800, 500);
      setResizable(false);
      setVisible(true);
   }
}