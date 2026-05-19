package org.example;

import javax.swing.SwingUtilities;
import org.example.config.AppConfig;
import org.example.controller.MainController;
import org.example.model.AppState;
import org.example.view.MainWindow;

public class App {
    public String getGreeting() {
        return AppConfig.load().getNotificationMessage();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppState state = new AppState(AppConfig.load());
            MainWindow window = new MainWindow();
            MainController controller = new MainController(state, window);
            controller.start();
        });
    }
}
