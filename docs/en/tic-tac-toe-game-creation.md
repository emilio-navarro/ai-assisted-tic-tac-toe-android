## Prompt to Create the Initial Android Project

> Before creating files or running commands, ask me which folder I want to use for the project and wait for my answer. Use the path I provide as the base folder and call the resulting `<base folder>/TicTacToeMasterclass` path `PROJECT_ROOT`. Do not choose a default location or use my personal folder on your own. Use `PROJECT_ROOT` for all project operations, including files, copies, commits, and Gradle commands.
>
> Create a new single-module Android project named TicTacToeMasterclass in `PROJECT_ROOT`, using Kotlin and Jetpack Compose with Gradle Kotlin DSL. Use exactly these versions in `gradle/libs.versions.toml` — do not suggest alternatives or change them:
>
> - `compileSdk = 37`
> - `minSdk = 30`
> - `targetSdk = 37`
> - `agp = 9.3.1`
> - `kotlin = 2.4.10`
> - `composeBom = 2026.06.01`
> - `coreKtx = 1.19.0`
> - `activityCompose = 1.13.0`
> - `junit = 4.13.2`
>
> Additional requirements:
>
> - Include the Gradle wrapper in the commit, pinned to Gradle `9.5.0`. Use JDK `17` to run Gradle.
> - Define all the versions above in `gradle/libs.versions.toml`. Do not use literal versions in any `build.gradle.kts` file.
> - AGP `9.3.1` has built-in Kotlin support: do not declare or apply `org.jetbrains.kotlin.android` (or `kotlin-android`) in the catalog or Gradle files. Declare and apply only `com.android.application` and `org.jetbrains.kotlin.plugin.compose`; the latter must get version `2.4.10` from the catalog. Do not use `buildscript { classpath(...) }` for Kotlin.
> - Use a single Activity hosting a Compose screen that displays "Hello World" in a `Text` composable, centered on the screen (for example, with a `Box` using `Modifier.fillMaxSize()` and `contentAlignment = Alignment.Center`). Follow MVVM: keep the screen content in a Composable (View) with no logic beyond displaying static text — a ViewModel is not needed yet for this placeholder screen.
> - Add a trivial JUnit unit test so the test task has something to run.
> - Do not add any dependencies beyond those already required by Compose and standard AndroidX libraries.
> - Add a `README.md` at the project root with a brief description, prerequisites (JDK, Android Studio), build and test commands, and a license section. Create `PROJECT_ROOT/LICENSE` with the full MIT license text and `Copyright (c) 2026 Emilio Navarro` for the app code.
>
> After creating the project, copy the `copilot-instructions.md` and `CLAUDE.md` files located alongside this document to `PROJECT_ROOT/.github/copilot-instructions.md` and `PROJECT_ROOT/CLAUDE.md`, respectively. Resolve the source paths relative to this document's folder, not `PROJECT_ROOT`. Include both files with their contents in the commit; do not create empty files. In the project's `README.md`, clarify that these two copied files retain Emilio Navarro's CC BY 4.0 license (https://creativecommons.org/licenses/by/4.0/), while MIT applies to the app code.
>
> From `PROJECT_ROOT`, run `./gradlew test` and `./gradlew assembleDebug`, and report the exact commands and their results. Do not claim success unless you ran them.

## Prompt to Create the Initial Tic-Tac-Toe Game

> Inspect this repository and read `.github/copilot-instructions.md`. Confirm that you understand the architecture rules, then propose a brief, numbered plan to implement a 3x3 Tic-Tac-Toe board for two local players (X/O), following those rules. Do not write code yet: finish with the plan and wait for my instructions. I may ask for changes to the plan or reply only "implement"; in that case, implement the agreed plan, add tests for the rules, run `./gradlew test` and `./gradlew assembleDebug`, and report their actual results.

## Prompt to Create Player vs. Computer Mode

> Add a single-player "vs. computer" mode to the TicTacToeMasterclass project, using the Minimax algorithm as pure domain logic (no Compose imports) and following the established MVVM architecture (the view only reads state and sends events; it contains no game-rule logic). The user must be able to switch between "two players" and "vs. computer" in the UI. Add unit tests to verify that Minimax takes an immediate winning move when one exists, blocks the opponent's only threat, and passes an exhaustive test that explores every possible human strategy to confirm the computer never loses. Do not claim the computer is unbeatable unless that exhaustive test actually passes. Run `./gradlew test` and `./gradlew assembleDebug`, and report the actual results.