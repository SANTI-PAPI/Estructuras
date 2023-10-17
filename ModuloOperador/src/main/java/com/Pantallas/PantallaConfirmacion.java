package com.Pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.parser.ParseException;

import com.Clases.Articulo;
import com.Clases.Cliente;
import com.Clases.Estructuras.interfaces.node.NodeInterface;
import com.Clases.Estructuras.linkedlist.ListaArticulos;
import com.Clases.Servidor.ClienteRMI;

public class PantallaConfirmacion extends JFrame {
    Cliente cliente;
    ListaArticulos pedido;
    ClienteRMI servidor;

    public PantallaConfirmacion(ListaArticulos listaPedido, Cliente cliente) throws FileNotFoundException, IOException, ParseException {
        System.out.println(listaPedido.getIdPedido());
        Properties config = new Properties();

        File archivo = new File("pom.xml");
        String dir = archivo.getCanonicalPath();
        dir = dir.substring(0, (dir.length() - 7));
        dir += "config.properties";

        try (FileInputStream fin = new FileInputStream(new File(dir))) {
            config.load(fin);
            servidor = new ClienteRMI((String) config.get("IP"), (String) config.get("PORT"), (String) config.get("SERVICENAME"));
        } catch (Exception e) {
        }
        this.pedido = listaPedido;
        this.cliente = cliente;
        iniciarComponentes();
        setTitle("FoodUPB - Confirmar pedido");
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

        JLabel labelTitulo = new JLabel("CONFIRMAR PEDIDO");
        labelTitulo.setBounds(285, 20, 400, 30);
        labelTitulo.setFont(labelTitulo.getFont().deriveFont(Font.PLAIN, 24f));
        mainPanel.add(labelTitulo);

        JButton buttonVolver = new JButton("VOLVER");
        buttonVolver.setBounds(20, 20, 100, 30);
        mainPanel.add(buttonVolver);
        buttonVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new PantallaRegistroDireccion(pedido, cliente);
                } catch (IOException | ParseException e1) {
                    e1.printStackTrace();
                }
                dispose();
            }
        });

        double valorTotal = 0;
        Object[][] articulos = new Object[pedido.size()][2];
        Iterator<NodeInterface<Articulo>> iterador = pedido.iterator();
        int i = 0;
        while (iterador.hasNext()) {
            Articulo articuloActual = iterador.next().getObject();
            String nombre = articuloActual.getNombre();
            int cantidad = articuloActual.getCantidad();
            articulos[i][0] = nombre;
            articulos[i][1] = cantidad;
            i++;
            valorTotal += (articuloActual.getCantidad() * articuloActual.getPrecio());
        }

        String[] columnas = { "Nombre del artículo", "Cantidad" };

        JTable tablaArticulos = new JTable(articulos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaArticulos.setSelectionMode(0);
        JScrollPane panelBusqueda = new JScrollPane(tablaArticulos);
        panelBusqueda.setBounds(60, 80, 500, 340);
        panelBusqueda.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        mainPanel.add(panelBusqueda);

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

        JLabel labelTotal = new JLabel("PRECIO TOTAL:");
        labelTotal.setBounds(600, 120, 100, 30);
        mainPanel.add(labelTotal);

        double valorImpuestos = (Double.parseDouble(String.valueOf(valorTotal)) / 100) * 19;
        JLabel labelValorTotal = new JLabel("$ " + String.valueOf(valorTotal));
        labelValorTotal.setBounds(600, 140, 100, 30);
        mainPanel.add(labelValorTotal);

        JLabel labelImpuestos = new JLabel("VALOR DE IMPUESTOS:");
        labelImpuestos.setBounds(600, 200, 135, 30);
        mainPanel.add(labelImpuestos);
        JLabel labelValorImpuestos = new JLabel("$ " + String.valueOf(valorImpuestos));
        labelValorImpuestos.setBounds(600, 220, 135, 30);
        mainPanel.add(labelValorImpuestos);

        JLabel labelDomicilio = new JLabel("VALOR DEL DOMICILIO:");
        labelDomicilio.setBounds(600, 280, 135, 30);
        mainPanel.add(labelDomicilio);
        JLabel labelValorDomicilio = new JLabel("$ ");
        labelValorDomicilio.setBounds(600, 300, 135, 30);
        mainPanel.add(labelValorDomicilio);

        JButton buttonConfirmar = new JButton("CONFIRMAR");
        buttonConfirmar.setBounds(610, 390, 120, 30);
        mainPanel.add(buttonConfirmar);
        buttonConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    servidor.writeClientes(cliente);
                    pedido.setCliente(cliente);
                    if (pedido.getIdPedido() == null) {
                        pedido.setIdPedido(DigestUtils.sha1Hex(LocalDateTime.now().toString()));
                        servidor.addNuevoPedido(pedido);
                    } else {
                        servidor.modificarPedido(pedido);
                    }
                    new ModuloOperador();
                    dispose();
                } catch (Exception e) {
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 800, 500);
        setResizable(false);
        setVisible(true);
    }
}
