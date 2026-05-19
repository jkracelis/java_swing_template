package org.example;

import javax.swing.SwingUtilities;
import org.example.view.MainView;

public class App {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(App::startApp);
  }

  private static void startApp() {
    MainView mainView = new MainView();
    mainView.setVisible(true);
  }
}
