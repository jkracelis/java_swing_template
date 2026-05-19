package org.example.view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import org.example.config.AppConfig;

public class MainView extends JFrame {

  private JLabel label;
  private JButton button;

  public MainView() {
    super(AppConfig.APP_TITLE);

    initUI();
  }

  private void initUI() {
    label = new JLabel("Hello Swing 👋", SwingConstants.CENTER);

    button = new JButton("Click me");
    button.addActionListener(e -> label.setText("Button clicked!"));

    setLayout(new BorderLayout());
    add(label, BorderLayout.CENTER);
    add(button, BorderLayout.SOUTH);

    setSize(AppConfig.WINDOW_WIDTH, AppConfig.WINDOW_HEIGHT);
    setResizable(AppConfig.RESIZABLE);
    setDefaultCloseOperation(AppConfig.CLOSE_OPERATION);

    setLocationRelativeTo(null);
  }
}
