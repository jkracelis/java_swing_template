# Java Swing Desktop App Structure

This project is a Java Swing desktop application organized with a common desktop app structure. The goal is to keep startup, configuration, state, event handling, and UI code separated so the project stays easier to grow.

## Folder Structure

```text
app/src/main/java/org/example/
├── App.java
├── config/
│   └── AppConfig.java
├── controller/
│   └── MainController.java
├── model/
│   └── AppState.java
└── view/
    ├── MainWindow.java
    └── components/
        └── NotificationPanel.java

scripts/
└── dev-reload.sh
```

## Responsibilities

### `App.java`

The application entry point. It creates the main app objects and starts the Swing UI.

### `config/`

Contains direct application defaults and configuration values.

`AppConfig.java` currently defines the window title, button text, notification header, notification message, and notification color directly in Java.

### `model/`

Stores application state.

`AppState.java` keeps the current UI state, such as the active notification header, message, color, and loaded config values.

### `controller/`

Connects the UI and state.

`MainController.java` handles user actions, updates the model, and tells the view to render.

### `view/`

Contains Swing UI code only.

`MainWindow.java` owns the main application window.

`components/NotificationPanel.java` is a reusable Swing panel that renders a bottom-left popup-style notification with a header, message body, and color.

### `scripts/`

Contains development helper scripts.

`dev-reload.sh` watches project files and restarts the app when something changes.

## Run

```bash
./gradlew run
```

## Live Reload During Development

Plain Swing cannot hot-swap every Java code change inside an already running window. For all-file live reload during development, use the dev reload script:

```bash
./scripts/dev-reload.sh
```

This watches the project files and restarts the app when something changes, including Java source files, resources, Gradle files, and README changes. It ignores generated folders like `.git`, `.gradle`, `build`, `app/build`, and `app/bin`.

## Test

```bash
./gradlew test
```
