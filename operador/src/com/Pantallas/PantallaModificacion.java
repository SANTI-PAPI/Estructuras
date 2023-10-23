package com.Pantallas;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.ColorUIResource;

import org.json.simple.parser.ParseException;

import com.Clases.Estructuras.linkedlist.ListaArticulos;
import com.Clases.Estructuras.linkedlist.ListaPedidos;
import com.Clases.Servidor.ClienteRMI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;

public class PantallaModificacion extends JFrame {
    String nombre;
    ClienteRMI servidor;
    ListaPedidos listaPedidosActivos;

    public PantallaModificacion(String nombre) throws IOException {
        this.nombre = nombre;
        Properties config = new Properties();
        File archivo = new File("config.properties");
        String dir = archivo.getCanonicalPath();

        try (FileInputStream fin = new FileInputStream(new File(dir))) {
            config.load(fin);
            servidor = new ClienteRMI((String) config.get("IP"), (String) config.get("PORT"), (String) config.get("SERVICENAME"));
        } catch (Exception e) {
        }
        listaPedidosActivos = servidor.getListaPedidosActivos();

        iniciarComponentes();
        setTitle("FoodUPB - Modificación de pedidos");
        setLocationRelativeTo(null);
        setResizable(false);
        this.setMaximizedBounds(getBounds());
    }

    public void iniciarComponentes() throws RemoteException {
        JPanel panelBackground = new JPanel();
        panelBackground.setBackground(new ColorUIResource(151, 151, 157));
        panelBackground.setVisible(true);
        panelBackground.setSize(getPreferredSize());
        panelBackground.setLayout(null);

        String[] columnas = { "Numero de teléfono", "ID del pedido" };
        Object[][] pedidos = servidor.readPedidos();
        JTable tablaPedidos = new JTable(pedidos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaPedidos.setSelectionMode(0);
        JScrollPane panelBusqueda = new JScrollPane(tablaPedidos);
        panelBusqueda.setBounds(105, 80, 560, 300);
        panelBusqueda.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelBackground.add(panelBusqueda);

        JButton buttonVolver = new JButton("VOLVER"); // Agrega el botón
        buttonVolver.setBounds(20, 20, 100, 30); // Posición del botón en la esquina superior izquierda
        panelBackground.add(buttonVolver);
        buttonVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new ModuloOperador(nombre);
                } catch (IOException | ParseException e1) {
                }
                dispose();
            }
        });

        JLabel labelTitulo = new JLabel("PEDIDOS SIN DESPACHAR");
        labelTitulo.setBounds(235, 30, 400, 30);
        labelTitulo.setFont(labelTitulo.getFont().deriveFont(Font.PLAIN, 24f));
        panelBackground.add(labelTitulo);

        JButton buttonModificar = new JButton();
        buttonModificar.setBounds(335, 400, 100, 40);
        buttonModificar.setText("MODIFICAR");
        buttonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (tablaPedidos.getSelectedRow() != -1) {
                    String idPedido = String.valueOf(tablaPedidos.getValueAt(tablaPedidos.getSelectedRow(), 1));
                    ListaArticulos pedidoActual = listaPedidosActivos.contains(idPedido);
                    if (pedidoActual != null) {
                        try {
                            new PantallaPedido(nombre, pedidoActual, pedidoActual.getCliente());
                            dispose();
                        } catch (IOException | ParseException e) {
                        }
                    }
                }
            }

        });
        panelBackground.add(buttonModificar, BorderLayout.NORTH);

        setVisible(true);
        add(panelBackground);
        setVisible(true);
        setSize(800, 500);
    }
}