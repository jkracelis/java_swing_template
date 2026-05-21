# Java Swing Desktop App Structure

This project is a Java Swing desktop application. The goal is to keep startup, configuration, state, business logic, event handling, and UI code separated so the project stays easier to grow.

## Folder Structure

```text
app/src/main/java/org/example/
├── App.java
├── config/
├── database/
├── middleware/
├── controller/
├── service/
├── dao/
├── model/
├── utils/
└── view/
    ├── MainWindow.java
    ├── config/
    │   └── Theme.java
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
- Initializes config, database, view, model, controller, services, and DAOs
- Connects dependencies (Dependency Injection)
- No business logic

---

### `config/`
App-wide settings (constants).
- Window size
- App constants and property file loaders
- Default application behavior

---

### `database/`
Database configuration and connection management.
- Manages connection pools or singletons (e.g., SQLite, H2, or MySQL connection instances)
- Handles startup database initialization and schema migration scripts
- Provides connection objects to the **DAO** layer

---

### `middleware/`
Interceptors and pre-processors.
- Sits between layers to intercept actions
- Handles cross-cutting concerns like input validation, logging, and application-level authentication
- Blocks unauthorized or invalid actions before they reach the Controller or Service

---

### `controller/`
Handles user interaction events.
- Receives UI events directly from the View
- Calls the appropriate **Service** to process data
- Keeps Swing UI thread actions isolated from heavy processing

---

### `service/`
Holds the core business logic.
- Coordinates data retrieval and manipulation
- Calls **DAOs** to fetch or persist data
- Combines multiple data operations into single logical actions (Transactions)
- Completely independent of the Swing UI code

---

### `dao/` (Data Access Object)
Handles database and file system communication.
- Contains direct SQL queries, JPA operations, or File I/O logic
- Leverages the **database** folder to obtain active connection objects
- Abstracts data storage implementation details away from the Service layer
- Returns clean **Model** blueprints to the upper layers

---

### `model/`
Holds application state and data blueprints.
- Defines data structures mapping directly to database tables or files
- Holds application-wide memory state
- Supports Observer updates to notify the View when data changes

---

### `utils/`
Global utility classes and helper functions.
- Contains reusable, standalone tools used across multiple layers
- Examples: String manipulators, date/time formatters, custom cryptography tools, file parsers

---

### `view/`
All UI (Swing components).
- Renders the UI visually
- Shows model data by observing state changes
- Sends raw user interactions (button clicks, key presses) to the Controller

---

#### `view/MainWindow.java`
Main JFrame and layout structure.

---

#### `view/components/`
Reusable UI components (custom buttons, tables, panels).

---

#### `view/config/Theme.java`
UI styling (colors, fonts, spacing).

---

## Flow
```text
User → View → [Middleware] → Controller → Service → DAO → Database/File
                                           │        ▲
                                           │        └─ (Gets Connection from Database Config)
                                           ▼
                                         Model (Updates State)
                                           │
                                           ▼ (via Observer Pattern)
                                         View (Renders Change)
```
*(Note: **Utils** can be called globally by any layer that requires independent helper functions).*

---

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
