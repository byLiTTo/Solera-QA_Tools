package qa.tools.models;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import qa.tools.constants.ColorPalette;

public class JGradientPanel extends JPanel {

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Convertir el objeto Graphics en Graphics2D
        Graphics2D g2d = (Graphics2D) g;

        // Establecer la calidad de renderizado
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Crear un gradiente de color
        GradientPaint gradient = new GradientPaint(
                0, 0,
                ColorPalette.DARK_PURPLE,
                getWidth(), getHeight(),
                ColorPalette.LIGHT_PURPLE
        );

        // Dibujar el gradiente de color en el panel
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}


