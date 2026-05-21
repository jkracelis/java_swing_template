package org.example.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainView extends JPanel {

  private JLabel label;
  private JButton button;

  public MainView() {
    label = new JLabel("Hello Swing 👋", SwingConstants.CENTER);
    button = new JButton("Click me");

    button.addActionListener(e -> label.setText("Button clicked!"));

    add(label);
    add(button);
  }
}
