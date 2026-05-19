package org.example.config;

public class AppConfig {
    public static final String DEFAULT_WINDOW_TITLE = "My Swing App";
    public static final String DEFAULT_BUTTON_TEXT = "Show notification";
    public static final String DEFAULT_NOTIFICATION_HEADER = "System Notice";
    public static final String DEFAULT_NOTIFICATION_MESSAGE = "Your desktop app structure is ready.";

    private final String windowTitle;
    private final String buttonText;
    private final String notificationHeader;
    private final String notificationMessage;

    public AppConfig(
            String windowTitle,
            String buttonText,
            String notificationHeader,
            String notificationMessage
    ) {
        this.windowTitle = windowTitle;
        this.buttonText = buttonText;
        this.notificationHeader = notificationHeader;
        this.notificationMessage = notificationMessage;
    }

    public static AppConfig load() {
        return new AppConfig(
                DEFAULT_WINDOW_TITLE,
                DEFAULT_BUTTON_TEXT,
                DEFAULT_NOTIFICATION_HEADER,
                DEFAULT_NOTIFICATION_MESSAGE
        );
    }

    public String getWindowTitle() {
        return windowTitle;
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
}
