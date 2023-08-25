package com.ModuloOperador;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class MSendPedido extends JFrame {
    public MSendPedido() {
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

        JPanel pannel2 = new JPanel();
        pannel2.setBackground(new ColorUIResource(255, 255, 255));
        pannel2.setBounds(150, 100, 500, 260);
        panelBackGround.add(pannel2, BorderLayout.CENTER);

        this.add(panelBackGround);
        this.setVisible(true);
        this.setSize(800, 500);

        JButton cerrarSesion = new JButton();
        cerrarSesion.setBounds(10, 10, 100, 40);
        cerrarSesion.setText("ATRAS");
        panelBackGround.add(cerrarSesion);

        JLabel pedwhitout = new JLabel("PEDIDO SIN DESPACHAR");
        pedwhitout.setBounds(325, 20, 500, 100);
        panelBackGround.add(pedwhitout);

        JButton modPedido = new JButton();
        modPedido.setBounds(295, 400, 200, 40);
        modPedido.setText("Ajustar Pedido");
        panelBackGround.add(modPedido, BorderLayout.NORTH);

        JButton aggCliente = new JButton();
        aggCliente.setBounds(20, 260, 200, 40);
        aggCliente.setText("Agregar nuevo cliente");
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MSendPedido().setVisible(true);

            }

        });
    }
}