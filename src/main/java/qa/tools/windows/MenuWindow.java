/*
 * Copyright (c) 2023. Carlos.GarciaSilva@solera.com
 * All rights reserved to QapterClaims FR team
 */

package qa.tools.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import qa.tools.constants.ResourcesConstants;
import qa.tools.constants.TestRailConstants;
import qa.tools.controllers.ExcelController;
import qa.tools.controllers.TestPlanController;
import qa.tools.models.JRoundedButton;
import qa.tools.models.JRoundedPanel;
import qa.tools.utils.PomReader;

public class MenuWindow extends JFrame {

    private static final Logger logger = Logger.getLogger(MenuWindow.class.getName());

    private static final int TITLE_BAR_WIDTH = 16;
    private static final int TITLE_BAR_HEIGHT = 39;
    private static final String AUTO_REGRESSION = "Auto Regression";
    ImageIcon imagenFondo;
    private JLabel labelFondo;

    private JLabel versionTextField;
    private JLayeredPane layeredPane;
    private JPanel testResultRegion;
    private JPanel testRunRegion;
    private JPanel testPlanNameRegion;
    private JRoundedButton runCustomButton;
    private JRoundedButton runTestPlanButton;

    public MenuWindow(String userName, String pass) {

        // Background panel  --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        imagenFondo = new ImageIcon(ResourcesConstants.MENU_BACKGROUND);
        labelFondo = new JLabel(imagenFondo);
        labelFondo.setSize(imagenFondo.getIconWidth(), imagenFondo.getIconHeight());
        labelFondo.setVerticalAlignment(SwingConstants.CENTER);
        labelFondo.setHorizontalAlignment(SwingConstants.CENTER);

        // Complete Regression Region    --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        JLabel completeIcon = new JLabel();
        completeIcon.setIcon(new ImageIcon(ResourcesConstants.COMPLETE_REGRESSION_ICON));

        JRoundedPanel completeRegressionPanel = new JRoundedPanel();
        completeRegressionPanel.setOpaque(false);
        completeRegressionPanel.setBackground(Color.blue);
        completeRegressionPanel.add(completeIcon, BorderLayout.CENTER);
        completeRegressionPanel.setBounds(10, 10, 100, 100);

        JLabel completeTitle = new JLabel(AUTO_REGRESSION);
        completeTitle.setBounds(13, 115, 95, 14);

        JLabel completeSection = new JLabel("CTFR and INT1FR");
        completeSection.setBounds(13, 130, 95, 14);

        JPanel completeRegressionRegion = new JPanel();
        completeRegressionRegion.setLayout(null);
        completeRegressionRegion.setOpaque(false);
        completeRegressionRegion.setBackground(Color.blue);
        completeRegressionRegion.setBounds(15, 20, 120, 150);
        completeRegressionRegion.add(completeTitle);
        completeRegressionRegion.add(completeSection);
        completeRegressionRegion.add(completeRegressionPanel);

        // CTFR Regression Region    --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        JLabel ctfrIcon = new JLabel();
        ctfrIcon.setIcon(new ImageIcon(ResourcesConstants.CTFR_REGRESSION_ICON));

        JRoundedPanel ctfrRegressionPanel = new JRoundedPanel();
        ctfrRegressionPanel.setOpaque(false);
        ctfrRegressionPanel.setBackground(Color.blue);
        ctfrRegressionPanel.setBounds(10, 10, 100, 100);
        ctfrRegressionPanel.add(ctfrIcon, BorderLayout.CENTER);

        JLabel ctfrTitle = new JLabel(AUTO_REGRESSION);
        ctfrTitle.setBounds(13, 115, 95, 14);

        JLabel ctfrSection = new JLabel("CTFR");
        ctfrSection.setBounds(45, 130, 95, 14);

        JPanel ctfrRegressionRegion = new JPanel();
        ctfrRegressionRegion.setLayout(null);
        ctfrRegressionRegion.setOpaque(false);
        ctfrRegressionRegion.setBackground(Color.blue);
        ctfrRegressionRegion.setBounds(165, 20, 120, 150);
        ctfrRegressionRegion.add(ctfrRegressionPanel);
        ctfrRegressionRegion.add(ctfrTitle);
        ctfrRegressionRegion.add(ctfrSection);

        // INT1FR Regression Region  --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        JLabel intfrIcon = new JLabel();
        intfrIcon.setIcon(new ImageIcon(ResourcesConstants.INT1FR_REGRESSION_ICON));

        JRoundedPanel intfrRegressionPanel = new JRoundedPanel();
        intfrRegressionPanel.setOpaque(false);
        intfrRegressionPanel.setBackground(Color.blue);
        intfrRegressionPanel.setBounds(10, 10, 100, 100);
        intfrRegressionPanel.add(intfrIcon, BorderLayout.CENTER);

        JLabel intfrTitle = new JLabel(AUTO_REGRESSION);
        intfrTitle.setBounds(13, 115, 95, 14);

        JLabel intfrSection = new JLabel("INT1FR");
        intfrSection.setBounds(45, 130, 95, 14);

        JPanel intfrRegressionRegion = new JPanel();
        intfrRegressionRegion.setLayout(null);
        intfrRegressionRegion.setOpaque(false);
        intfrRegressionRegion.setBackground(Color.blue);
        intfrRegressionRegion.setBounds(315, 20, 120, 150);
        intfrRegressionRegion.add(intfrRegressionPanel);
        intfrRegressionRegion.add(intfrTitle);
        intfrRegressionRegion.add(intfrSection);

        // Custom Regression Region  --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        JLabel customIcon = new JLabel();
        customIcon.setIcon(new ImageIcon(ResourcesConstants.CUSTOM_REGRESSION_ICON));

        JRoundedPanel customRegressionPanel = new JRoundedPanel();
        customRegressionPanel.setOpaque(false);
        customRegressionPanel.setBackground(Color.blue);
        customRegressionPanel.setBounds(10, 10, 100, 100);
        customRegressionPanel.add(customIcon, BorderLayout.CENTER);

        JLabel customTitle = new JLabel(AUTO_REGRESSION);
        customTitle.setBounds(13, 115, 95, 14);

        JLabel customSection = new JLabel("Custom");
        customSection.setBounds(45, 130, 95, 14);

        JPanel customRegressionRegion = new JPanel();
        customRegressionRegion.setLayout(null);
        customRegressionRegion.setOpaque(false);
        customRegressionRegion.setBackground(Color.blue);
        customRegressionRegion.setBounds(465, 20, 120, 150);
        customRegressionRegion.add(customRegressionPanel);
        customRegressionRegion.add(customTitle);
        customRegressionRegion.add(customSection);

        // Regression to test run --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        JLabel testPlanIcon = new JLabel();
        testPlanIcon.setIcon(new ImageIcon(ResourcesConstants.TEST_PLAN_ICON));

        JRoundedPanel testPlanPanel = new JRoundedPanel();
        testPlanPanel.setOpaque(false);
        testPlanPanel.setBackground(Color.blue);
        testPlanPanel.add(testPlanIcon, BorderLayout.CENTER);
        testPlanPanel.setBounds(10, 10, 100, 100);

        JLabel testPlanTitle = new JLabel(AUTO_REGRESSION);
        testPlanTitle.setBounds(13, 115, 95, 14);

        JLabel testPlanSection = new JLabel("to Test Plan");
        testPlanSection.setBounds(25, 130, 95, 14);

        JPanel testPlanRegion = new JPanel();
        testPlanRegion.setLayout(null);
        testPlanRegion.setOpaque(false);
        testPlanRegion.setBackground(Color.blue);
        testPlanRegion.setBounds(15, 180, 120, 150);
        testPlanRegion.add(testPlanTitle);
        testPlanRegion.add(testPlanSection);
        testPlanRegion.add(testPlanPanel);

        // Menu region   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        JPanel menuRegion = new JPanel();
        menuRegion.setLayout(null);
        menuRegion.setOpaque(false);
        menuRegion.setBackground(Color.red);
        menuRegion.setBounds(130, 60, 600, 350);
        menuRegion.add(completeRegressionRegion);
        menuRegion.add(ctfrRegressionRegion);
        menuRegion.add(intfrRegressionRegion);
        menuRegion.add(customRegressionRegion);
        menuRegion.add(testPlanRegion);

        // Version region    --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        versionTextField = new JLabel(PomReader.readSnapshotVersion());
        versionTextField.setBounds(10, imagenFondo.getIconHeight() - 40, 200, 44);

        // Test Run Region   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        JLabel testRunLabel = new JLabel("Test Runs name:");
        testRunLabel.setBounds(160, 185, 400, 24);

        String name = String.format(
                TestRailConstants.AUTOREGRESSION_NAME_FORMAT,
                "CTFR",
                DateTimeFormatter.ofPattern("MM").format(LocalDateTime.now()),
                DateTimeFormatter.ofPattern("yyyy").format(LocalDateTime.now())
        );

        JTextField testRunTextField = new JTextField(name);
        testRunTextField.setBounds(10, 0, 400, 24);
        testRunTextField.setBorder(null);
        testRunTextField.setOpaque(false);

        JRoundedPanel testRunPanel = new JRoundedPanel();
        testRunPanel.setLayout(null);
        testRunPanel.setOpaque(false);
        testRunPanel.setBackground(Color.red);
        testRunPanel.setBounds(150, 205, 400, 24);
        testRunPanel.add(testRunTextField);

        testRunRegion = new JPanel();
        testRunRegion.setLayout(null);
        testRunRegion.setOpaque(false);
        testRunRegion.setBackground(Color.red);
        testRunRegion.setBounds(130, 60, 600, 350);
        testRunRegion.add(testRunLabel);
        testRunRegion.add(testRunPanel);
        testRunRegion.setVisible(false);

        // Test Results Region   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        JLabel testResultLabel = new JLabel("Test Results name:");
        testResultLabel.setBounds(160, 170, 400, 24);

        JTextField testResultTextField = new JTextField("11:43 24/07 QapterClaims FR");
        testResultTextField.setBounds(10, 0, 400, 24);
        testResultTextField.setBorder(null);
        testResultTextField.setOpaque(false);

        JRoundedPanel testResultPanel = new JRoundedPanel();
        testResultPanel.setLayout(null);
        testResultPanel.setOpaque(false);
        testResultPanel.setBackground(Color.red);
        testResultPanel.setBounds(150, 190, 400, 24);
        testResultPanel.add(testResultTextField);

        testResultRegion = new JPanel();
        testResultRegion.setLayout(null);
        testResultRegion.setOpaque(false);
        testResultRegion.setBackground(Color.red);
        testResultRegion.setBounds(130, 120, 600, 350);
        testResultRegion.add(testResultLabel);
        testResultRegion.add(testResultPanel);
        testResultRegion.setVisible(false);

        // Test Plan Region  --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        JLabel testPlanNameLabel = new JLabel("Test Plan name:");
        testPlanNameLabel.setBounds(160, 185, 400, 24);

        JTextField testPlanNameField = new JTextField("FR_QAPTER_CLAIMS_SCRIPT_TEST");
        testPlanNameField.setBounds(10, 0, 400, 24);
        testPlanNameField.setBorder(null);
        testPlanNameField.setOpaque(false);

        JRoundedPanel testPlanNamePanel = new JRoundedPanel();
        testPlanNamePanel.setLayout(null);
        testPlanNamePanel.setOpaque(false);
        testPlanNamePanel.setBackground(Color.red);
        testPlanNamePanel.setBounds(150, 205, 400, 24);
        testPlanNamePanel.add(testPlanNameField);

        testPlanNameRegion = new JPanel();
        testPlanNameRegion.setLayout(null);
        testPlanNameRegion.setOpaque(false);
        testPlanNameRegion.setBackground(Color.red);
        testPlanNameRegion.setBounds(130, 150, 600, 350);
        testPlanNameRegion.add(testPlanNameLabel);
        testPlanNameRegion.add(testPlanNamePanel);
        testPlanNameRegion.setVisible(false);

        // Custom Auto Regression Button --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        runCustomButton = new JRoundedButton("Run");
        runCustomButton.setBorder(null);
        runCustomButton.setOpaque(false);
        runCustomButton.setBounds(285, 345, 100, 24);
        runCustomButton.setVisible(false);

        // Auto regression to test plan Button   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        runTestPlanButton = new JRoundedButton("Run");
        runTestPlanButton.setBorder(null);
        runTestPlanButton.setOpaque(false);
        runTestPlanButton.setBounds(285, 390, 100, 24);
        runTestPlanButton.setVisible(false);

        //
        JRadioButton graphicalInterface = new JRadioButton("Graphical Interface");
        graphicalInterface.setBounds(710, 5, 200, 24);
        graphicalInterface.setVisible(true);
        graphicalInterface.setOpaque(false);

        // Layered panel --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        layeredPane = new JLayeredPane();
        layeredPane.add(labelFondo, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(versionTextField, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(menuRegion, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(testRunRegion, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(testResultRegion, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(testPlanNameRegion, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(runCustomButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(runTestPlanButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(graphicalInterface, JLayeredPane.PALETTE_LAYER);

        // Adding content to JFrame  --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
        setContentPane(layeredPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(Color.RED);
        setResizable(false);
        setIconImage(new ImageIcon(ResourcesConstants.APP_ICON).getImage());
        setSize((labelFondo.getWidth() + TITLE_BAR_WIDTH), (labelFondo.getHeight() + TITLE_BAR_HEIGHT));
        setLocationRelativeTo(null);
        setTitle("QapterClaims FR - QA tools");

        completeRegressionPanel.addMouseListener(new MouseAdapter() {
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
                customRunVisible(false);
                testPlanVisible(false);

                final JFileChooser fileChooserCT = new JFileChooser();
                fileChooserCT.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooserCT.showOpenDialog(layeredPane);
                if (fileChooserCT.getSelectedFile() != null) {
                    logger.log(Level.INFO, fileChooserCT.getSelectedFile().toString());
                }

                final JFileChooser fileChooserINT = new JFileChooser();
                fileChooserINT.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooserINT.showOpenDialog(layeredPane);
                if (fileChooserINT.getSelectedFile() != null) {
                    logger.log(Level.INFO, fileChooserINT.getSelectedFile().toString());
                }

                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                ExcelController controller = new ExcelController(graphicalInterface.isSelected());

                controller.setUserName(userName);
                controller.setPass(pass);

                controller.openTestRailRunsWithLogin();
                controller.updateCtfr(fileChooserCT.getSelectedFile().getAbsolutePath());

                controller.openTestRailRuns();
                controller.updateInt1fr(fileChooserINT.getSelectedFile().getAbsolutePath());

                controller.closeBrowser();

                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });

        ctfrRegressionPanel.addMouseListener(new MouseAdapter() {
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
                customRunVisible(false);
                testPlanVisible(false);

                final JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.showOpenDialog(layeredPane);
                if (fileChooser.getSelectedFile() != null) {
                    logger.log(Level.INFO, fileChooser.getSelectedFile().toString());
                }

                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                ExcelController controller = new ExcelController(graphicalInterface.isSelected());

                controller.setUserName(userName);
                controller.setPass(pass);

                controller.openTestRailRunsWithLogin();
                controller.updateCtfr(fileChooser.getSelectedFile().getAbsolutePath());

                controller.closeBrowser();

                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });

        intfrRegressionPanel.addMouseListener(new MouseAdapter() {
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
                customRunVisible(false);
                testPlanVisible(false);

                final JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.showOpenDialog(layeredPane);
                if (fileChooser.getSelectedFile() != null) {
                    logger.log(Level.INFO, fileChooser.getSelectedFile().toString());
                }

                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                ExcelController controller = new ExcelController(graphicalInterface.isSelected());

                controller.setUserName(userName);
                controller.setPass(pass);

                controller.openTestRailRunsWithLogin();
                controller.updateInt1fr(fileChooser.getSelectedFile().getAbsolutePath());

                controller.closeBrowser();

                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });

        customRegressionPanel.addMouseListener(new MouseAdapter() {
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
                testPlanVisible(false);
                customRunVisible(true);
            }
        });

        runCustomButton.addMouseListener(new MouseAdapter() {
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
                customRunVisible(false);
                testPlanVisible(false);

                final JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.showOpenDialog(layeredPane);
                if (fileChooser.getSelectedFile() != null) {
                    logger.log(Level.INFO, fileChooser.getSelectedFile().toString());
                }

                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                ExcelController controller = new ExcelController(graphicalInterface.isSelected());

                controller.setUserName(userName);
                controller.setPass(pass);

                controller.openTestRailRunsWithLogin();
                controller.updateCustom(
                        fileChooser.getSelectedFile().getAbsolutePath(),
                        testRunTextField.getText(),
                        testResultTextField.getText()
                );

                controller.closeBrowser();

                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });

        testPlanPanel.addMouseListener(new MouseAdapter() {
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
                customRunVisible(false);
                testPlanVisible(true);
            }
        });

        runTestPlanButton.addMouseListener(new MouseAdapter() {
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
                customRunVisible(false);
                testPlanVisible(false);

                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                TestPlanController controller = new TestPlanController(graphicalInterface.isSelected());

                controller.setUserName(userName);
                controller.setPass(pass);

                controller.openTestRailRunsWithLogin();
                controller.updateTestPlan(
                        testRunTextField.getText(),
                        testResultTextField.getText(),
                        testPlanNameField.getText()
                );

                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    public static void runApplication() {
        new MenuWindow("", "");
    }

    private void customRunVisible(boolean value) {
        testResultRegion.setVisible(value);
        testRunRegion.setVisible(value);
        runCustomButton.setVisible(value);
    }

    private void testPlanVisible(boolean value) {
        testResultRegion.setVisible(value);
        testRunRegion.setVisible(value);
        testPlanNameRegion.setVisible(value);
        runTestPlanButton.setVisible(value);
    }
}
