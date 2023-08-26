package com.ModuloOperador;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ModuloCocina.*;

public class ModuloOperador extends JFrame {
    public ModuloOperador() {
        iniciarComponentes();
        setTitle("FoodUPB - Modulo de operador");
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

        JButton buttonVolver = new JButton();
        buttonVolver.setBounds(10, 10, 140, 40);
        buttonVolver.setText("CERRAR SESIÓN");
        panelBackGround.add(buttonVolver);

        JButton buttonCocina = new JButton();
        buttonCocina.setBounds(10, 410, 140, 40);
        buttonCocina.setText("MÓDULO COCINA");
        panelBackGround.add(buttonCocina);
        buttonCocina.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                ModuloCocina moduloCocina = new ModuloCocina();
                dispose();
            }
          } );

        JLabel noPhone = new JLabel();
        noPhone.setText("Número de teléfono");
        noPhone.setBounds(10, 20, 200, 20);
        panelDerecho.add(noPhone);

        JTextField phone = new JTextField();
        phone.setBounds(10, 40, 300, 30);
        panelDerecho.add(phone);

        JButton buttonPedido = new JButton();
        buttonPedido.setBounds(60, 100, 200, 40);
        buttonPedido.setText("Nuevo pedido");
        panelDerecho.add(buttonPedido);
        buttonPedido.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                PantallaPedido pantallaPedido = new PantallaPedido();
                dispose();
            }
          } );

        JButton buttonModificar = new JButton();
        buttonModificar.setBounds(60, 160, 200, 40);
        buttonModificar.setText("Ajustar pedido");
        panelDerecho.add(buttonModificar);
        buttonModificar.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                PantallaModificacion pantallaModificacion = new PantallaModificacion();
                dispose();
            }
          } );

        JButton aggCliente = new JButton();
        aggCliente.setBounds(60, 260, 200, 40);
        aggCliente.setText("Agregar nuevo cliente");
        panelDerecho.add(aggCliente);

        this.add(panelBackGround);
        this.setVisible(true);
        this.setSize(800, 500);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModuloOperador().setVisible(true);
            }
        });
    }
}