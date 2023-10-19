package com.Pantallas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.parser.ParseException;

import com.Clases.Servidor.ClienteRMI;

public class PantallaAnadirUsuario extends JFrame {
    ClienteRMI servidor;
    int tipoUsuario;
    String nombre;

    public PantallaAnadirUsuario(int tipoUsuario, String nombre) throws IOException {
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
                if (!campoId.getText().equals("") && !campoNombre.getText().equals("") && !campoPassword.getText().equals("")) {
                    switch (tipoUsuario) {
                    case 1:
                        try {
                            servidor.writeAdministrador(campoId.getText(), campoNombre.getText(), campoPassword.getText());
                        } catch (Exception e) {

                        }
                        break;
                    case 2:
                        try {
                            servidor.writeOperador(campoId.getText(), campoNombre.getText(), campoPassword.getText());
                        } catch (Exception e) {
                            
                        }
                        break;
                    case 3:
                        try {
                            // servidor.writeDomiciliario(campoId.getText(), campoNombre.getText(),
                            // campoPassword.getText());
                        } catch (Exception e) {
                            
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
