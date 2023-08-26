package com.ModuloOperador;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaModificacion extends JFrame {
    public PantallaModificacion() {
        iniciarComponentes();
        this.setMaximizedBounds(getBounds());
    }

    public void iniciarComponentes() {
        JPanel panelBackground = new JPanel();
        panelBackground.setBackground(new ColorUIResource(151, 151, 157));
        panelBackground.setVisible(true);
        panelBackground.setSize(getPreferredSize());
        panelBackground.setLayout(null);

        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(new ColorUIResource(255, 255, 255));
        panelCentro.setBounds(150, 100, 500, 260);
        panelBackground.add(panelCentro, BorderLayout.CENTER);

        JButton buttonVolver = new JButton("VOLVER"); // Agrega el botón
        buttonVolver.setBounds(20, 20, 100, 30); // Posición del botón en la esquina superior izquierda
        panelBackground.add(buttonVolver);
        buttonVolver.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                ModuloOperador moduloOperador = new ModuloOperador();
                dispose();
            }
          } );

        JLabel pedwhitout = new JLabel("PEDIDO SIN DESPACHAR");
        pedwhitout.setBounds(325, 20, 500, 100);
        panelBackground.add(pedwhitout);

        JButton modPedido = new JButton();
        modPedido.setBounds(295, 400, 200, 40);
        modPedido.setText("Ajustar pedido");
        panelBackground.add(modPedido, BorderLayout.NORTH);

        JButton aggCliente = new JButton();
        aggCliente.setBounds(20, 260, 200, 40);
        aggCliente.setText("Agregar nuevo cliente");

        setVisible(true);
        this.add(panelBackground);
        setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(800, 500);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaModificacion().setVisible(true);

            }

        });
    }
}