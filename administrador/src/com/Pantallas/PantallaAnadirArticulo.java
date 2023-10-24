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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import com.Clases.FiltroEntero;
import com.Clases.Servidor.ClienteRMI;

public class PantallaAnadirArticulo extends JFrame {
    ClienteRMI servidor;
    String nombre;

    public PantallaAnadirArticulo(String nombre) throws IOException {
        Properties config = new Properties();

        File archivo = new File("config.properties");
        String dir = archivo.getCanonicalPath();

        try (FileInputStream fin = new FileInputStream(new File(dir))) {
            config.load(fin);
            servidor = new ClienteRMI((String) config.get("IP"), (String) config.get("PORT"), (String) config.get("SERVICENAME"));
        } catch (Exception e) {
        }
        this.nombre = nombre;
        iniciarComponentes();
        setTitle("FoodUPB - Nuevo artículo");
        setLocationRelativeTo(null);
        setResizable(false);
        setMaximizedBounds(getBounds());
    }

    private void iniciarComponentes() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JLabel labelTitulo = new JLabel("NUEVO ARTÍCULO");
        labelTitulo.setBounds(125, 10, 400, 30);
        labelTitulo.setFont(labelTitulo.getFont().deriveFont(Font.PLAIN, 20f));

        JLabel labelNombre = new JLabel("Nombre");
        JLabel labelPrecio = new JLabel("Precio");
        JRadioButton rButtonComplejo = new JRadioButton("Plato complejo");
        JTextField campoNombre = new JTextField();
        JTextField campoPrecio = new JTextField();
        JButton botonAnadir = new JButton("AÑADIR");
        botonAnadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!campoNombre.getText().equals("") && !campoPrecio.getText().equals("")) {
                    servidor.writeArticulo(campoNombre.getText(), Integer.parseInt(campoPrecio.getText()), rButtonComplejo.isEnabled());
                    new ModuloAdministrador(nombre);
                    dispose();
                }
            }
        });

        labelNombre.setBounds(20, 35, 100, 30);
        labelPrecio.setBounds(220, 35, 120, 30);
        campoNombre.setBounds(20, 65, 180, 30);
        campoPrecio.setBounds(220, 65, 180, 30);
        PlainDocument doc = (PlainDocument) campoPrecio.getDocument();
        doc.setDocumentFilter(new FiltroEntero(6));
        rButtonComplejo.setBounds(20, 110, 160, 30);
        botonAnadir.setBounds(320, 125, 80, 30);

        mainPanel.add(labelNombre);
        mainPanel.add(labelPrecio);
        mainPanel.add(campoNombre);
        mainPanel.add(campoPrecio);
        mainPanel.add(rButtonComplejo);
        mainPanel.add(botonAnadir);
        mainPanel.add(labelTitulo);

        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(430, 220);
        setResizable(false);
        setVisible(true);
    }

}
