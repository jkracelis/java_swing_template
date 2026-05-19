# Java Swing Desktop App Structure

This project is a Java Swing desktop application organized with MVC and the observer pattern. The goal is to keep startup, configuration, state, business logic, event handling, and UI code separated so the project stays easier to grow.

## Folder Structure

```text
app/src/main/java/org/example/
├── App.java
├── config/
├── controller/
├── model/
└── view/
    ├── MainWindow.java
    ├── config/
    │   └── ViewTheme.java
    └── components/

scripts/
├── dev-reload.bat
├── dev-reload.ps1
└── dev-reload.sh

.github/workflows/
├── build.yml
└── formatter.yml
```

## Folder Structure

### `App.java`
Entry point of the app.
- Starts Swing UI thread
- Initializes config, view, model, controller
- No business logic

---

### `config/`
App-wide settings (constants).

- Window size
- Title
- Default behavior

---

### `controller/`
Handles user actions and app logic.

- Receives input from View
- Updates Model
- Acts as bridge between View and Model

---

### `model/`
Holds application state.

- App data / state
- Business rules
- Supports Observer updates

---

### `view/`
All UI (Swing components).

- Renders UI
- Shows model data
- Sends events to controller

---

#### `view/MainWindow.java`
Main JFrame and layout.

---

#### `view/components/`
Reusable UI components.

---

#### `view/config/ViewTheme.java`
UI styling (colors, fonts, spacing).

---

## Flow
User → View → Controller → Model → View



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
