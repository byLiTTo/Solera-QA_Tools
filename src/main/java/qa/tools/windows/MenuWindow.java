package qa.tools.windows;

import qa.tools.constants.ResourcesConstants;
import qa.tools.models.JRoundedButton;
import qa.tools.models.JRoundedPanel;
import qa.tools.utils.PomReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuWindow extends JFrame {
    ImageIcon imagenFondo;
    private JPanel jpanel;
    private JLabel labelFondo;
    private JLabel userIcon;
    private JRoundedPanel userIconPanel;
    private JTextField userTextField;
    private JRoundedPanel userRegion;
    private JLabel passIcon;
    private JRoundedPanel passIconPanel;
    private JPasswordField passTextField;
    private JRoundedPanel passRegion;
    private JRoundedButton loginButton;
    private JPanel loginRegion;
    private JLabel versionTextField;
    private JLayeredPane layeredPane;

    public MenuWindow() {

        // Background panel  --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        imagenFondo = new ImageIcon(ResourcesConstants.LOGIN_BACKGROUND);
        labelFondo = new JLabel(imagenFondo);
        labelFondo.setSize(imagenFondo.getIconWidth(), imagenFondo.getIconHeight());
        labelFondo.setVerticalAlignment(SwingConstants.CENTER);
        labelFondo.setHorizontalAlignment(SwingConstants.CENTER);

        // Version region    --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        versionTextField = new JLabel(PomReader.readSnapshotVersion());
        versionTextField.setSize(200, 44);
        versionTextField.setBounds(10, imagenFondo.getIconHeight() - 40, 200, 44);

        // Layered panel --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        layeredPane = new JLayeredPane();
        layeredPane.add(labelFondo, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(versionTextField, JLayeredPane.PALETTE_LAYER);

        // Adding content to JFrame  --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        setContentPane(layeredPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.RED);
        setResizable(false);
        setIconImage(new ImageIcon(ResourcesConstants.APP_ICON).getImage());
        setSize((labelFondo.getWidth()), (labelFondo.getHeight() + 28));
        setLocationRelativeTo(null);
        setTitle("QapterClaims FR - QA tools");
    }

    public static void runApplication() {
        new MenuWindow();
    }
}
