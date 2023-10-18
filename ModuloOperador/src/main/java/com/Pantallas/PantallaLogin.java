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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;

import org.json.simple.parser.ParseException;

import com.Clases.Servidor.ClienteRMI;

public class PantallaLogin extends JFrame {
    ClienteRMI servidor;
    public PantallaLogin() throws IOException {
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
        iniciarComponentes();
        setTitle("FoodUPB - Inicio de sesion (operador)");
        setLocationRelativeTo(null);
        setResizable(false);
        setMaximizedBounds(getBounds());
    }

    public void iniciarComponentes() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new ColorUIResource(0, 151, 157));
        getContentPane().add(mainPanel);

        JLabel labelTitulo = new JLabel("INICIO DE SESION");
        JLabel labelUsuario = new JLabel("Nombre de usuario");
        JLabel labelPassword = new JLabel("Contraseña");
        JTextField campoUsuario = new JTextField();
        JPasswordField campoPassword = new JPasswordField();
        JButton botonEntrar = new JButton("INICIAR SESION");
        botonEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!campoUsuario.getText().equals("") && !(campoPassword.getPassword().toString().equals(""))) {
                    String nombre;
                    String password = "";
                    try {
                        char[] arrPassword = campoPassword.getPassword();
                        for (int i = 0; i < arrPassword.length; i++) {
                            password += arrPassword[i];
                        }
                        nombre = servidor.getUsuario(campoUsuario.getText(), password);
                        if (!nombre.equals("")) {
                            new ModuloOperador(nombre);
                            dispose();
                        }
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }

                }
            }

        });

        labelTitulo.setBounds(100, 15, 220, 30);
        labelTitulo.setFont(labelTitulo.getFont().deriveFont(Font.PLAIN, 24f));
        labelUsuario.setBounds(80, 50, 120, 30);
        campoUsuario.setBounds(80, 80, 250, 30);
        labelPassword.setBounds(80, 120, 120, 30);
        campoPassword.setBounds(80, 150, 250, 30);
        botonEntrar.setBounds(120, 200, 170, 30);

        mainPanel.add(labelTitulo);
        mainPanel.add(labelUsuario);
        mainPanel.add(labelPassword);
        mainPanel.add(campoUsuario);
        mainPanel.add(campoPassword);
        mainPanel.add(botonEntrar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 420, 300);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PantallaLogin().setVisible(true);
                } catch (IOException e) {
                }
            }
        });
    }
}
