package qa.tools;

import javax.swing.SwingUtilities;
import qa.tools.windows.LoginWindow;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginWindow().setVisible(true));
    }
}
