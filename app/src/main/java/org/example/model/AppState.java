package org.example.model;

import java.util.ArrayList;
import java.util.List;
import org.example.config.AppConfig;

public class AppState {
  private final List<AppStateObserver> observers;
  private AppConfig config;
  private String notificationHeader;
  private String notificationMessage;
  private boolean notificationSuccess;
  private boolean notificationVisible;
  private boolean loggedIn;

  public AppState(AppConfig config) {
    this.observers = new ArrayList<>();
    applyConfig(config);
  }

  public void addObserver(AppStateObserver observer) {
    observers.add(observer);
  }

  public void applyConfig(AppConfig config) {
    this.config = config;
    this.notificationHeader = config.getNotificationHeader();
    this.notificationMessage = config.getNotificationMessage();
    this.notificationSuccess = false;
    this.notificationVisible = false;
    this.loggedIn = false;
    notifyObservers();
  }

  public void login(String username, String password) {
    if (isBlank(username) || isBlank(password)) {
      showError("Missing Credentials", "Username and password are required.");
      return;
    }

    if (config.getValidUsername().equals(username) && config.getValidPassword().equals(password)) {
      loggedIn = true;
      showSuccess("Login Successful", "Welcome back, " + username + ".");
      return;
    }

    loggedIn = false;
    showError("Login Failed", "Invalid username or password.");
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

  public boolean isLoggedIn() {
    return loggedIn;
  }

  private void showSuccess(String header, String message) {
    notificationHeader = header;
    notificationMessage = message;
    notificationSuccess = true;
    notificationVisible = true;
    notifyObservers();
  }

  private void showError(String header, String message) {
    notificationHeader = header;
    notificationMessage = message;
    notificationSuccess = false;
    notificationVisible = true;
    notifyObservers();
  }

  private void notifyObservers() {
    for (AppStateObserver observer : observers) {
      observer.onStateChanged(this);
    }
  }

  private boolean isBlank(String value) {
    return value == null || value.trim().isEmpty();
  }
}
