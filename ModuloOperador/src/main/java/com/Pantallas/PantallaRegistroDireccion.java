package com.Pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.parser.ParseException;

import com.Clases.Cliente;
import com.Clases.EnumComunaBucaramanga;
import com.Clases.EnumComunaFloridablanca;
import com.Clases.EnumComunaGiron;
import com.Clases.EnumComunaPiedecuesta;
import com.Clases.TipoDireccion;
import com.Clases.Estructuras.linkedlist.ListaArticulos;

public class PantallaRegistroDireccion extends JFrame {
   String telefono;
   Cliente cliente;

   public PantallaRegistroDireccion(ListaArticulos listaPedido, String telefono)
         throws FileNotFoundException, IOException, ParseException {
      iniciarComponentes(listaPedido);
      this.telefono = telefono;
      setTitle("FoodUPB - Registro del cliente");
      setLocationRelativeTo(null);
      setResizable(false);
      setMaximizedBounds(getBounds());
   }

   public PantallaRegistroDireccion(ListaArticulos listaPedido, Cliente cliente)
         throws FileNotFoundException, IOException, ParseException {
      this.cliente = cliente;
      iniciarComponentes(listaPedido);
      setTitle("FoodUPB - Registro del cliente");
      setLocationRelativeTo(null);
      setResizable(false);
      setMaximizedBounds(getBounds());
   }

   private void iniciarComponentes(ListaArticulos listaPedido)
         throws FileNotFoundException, IOException, ParseException {
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
               if (telefono != null) {
                  new PantallaPedido(listaPedido, telefono);
               }
               if (cliente != null) {
                  new PantallaPedido(listaPedido, cliente);
               }

            } catch (IOException | ParseException e1) {
            }
            dispose();
         }
      });

      JLabel labelTitulo = new JLabel("INFORMACIÓN DEL CLIENTE");
      labelTitulo.setBounds(235, 20, 400, 30);
      labelTitulo.setFont(labelTitulo.getFont().deriveFont(Font.PLAIN, 24f));

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
      for (TipoDireccion direccion : TipoDireccion.values()) {
         comboDireccion.addItem(direccion);
      }
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
      campoDirAdicional.setBounds(60, 240, 260, 30);
      JLabel labelMunicipio = new JLabel("Municipio");
      labelMunicipio.setBounds(360, 210, 100, 30);
      JComboBox<String> comboMunicipio = new JComboBox<>();
      comboMunicipio.addItem("Bucaramanga");
      comboMunicipio.addItem("Floridablanca");
      comboMunicipio.addItem("Piedecuesta");
      comboMunicipio.addItem("Giron");
      comboMunicipio.setBounds(360, 240, 160, 30);
      JLabel labelComuna = new JLabel("Comuna");
      labelComuna.setBounds(560, 210, 100, 30);
      JComboBox<String> comboComuna = new JComboBox<>();
      comboComuna.setBounds(560, 240, 160, 30);
      comboMunicipio.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (comboMunicipio.getSelectedItem().equals("Bucaramanga")) {
               comboComuna.removeAllItems();
               for (EnumComunaBucaramanga comuna : EnumComunaBucaramanga.values()) {
                  comboComuna.addItem(comuna.toString());
               }
            } else if (comboMunicipio.getSelectedItem().equals("Floridablanca")) {
               comboComuna.removeAllItems();
               for (EnumComunaFloridablanca comuna : EnumComunaFloridablanca.values()) {
                  comboComuna.addItem(comuna.toString());
               }
            } else if (comboMunicipio.getSelectedItem().equals("Piedecuesta")) {
               comboComuna.removeAllItems();
               for (EnumComunaPiedecuesta comuna : EnumComunaPiedecuesta.values()) {
                  comboComuna.addItem(comuna.toString());
               }
            } else if (comboMunicipio.getSelectedItem().equals("Giron")) {
               comboComuna.removeAllItems();
               for (EnumComunaGiron comuna : EnumComunaGiron.values()) {
                  comboComuna.addItem(comuna.toString());
               }
            }
         }
      });

      JLabel labelBarrio = new JLabel("Barrio");
      labelBarrio.setBounds(60, 285, 200, 30);
      JTextField campoBarrio = new JTextField();
      campoBarrio.setBounds(60, 315, 200, 30);

      JButton buttonContinuar = new JButton("CONTINUAR");
      buttonContinuar.setBounds(600, 315, 120, 30);
      buttonContinuar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (!((String) campoNombres.getText()).equals("") && !((String) campoApellidos.getText()).equals("")
                  && !((String) campoDireccion.getText()).equals("")
                  && !((String) campoDireccion2.getText()).equals("")
                  && !((String) campoDirAdicional.getText()).equals("")
                  && !((String) campoBarrio.getText()).equals("")
                  && (comboComuna.getSelectedItem() != null)) {
               if (telefono != null) {
                  cliente = new Cliente(telefono, campoNombres.getText(), campoApellidos.getText(),
                        (TipoDireccion) comboDireccion.getSelectedItem(),
                        campoDireccion.getText(), campoDireccion2.getText(), campoDirAdicional.getText(),
                        (String) comboMunicipio.getSelectedItem(),
                        (String) comboComuna.getSelectedItem(), campoBarrio.getText());
               }
               try {
                  new PantallaConfirmacion(listaPedido, cliente);
               } catch (IOException | ParseException e1) {
                  e1.printStackTrace();
               }
               dispose();
            }
         }
      });

      if (cliente != null) {
         campoNombres.setText(cliente.getNombre());
         campoApellidos.setText(cliente.getApellido());
         comboDireccion.setSelectedItem(cliente.getTipoDireccion());
         campoDireccion.setText(cliente.getDireccion1());
         campoDireccion2.setText(cliente.getDireccion2());
         campoDirAdicional.setText(cliente.getDireccionAdicional());
         comboMunicipio.setSelectedItem(cliente.getMunicipio());
         comboComuna.setSelectedItem(cliente.getComuna());
         campoBarrio.setText(cliente.getBarrio());
      }

      mainPanel.add(labelTitulo);
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
      mainPanel.add(labelComuna);
      mainPanel.add(comboComuna);
      mainPanel.add(labelMunicipio);
      mainPanel.add(comboMunicipio);
      mainPanel.add(labelBarrio);
      mainPanel.add(campoBarrio);
      mainPanel.add(buttonContinuar);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(0, 0, 800, 500);
      setResizable(false);
      setVisible(true);
   }
}
