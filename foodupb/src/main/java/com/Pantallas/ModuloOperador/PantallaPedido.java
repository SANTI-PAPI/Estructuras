package com.Pantallas.ModuloOperador;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaPedido extends JFrame {
    public PantallaPedido() {
        iniciarComponentes();
        setTitle("FoodUPB - Creación del pedido");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridLayout(1, 2));
        setMaximizedBounds(getBounds());
    }
    public static void main(String[] args) {
        PantallaPedido pantalla = new PantallaPedido();
        pantalla.setVisible(true);
    }

    private void iniciarComponentes() {
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
                new ModuloOperador();
                dispose();
            }
          } );

        JTextField fieldBuscador = new JTextField();
        fieldBuscador.setBounds(80, 60, 330, 30); // Posición del buscador debajo del botón
        panelIzquierda.add(fieldBuscador);

        JLabel labelBuscador = new JLabel("  🔍  ");
        labelBuscador.setBounds(50, 60, 30, 30); // Posición de la etiqueta "CANTIDAD"
        labelBuscador.setHorizontalAlignment(JLabel.CENTER); // Centrar el texto
        panelIzquierda.add(labelBuscador);

        String[] art = {"Hamburguesa sencilla      $600", "Hamburguesa doble      $900", "Perro sencillo      $500", "Perro doble      $800"};
        JList articulos = new JList<>(art);
        articulos.setSelectionMode(0);
        JPanel panelsito = new JPanel(new GridLayout(art.length+1, 2, 10, 0));
        panelsito.add(articulos);
        JScrollPane panelBusqueda = new JScrollPane(panelsito);
        panelBusqueda.setBounds(60, 120, 370, 200); // Posición de cajasPanel debajo del buscador
        panelBusqueda.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelIzquierda.add(panelBusqueda);

        JTextField fieldCantidad = new JTextField("1");
        fieldCantidad.setBounds(115, 350, 75, 30); // Posición de la caja de texto debajo de "CANTIDAD"
        panelIzquierda.add(fieldCantidad);

        JLabel labelCantidad = new JLabel("CANTIDAD");
        labelCantidad.setBounds(115, 380, 75, 30); // Posición de la etiqueta "CANTIDAD"
        labelCantidad.setHorizontalAlignment(JLabel.CENTER); // Centrar el texto
        panelIzquierda.add(labelCantidad);

        JButton buttonAgregar = new JButton("AGREGAR");
        buttonAgregar.setBounds(265, 350, 100, 30); // Posición del botón "AGREGAR" al lado de la caja de texto
        panelIzquierda.add(buttonAgregar);

        JPanel panelDerecha = new JPanel(null); // Panel para poner caja y botones
        panelDerecha.setPreferredSize(new Dimension(300, 450));
        panelDerecha.setBackground(Color.GRAY);
        mainPanel.add(panelDerecha, BorderLayout.EAST);

        JScrollPane panelPedido = new JScrollPane(); //Crear un caja donde luego se verá el pedido
        panelPedido.setBounds(60, 75, 200, 200);
        panelPedido.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelPedido.setBackground(Color.WHITE);
        panelDerecha.add(panelPedido);

        JButton quitarBoton = new JButton("QUITAR"); //Crea el botón Quitar
        quitarBoton.setBounds(105, 295, 120, 30);
        panelDerecha.add(quitarBoton);

        JButton continuarBoton = new JButton("CONTINUAR"); //Crea el botón Continuar
        continuarBoton.setBounds(105, 335, 120, 30);
        panelDerecha.add(continuarBoton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 800, 500);
        setResizable(false);
        setVisible(true);
    }
}