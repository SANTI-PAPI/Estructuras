package com.Pantallas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Properties;

import com.Clases.Articulo;
import com.Clases.Cliente;
import com.Clases.Comuna;
import com.Clases.Estructuras.grafo.Grafo;
import com.Clases.Estructuras.interfaces.node.NodeInterface;
import com.Clases.Estructuras.linkedlist.ListaArticulos;
import com.Clases.Estructuras.linkedlist.ListaComunas;
import com.Clases.Estructuras.linkedlist.ListaEnlazada;
import com.Clases.Estructuras.linkedlist.ListaPedidos;
import com.Clases.Estructuras.queue.ColaPrioridad;
import com.Clases.Servidor.ClienteRMI;

public class Domiciliario extends JFrame {
    int cont = 0;
    String nombre;
    boolean flagEnd = false;
    String telefono;
    Cliente cliente;
    ListaArticulos pedido;
    DefaultTableModel modelo;
    DefaultTableModel modeloPedidos;
    DefaultTableModel modeloRuta;
    String idPedido;
    int pedidoActual = 0;
    ClienteRMI servidor;
    ColaPrioridad<Articulo> colaDespacho = new ColaPrioridad<>(10);
    Grafo grfMetropolitano;

    public Domiciliario() throws IOException, ParseException {
        grfMetropolitano = new Grafo(46);

        // Floridablnca
        grfMetropolitano.addConexion(1, 2);
        grfMetropolitano.addConexion(1, 4);
        grfMetropolitano.addConexion(2, 7);
        grfMetropolitano.addConexion(2, 5);
        grfMetropolitano.addConexion(4, 6);
        grfMetropolitano.addConexion(4, 8);

        // Bucaramanga
        grfMetropolitano.addConexion(1, 9);
        grfMetropolitano.addConexion(9, 10);
        grfMetropolitano.addConexion(9, 11);
        grfMetropolitano.addConexion(10, 12);
        grfMetropolitano.addConexion(10, 13);
        grfMetropolitano.addConexion(11, 14);
        grfMetropolitano.addConexion(11, 15);
        grfMetropolitano.addConexion(12, 16);
        grfMetropolitano.addConexion(12, 17);
        grfMetropolitano.addConexion(13, 18);
        grfMetropolitano.addConexion(15, 19);
        grfMetropolitano.addConexion(19, 20);
        grfMetropolitano.addConexion(20, 21);
        grfMetropolitano.addConexion(21, 22);
        grfMetropolitano.addConexion(22, 23);
        grfMetropolitano.addConexion(23, 24);

        // Giron
        grfMetropolitano.addConexion(5, 25);
        grfMetropolitano.addConexion(25, 26);
        grfMetropolitano.addConexion(26, 27);
        grfMetropolitano.addConexion(25, 28);
        grfMetropolitano.addConexion(28, 29);
        grfMetropolitano.addConexion(28, 30);
        grfMetropolitano.addConexion(29, 31);
        grfMetropolitano.addConexion(31, 32);
        grfMetropolitano.addConexion(32, 33);
        grfMetropolitano.addConexion(32, 34);
        grfMetropolitano.addConexion(34, 35);
        grfMetropolitano.addConexion(30, 36);
        grfMetropolitano.addConexion(36, 37);
        grfMetropolitano.addConexion(37, 38);
        grfMetropolitano.addConexion(36, 39);
        grfMetropolitano.addConexion(39, 40);
        grfMetropolitano.addConexion(40, 41);
        grfMetropolitano.addConexion(40, 42);
        grfMetropolitano.addConexion(42, 43);
        grfMetropolitano.addConexion(43, 44);

        // Piedecuesta
        // grfMetropolitano.addConexion(8, 45);
        Properties config = new Properties();

        File archivo = new File(
                "C:\\Users\\santiago\\Documents\\REPOSITORIO-FINAL-ESTRUCTURAS\\Estructuras\\Domicilio\\ModuloDomiciliario\\config.properties");
        String dir = archivo.getCanonicalPath();
        System.out.println(dir);
        System.out.println("flag");
        try (FileInputStream fin = new FileInputStream(new File(dir))) {
            System.out.println("falg1");
            config.load(fin);
            servidor = new ClienteRMI((String) config.get("IP"), (String) config.get("PORT"),
                    (String) config.get("SERVICENAME"));
        } catch (Exception e) {
            System.out.println("llama");
            System.out.println(e.getMessage());
        }
        this.nombre = nombre;
        this.cliente = cliente;
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        // Crear el marco principal
        this.setVisible(true);
        this.setBounds(EXIT_ON_CLOSE, ABORT, 1000, 600);
        this.setLayout(null);

        // Crear un panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(173, 216, 230));
        mainPanel.setBounds(0, 0, 1000, 800);

        // Panel izquierdo para "Pedido"
        JPanel pedidoPanel = new JPanel();
        pedidoPanel.setBounds(30, 30, 400, 400);
        pedidoPanel.setLayout(new BorderLayout());
        pedidoPanel.add(new JLabel("Pedido"), BorderLayout.NORTH);

        // Panel derecho para "Ruta"
        JPanel rutaPanel = new JPanel();
        rutaPanel.setBounds(450, 50, 400, 300);
        rutaPanel.add(new JLabel("Ruta"));

        //tabLa de la ruta 
        modeloPedidos = new DefaultTableModel();
        modeloPedidos.addColumn("Artículo");
        modeloPedidos.addColumn("Domicilio");
        JTable tablaPedidos = new JTable(modeloPedidos);
        JScrollPane scrollPane = new JScrollPane(tablaPedidos);

        pedidoPanel.add(scrollPane);

        // Botón "Siguiente Pedido"
        JButton siguientePedidoButton = new JButton("Siguiente Articulo");
        siguientePedidoButton.setBounds(30, 450, 300, 50);

        mainPanel.add(siguientePedidoButton);
        mainPanel.add(pedidoPanel);
        mainPanel.add(rutaPanel);

        // Agregar componentes al panel principal
        this.add(mainPanel);

        // Crear una tabla para mostrar los detalles del pedido actual
        modeloRuta = new DefaultTableModel();
        modeloRuta.addColumn("Comuna");
        JTable tablaRutas = new JTable(modeloRuta);
        JScrollPane scrollPaneRuta = new JScrollPane(tablaRutas);

        rutaPanel.add(scrollPaneRuta, BorderLayout.CENTER);

        siguientePedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cont++;
                if (cont == 8) {
                    ListaEnlazada<String> listaComunas = new ListaEnlazada<>();
                    for (int i = 0; i < 8; i ++) {
                        String comunaActual = (String) modeloPedidos.getValueAt(i, 1);
                        if (!listaComunas.contains(comunaActual)) {
                            listaComunas.add(comunaActual);
                       }
                    }
                    ListaComunas listaNodos = new ListaComunas();
                    Iterator<NodeInterface<String>> iterador = listaComunas.iterator();
                    while (iterador.hasNext()) {
                        int valorNodo = comunaToInteger(iterador.next().getObject());
                        ListaEnlazada<Integer> listaRecorrido = grfMetropolitano.recorrido(9, valorNodo);
                        Comuna comunaActual = new Comuna(valorNodo, listaRecorrido.getFromEnd());
                        listaNodos.add(comunaActual);
                    }
                    listaNodos.sort();
                    while (!listaNodos.isEmpty()) {
                        String[] arreglo = new String[1];
                        arreglo[0] = integerToComuna(listaNodos.pop().getValorNodo());
                        modeloRuta.addRow(arreglo);
                    }
                }
                if (cont > 8) {
                    modeloRuta.setColumnCount(0);
                    cont = 0;
                }
                try {
                    cargarPedidoEnTabla();
                } catch (Exception e1) {
                } 
            }
        });
    }

    private void cargarPedidoEnTabla() throws RemoteException, IOException {
        modeloPedidos.setRowCount(cont); // Limpiar la tabla
        Articulo articuloActual = servidor.desencolarArticuloDomiiciliario();
        cliente = articuloActual.getClienteAsociado();
        modeloPedidos.addRow(new Object[] { articuloActual.getNombre().toString(), cliente.getComuna().toString() });
    }

    public int comunaToInteger(String comuna) {
        switch (comuna) {
            case "CALDAS":
                return 1;

            case "BOSQUE":
                return 2;

            case "CUMBRE":
                return 3;

            case "CASCO_ANTIGUO":
                return 4;

            case "LAGOS":
                return 5;

            case "BUCARICA":
                return 6;

            case "CANAVERAL":
                return 7;

            case "VILLABEL":
                return 8;

            case "PROVENZA":
                return 9;

            case "SUR":
                return 10;

            case "PEDREGOSA":
                return 11;

            case "MUTIS":
                return 12;

            case "SUROCCIDENTE":
                return 13;

            case "CONCORDIA":
                return 14;

            case "CIUDADELA":
                return 16;

            case "CENTRO":
                return 17;

            case "GARCIA_ROVIRA":
                return 18;

            case "ORIENTAL":
                return 19;

            case "MORRORRICO":
                return 20;

            case "OCCIDENTAL":
                return 21;

            case "SAN_FRANCISCO":
                return 22;

            case "NORORIENTAL":
                return 23;

            case "NORTE":
                return 24;

            case "RUITOQUE":
                return 25;

            case "ACAPULCO":
                return 26;

            case "BARBOSA":
                return 27;
            case "PALO_GORDO":
                return 28;

            case "LLANADAS":
                return 29;

            case "RIO_FRIO":
                return 30;

            case "CASCO_URBANO":
                return 31;

            case "CARRIZAL":
                return 32;

            case "BOCAS":
                return 33;

            case "LAGUNETAS":
                return 34;

            case "LLANO_GRANDE":
                return 35;

            case "PENAS":
                return 36;

            case "CHOCOITA":
                return 37;

            case "CHOCOA":
                return 38;

            case "CANTALTA":
                return 39;

            case "PANTANO":
                return 40;

            case "MOTOSO":
                return 41;

            case "CEDRO":
                return 42;

            case "PARROQUIA":
                return 43;

            case "SOGAMOSO":
                return 44;

            case "MARTHA":
                return 45;
        }

        return -1;
    }
    public String integerToComuna(int comuna) {
        switch (comuna) {
            case 1:
                return "CALDAS";

            case 2:
                return "BOSQUE";

            case 3:
                return "CUMBRE";

            case 4:
                return "CASCO_ANTIGUO";

            case 5:
                return "LAGOS";

            case 6:
                return "BUCARICA";

            case 7:
                return "CANAVERAL";

            case 8:
                return "VILLABEL";

            case 9:
                return  "PROVENZA";

            case 10:
                return "SUR";

            case 11:
                return "PEDREGOSA";

            case 12:
                return "MUTIS";

            case 13:
                return "SUROCCIDENTE";

            case 14:
                return "CONCORDIA";

            case 16:
                return "CIUDADELA";

            case 17:
                return "CENTRO";

            case 18:
                return "GARCIA_ROVIRA";

            case 19:
                return "ORIENTAL";

            case 20:
                return "MORRORRICO";

            case 21:
                return "OCCIDENTAL";

            case 22:
                return "SAN_FRANCISCO";

            case 23:
                return "NORORIENTAL";

            case 24:
                return "NORTE";

            case 25:
                return "RUITOQUE";

            case 26:
                return "ACAPULCO";

            case 27:
                return "BARBOSA";
            case 28:
                return "PALO_GORDO";

            case 29:
                return "LLANADAS";

            case 30:
                return "RIO_FRIO";

            case 31:
                return "CASCO_URBANO";

            case 32:
                return "CARRIZAL";

            case 33:
                return "BOCAS";

            case 34:
                return "LAGUNETAS";

            case 35:
                return "LLANO_GRANDE";

            case 36:
                return  "PENAS";

            case 37:
                return "CHOCOITA";

            case 38:
                return "CHOCOA";

            case 39:
                return "CANTALTA";

            case 40:
                return "PANTANO";

            case 41:
                return "MOTOSO";

            case 42:
                return "CEDRO";

            case 43:
                return "PARROQUIA";

            case 44:
                return "SOGAMOSO";

            case 45:
                return "MARTHA";
        }

        return " ";
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Domiciliario domiciliario;

                try {
                    new Domiciliario();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
    }
}
