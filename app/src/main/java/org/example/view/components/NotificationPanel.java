package org.example.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class NotificationPanel extends JPanel {
  private final JLabel headerLabel;
  private final JTextArea messageText;
  private final JLabel countdownLabel;

  public NotificationPanel() {
    this.headerLabel = new JLabel();
    this.messageText = new JTextArea();
    this.countdownLabel = new JLabel();

    build();
  }

  public void render(String header, String message, Color color) {
    setBackground(color);
    headerLabel.setText(header);
    messageText.setText(message);
    revalidate();
    repaint();
  }

  public void renderCountdown(int seconds) {
    countdownLabel.setText("Closing in " + seconds + "s");
    revalidate();
    repaint();
  }

  private void build() {
    setLayout(new BorderLayout(0, 6));
    setPreferredSize(new Dimension(260, 112));
    setVisible(false);
    setBorder(
        BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 80)),
            new EmptyBorder(12, 14, 12, 14)));

    headerLabel.setForeground(Color.WHITE);
    headerLabel.setFont(headerLabel.getFont().deriveFont(Font.BOLD, 14f));

    messageText.setOpaque(false);
    messageText.setEditable(false);
    messageText.setFocusable(false);
    messageText.setLineWrap(true);
    messageText.setWrapStyleWord(true);
    messageText.setForeground(new Color(235, 245, 255));
    messageText.setFont(messageText.getFont().deriveFont(12f));

    countdownLabel.setForeground(new Color(219, 234, 254));
    countdownLabel.setFont(countdownLabel.getFont().deriveFont(Font.BOLD, 11f));

    add(headerLabel, BorderLayout.NORTH);
    add(messageText, BorderLayout.CENTER);
    add(countdownLabel, BorderLayout.SOUTH);
  }
}
