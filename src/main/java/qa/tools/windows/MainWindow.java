package qa.tools.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import qa.tools.constants.ResourcesConstants;
import qa.tools.models.JRoundedButton;
import qa.tools.models.JRoundedPanel;
import qa.tools.utils.PomReader;

public class MainWindow extends JFrame {

    private JPanel jpanel;

    public MainWindow(String appTitle) {
        super(appTitle);

        // Background panel  --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        ImageIcon imagenFondo = new ImageIcon(ResourcesConstants.MAIN_MENU_BACKGROUND);
        JLabel labelFondo = new JLabel(imagenFondo);
        labelFondo.setSize(imagenFondo.getIconWidth(), imagenFondo.getIconHeight());
        labelFondo.setVerticalAlignment(SwingConstants.CENTER);
        labelFondo.setHorizontalAlignment(SwingConstants.CENTER);

        // User region   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        JLabel userIcon = new JLabel();
        userIcon.setIcon(new ImageIcon(ResourcesConstants.USER_ICON));

        JRoundedPanel userIconPanel = new JRoundedPanel();
        userIconPanel.setSize(44, 44);
        userIconPanel.add(userIcon, BorderLayout.CENTER);

        JTextField userTextField = new JTextField();
        userTextField.setBounds(60, 0, 200, 44);
        userTextField.setBorder(null);
        userTextField.setOpaque(false);

        JRoundedPanel userRegion = new JRoundedPanel();
        userRegion.setLayout(null);
        userRegion.setOpaque(false);
        userRegion.setBackground(Color.red);
        userRegion.setBounds(275, 300, 305, 44);

        userRegion.add(userIconPanel);
        userRegion.add(userTextField);

        // Password region   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        JLabel passIcon = new JLabel();
        passIcon.setIcon(new ImageIcon(ResourcesConstants.PASS_ICON));

        JRoundedPanel passIconPanel = new JRoundedPanel();
        passIconPanel.setSize(44, 44);
        passIconPanel.add(passIcon, BorderLayout.CENTER);

        JPasswordField passTextField = new JPasswordField();
        passTextField.setBounds(60, 0, 200, 44);
        passTextField.setBorder(null);
        passTextField.setOpaque(false);

        JRoundedPanel passRegion = new JRoundedPanel();
        passRegion.setLayout(null);
        passRegion.setOpaque(false);
        passRegion.setBackground(Color.red);
        passRegion.setBounds(275, 355, 305, 44);

        passRegion.add(passIconPanel);
        passRegion.add(passTextField);

        // Login button  --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        JRoundedButton loginButton = new JRoundedButton("LOGIN");
        loginButton.setBounds(60, 0, 200, 44);
        loginButton.setBorder(null);
        loginButton.setOpaque(false);

        JPanel loginRegion = new JPanel();
        loginRegion.setOpaque(false);
        loginRegion.setBackground(Color.red);
        loginRegion.setBounds(275, 410, 305, 44);
        loginRegion.setLayout(null);
        loginRegion.add(loginButton);

//        JTextField versionTextField = new JTextField(PomReader.readSnapshotVersion());
        JTextField versionTextField = new JTextField("HOLAAAA");
        userTextField.setBounds(60, 0, 200, 44);
        userTextField.setBorder(null);
        userTextField.setOpaque(false);

        JPanel versionRegion = new JPanel();
        versionRegion.setOpaque(false);
        versionRegion.setBackground(Color.red);
        versionRegion.setBounds(275, 410, 305, 44);
        versionRegion.setLayout(null);
        versionRegion.add(versionTextField);

        // Layered panel --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.add(labelFondo, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(userRegion, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(passRegion, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(loginRegion, JLayeredPane.PALETTE_LAYER);

        // Adding content to JFrame  --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        this.setContentPane(layeredPane);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBackground(Color.RED);
        this.setResizable(false);
        this.setIconImage(new ImageIcon(ResourcesConstants.APP_ICON).getImage());
        this.setBounds(0, 0, (labelFondo.getWidth() + 16), (labelFondo.getHeight() + 39));
        this.setLocationRelativeTo(null);


    }

    public static void runApplication() {
        new MainWindow("QapterClaims FR - QA tools");
    }

}
