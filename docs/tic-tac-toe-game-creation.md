## Prompt para crear proyecto inicial de Android

> Antes de crear archivos o ejecutar comandos, pregúntame en qué carpeta quiero crear el proyecto y espera mi respuesta. Usa la ruta que te dé como carpeta base y llama `PROJECT_ROOT` a la ruta resultante `<carpeta base>/TicTacToeMasterclass`. No elijas una ubicación predeterminada ni uses mi carpeta personal por tu cuenta. Usa `PROJECT_ROOT` para todas las operaciones del proyecto, incluidos archivos, copias, commit y comandos de Gradle.
>
> Crea en `PROJECT_ROOT` un nuevo proyecto de Android de un solo módulo llamado TicTacToeMasterclass usando Kotlin y Jetpack Compose, con Gradle Kotlin DSL. Usa exactamente estas versiones en `gradle/libs.versions.toml` — no propongas alternativas ni las cambies:
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
> Requisitos adicionales:
>
> - Incluye el Gradle wrapper en el commit, fijado a Gradle `9.5.0`. Usa JDK `17` para ejecutar Gradle.
> - Define todas las versiones anteriores en `gradle/libs.versions.toml`. Ninguna versión literal en ningún `build.gradle.kts`.
> - AGP `9.3.1` usa Kotlin integrado: no declares ni apliques `org.jetbrains.kotlin.android` (ni `kotlin-android`) en el catálogo ni en archivos Gradle. Declara y aplica solamente `com.android.application` y `org.jetbrains.kotlin.plugin.compose`; este último debe tomar su versión `2.4.10` desde el catálogo. No uses `buildscript { classpath(...) }` para Kotlin.
> - Una sola Activity que aloje una pantalla de Compose que muestre "Hello World" en un composable `Text`, centrado en el medio de la pantalla (por ejemplo, con un `Box` que use `Modifier.fillMaxSize()` y `contentAlignment = Alignment.Center`), siguiendo MVVM: mantén el contenido de la pantalla en un Composable (View) sin más lógica que mostrar texto estático — todavía no se necesita un ViewModel para esta pantalla de marcador de posición.
> - Una prueba unitaria trivial usando JUnit para que la tarea de test tenga algo que ejecutar.
> - No agregues ninguna dependencia más allá de lo que Compose y las bibliotecas estándar de AndroidX ya requieren.
> - Agrega un `README.md` en la raíz del proyecto con una descripción breve, los requisitos previos (JDK, Android Studio), los comandos para compilar y probar el proyecto, y una sección de licencias. Crea `PROJECT_ROOT/LICENSE` con el texto íntegro de la licencia MIT y `Copyright (c) 2026 Emilio Navarro` para el código de la app.
>
> Al terminar de crear el proyecto, copia los archivos `copilot-instructions.md` y `CLAUDE.md` ubicados junto a este documento a `PROJECT_ROOT/.github/copilot-instructions.md` y `PROJECT_ROOT/CLAUDE.md`, respectivamente. Resuelve las rutas de origen desde la carpeta de este documento, no desde `PROJECT_ROOT`. Incluye ambos archivos con su contenido en el commit; no crees archivos vacíos. En el `README.md` del proyecto, aclara que estos dos archivos copiados conservan la licencia CC BY 4.0 de Emilio Navarro (https://creativecommons.org/licenses/by/4.0/), mientras que MIT se aplica al código de la app.
>
> Desde `PROJECT_ROOT`, ejecuta `./gradlew test` y `./gradlew assembleDebug` y reporta los comandos exactos y sus resultados. No afirmes éxito sin haberlos ejecutado.

## Prompt para crear Tic-Tac-Toe inicial

> Inspecciona este repositorio y lee `.github/copilot-instructions.md`. Confirma que entiendes las reglas de arquitectura, y luego propone un plan breve y numerado para implementar un tablero de Tic-Tac-Toe de 3x3 con dos jugadores locales (X/O), siguiendo esas reglas. No escribas código todavía: termina con el plan y espera mis indicaciones. Puedo pedir cambios al plan o responder solo «implementar»; en ese caso, implementa el plan acordado, agrega pruebas de las reglas, ejecuta `./gradlew test` y `./gradlew assembleDebug` y reporta sus resultados reales.

## Prompt para crear modo 'jugador vs. computadora'

> Agrega al proyecto TicTacToeMasterclass un modo 'vs. computadora' de un solo jugador contra el dispositivo, usando el algoritmo Minimax como lógica de dominio pura (sin imports de Compose), respetando la arquitectura MVVM ya establecida (la vista solo lee estado y envía eventos, sin lógica de reglas). El usuario debe poder alternar entre 'dos jugadores' y 'vs. computadora' desde la UI. Incluye pruebas unitarias que verifiquen: que Minimax toma una jugada ganadora inmediata cuando existe, que bloquea la única amenaza del oponente, y una prueba exhaustiva que recorra todas las estrategias humanas posibles para comprobar que la computadora nunca pierde. No afirmes que la computadora es invencible a menos que esa prueba exhaustiva realmente pase. Ejecuta ./gradlew test y ./gradlew assembleDebug y reporta los resultados reales.