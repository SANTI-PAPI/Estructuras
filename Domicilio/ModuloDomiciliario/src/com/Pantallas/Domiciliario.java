package com.Pantallas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.Clases.Articulo;
import com.Clases.Cliente;
import com.Clases.Estructuras.linkedlist.ListaArticulos;
import com.Clases.Estructuras.linkedlist.ListaPedidos;
import com.Clases.Servidor.ClienteRMI;

public class Domiciliario extends JFrame {
    String nombre;
    boolean flagEnd = false;
    String telefono;
    Cliente cliente;
    ListaArticulos pedido;
    DefaultTableModel modelo;
    DefaultTableModel modeloPedidos;
    String idPedido;
    int pedidoActual = 0;
    ClienteRMI servidor;

    public Domiciliario(String nombre, ListaArticulos listaPedido, Cliente cliente) {
        this.nombre = nombre;
        this.pedido = listaPedido;
        this.cliente = cliente;
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        // Crear el marco principal
        this.setVisible(true);
        this.setBounds(EXIT_ON_CLOSE, ABORT, 1000, 600);
        this.setLayout(null);

        // Crear un panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(173, 216, 230));
        mainPanel.setBounds(0, 0, 1000, 800);

        // Panel izquierdo para "Pedido"
        JPanel pedidoPanel = new JPanel();
        pedidoPanel.setBounds(30, 30, 400, 400);
        pedidoPanel.setLayout(new BorderLayout());
        pedidoPanel.add(new JLabel("Pedido"), BorderLayout.NORTH);

        // Panel derecho para "Ruta"
        JPanel rutaPanel = new JPanel();
        rutaPanel.setBounds(450, 50, 400, 300);
        rutaPanel.add(new JLabel("Ruta"));

        // Botón "Siguiente Pedido"
        JButton siguientePedidoButton = new JButton("Siguiente Pedido");
        siguientePedidoButton.setBounds(30, 450, 300, 50);

        mainPanel.add(siguientePedidoButton);
        mainPanel.add(pedidoPanel);
        mainPanel.add(rutaPanel);

        // Agregar componentes al panel principal
        this.add(mainPanel);

        // Crear una tabla para mostrar los detalles del pedido actual
        String[] columnNames = { "Nombre del artículo", "Cantidad" };
        modeloPedidos = new DefaultTableModel(columnNames, 0);
        JTable tablaPedidos = new JTable(modeloPedidos);
        JScrollPane scrollPane = new JScrollPane(tablaPedidos);

        pedidoPanel.add(scrollPane, BorderLayout.CENTER);

        siguientePedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pedidoActual < pedido.size() - 1) {
                    pedidoActual++;
                    cargarPedidoEnTabla();
                }
            }
        });

    }

    private void cargarPedidoEnTabla() {
        modeloPedidos.setRowCount(0); // Limpiar la tabla
        Articulo articuloActual = pedido.get();
        modeloPedidos.addRow(new Object[] { articuloActual.getNombre(), articuloActual.getCantidad() });
    }

    public static void main(String[] args) {
        ListaArticulos articulos = new ListaArticulos();
        articulos.add(new Articulo(1, "Hamburguesa", 15000, true));

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Domiciliario domiciliario = new Domiciliario("Pepe", articulos, null);
                domiciliario.setVisible(true);
            }
        });
    }
}
