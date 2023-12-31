package com.Pantallas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.simple.parser.ParseException;

import com.Clases.Articulo;
import com.Clases.Cliente;
import com.Clases.Estructuras.interfaces.node.NodeInterface;
import com.Clases.Estructuras.linkedlist.ListaArticulos;
import com.Clases.Estructuras.linkedlist.ListaPedidos;
import com.Clases.Servidor.ClienteRMI;

public class PantallaPedidoPrevio extends JFrame {
    String nombre;
    Cliente cliente;
    ListaArticulos listaArticulos;
    ListaPedidos listaPedidos;
    ClienteRMI servidor;

    public PantallaPedidoPrevio(String nombre, Cliente cliente) throws FileNotFoundException, IOException, ParseException {
        this.nombre = nombre;
        this.cliente = cliente;
        Properties config = new Properties();
        File archivo = new File("config.properties");
        String dir = archivo.getCanonicalPath();
        try (FileInputStream fin = new FileInputStream(new File(dir))) {
            config.load(fin);
            servidor = new ClienteRMI((String) config.get("IP"), (String) config.get("PORT"), (String) config.get("SERVICENAME"));
        } catch (Exception e) {
        }
        listaPedidos = servidor.getListaPedidos(cliente.getNumeroTelefono());
        iniciarComponentes();
        setTitle("FoodUPB - Pedidos previos");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridLayout(1, 2));
        setMaximizedBounds(getBounds());
    }

    private void iniciarComponentes() throws FileNotFoundException, IOException, ParseException {
        JPanel mainPanel = new JPanel(); // Panel principal
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(mainPanel);

        JLabel labelTitulo = new JLabel("PEDIDOS PREVIOS");
        labelTitulo.setBounds(290, 20, 400, 30);
        labelTitulo.setFont(labelTitulo.getFont().deriveFont(Font.PLAIN, 24f));
        mainPanel.add(labelTitulo);

        JButton buttonVolver = new JButton("VOLVER"); // Agrega el botón
        buttonVolver.setBounds(20, 20, 100, 30); // Posición del botón en la esquina superior izquierda
        mainPanel.add(buttonVolver);
        buttonVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new ModuloOperador(nombre);
                } catch (IOException | ParseException e1) {
                }
                dispose();
            }
        });
        JTable tablaArticulos = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaArticulos.setSelectionMode(0);
        DefaultTableModel modelo = new DefaultTableModel();
        tablaArticulos.setModel(modelo);
        modelo.addColumn("Nombre del artículo");
        modelo.addColumn("Cantidad");
        ListaArticulos listaActual = listaPedidos.getIndex(0);
        if (listaActual != null) {
            Iterator<NodeInterface<Articulo>> iterador = listaActual.iterator();
            while (iterador.hasNext()) {
                Articulo articuloActual = iterador.next().getObject();
                String nombreActual = articuloActual.getNombre();
                int cantidad = articuloActual.getCantidad();
                Object[] nuevaFila = { nombreActual, cantidad };
                modelo.addRow(nuevaFila);
            }
        }

        JScrollPane panelBusqueda = new JScrollPane(tablaArticulos);
        panelBusqueda.setBounds(60, 120, 500, 300);
        panelBusqueda.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        mainPanel.add(panelBusqueda);

        JComboBox<String> comboPedidos = new JComboBox<>();
        comboPedidos.setBounds(160, 80, 300, 20);
        for (int i = 1; i <= listaPedidos.size(); i++) {
            comboPedidos.addItem("Pedido frecuente #" + String.valueOf(i));
        }
        comboPedidos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int indice = comboPedidos.getSelectedIndex();
                ListaArticulos listaActual = listaPedidos.getIndex(indice);
                DefaultTableModel modelo = (DefaultTableModel) tablaArticulos.getModel();
                modelo.setRowCount(0);
                Iterator<NodeInterface<Articulo>> iterador = listaActual.iterator();
                while (iterador.hasNext()) {
                    Articulo articuloActual = iterador.next().getObject();
                    String nombreActual = articuloActual.getNombre();
                    int cantidad = articuloActual.getCantidad();
                    Object[] nuevaFila = { nombreActual, cantidad };
                    modelo.addRow(nuevaFila);
                }

            }
        });

        mainPanel.add(comboPedidos);

        JButton buttonElegir = new JButton("ELEGIR");
        buttonElegir.setBounds(620, 120, 100, 30); // Posición del botón "AGREGAR" al lado de la caja de texto
        mainPanel.add(buttonElegir);

        buttonElegir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (comboPedidos.getSelectedIndex() != -1) {
                        ListaArticulos lista = listaPedidos.getIndex(comboPedidos.getSelectedIndex());
                        new PantallaPedido(nombre, lista, cliente);
                        dispose();
                    }
                } catch (Exception ex) {
                }
            }
        });

        JButton buttonOmitir = new JButton("NUEVO PEDIDO"); // Crea el botón Continuar
        buttonOmitir.setBounds(610, 390, 120, 30);
        mainPanel.add(buttonOmitir);
        buttonOmitir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    new PantallaPedido(nombre, cliente);
                    dispose();
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 800, 500);
        setResizable(false);
        setVisible(true);
    }
}
