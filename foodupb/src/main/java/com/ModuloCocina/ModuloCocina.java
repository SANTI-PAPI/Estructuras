package com.ModuloCocina;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.ColorUIResource;

import com.ModuloOperador.ModuloOperador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModuloCocina extends JFrame {
    public ModuloCocina() {
        iniciarComponentes();
        this.setMaximizedBounds(getBounds());
    }

    public void iniciarComponentes() {
        JPanel panelBackGround = new JPanel();
        panelBackGround.setBackground(new ColorUIResource(151, 151, 157));
        panelBackGround.setVisible(true);
        panelBackGround.setSize(getPreferredSize());
        panelBackGround.setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setBackground(new ColorUIResource(255, 255, 255));
        panel1.setVisible(true);
        panel1.setBounds(10, 80, 300, 300);
        panelBackGround.add(panel1);

        JPanel panel2 = new JPanel();
        panel2.setBackground(new ColorUIResource(255, 255, 255));
        panel2.setVisible(true);
        panel2.setBounds(320, 80, 300, 300);
        panelBackGround.add(panel2);

        JPanel panel3 = new JPanel();
        panel3.setBackground(new ColorUIResource(255, 255, 255));
        panel3.setVisible(true);
        panel3.setBounds(630, 80, 300, 300);
        panelBackGround.add(panel3);

        JButton buttonVolver = new JButton();
        buttonVolver.setBounds(10, 10, 100, 40);
        buttonVolver.setText("VOLVER");
        panelBackGround.add(buttonVolver);
        buttonVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModuloOperador moduloOperador = new ModuloOperador();
                dispose();
            }
        });
        
        this.add(panelBackGround);
        setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(800, 500);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModuloCocina().setVisible(true);

            }

        });
    }
}