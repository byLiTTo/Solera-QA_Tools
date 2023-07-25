package qa.tools.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class LoginWindow extends JFrame {

    private static final int TITLE_BAR_WIDTH = 16;
    private static final int TITLE_BAR_HEIGHT = 39;
    private ImageIcon imagenFondo;
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

    public LoginWindow() {

        // Background panel  --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        imagenFondo = new ImageIcon(ResourcesConstants.LOGIN_BACKGROUND);
        labelFondo = new JLabel(imagenFondo);
        labelFondo.setSize(imagenFondo.getIconWidth(), imagenFondo.getIconHeight());
        labelFondo.setVerticalAlignment(SwingConstants.CENTER);
        labelFondo.setHorizontalAlignment(SwingConstants.CENTER);

        // User region   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        userIcon = new JLabel();
        userIcon.setIcon(new ImageIcon(ResourcesConstants.USER_ICON));

        userIconPanel = new JRoundedPanel();
        userIconPanel.setSize(44, 44);
        userIconPanel.add(userIcon, BorderLayout.CENTER);
        userIconPanel.setOpaque(false);

        userTextField = new JTextField();
        userTextField.setBounds(60, 0, 200, 44);
        userTextField.setBorder(null);
        userTextField.setOpaque(false);

        userRegion = new JRoundedPanel();
        userRegion.setLayout(null);
        userRegion.setOpaque(false);
        userRegion.setBackground(Color.red);
        userRegion.setBounds(275, 300, 305, 44);

        userRegion.add(userIconPanel);
        userRegion.add(userTextField);

        // Password region   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        passIcon = new JLabel();
        passIcon.setIcon(new ImageIcon(ResourcesConstants.PASS_ICON));

        passIconPanel = new JRoundedPanel();
        passIconPanel.setSize(44, 44);
        passIconPanel.add(passIcon, BorderLayout.CENTER);
        passIconPanel.setOpaque(false);

        passTextField = new JPasswordField();
        passTextField.setBounds(60, 0, 200, 44);
        passTextField.setBorder(null);
        passTextField.setOpaque(false);

        passRegion = new JRoundedPanel();
        passRegion.setLayout(null);
        passRegion.setOpaque(false);
        passRegion.setBackground(Color.red);
        passRegion.setBounds(275, 355, 305, 44);

        passRegion.add(passIconPanel);
        passRegion.add(passTextField);

        // Login button  --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        loginButton = new JRoundedButton("LOGIN");
        loginButton.setBounds(60, 0, 200, 44);
        loginButton.setBorder(null);
        loginButton.setOpaque(false);

        loginRegion = new JPanel();
        loginRegion.setOpaque(false);
        loginRegion.setBackground(Color.red);
        loginRegion.setBounds(275, 410, 305, 44);
        loginRegion.setLayout(null);
        loginRegion.add(loginButton);

        // Version region    --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        versionTextField = new JLabel(PomReader.readSnapshotVersion());
        versionTextField.setBounds(10, imagenFondo.getIconHeight() - 40, 200, 44);

        // Layered panel --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        layeredPane = new JLayeredPane();
        layeredPane.add(labelFondo, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(userRegion, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(passRegion, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(loginRegion, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(versionTextField, JLayeredPane.PALETTE_LAYER);

        // Adding content to JFrame  --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        setContentPane(layeredPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(Color.RED);
        setResizable(false);
        setIconImage(new ImageIcon(ResourcesConstants.APP_ICON).getImage());
        setSize((labelFondo.getWidth() + TITLE_BAR_WIDTH), (labelFondo.getHeight() + TITLE_BAR_HEIGHT));
        setLocationRelativeTo(null);
        setTitle("QapterClaims FR - QA tools");

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (userTextField.getText().isEmpty() || passTextField.getText().isEmpty()) {
                    openLoginWindow();
                } else {
                    openMenuWindow();
                }
            }
        });

        userTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    passTextField.requestFocus();
                }
            }
        });

        passTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (userTextField.getText().isEmpty() || passTextField.getText().isEmpty()) {
                        openLoginWindow();
                    } else {
                        openMenuWindow();
                    }
                }
            }
        });
    }

    public static void runApplication() {
        new LoginWindow();
    }

    private void openMenuWindow() {
        MenuWindow menuWindow = new MenuWindow(userTextField.getText(), passTextField.getText());
        menuWindow.setLocationRelativeTo(this);
        menuWindow.setVisible(true);
        setVisible(false);
    }

    private void openLoginWindow() {
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setLocationRelativeTo(this);
        loginWindow.setVisible(true);
        setVisible(false);
    }
}
