package com.Pantallas.MenuInicio;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class PantallaInicio extends JFrame {
    public PantallaInicio() {
        iniciarComponentes();
        this.setMaximizedBounds(getBounds());
    }
    
    public void iniciarComponentes() {
        JPanel panelBackGround = new JPanel();
        panelBackGround.setBackground(new ColorUIResource(151, 151, 157));
        panelBackGround.setVisible(true);
        panelBackGround.setSize(getPreferredSize());
        panelBackGround.setLayout(null);

        this.add(panelBackGround);
        setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(800, 500);

        JLabel labelTitulo = new JLabel("Inicio de sesión");
        labelTitulo.setBounds(335, 20, 500, 100);
        labelTitulo.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
        panelBackGround.add(labelTitulo);

        JButton buttonEntrar = new JButton();
        buttonEntrar.setBounds(295, 400, 200, 40);
        buttonEntrar.setText("Iniciar sesión");
        buttonEntrar.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
        panelBackGround.add(buttonEntrar, BorderLayout.NORTH);

        JTextField nombre = new JTextField();
        nombre.setBounds(200, 100, 500, 50);
        panelBackGround.add(nombre);

        JPasswordField contra = new JPasswordField();
        contra.setBounds(200, 200, 500, 50);
        panelBackGround.add(contra);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaInicio().setVisible(true);

            }

        });
    }
}