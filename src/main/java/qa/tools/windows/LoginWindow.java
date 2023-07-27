/*
 * Copyright (c) 2023. Carlos.GarciaSilva@solera.com
 * All rights reserved to QapterClaims FR team
 */

package qa.tools.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;
import qa.tools.constants.ResourcesConstants;
import qa.tools.models.JRoundedButton;
import qa.tools.models.JRoundedPanel;
import qa.tools.utils.PomReader;

public class LoginWindow extends JFrame {

    private static final Logger logger = Logger.getLogger(LoginWindow.class.getName());

    private static final int TITLE_BAR_WIDTH = 16;
    private static final int TITLE_BAR_HEIGHT = 39;
    private static final String BAD_LOCATION_EXCEPTION = "BadLocationException: ";
    private final JTextField userTextField;
    private final JPasswordField passTextField;

    public LoginWindow() {

        // Background panel  --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        ImageIcon imagenFondo = new ImageIcon(ResourcesConstants.LOGIN_BACKGROUND);
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
        userIconPanel.setOpaque(false);

        userTextField = new JTextField();
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
        passIconPanel.setOpaque(false);

        passTextField = new JPasswordField();
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

        // Version region    --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        JLabel versionTextField = new JLabel(PomReader.readSnapshotVersion());
        versionTextField.setBounds(10, imagenFondo.getIconHeight() - 40, 200, 44);

        // Layered panel --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        JLayeredPane layeredPane = new JLayeredPane();
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
                try {
                    if (!userTextField.getDocument().getText(0, userTextField.getDocument().getLength())
                            .isEmpty() && !passTextField.getDocument()
                            .getText(0, passTextField.getDocument().getLength()).isEmpty()) {
                        openMenuWindow(userTextField.getDocument().getText(0, userTextField.getDocument().getLength()),
                                passTextField.getDocument().getText(0, passTextField.getDocument().getLength()));
                    }
                } catch (BadLocationException ex) {
                    logger.log(Level.WARNING, BAD_LOCATION_EXCEPTION, ex);
                }
            }
        });

        passTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        if (!userTextField.getDocument().getText(0, userTextField.getDocument().getLength()).isEmpty()
                                && !passTextField.getDocument().getText(0, passTextField.getDocument().getLength())
                                .isEmpty()) {
                            openMenuWindow(
                                    userTextField.getDocument().getText(0, userTextField.getDocument().getLength()),
                                    passTextField.getDocument().getText(0, passTextField.getDocument().getLength()));
                        }
                    } catch (BadLocationException ex) {
                        logger.log(Level.WARNING, BAD_LOCATION_EXCEPTION, ex);
                    }
                }
            }
        });
    }

    private void openMenuWindow(String user, String pass) {
        MenuWindow menuWindow = new MenuWindow(user, pass);
        menuWindow.setLocationRelativeTo(this);
        menuWindow.setVisible(true);
        setVisible(false);
    }
}
