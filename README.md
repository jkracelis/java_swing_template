# Java Swing Desktop App Structure

This project is a Java Swing desktop application organized with MVC and the observer pattern. The goal is to keep startup, configuration, state, business logic, event handling, and UI code separated so the project stays easier to grow.

## Folder Structure

```text
app/src/main/java/org/example/
├── App.java
├── config/
│   └── AppConfig.java
├── controller/
│   └── MainController.java
├── model/
│   ├── AppState.java
│   └── AppStateObserver.java
└── view/
    ├── MainWindow.java
    ├── config/
    │   └── ViewTheme.java
    └── components/
        └── NotificationPanel.java

scripts/
├── dev-reload.bat
├── dev-reload.ps1
└── dev-reload.sh

.github/workflows/
├── build.yml
└── formatter.yml
```

## Responsibilities

### `App.java`

The application entry point. It creates the main app objects and starts the Swing UI.

### `config/`

Contains direct application defaults and configuration values.

`AppConfig.java` currently defines app-level settings like window title, window size, login button text, notification copy, and sample login credentials directly in Java.

### `model/`

Stores application state and business logic.

`AppState.java` validates login attempts, keeps the current login state, and notifies observers when state changes.

`AppStateObserver.java` defines the observer contract used by the view.

### `controller/`

Connects the UI and model.

`MainController.java` handles user actions from the view and calls model methods. It does not contain login business rules.

### `view/`

Contains Swing UI code only.

`MainWindow.java` owns the main application window.

`config/ViewTheme.java` stores shared UI design tokens like `PRIMARY`, `SECONDARY`, `MUTED`, `ACCENT`, `DESTRUCTIVE`, spacing steps, padding helpers, and notification sizing. It works like a small Swing version of Sass variables or Tailwind theme values.

`components/NotificationPanel.java` is a reusable Swing panel that renders a bottom-right overlay notification with a header, message body, color, and countdown.

## MVC and Observer Flow

The sample login uses this flow:

```text
View button click
→ Controller reads username/password from the view
→ Controller calls AppState.login(...)
→ Model validates credentials
→ Model notifies observers
→ View receives AppState and re-renders
```

Sample credentials:

```text
username: admin
password: root
```

### `scripts/`

Contains development helper scripts.

`dev-reload.sh` watches project files and restarts the app when something changes on Linux, macOS, or WSL.

`dev-reload.bat` does the same for Windows users.

### `.github/workflows/`

Contains GitHub Actions workflows.

`build.yml` runs the Gradle build and tests on Linux, Windows, and macOS.

`formatter.yml` runs the Spotless formatter check.

## Run

```bash
./gradlew run
```

## Live Reload During Development

Plain Swing cannot hot-swap every Java code change inside an already running window. For all-file live reload during development, use the dev reload script:

Linux, macOS, or WSL:

```bash
./scripts/dev-reload.sh
```

Windows:

```bat
scripts\dev-reload.bat
```

This watches the project files and restarts the app when something changes, including Java source files, resources, Gradle files, and README changes. It ignores generated folders like `.git`, `.gradle`, `build`, `app/build`, and `app/bin`.

## Test

```bash
./gradlew test
```

## Build

```bash
./gradlew clean build
```

## Format

This project uses Spotless with Google Java Format.

Check formatting:

```bash
./gradlew spotlessCheck
```

Apply formatting:

```bash
./gradlew spotlessApply
```

GitHub Actions runs both the build and formatter checks automatically on pushes and pull requests.
