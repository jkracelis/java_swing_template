package org.example.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import org.example.view.config.ViewTheme;

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
    setLayout(new BorderLayout(0, ViewTheme.SPACE_2));
    setPreferredSize(ViewTheme.NOTIFICATION_SIZE);
    setVisible(false);
    setBorder(
        BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ViewTheme.NOTIFICATION_BORDER),
            ViewTheme.NOTIFICATION_PADDING));

    headerLabel.setForeground(ViewTheme.TEXT_ON_COLOR);
    headerLabel.setFont(headerLabel.getFont().deriveFont(Font.BOLD, 14f));

    messageText.setOpaque(false);
    messageText.setEditable(false);
    messageText.setFocusable(false);
    messageText.setLineWrap(true);
    messageText.setWrapStyleWord(true);
    messageText.setForeground(ViewTheme.TEXT_ON_COLOR_MUTED);
    messageText.setFont(messageText.getFont().deriveFont(12f));

    countdownLabel.setForeground(ViewTheme.ACCENT);
    countdownLabel.setFont(countdownLabel.getFont().deriveFont(Font.BOLD, 11f));

    add(headerLabel, BorderLayout.NORTH);
    add(messageText, BorderLayout.CENTER);
    add(countdownLabel, BorderLayout.SOUTH);
  }
}
