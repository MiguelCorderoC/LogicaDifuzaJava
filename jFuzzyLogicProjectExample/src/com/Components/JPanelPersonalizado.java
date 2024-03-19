package com.Components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

//Componente creado para obtener paneles personalizados con borde y titulo
public class JPanelPersonalizado extends JPanel {
    public JPanelPersonalizado(String titulo) {
        // Crear un borde de línea con un grosor de 3 píxeles
        Border bordeGrueso = BorderFactory.createLineBorder(Color.BLACK, 2);

        // Crear un TitledBorder personalizado con el título proporcionado
        TitledBorder titledBorder = BorderFactory.createTitledBorder(bordeGrueso, titulo);
        setBorder(titledBorder);
    }
}