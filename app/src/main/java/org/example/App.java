package org.example;

import javax.swing.JFrame;
import org.example.config.AppConfig;
import org.example.view.MainView;

public class App {

  public static void main(String[] args) {
    startApp();
  }

  private static void startApp() {
    JFrame frame = new JFrame();

    frame.setTitle(AppConfig.APP_TITLE);
    frame.setSize(AppConfig.WINDOW_WIDTH, AppConfig.WINDOW_HEIGHT);
    frame.setLocationRelativeTo(null);
    frame.setResizable(AppConfig.RESIZABLE);
    frame.setDefaultCloseOperation(AppConfig.CLOSE_OPERATION);

    frame.add(new MainView());
    frame.setVisible(true);
  }
}
