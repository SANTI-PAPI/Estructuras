package com.Pantallas;

import java.io.FileNotFoundException;
import java.io.IOException;

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

import com.Clases.Cliente;
import com.Clases.Estructuras.linkedlist.ListaArticulos;
import com.Clases.Estructuras.linkedlist.ListaPedidos;

public class PantallaPedidoPrevio extends JFrame {
    String nombre;
    Cliente cliente;
    ListaArticulos listaArticulos;
    ListaPedidos listaPedidos;

    public PantallaPedidoPrevio(String nombre, Cliente cliente) throws FileNotFoundException, IOException, ParseException {
        this.nombre = nombre;
        this.cliente = cliente;
        iniciarComponentes();
        setTitle("FoodUPB - Pedidos previos");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridLayout(1, 2));
        setMaximizedBounds(getBounds());
        // listaPedidos = JSONManager.getListaPedidos(cliente.getNumeroTelefono());
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
        String[] columnas = { "Cantidad", "Nombre del artículo" };
        Object[][] articulos = new Object[0][2];
        JTable tablaArticulos = new JTable(articulos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaArticulos.setSelectionMode(0);
        JScrollPane panelBusqueda = new JScrollPane(tablaArticulos);
        panelBusqueda.setBounds(60, 120, 500, 300); 
        panelBusqueda.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        mainPanel.add(panelBusqueda);

        JComboBox<String> comboPedidos = new JComboBox<>();
        comboPedidos.setBounds(160, 80, 300, 20);
        mainPanel.add(comboPedidos);

        DefaultTableModel modelo = new DefaultTableModel() {
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

        JButton buttonElegir = new JButton("ELEGIR");
        buttonElegir.setBounds(620, 120, 100, 30); // Posición del botón "AGREGAR" al lado de la caja de texto
        mainPanel.add(buttonElegir);

        buttonElegir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
