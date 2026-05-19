package org.example.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import org.example.model.AppState;
import org.example.model.AppStateObserver;
import org.example.view.components.NotificationPanel;
import org.example.view.config.ViewTheme;

public class MainWindow implements AppStateObserver {
  private final JFrame frame;
  private final JTextField usernameField;
  private final JPasswordField passwordField;
  private final JButton loginButton;
  private final JLabel statusLabel;
  private final NotificationPanel notificationPanel;
  private final Timer notificationTimer;
  private int secondsUntilNotificationCloses;

  public MainWindow() {
    this.frame = new JFrame();
    this.usernameField = new JTextField(18);
    this.passwordField = new JPasswordField(18);
    this.loginButton = new JButton();
    this.statusLabel = new JLabel();
    this.notificationPanel = new NotificationPanel();
    this.notificationTimer = new Timer(1000, event -> updateNotificationCountdown());

    build();
  }

  public void show() {
    frame.setVisible(true);
  }

  @Override
  public void onStateChanged(AppState state) {
    frame.setTitle(state.getConfig().getWindowTitle());
    frame.setSize(state.getConfig().getWindowWidth(), state.getConfig().getWindowHeight());
    loginButton.setText(state.getConfig().getButtonText());
    statusLabel.setText(state.isLoggedIn() ? "Logged in" : "Not logged in");
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

  public void onLoginSubmitted(Runnable listener) {
    loginButton.addActionListener(event -> listener.run());
    passwordField.addActionListener(event -> listener.run());
  }

  public String getUsername() {
    return usernameField.getText().trim();
  }

  public String getPassword() {
    return new String(passwordField.getPassword());
  }

  private void build() {
    JPanel contentPanel = new JPanel(new GridBagLayout());
    contentPanel.setBackground(ViewTheme.SURFACE_SOFT);
    contentPanel.setBorder(ViewTheme.padding(ViewTheme.SPACE_6));

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = ViewTheme.inset(ViewTheme.SPACE_2);
    constraints.fill = GridBagConstraints.HORIZONTAL;

    addLabel(contentPanel, constraints, "Username", 0);
    addField(contentPanel, constraints, usernameField, 0);
    addLabel(contentPanel, constraints, "Password", 1);
    addField(contentPanel, constraints, passwordField, 1);

    constraints.gridx = 1;
    constraints.gridy = 2;
    constraints.weightx = 1;
    contentPanel.add(loginButton, constraints);

    constraints.gridx = 1;
    constraints.gridy = 3;
    contentPanel.add(statusLabel, constraints);

    frame.setLayout(new BorderLayout());
    frame.add(contentPanel, BorderLayout.CENTER);
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

  private void addLabel(
      JPanel contentPanel, GridBagConstraints constraints, String labelText, int row) {
    constraints.gridx = 0;
    constraints.gridy = row;
    constraints.weightx = 0;
    contentPanel.add(new JLabel(labelText), constraints);
  }

  private void addField(
      JPanel contentPanel, GridBagConstraints constraints, JTextField field, int row) {
    constraints.gridx = 1;
    constraints.gridy = row;
    constraints.weightx = 1;
    contentPanel.add(field, constraints);
  }

  private void hideNotification() {
    notificationTimer.stop();
    notificationPanel.setVisible(false);
    frame.revalidate();
    frame.repaint();
  }

  private void startNotificationCountdown() {
    secondsUntilNotificationCloses = ViewTheme.NOTIFICATION_DURATION_SECONDS;
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
    int x = layeredPane.getWidth() - preferredSize.width - ViewTheme.SPACE_4;
    int y = layeredPane.getHeight() - preferredSize.height - ViewTheme.SPACE_4;

    notificationPanel.setBounds(
        Math.max(ViewTheme.SPACE_4, x),
        Math.max(ViewTheme.SPACE_4, y),
        preferredSize.width,
        preferredSize.height);
  }

  private Color notificationColor(AppState state) {
    if (!state.isNotificationVisible()) {
      return ViewTheme.PRIMARY;
    }

    if (state.isNotificationSuccess()) {
      return ViewTheme.SECONDARY;
    }

    return ViewTheme.DESTRUCTIVE;
  }
}
