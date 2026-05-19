package org.example.controller;

import org.example.model.AppState;
import org.example.view.MainWindow;

public class MainController {
  private final AppState state;
  private final MainWindow window;

  public MainController(AppState state, MainWindow window) {
    this.state = state;
    this.window = window;
  }

  public void start() {
    state.addObserver(window);
    window.onLoginSubmitted(this::handleLoginSubmitted);
    window.onStateChanged(state);
    window.show();
  }

  private void handleLoginSubmitted() {
    state.login(window.getUsername(), window.getPassword());
  }
}
