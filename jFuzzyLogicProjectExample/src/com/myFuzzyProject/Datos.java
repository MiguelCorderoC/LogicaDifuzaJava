package com.myFuzzyProject;

// Esta clase es solo para crear objetos que almacenarán los datos del historial de las predicciones hechas
public class Datos {
    private double servicio;
    private double comida;
    private double precio;
    private double reputacion;
    private String resultado;

    public Datos(double servicio, double comida, double precio, double reputacion, String resultado) {
        this.servicio = servicio;
        this.comida = comida;
        this.precio = precio;
        this.reputacion = reputacion;
        this.resultado = resultado;
    }

    // Métodos get y set para el campo "servicio"
    public double getServicio() {
        return servicio;
    }

    public void setServicio(double servicio) {
        this.servicio = servicio;
    }

    // Métodos get y set para el campo "comida"
    public double getComida() {
        return comida;
    }

    public void setComida(double comida) {
        this.comida = comida;
    }

    // Métodos get y set para el campo "precio"
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Métodos get y set para el campo "reputacion"
    public double getReputacion() {
        return reputacion;
    }

    public void setReputacion(double reputacion) {
        this.reputacion = reputacion;
    }

    // Métodos get y set para el campo "resultado"
    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}