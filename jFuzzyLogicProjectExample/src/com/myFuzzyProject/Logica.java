package com.myFuzzyProject;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Logica {

    static ArrayList<Datos> arrayHistorial = new ArrayList<>();

    public static String recomendar(String txtServicio, String txtComida, String txtPrecio, String txtReputacion) {
        double servicio = 0;
        double comida = 0;
        double precio = 0;
        double reputacion = 0;

        // Convierto los textos a datos tipo Double
        try {
            servicio = Double.parseDouble(txtServicio);
            comida = Double.parseDouble(txtComida);
            precio = Double.parseDouble(txtPrecio);
            reputacion = Double.parseDouble(txtReputacion);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor ingresa numeros enteros");
        }

        // Creo un objeto para poder leer el archivo fcl, que contiene las reglas
        // difusas
        String filename = "prueba.fcl";
        FIS fis = FIS.load(filename, true);

        // En caso de no encontrar el archivo
        if (fis == null) {
            System.err.println("Can't load file: '" + filename + "'");
            System.exit(1);
        }

        // Obtengo las funciones por default de este objeto
        FunctionBlock fb = fis.getFunctionBlock(null);

        // Cambio y doy valores a las variables de entrada
        fb.setVariable("servicio", servicio);
        fb.setVariable("comida", comida);
        fb.setVariable("precio", precio);
        fb.setVariable("reputacion", reputacion);

        // funcion para evaluar
        fb.evaluate();

        // Muestro las variables de salida
        fb.getVariable("recomendacion").defuzzify();

        // Imprimo las reglas difusas
        System.out.println(fb);
        System.out.println("recomendacion: " + fb.getVariable("recomendacion").getValue());

        // Retorno el resultado de todo para mostrarlo en el campo de texto Resultado
        return " " + fb.getVariable("recomendacion").getValue();
    }

    // Funcion para obtener las graficas
    public static void grafica(String txtServicio, String txtComida, String txtPrecio, String txtReputacion) {
        double servicio = 0;
        double comida = 0;
        double precio = 0;
        double reputacion = 0;

        // Convierto los textos a datos tipo Double
        try {
            servicio = Double.parseDouble(txtServicio);
            comida = Double.parseDouble(txtComida);
            precio = Double.parseDouble(txtPrecio);
            reputacion = Double.parseDouble(txtReputacion);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor ingresa numeros enteros");
        }

        // Igual que el anterior cargo el archivo fcl
        String fileName = "prueba.fcl";
        FIS fis = FIS.load(fileName, true);

        if (fis == null) {
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }

        FunctionBlock functionBlock = fis.getFunctionBlock(null);

        // Funcion para mostrar las graficas
        JFuzzyChart.get().chart(functionBlock);

        fis.setVariable("servicio", servicio);
        fis.setVariable("comida", comida);
        fis.setVariable("precio", precio);
        fis.setVariable("reputacion", reputacion);

        fis.evaluate();

        Variable tip = functionBlock.getVariable("recomendacion");
        JFuzzyChart.get().chart(tip, tip.getDefuzzifier(), true);

        System.out.println(fis);
    }

    public static void historial(String txtServicio, String txtComida, String txtPrecio, String txtReputacion,
            String txtResultado) {
        double servicio = 0;
        double comida = 0;
        double precio = 0;
        double reputacion = 0;

        // Convierto los textos a datos tipo Double
        try {
            servicio = Double.parseDouble(txtServicio);
            comida = Double.parseDouble(txtComida);
            precio = Double.parseDouble(txtPrecio);
            reputacion = Double.parseDouble(txtReputacion);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor ingresa numeros enteros");
        }

        Datos dato = new Datos(servicio, comida, precio, reputacion, txtResultado);
        arrayHistorial.add(dato);
        // JOptionPane.showMessageDialog(null, "Objeto guardadi con exito");
    }
}
