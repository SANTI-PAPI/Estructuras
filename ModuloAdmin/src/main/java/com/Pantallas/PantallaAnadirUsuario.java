package com.Pantallas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.parser.ParseException;

import com.Datos.JSONManager;

public class PantallaAnadirUsuario extends JFrame {
    int tipoUsuario;
    String nombre;

    public PantallaAnadirUsuario(int tipoUsuario, String nombre) {
        System.out.println(tipoUsuario);
        this.tipoUsuario = tipoUsuario;
        this.nombre = nombre;
        iniciarComponentes();
        setTitle("FoodUPB - Añadir usuario");
        setLocationRelativeTo(null);
        setResizable(false);
        setMaximizedBounds(getBounds());
    }

    private void iniciarComponentes() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JLabel labelTitulo = new JLabel("NUEVO USUARIO");
        labelTitulo.setBounds(125, 10, 400, 30);
        labelTitulo.setFont(labelTitulo.getFont().deriveFont(Font.PLAIN, 20f));

        JLabel labelNombre = new JLabel("Nombre");
        JLabel labelId = new JLabel("Nombre de usuario");
        JLabel labelPassword = new JLabel("Contraseña");
        JTextField campoNombre = new JTextField();
        JTextField campoId = new JTextField();
        JTextField campoPassword = new JTextField();
        JButton botonAnadir = new JButton("AÑADIR");
        botonAnadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!campoId.getText().equals("") && !campoNombre.getText().equals("")
                        && !campoPassword.getText().equals("")) {
                    switch (tipoUsuario) {
                        case 1:
                            try {
                                JSONManager.writeAdministrador(campoId.getText(), campoNombre.getText(),
                                        campoPassword.getText());
                            } catch (IOException | ParseException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            try {
                                JSONManager.writeOperador(campoId.getText(), campoNombre.getText(),
                                        campoPassword.getText());
                            } catch (IOException | ParseException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 3:
                            try {
                                JSONManager.writeDomiciliario(campoId.getText(), campoNombre.getText(),
                                        campoPassword.getText());
                            } catch (IOException | ParseException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                    new ModuloAdministrador(nombre);
                    dispose();
                }
            }

        });

        labelNombre.setBounds(20, 35, 100, 30);
        labelId.setBounds(220, 35, 120, 30);
        labelPassword.setBounds(20, 95, 100, 30);
        campoNombre.setBounds(20, 65, 180, 30);
        campoId.setBounds(220, 65, 180, 30);
        campoPassword.setBounds(20, 125, 180, 30);
        botonAnadir.setBounds(320, 125, 80, 30);

        mainPanel.add(labelNombre);
        mainPanel.add(labelId);
        mainPanel.add(labelPassword);
        mainPanel.add(campoNombre);
        mainPanel.add(campoId);
        mainPanel.add(campoPassword);
        mainPanel.add(botonAnadir);
        mainPanel.add(labelTitulo);

        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(430, 220);
        setResizable(false);
        setVisible(true);
    }

}
