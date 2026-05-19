package org.example.model;

import org.example.config.AppConfig;

public class AppState {
    private AppConfig config;
    private String notificationHeader;
    private String notificationMessage;
    private boolean notificationSuccess;
    private boolean notificationVisible;

    public AppState(AppConfig config) {
        applyConfig(config);
    }

    public void applyConfig(AppConfig config) {
        this.config = config;
        this.notificationHeader = config.getNotificationHeader();
        this.notificationMessage = config.getNotificationMessage();
        this.notificationSuccess = false;
        this.notificationVisible = false;
    }

    public void markButtonClicked() {
        notificationHeader = "Action Complete";
        notificationMessage = "The notification button was clicked.";
        notificationSuccess = true;
        notificationVisible = true;
    }

    public AppConfig getConfig() {
        return config;
    }

    public String getNotificationHeader() {
        return notificationHeader;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public boolean isNotificationSuccess() {
        return notificationSuccess;
    }

    public boolean isNotificationVisible() {
        return notificationVisible;
    }
}
