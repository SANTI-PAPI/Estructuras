package com.Init;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class MInit extends JFrame {
    public MInit() {
        iniciarComponentes();
        this.setMaximizedBounds(getBounds());

    }

    /**
     * 
     */
    public void iniciarComponentes() {
        JPanel panelBackGround = new JPanel();
        panelBackGround.setBackground(new ColorUIResource(151, 151, 157));
        panelBackGround.setVisible(true);
        panelBackGround.setSize(getPreferredSize());
        panelBackGround.setLayout(null);

        this.add(panelBackGround);
        this.setVisible(true);
        this.setSize(800, 500);

        JButton cerrarSesion = new JButton();
        cerrarSesion.setBounds(10, 10, 100, 40);
        cerrarSesion.setText("ATRAS");
        panelBackGround.add(cerrarSesion);

        JLabel pedwhitout = new JLabel("Inicio");
        pedwhitout.setBounds(325, 20, 500, 100);
        panelBackGround.add(pedwhitout);

        JButton modPedido = new JButton();
        modPedido.setBounds(295, 400, 200, 40);
        modPedido.setText("Iniciar");
        panelBackGround.add(modPedido, BorderLayout.NORTH);

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
                new MInit().setVisible(true);

            }

        });
    }
}