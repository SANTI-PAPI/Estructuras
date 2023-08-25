package com.ModuloOperador;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class ModuloOperador extends JFrame {
    public ModuloOperador() {
        iniciarComponentes();
        this.setLayout(new GridLayout(1, 2));
        this.setMaximizedBounds(getBounds());

    }

    public void iniciarComponentes() {
        JPanel panelBackGround = new JPanel();
        panelBackGround.setBackground(new ColorUIResource(151, 151, 157));
        panelBackGround.setVisible(true);
        panelBackGround.setSize(800, 500);
        panelBackGround.setLayout(null);

        JPanel pannel2 = new JPanel();
        pannel2.setBackground(new ColorUIResource(255, 255, 255));
        pannel2.setBounds(350, 0, 1800, 1500);
        pannel2.setLayout(null);
        panelBackGround.add(pannel2);

        this.add(panelBackGround);
        this.setVisible(true);
        this.setSize(800, 500);

        JButton cerrarSesion = new JButton();
        cerrarSesion.setBounds(10, 10, 200, 40);
        cerrarSesion.setText("CERRAR SESIÓN");
        panelBackGround.add(cerrarSesion);

        JLabel noPhone = new JLabel();
        noPhone.setText("# Telefono");
        noPhone.setBounds(10, 20, 200, 20);
        pannel2.add(noPhone);

        JTextField phone = new JTextField();
        phone.setBounds(10, 40, 300, 30);
        pannel2.add(phone);

        JButton newPedido = new JButton();
        newPedido.setBounds(20, 100, 200, 40);
        newPedido.setText("Nuevo Pedido");
        pannel2.add(newPedido);

        JButton modPedido = new JButton();
        modPedido.setBounds(20, 160, 200, 40);
        modPedido.setText("Ajustar Pedido");
        pannel2.add(modPedido);

        JButton aggCliente = new JButton();
        aggCliente.setBounds(20, 260, 200, 40);
        aggCliente.setText("Agregar nuevo cliente");
        pannel2.add(aggCliente);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModuloOperador().setVisible(true);

            }

        });
    }
}