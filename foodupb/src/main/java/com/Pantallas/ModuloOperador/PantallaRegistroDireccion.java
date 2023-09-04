package com.Pantallas.ModuloOperador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;

import org.json.simple.parser.ParseException;

import com.Clases.TipoDireccion;
import com.Datos.JSONManager;

public class PantallaRegistroDireccion extends JFrame {

    public PantallaRegistroDireccion() throws FileNotFoundException, IOException, ParseException {
      iniciarComponentes();
      setTitle("FoodUPB - Creación del pedido");
      setLocationRelativeTo(null);
      setResizable(false);
      setMaximizedBounds(getBounds());
   }

   private void iniciarComponentes() throws FileNotFoundException, IOException, ParseException {

      JPanel mainPanel = new JPanel(); // Panel principal
      mainPanel.setLayout(null);
      mainPanel.setBackground(Color.LIGHT_GRAY);
      getContentPane().add(mainPanel);

      JButton buttonVolver = new JButton("VOLVER"); // Agrega el botón
      buttonVolver.setBounds(20, 20, 100, 30); // Posición del botón en la esquina superior izquierda
      mainPanel.add(buttonVolver);
      buttonVolver.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
                new PantallaPedido();
            } catch (IOException | ParseException e1) {
            }
            dispose();
         }
      });

      JLabel labelNombres = new JLabel("Nombres");
      labelNombres.setBounds(60, 60, 100, 30); 
      JTextField campoNombres = new JTextField();
      campoNombres.setBounds(60, 90, 300, 30);
      JLabel labelApellidos = new JLabel("Apellidos");
      labelApellidos.setBounds(420, 60, 100, 30);
      JTextField campoApellidos = new JTextField();
      campoApellidos.setBounds(420, 90, 300, 30);

      JLabel labelDireccion = new JLabel("Dirección");
      labelDireccion.setBounds(60, 135, 100, 30); 
      JComboBox<TipoDireccion> comboDireccion = new JComboBox<>();
      comboDireccion.setBounds(60, 165, 150, 30);
      JTextField campoDireccion = new JTextField();
      campoDireccion.setBounds(250, 165, 150, 30);
      JLabel labelDireccion2 = new JLabel("#");
      labelDireccion2.setBounds(440, 135, 100, 30);
      JTextField campoDireccion2 = new JTextField();
      campoDireccion2.setBounds(440, 165, 280, 30);

      JLabel labelDirAdicional = new JLabel("Dirección adicional");
      labelDirAdicional.setBounds(60, 210, 200, 30); 
      JTextField campoDirAdicional = new JTextField();
      campoDirAdicional.setBounds(60, 240, 300, 30);
      JLabel labelBarrio = new JLabel("Barrio");
      labelBarrio.setBounds(420, 210, 100, 30);
      JTextField campoBarrio = new JTextField();
      campoBarrio.setBounds(420, 240, 300, 30);
      JLabel labelMunicipio = new JLabel("Municipio");
      labelMunicipio.setBounds(60, 285, 200, 30); 
      JTextField campoMunicipio = new JTextField();
      campoMunicipio.setBounds(60, 315, 300, 30);
      mainPanel.add(labelNombres);
      mainPanel.add(campoNombres);
      mainPanel.add(labelApellidos);
      mainPanel.add(campoApellidos);
      mainPanel.add(labelDireccion);
      mainPanel.add(comboDireccion);
      mainPanel.add(campoDireccion);
      mainPanel.add(labelDireccion2);
      mainPanel.add(campoDireccion2);
      mainPanel.add(labelDirAdicional);
      mainPanel.add(campoDirAdicional);
      mainPanel.add(labelBarrio);
      mainPanel.add(campoBarrio);
      mainPanel.add(labelMunicipio);
      mainPanel.add(campoMunicipio);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(0, 0, 800, 500);
      setResizable(false);
      setVisible(true);
   }
}
