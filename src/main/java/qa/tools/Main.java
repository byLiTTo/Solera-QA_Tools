package qa.tools;

import qa.tools.windows.LoginWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        LoginWindow.runApplication();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginWindow().setVisible(true);
            }
        });
    }
}
