## Product Context

TicTacToeMasterclass es una app Android de ejemplo para aprender a dirigir agentes de IA en un proyecto de software. Comienza con una pantalla «Hello World», luego se convierte en un juego local de Tic-Tac-Toe para dos jugadores (tablero 3x3, indicador de turno, detección de victoria/empate, prevención de movimientos inválidos, reinicio) y finalmente incorpora un modo contra el dispositivo con Minimax. El juego es un problema pequeño y familiar para concentrarse en cómo se dirige, revisa y valida el trabajo del agente.

## User Context

- Autor: Emilio Navarro.
- Audiencia: personas que quieren aprender a crear una app Android con agentes de IA; no asumir experiencia previa en Android.
- Entorno: VS Code con GitHub Copilot Agent Mode para seguir los prompts; Android Studio para gestionar el SDK y ejecutar la app en un emulador.
- Restricción: mantener los cambios pequeños y verificables para que se pueda seguir cada etapa del ejemplo.
- Preferencia de comunicación: reporte honesto y concreto de lo que se ejecutó y su resultado real — nunca afirmar que un build o prueba pasó sin haberlo ejecutado.

## Stack

- Kotlin 2.4.10, Jetpack Compose (BOM 2026.06.01), Gradle Kotlin DSL, AGP 9.3.1, Gradle 9.5.0 y JDK 17.
- `compileSdk = 37`, `minSdk = 30`, `targetSdk = 37`.
- `coreKtx = 1.19.0`, `activityCompose = 1.13.0`, `junit = 4.13.2`.
- Todas las versiones anteriores están fijadas en `gradle/libs.versions.toml`. Ninguna versión literal en ningún `build.gradle.kts`.
- AGP 9 usa Kotlin integrado: no declarar ni aplicar `org.jetbrains.kotlin.android` (ni `kotlin-android`) en el catálogo o archivos Gradle. Declarar y aplicar solamente `com.android.application` y `org.jetbrains.kotlin.plugin.compose`; este último toma la versión `kotlin` del catálogo. No usar `buildscript { classpath(...) }` para Kotlin.
- El Gradle wrapper está incluido en el proyecto. Confirmar que el SDK de Android esté disponible antes de compilar.

## Arquitectura

- MVVM (Model-View-ViewModel).
- Flujo de datos unidireccional (UDF).
- El estado de la UI es inmutable, expuesto mediante `State`/`StateFlow`.
- La lógica de dominio del juego (reglas, detección de victoria/empate, validación de movimientos) vive en un módulo de Kotlin puro sin imports de Compose (el Model).
- Un único `ViewModel` media entre la lógica de dominio y la UI: mantiene y expone el estado de la UI, y maneja las acciones del usuario (movimientos, reinicio). Los composables (la View) leen el estado y envían eventos al `ViewModel`; no deben contener lógica de reglas del juego ni mutar el estado directamente.

## Nomenclatura y Estilo

- Convenciones estándar de nomenclatura de Kotlin: tipos en PascalCase, miembros en camelCase.
- Los composables usan PascalCase y están libres de efectos secundarios en la medida de lo posible.

## Dependencias

- No agregar bibliotecas de terceros innecesarias. Usar Jetpack Compose y las bibliotecas estándar de Kotlin/Android ya presentes en el scaffold.
- No agregar una dependencia sin indicar por qué.
- Todas las versiones de dependencias y de SDK deben resolverse a través de `gradle/libs.versions.toml`. No incluir versiones fijas en ningún `build.gradle.kts`.

## Pruebas

- Se requieren pruebas unitarias para toda la lógica de reglas del juego: detección de victoria, detección de empate, prevención de movimientos inválidos.
- Ejecutar la prueba o build más acotado relevante después de cada cambio significativo.

## Accesibilidad y Diseño

- Diseño responsivo en modo vertical (portrait).
- Descripciones de contenido / etiquetas de accesibilidad en las celdas interactivas del tablero y en las acciones clave.

## Comandos de Validación

- `./gradlew test`
- `./gradlew assembleDebug`

(Confirmar las rutas exactas de los módulos en el proyecto.)

## Definición de Terminado

Un cambio está terminado solo cuando: el build está limpio, las pruebas relevantes pasan, y no quedan diagnósticos sin resolver.

## Reporte

Reportar honestamente las verificaciones que fallaron o no se ejecutaron. Nunca afirmar que un build o prueba pasó sin haberlo ejecutado.
