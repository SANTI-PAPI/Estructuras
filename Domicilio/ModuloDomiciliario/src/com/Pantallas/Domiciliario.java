package com.Pantallas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Properties;

import com.Clases.Articulo;
import com.Clases.Cliente;
import com.Clases.Estructuras.linkedlist.ListaArticulos;
import com.Clases.Estructuras.linkedlist.ListaPedidos;
import com.Clases.Estructuras.queue.ColaPrioridad;
import com.Clases.Servidor.ClienteRMI;

public class Domiciliario extends JFrame {
    int cont = 0;
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
    ColaPrioridad<Articulo> colaDespacho = new ColaPrioridad<>(10);

    public Domiciliario(String nombre, ListaArticulos listaPedido, Cliente cliente) throws IOException, ParseException {
        Properties config = new Properties();

        File archivo = new File("config.properties");
        String dir = archivo.getCanonicalPath();

        try (FileInputStream fin = new FileInputStream(new File(dir))) {
            config.load(fin);
            servidor = new ClienteRMI((String) config.get("IP"), (String) config.get("PORT"),
                    (String) config.get("SERVICENAME"));
        } catch (Exception e) {
        }
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
        JButton siguientePedidoButton = new JButton("Siguiente Articulo");
        siguientePedidoButton.setBounds(30, 450, 300, 50);

        mainPanel.add(siguientePedidoButton);
        mainPanel.add(pedidoPanel);
        mainPanel.add(rutaPanel);

        // Agregar componentes al panel principal
        this.add(mainPanel);

        // Crear una tabla para mostrar los detalles del pedido actual
        modeloPedidos = new DefaultTableModel();
        modeloPedidos.addColumn("Nombre del Artículo");
        modeloPedidos.addColumn("Domicilio");
        JTable tablaPedidos = new JTable(modeloPedidos);
        JScrollPane scrollPane = new JScrollPane(tablaPedidos);

        pedidoPanel.add(scrollPane, BorderLayout.CENTER);

        siguientePedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cont++;
                if (cont > 8) {
                    cont = 0;
                }
                try {
                    cargarPedidoEnTabla();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void cargarPedidoEnTabla() throws RemoteException, IOException {

        modeloPedidos.setRowCount(cont); // Limpiar la tabla
        Articulo articuloActual = servidor.desencolarArticulo();
        cliente = articuloActual.getClienteAsociado();
        modeloPedidos.addRow(new Object[] { articuloActual.getNombre().toString(), cliente.getComuna().toString() });
    }

    public static void main(String[] args) {
        ListaArticulos articulos = new ListaArticulos();
        articulos.add(new Articulo(1, "Hamburguesa", 15000, true));

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Domiciliario domiciliario;

                try {
                    domiciliario = new Domiciliario("Pepe", articulos, null);
                    domiciliario.setVisible(true);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
    }
}
