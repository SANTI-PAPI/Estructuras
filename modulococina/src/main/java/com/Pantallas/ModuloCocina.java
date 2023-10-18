package com.Pantallas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.PlainDocument;
import org.json.simple.parser.ParseException;

import com.Clases.Articulo;
import com.Clases.FiltroEntero;
import com.Clases.Estructuras.linkedlist.ListaClientes;
import com.Clases.Servidor.ClienteRMI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ModuloCocina extends JFrame {
    ClienteRMI servidor;
    Articulo articulo1, articulo2, articulo3, articulo4;

    public ModuloCocina() throws IOException, ParseException {
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
        setTitle("FoodUPB - Modulo de cocina");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridLayout(1, 2));
        setMaximizedBounds(getBounds());
    }

    public void iniciarComponentes() {
        JPanel panelBackGround = new JPanel();
        panelBackGround.setBackground(new ColorUIResource(0, 151, 157));
        panelBackGround.setVisible(true);
        panelBackGround.setSize(500, 500);
        panelBackGround.setLayout(null);

        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(new ColorUIResource(255, 255, 255));
        panelDerecho.setBounds(160, 0, 1800, 1500);
        panelDerecho.setLayout(null);
        panelBackGround.add(panelDerecho);

        JPanel panelFogon1 = new JPanel();
        panelFogon1.setLayout(null);
        panelFogon1.setBounds(100, 40, 150, 150);
        JButton buttonFogon1 = new JButton();
        buttonFogon1.setBounds(0, 0, 150, 30);
        buttonFogon1.setText("Desencolar");
        JLabel labelFogon1 = new JLabel("FOGÓN VACÍO");
        buttonFogon1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Articulo articulo = servidor.desencolarArticulo();
                if (articulo1 != null) {
                    servidor.sendArticulo(articulo1);
                }

                if (articulo != null) {
                    articulo1 = articulo;
                    labelFogon1.setText(articulo.getNombre());
                } else {
                    articulo1 = null;
                    labelFogon1.setText("FOGÓN VACÍO");
                }
            }
        });
        labelFogon1.setBounds(5, 40, 150, 30);
        panelFogon1.add(buttonFogon1);
        panelFogon1.add(labelFogon1);
        panelDerecho.add(panelFogon1);

        JPanel panelFogon2 = new JPanel();
        panelFogon2.setLayout(null);
        panelFogon2.setBounds(360, 40, 150, 150);
        JButton buttonFogon2 = new JButton();
        buttonFogon2.setBounds(0, 0, 150, 30);
        buttonFogon2.setText("Desencolar");
        panelDerecho.add(buttonFogon2);
        JLabel labelFogon2 = new JLabel("FOGÓN VACÍO");
        buttonFogon2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Articulo articulo = servidor.desencolarArticulo();
                if (articulo2 != null) {
                    servidor.sendArticulo(articulo2);
                }

                if (articulo != null) {
                    articulo2 = articulo;
                    labelFogon2.setText(articulo.getNombre());
                } else {
                    articulo2 = null;
                    labelFogon2.setText("FOGÓN VACÍO");
                }
            }
        });
        labelFogon2.setBounds(5, 40, 150, 30);
        panelFogon2.add(buttonFogon2);
        panelFogon2.add(labelFogon2);
        panelDerecho.add(panelFogon2);

        JPanel panelFogon3 = new JPanel();
        panelFogon3.setLayout(null);
        panelFogon3.setBounds(100, 220, 150, 150);
        JButton buttonFogon3 = new JButton();
        buttonFogon3.setBounds(0, 0, 150, 30);
        buttonFogon3.setText("Desencolar");
        panelDerecho.add(buttonFogon3);
        JLabel labelFogon3 = new JLabel("FOGÓN VACÍO");
        buttonFogon3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Articulo articulo = servidor.desencolarArticulo();
                if (articulo3 != null) {
                    servidor.sendArticulo(articulo3);
                }

                if (articulo != null) {
                    articulo3 = articulo;
                    labelFogon3.setText(articulo.getNombre());
                } else {
                    articulo3 = null;
                    labelFogon3.setText("FOGÓN VACÍO");
                }
            }
        });
        labelFogon3.setBounds(5, 40, 150, 30);
        panelFogon3.add(buttonFogon3);
        panelFogon3.add(labelFogon3);
        panelDerecho.add(panelFogon3);

        JPanel panelFogon4 = new JPanel();
        panelFogon4.setLayout(null);
        panelFogon4.setBounds(360, 220, 150, 150);
        JButton buttonFogon4 = new JButton();
        buttonFogon4.setBounds(0, 0, 150, 30);
        buttonFogon4.setText("Desencolar");
        panelDerecho.add(buttonFogon4);
        JLabel labelFogon4 = new JLabel("FOGÓN VACÍO");
        buttonFogon4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Articulo articulo = servidor.desencolarArticulo();
                if (articulo4 != null) {
                    servidor.sendArticulo(articulo4);
                }

                if (articulo != null) {
                    articulo4 = articulo;
                    labelFogon4.setText(articulo.getNombre());
                } else {
                    articulo4 = null;
                    labelFogon4.setText("FOGÓN VACÍO");
                }
            }
        });
        labelFogon4.setBounds(5, 40, 150, 30);
        panelFogon4.add(buttonFogon4);
        panelFogon4.add(labelFogon4);
        panelDerecho.add(panelFogon4);

        this.add(panelBackGround);
        this.setVisible(true);
        this.setSize(800, 500);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ModuloCocina().setVisible(true);
                } catch (IOException | ParseException e) {
                }
            }
        });
    }
}