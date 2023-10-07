package com.Pantallas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.PlainDocument;
import org.json.simple.parser.ParseException;

import com.Clases.Cliente;
import com.Clases.FiltroEntero;
import com.Clases.Estructuras.linkedlist.ListaClientes;
import com.Datos.JSONManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ModuloOperador extends JFrame {
    ListaClientes listaClientes;

    public ModuloOperador() {
        iniciarComponentes();
        setTitle("FoodUPB - Modulo de operador");
        try {
            listaClientes = JSONManager.readClientes();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
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

        JLabel noPhone = new JLabel();
        noPhone.setText("Número de teléfono");
        noPhone.setBounds(10, 20, 200, 20);
        panelDerecho.add(noPhone);

        JTextField fieldTelefono = new JTextField();
        fieldTelefono.setBounds(10, 40, 300, 30);
        panelDerecho.add(fieldTelefono);
        PlainDocument doc = (PlainDocument) fieldTelefono.getDocument();
        doc.setDocumentFilter(new FiltroEntero(10));

        JButton buttonPedido = new JButton();
        buttonPedido.setBounds(60, 100, 200, 40);
        buttonPedido.setText("Nuevo pedido");
        panelDerecho.add(buttonPedido);
        buttonPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (fieldTelefono.getText().length() == 10) {
                        Cliente cliente = listaClientes.contains(fieldTelefono.getText());
                        if (cliente != null) {
                            new PantallaPedidoPrevio(cliente);
                        } else {
                            new PantallaPedido(fieldTelefono.getText());
                        }
                        dispose();
                    }
                } catch (IOException | ParseException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton buttonModificar = new JButton();
        buttonModificar.setBounds(60, 160, 200, 40);
        buttonModificar.setText("Ajustar pedido");
        panelDerecho.add(buttonModificar);
        buttonModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PantallaModificacion();
                dispose();
            }
        });

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