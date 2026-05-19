package org.example.config;

public class AppConfig {
  public static final String DEFAULT_WINDOW_TITLE = "My Swing App";
  public static final int DEFAULT_WINDOW_WIDTH = 460;
  public static final int DEFAULT_WINDOW_HEIGHT = 300;
  public static final String DEFAULT_BUTTON_TEXT = "Login";
  public static final String DEFAULT_NOTIFICATION_HEADER = "Login Required";
  public static final String DEFAULT_NOTIFICATION_MESSAGE =
      "Use admin/root to try the sample login.";
  public static final String VALID_USERNAME = "admin";
  public static final String VALID_PASSWORD = "root";

  private final String windowTitle;
  private final int windowWidth;
  private final int windowHeight;
  private final String buttonText;
  private final String notificationHeader;
  private final String notificationMessage;
  private final String validUsername;
  private final String validPassword;

  public AppConfig(
      String windowTitle,
      int windowWidth,
      int windowHeight,
      String buttonText,
      String notificationHeader,
      String notificationMessage,
      String validUsername,
      String validPassword) {
    this.windowTitle = windowTitle;
    this.windowWidth = windowWidth;
    this.windowHeight = windowHeight;
    this.buttonText = buttonText;
    this.notificationHeader = notificationHeader;
    this.notificationMessage = notificationMessage;
    this.validUsername = validUsername;
    this.validPassword = validPassword;
  }

  public static AppConfig load() {
    return new AppConfig(
        DEFAULT_WINDOW_TITLE,
        DEFAULT_WINDOW_WIDTH,
        DEFAULT_WINDOW_HEIGHT,
        DEFAULT_BUTTON_TEXT,
        DEFAULT_NOTIFICATION_HEADER,
        DEFAULT_NOTIFICATION_MESSAGE,
        VALID_USERNAME,
        VALID_PASSWORD);
  }

  public String getWindowTitle() {
    return windowTitle;
  }

  public int getWindowWidth() {
    return windowWidth;
  }

  public int getWindowHeight() {
    return windowHeight;
  }

  public String getButtonText() {
    return buttonText;
  }

  public String getNotificationHeader() {
    return notificationHeader;
  }

  public String getNotificationMessage() {
    return notificationMessage;
  }

  public String getValidUsername() {
    return validUsername;
  }

  public String getValidPassword() {
    return validPassword;
  }
}
