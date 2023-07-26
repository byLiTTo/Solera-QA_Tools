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
import javax.swing.JButton;
import qa.tools.constants.ColorPalette;

public class JRoundedButton extends JButton {

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    private int arcWidth = 20; // Ancho de la esquina redondeada
    private int arcHeight = 20; // Alto de la esquina redondeada

    public JRoundedButton(String label) {
        super(label);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D roundedRect = new RoundRectangle2D.Double(
                0, 0, getWidth() - 1d, getHeight() - 1d, arcWidth, arcHeight);

        GradientPaint gradient = new GradientPaint(
                0, 0,
                ColorPalette.DARK_PURPLE,
                0, getHeight(),
                ColorPalette.LIGHT_PURPLE);

        g2d.clip(roundedRect);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(Color.WHITE);
        g2d.drawString(getText(), getWidth() / 2 - (getText().length() * 3), getHeight() / 2 + 5);

        g2d.setColor(Color.WHITE);
        g2d.draw(roundedRect);

        g2d.dispose();
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    @Override
    protected void paintBorder(Graphics g) {

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
    }
}
