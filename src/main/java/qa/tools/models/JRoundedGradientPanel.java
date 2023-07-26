/*
 * Copyright (c) 2023. Carlos.GarciaSilva@solera.com
 * All rights reserved to QapterClaims FR team
 */

package qa.tools.models;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;
import qa.tools.constants.ColorPalette;

public class JRoundedGradientPanel extends JPanel {
    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->

    private int arcWidth = 20; // Ancho de la esquina redondeada
    private int arcHeight = 20; // Alto de la esquina redondeada

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Convertir el objeto Graphics en Graphics2D
        Graphics2D g2d = (Graphics2D) g;

        // Establecer la calidad de renderizado
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Crear un objeto RoundRectangle2D para representar el borde redondeado
        RoundRectangle2D roundedRect = new RoundRectangle2D.Double(
                0, 0, getWidth() - 1d, getHeight() - 1d, arcWidth, arcHeight);

        // Dibujar el borde redondeado en el panel
        g2d.setColor(Color.BLACK);
        g2d.draw(roundedRect);

        // Crear un gradiente de color
        GradientPaint gradient = new GradientPaint(
                0, 0,
                ColorPalette.DARK_PURPLE,
                getWidth(), getHeight(),
                ColorPalette.LIGHT_PURPLE
        );

        // Dibujar el gradiente de color en el panel, dentro del borde redondeado
        g2d.clip(roundedRect);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
