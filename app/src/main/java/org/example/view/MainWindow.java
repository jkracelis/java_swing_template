package org.example.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.example.model.AppState;
import org.example.view.components.NotificationPanel;

public class MainWindow {
  private static final Color DEFAULT_NOTIFICATION_COLOR = new Color(37, 99, 235);
  private static final Color SUCCESS_NOTIFICATION_COLOR = new Color(22, 163, 74);
  private static final int NOTIFICATION_MARGIN = 16;
  private static final int NOTIFICATION_DURATION_SECONDS = 3;

  private final JFrame frame;
  private final JButton actionButton;
  private final NotificationPanel notificationPanel;
  private final Timer notificationTimer;
  private int secondsUntilNotificationCloses;

  public MainWindow() {
    this.frame = new JFrame();
    this.actionButton = new JButton();
    this.notificationPanel = new NotificationPanel();
    this.notificationTimer = new Timer(1000, event -> updateNotificationCountdown());

    build();
  }

  public void show() {
    frame.setVisible(true);
  }

  public void render(AppState state) {
    frame.setTitle(state.getConfig().getWindowTitle());
    actionButton.setText(state.getConfig().getButtonText());
    notificationPanel.render(
        state.getNotificationHeader(), state.getNotificationMessage(), notificationColor(state));
    updateNotificationBounds();
    notificationPanel.setVisible(state.isNotificationVisible());

    if (state.isNotificationVisible()) {
      startNotificationCountdown();
    }

    frame.revalidate();
    frame.repaint();
  }

  public void onButtonClicked(Runnable listener) {
    actionButton.addActionListener(event -> listener.run());
  }

  private void build() {
    JPanel contentPanel = new JPanel(new GridBagLayout());

    contentPanel.add(actionButton);

    frame.setLayout(new BorderLayout());
    frame.add(contentPanel, BorderLayout.CENTER);
    frame.setSize(400, 250);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.getLayeredPane().add(notificationPanel, JLayeredPane.POPUP_LAYER);
    frame.addComponentListener(
        new ComponentAdapter() {
          @Override
          public void componentResized(ComponentEvent event) {
            updateNotificationBounds();
          }
        });
  }

  private void hideNotification() {
    notificationTimer.stop();
    notificationPanel.setVisible(false);
    frame.revalidate();
    frame.repaint();
  }

  private void startNotificationCountdown() {
    secondsUntilNotificationCloses = NOTIFICATION_DURATION_SECONDS;
    notificationPanel.renderCountdown(secondsUntilNotificationCloses);
    notificationTimer.restart();
  }

  private void updateNotificationCountdown() {
    secondsUntilNotificationCloses--;

    if (secondsUntilNotificationCloses <= 0) {
      hideNotification();
      return;
    }

    notificationPanel.renderCountdown(secondsUntilNotificationCloses);
    frame.repaint();
  }

  private void updateNotificationBounds() {
    JLayeredPane layeredPane = frame.getLayeredPane();
    Dimension preferredSize = notificationPanel.getPreferredSize();
    int x = layeredPane.getWidth() - preferredSize.width - NOTIFICATION_MARGIN;
    int y = layeredPane.getHeight() - preferredSize.height - NOTIFICATION_MARGIN;

    notificationPanel.setBounds(
        Math.max(NOTIFICATION_MARGIN, x),
        Math.max(NOTIFICATION_MARGIN, y),
        preferredSize.width,
        preferredSize.height);
  }

  private Color notificationColor(AppState state) {
    if (state.isNotificationSuccess()) {
      return SUCCESS_NOTIFICATION_COLOR;
    }

    return DEFAULT_NOTIFICATION_COLOR;
  }
}
