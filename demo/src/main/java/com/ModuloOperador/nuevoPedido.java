import javax.swing.*;
import java.awt.*;

public class nuevoPedido extends JFrame {

    public static void main(String[] args) {
        nuevoPedido pepe = new nuevoPedido();
        pepe.setVisible(true);
    }

    public nuevoPedido() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 800, 500);
        setResizable(false); 

        JPanel mainPanel = new JPanel(); // Panel principal
        mainPanel.setLayout(new BorderLayout());
        getContentPane().add(mainPanel);

        JPanel panelIzquierda = new JPanel(null); // Utiliza un layout nulo
        panelIzquierda.setPreferredSize(new Dimension(500, 200));
        panelIzquierda.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(panelIzquierda, BorderLayout.WEST);

        JButton boton = new JButton("<-----"); // Agrega el botón
        boton.setBounds(20, 20, 100, 30); // Posición del botón en la esquina superior izquierda
        panelIzquierda.add(boton);

        JTextField buscador = new JTextField("🔍");
        buscador.setBounds(80, 60, 330, 30); // Posición del buscador debajo del botón
        panelIzquierda.add(buscador);

        JPanel cajasPanel = new JPanel();
        cajasPanel.setBounds(115, 120, 200, 200); // Posición de cajasPanel debajo del buscador
        cajasPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panelIzquierda.add(cajasPanel);

        JSlider slider = new JSlider(JSlider.VERTICAL, 0, 100, 50); // Crea un slider vertical
        slider.setBounds(315, 120, 50, 200); // Posición del slider al lado del cajasPanel
        panelIzquierda.add(slider);

        JTextField cajaTexto = new JTextField();
        cajaTexto.setBounds(85, 350, 200, 30); // Posición de la caja de texto debajo de "CANTIDAD"
        panelIzquierda.add(cajaTexto);

        JLabel cantidadLabel = new JLabel("CANTIDAD");
        cantidadLabel.setBounds(85, 380, 200, 30); // Posición de la etiqueta "CANTIDAD"
        cantidadLabel.setHorizontalAlignment(JLabel.CENTER); // Centrar el texto
        panelIzquierda.add(cantidadLabel);

        JButton agregarButton = new JButton("AGREGAR");
        agregarButton.setBounds(300, 350, 100, 30); // Posición del botón "AGREGAR" al lado de la caja de texto
        panelIzquierda.add(agregarButton);

        JPanel panelDerecha = new JPanel(null); // Panel para poner caja y botones
        panelDerecha.setPreferredSize(new Dimension(300, 450));
        panelDerecha.setBackground(Color.GRAY);
        mainPanel.add(panelDerecha, BorderLayout.EAST);

        JPanel cajaPanelDerecha = new JPanel(); //Crear un caja donde luego se verá el pedido
        cajaPanelDerecha.setBounds(55, 50, 200, 200);
        cajaPanelDerecha.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        cajaPanelDerecha.setBackground(Color.WHITE);
        panelDerecha.add(cajaPanelDerecha);

        JButton quitarBoton = new JButton("QUITAR"); //Crea el botón Quitar
        quitarBoton.setBounds(95, 270, 120, 30);
        panelDerecha.add(quitarBoton);

        JButton continuarBoton = new JButton("CONTINUAR"); //Crea el botón Continuar
        continuarBoton.setBounds(95, 310, 120, 30);
        panelDerecha.add(continuarBoton);
    }
}
