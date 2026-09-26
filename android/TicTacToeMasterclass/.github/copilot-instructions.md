## Product Context

TicTacToeMasterclass is a sample Android app for learning how to direct AI agents on a software project. It starts with a "Hello World" screen, becomes a local two-player Tic-Tac-Toe game (3x3 board, turn indicator, win/draw detection, invalid-move prevention, restart), and finally adds a Minimax mode against the device. The game is a small, familiar problem that lets us focus on how the agent's work is directed, reviewed, and validated.

## User Context

- Author: Emilio Navarro.
- Audience: people who want to learn to create an Android app with AI agents; do not assume prior Android experience.
- Environment: VS Code with GitHub Copilot Agent Mode to follow the prompts; Android Studio to manage the SDK and run the app in an emulator.
- Constraint: keep changes small and verifiable so each stage of the example can be followed.
- Communication preference: report honestly and concretely what was run and its actual result — never claim a build or test passed without running it.

## Stack

- Kotlin 2.4.10, Jetpack Compose (BOM 2026.06.01), Gradle Kotlin DSL, AGP 9.3.1, Gradle 9.5.0, and JDK 17.
- `compileSdk = 37`, `minSdk = 30`, `targetSdk = 37`.
- `coreKtx = 1.19.0`, `activityCompose = 1.13.0`, `junit = 4.13.2`.
- All versions above are pinned in `gradle/libs.versions.toml`. Do not use literal versions in any `build.gradle.kts` file.
- AGP 9 has built-in Kotlin support: do not declare or apply `org.jetbrains.kotlin.android` (or `kotlin-android`) in the catalog or Gradle files. Declare and apply only `com.android.application` and `org.jetbrains.kotlin.plugin.compose`; the latter gets its `kotlin` version from the catalog. Do not use `buildscript { classpath(...) }` for Kotlin.
- The Gradle wrapper is included in the project. Confirm the Android SDK is available before building.

## Architecture

- MVVM (Model-View-ViewModel).
- Unidirectional data flow (UDF).
- UI state is immutable and exposed through `State`/`StateFlow`.
- Game domain logic (rules, win/draw detection, move validation) lives in a pure Kotlin module with no Compose imports (the Model).
- A single `ViewModel` mediates between the domain logic and UI: it maintains and exposes UI state and handles user actions (moves, restart). Composables (the View) read state and send events to the `ViewModel`; they must not contain game-rule logic or mutate state directly.

## Naming and Style

- Use standard Kotlin naming conventions: types in PascalCase, members in camelCase.
- Composables use PascalCase and should be free of side effects where possible.

## Dependencies

- Do not add unnecessary third-party libraries. Use Jetpack Compose and the standard Kotlin/Android libraries already present in the scaffold.
- Do not add a dependency without explaining why.
- All dependency and SDK versions must be resolved through `gradle/libs.versions.toml`. Do not hard-code versions in any `build.gradle.kts` file.

## Testing

- Unit tests are required for all game-rule logic: win detection, draw detection, and prevention of invalid moves.
- Run the narrowest relevant test or build after each significant change.

## Accessibility and Design

- Responsive design in portrait orientation.
- Add content descriptions/accessibility labels to interactive board cells and key actions.

## Validation Commands

- `./gradlew test`
- `./gradlew assembleDebug`

(Confirm the exact module paths in the project.)

## Definition of Done

A change is complete only when the build is clean, relevant tests pass, and no unresolved diagnostics remain.

## Reporting

Report honestly which checks failed or were not run. Never claim a build or test passed without running it.