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
    window.onButtonClicked(this::handleButtonClicked);
    render();
    window.show();
  }

  private void handleButtonClicked() {
    state.markButtonClicked();
    render();
  }

  private void render() {
    window.render(state);
  }
}
