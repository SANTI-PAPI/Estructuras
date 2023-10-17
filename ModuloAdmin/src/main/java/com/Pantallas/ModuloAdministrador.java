package com.Pantallas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ModuloAdministrador extends JFrame {
    String nombre;

    public ModuloAdministrador(String nombre) {
        this.nombre = nombre;
        iniciarComponentes();
        setTitle("FoodUPB - Modulo de administrador");
        setLocationRelativeTo(null);
        setResizable(false);
        setMaximizedBounds(getBounds());
    }

    public void iniciarComponentes() {
        JPanel panelIzquierda = new JPanel();
        panelIzquierda.setSize(200, 500);
        panelIzquierda.setLayout(null);

        JButton buttonVolver = new JButton();
        buttonVolver.setBounds(10, 10, 140, 40);
        buttonVolver.setText("CERRAR SESION");
        buttonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new PantallaLogin();
                dispose();
            }
        });
        panelIzquierda.add(buttonVolver);

        JLabel labelNombre = new JLabel("Usuario:");
        labelNombre.setBounds(15, 400, 140, 40);
        panelIzquierda.add(labelNombre);
        JLabel labelNombre2 = new JLabel(nombre);
        labelNombre2.setBounds(15, 420, 140, 40);
        panelIzquierda.add(labelNombre2);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setBounds(160, 0, 440, 500);
        JPanel panelGestionUsuarios = new JPanel();
        panelGestionUsuarios.setLayout(null);
        JPanel panelBaseDeDatos = new JPanel();
        panelBaseDeDatos.setLayout(null);

        JLabel labelArticulo = new JLabel("Artículo");
        JButton botonAnadirArticulo = new JButton("AÑADIR");
        botonAnadirArticulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new PantallaAnadirArticulo(nombre);
                dispose();
            }
        });
        JButton botonQuitarArticulo = new JButton("QUITAR");

        JLabel labelCliente = new JLabel("Cliente");
        JButton botonAnadirCliente = new JButton("AÑADIR");
        botonAnadirCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new PantallaAnadirCliente(nombre);
                dispose();
            }
        });
        JButton botonQuitarCliente = new JButton("QUITAR");

        JLabel labelAdmin = new JLabel("Administrador");
        JButton botonAnadirAdmin = new JButton("AÑADIR");
        botonAnadirAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new PantallaAnadirUsuario(1, nombre);
                dispose();
            }
        });
        JButton botonQuitarAdmin = new JButton("QUITAR");
        JLabel labelOperador = new JLabel("Operador");
        JButton botonAnadirOperador = new JButton("AÑADIR");
        botonAnadirOperador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg1) {
                new PantallaAnadirUsuario(2, nombre);
                dispose();
            }
        });
        JButton botonQuitarOperador = new JButton("QUITAR");
        JLabel labelDomiciliario = new JLabel("Domiciliario");
        JButton botonAnadirDomiciliario = new JButton("AÑADIR");
        botonAnadirDomiciliario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg2) {
                new PantallaAnadirUsuario(3, nombre);
                dispose();
            }
        });
        JButton botonQuitarDomiciliario = new JButton("QUITAR");

        labelArticulo.setBounds(175, 120, 100, 20);
        labelArticulo.setFont(labelArticulo.getFont().deriveFont(Font.PLAIN, 20f));
        labelCliente.setBounds(178, 210, 100, 20);
        labelCliente.setFont(labelArticulo.getFont().deriveFont(Font.PLAIN, 20f));

        botonAnadirArticulo.setBounds(95, 150, 100, 30);
        botonQuitarArticulo.setBounds(225, 150, 100, 30);
        botonAnadirCliente.setBounds(95, 240, 100, 30);
        botonQuitarCliente.setBounds(225, 240, 100, 30);

        panelBaseDeDatos.add(labelArticulo);
        panelBaseDeDatos.add(botonAnadirArticulo);
        panelBaseDeDatos.add(botonQuitarArticulo);
        panelBaseDeDatos.add(labelCliente);
        panelBaseDeDatos.add(botonAnadirCliente);
        panelBaseDeDatos.add(botonQuitarCliente);

        labelAdmin.setBounds(150, 60, 150, 20);
        labelAdmin.setFont(labelAdmin.getFont().deriveFont(Font.PLAIN, 20f));
        labelOperador.setBounds(165, 150, 100, 20);
        labelOperador.setFont(labelOperador.getFont().deriveFont(Font.PLAIN, 20f));
        labelDomiciliario.setBounds(160, 240, 100, 20);
        labelDomiciliario.setFont(labelDomiciliario.getFont().deriveFont(Font.PLAIN, 20f));

        botonAnadirAdmin.setBounds(95, 90, 100, 30);
        botonAnadirOperador.setBounds(95, 180, 100, 30);
        botonAnadirDomiciliario.setBounds(95, 270, 100, 30);
        botonQuitarAdmin.setBounds(225, 90, 100, 30);
        botonQuitarOperador.setBounds(225, 180, 100, 30);
        botonQuitarDomiciliario.setBounds(225, 270, 100, 30);

        panelGestionUsuarios.add(labelAdmin);
        panelGestionUsuarios.add(labelOperador);
        panelGestionUsuarios.add(labelDomiciliario);
        panelGestionUsuarios.add(botonAnadirAdmin);
        panelGestionUsuarios.add(botonAnadirDomiciliario);
        panelGestionUsuarios.add(botonAnadirOperador);
        panelGestionUsuarios.add(botonQuitarAdmin);
        panelGestionUsuarios.add(botonQuitarDomiciliario);
        panelGestionUsuarios.add(botonQuitarOperador);

        tabs.addTab("Gestion de usuarios", panelGestionUsuarios);
        tabs.addTab("Base de datos", panelBaseDeDatos);
        panelIzquierda.add(tabs);

        add(panelIzquierda);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 600, 500);
        setResizable(false);
        setVisible(true);
    }
}
