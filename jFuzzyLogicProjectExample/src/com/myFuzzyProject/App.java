package com.myFuzzyProject;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import com.Components.*;

public class App extends JFrame implements ActionListener {

    ArrayList<Datos> historial = Logica.arrayHistorial;
    JPanel pnlPrincipal = new JPanel();
    JPanelPersonalizado pnlDatos = new JPanelPersonalizado("Datos:");
    JPanelPersonalizado pnlOpciones = new JPanelPersonalizado("Opciones");
    JPanelPersonalizado pnlConsulta = new JPanelPersonalizado("Consulta");
    JButton btnPredecir = new JButton("Predecir");
    JButton btnGraficar = new JButton("Graficar");
    JButton btnHistorial = new JButton("Guardar");
    JTextArea txtServicio = new JTextArea();
    JTextArea txtComida = new JTextArea();
    JTextArea txtPrecio = new JTextArea();
    JTextArea txtReputacion = new JTextArea();
    JTextArea txtResultado = new JTextArea();
    JTable tablaConsulta;

    public App() {
        setTitle("Sistema difuso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new GridLayout(2, 1));

        // Llenar la tabla con los datos del historial
        initComponents();

        btnPredecir.addActionListener(this);
        btnGraficar.addActionListener(this);
        btnHistorial.addActionListener(this);

        add(pnlPrincipal);
        add(pnlConsulta);
        setVisible(true);
    }

    private void initComponents() {
        // Configuracion de botones
        btnPredecir.setForeground(Color.WHITE);
        btnPredecir.setBackground(Color.decode("#124076"));
        btnHistorial.setForeground(Color.WHITE);
        btnHistorial.setBackground(Color.decode("#D04848"));
        btnGraficar.setBackground(Color.decode("#4CCD99"));
        btnGraficar.setForeground(Color.WHITE);

        // Configuración de la fuente para los botones con un tamaño de 16 píxeles
        Font font = new Font(btnPredecir.getFont().getName(), Font.BOLD, 16);
        btnPredecir.setFont(font);
        btnHistorial.setFont(font);
        btnGraficar.setFont(font);

        // Configuracion del panel Datos
        Border borderDatos = BorderFactory.createEmptyBorder(20, 50, 20, 50);
        pnlDatos.setBorder(borderDatos);
        pnlDatos.setBackground(Color.white);
        pnlDatos.setLayout(new GridLayout(10, 1));
        pnlDatos.add(JTextBoxPersonalizado.createLabel("Servicio (0 - 9):"));
        pnlDatos.add(JTextBoxPersonalizado.createBorderedTextArea(txtServicio));
        pnlDatos.add(JTextBoxPersonalizado.createLabel("Comida (0 - 9):"));
        pnlDatos.add(JTextBoxPersonalizado.createBorderedTextArea(txtComida));
        pnlDatos.add(JTextBoxPersonalizado.createLabel("Precio (0 - 9):"));
        pnlDatos.add(JTextBoxPersonalizado.createBorderedTextArea(txtPrecio));
        pnlDatos.add(JTextBoxPersonalizado.createLabel("Reputacion (0 - 5):"));
        pnlDatos.add(JTextBoxPersonalizado.createBorderedTextArea(txtReputacion));
        pnlDatos.add(JTextBoxPersonalizado.createLabel("Resultado:"));
        pnlDatos.add(JTextBoxPersonalizado.createBorderedTextArea(txtResultado));

        // configuracion del panel de opciones
        pnlOpciones.setBorder(borderDatos);
        pnlOpciones.setLayout(new GridLayout(7, 1));
        pnlOpciones.add(new Label());
        pnlOpciones.add(btnPredecir);
        pnlOpciones.add(new Label());
        pnlOpciones.add(btnGraficar);
        pnlOpciones.add(new Label());
        pnlOpciones.add(btnHistorial);
        pnlOpciones.add(new Label());

        // Configuracion del panel de consultas
        pnlConsulta.setBackground(Color.white);
        pnlConsulta.setLayout(new BorderLayout());

        // Crear modelo de tabla
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Servicio");
        modeloTabla.addColumn("Comida");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Reputacion");
        modeloTabla.addColumn("Resultado");
        modeloTabla.addColumn("Grafica");

        // Crear la tabla con el modelo
        tablaConsulta = new JTable(modeloTabla);

        tablaConsulta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaConsulta.rowAtPoint(e.getPoint());
                int columna = tablaConsulta.columnAtPoint(e.getPoint());
                if (columna == tablaConsulta.getColumnCount() - 1) { // Verifica si la celda clicada es la columna
                                                                     // "Grafica"
                    // Obtener valores de la fila seleccionada
                    DefaultTableModel modeloTabla = (DefaultTableModel) tablaConsulta.getModel();
                    Object servicioObj = modeloTabla.getValueAt(fila, 0);
                    Object comidaObj = modeloTabla.getValueAt(fila, 1);
                    Object precioObj = modeloTabla.getValueAt(fila, 2);
                    Object reputacionObj = modeloTabla.getValueAt(fila, 3);
                    Object resultadoObj = modeloTabla.getValueAt(fila, 4);

                    // Convertir los valores a String
                    String servicio = servicioObj.toString();
                    String comida = comidaObj.toString();
                    String precio = precioObj.toString();
                    String reputacion = reputacionObj.toString();
                    String resultado = resultadoObj.toString();

                    // Aquí puedes usar los valores obtenidos según sea necesario
                    System.out.println("Servicio: " + servicio);
                    System.out.println("Comida: " + comida);
                    System.out.println("Precio: " + precio);
                    System.out.println("Reputación: " + reputacion);
                    System.out.println("Resultado: " + resultado);

                    // Llama al método para graficar con los valores obtenidos
                    Logica.grafica(servicio, comida, precio, reputacion);
                }
            }
        });

        // Agregar la tabla a un JScrollPane para permitir el desplazamiento si es
        // necesario
        JScrollPane scrollPane = new JScrollPane(tablaConsulta);

        // Agregar el JScrollPane al panel de consulta
        pnlConsulta.add(scrollPane, BorderLayout.CENTER);

        // Configuracion del panel principal
        pnlPrincipal.setLayout(new GridLayout(1, 2));
        pnlPrincipal.add(pnlDatos);
        pnlPrincipal.add(pnlOpciones);
    }

    public static void main(String[] args) {
        new App();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPredecir) {
            txtResultado.setText(Logica.recomendar(txtServicio.getText(), txtComida.getText(), txtPrecio.getText(),
                    txtReputacion.getText()));
        }
        if (e.getSource() == btnGraficar) {
            Logica.grafica(txtServicio.getText(), txtComida.getText(), txtPrecio.getText(), txtReputacion.getText());
        }
        if (e.getSource() == btnHistorial) {
            Logica.historial(txtServicio.getText(), txtComida.getText(), txtPrecio.getText(),
                    txtReputacion.getText(), txtResultado.getText());
            cargarHistorialEnTabla(); // Llama al método para cargar el historial en la tabla
        }
    }

    private void cargarHistorialEnTabla() {
        DefaultTableModel modeloTabla = (DefaultTableModel) tablaConsulta.getModel();
        modeloTabla.setRowCount(0); // Limpia la tabla antes de llenarla con nuevos datos

        for (Datos dato : historial) {
            modeloTabla.addRow(new Object[] { dato.getServicio(), dato.getComida(), dato.getPrecio(),
                    dato.getReputacion(), dato.getResultado(), "Grafica" });
        }

    }
}