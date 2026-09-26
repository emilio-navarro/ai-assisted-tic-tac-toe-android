# Ejemplo Android: de la idea a una app con agentes de IA

Este ejemplo usa un juego de Tic-Tac-Toe para mostrar cómo dirigir el desarrollo de software con agentes de IA. La meta no es producir un juego complejo ni limitarse a pedir código: es dar al agente contexto e instrucciones persistentes, dividir el trabajo en etapas, revisar sus decisiones y comprobar el resultado con pruebas, compilación y ejecución real. Eso es lo que aquí significa **desarrollo dirigido por agentes** (o desarrollo *agéntico*). Puedes seguir los pasos para crear tu propia copia del proyecto Android.

## Herramientas

- **Visual Studio Code (VS Code)** con GitHub Copilot en modo agente: ejecutar los prompts de la secuencia, inspeccionar los cambios y lanzar los comandos de Gradle desde la terminal.
- **Android Studio**: preparar el SDK y el emulador, abrir el proyecto Android y ejecutar la app para comprobar lo que ve y puede hacer una persona. Compilar y probar desde la terminal no sustituye esta comprobación visual.

## Pasos para crear la app

Los tres prompts están, en este orden, en [tic-tac-toe-game-creation.md](tic-tac-toe-game-creation.md). Ejecútalos en VS Code con GitHub Copilot en modo agente. Antes de pasar al siguiente paso, lee lo que hizo el agente y comprueba los resultados que reportó; si una prueba o compilación falla, resuélvela en esa etapa.

1. **Corre «Prompt para crear proyecto inicial de Android».** El agente te preguntará dónde guardar el proyecto antes de crear nada. Elige una carpeta a la que tengas acceso y deja que prepare la app con una pantalla «Hello World», las instrucciones persistentes de [copilot-instructions.md](copilot-instructions.md) y [CLAUDE.md](CLAUDE.md), y las pruebas y licencia indicadas en el prompt. Al terminar, comprueba que `./gradlew test` y `./gradlew assembleDebug` pasaron y abre el proyecto en Android Studio para ejecutar la app. Empezamos por algo tan pequeño para saber que el entorno funciona: todavía no hay juego, pero ya tenemos una base que se puede ver y verificar.

2. **Corre «Prompt para crear Tic-Tac-Toe inicial» desde el proyecto recién creado.** El agente terminará con un plan, sin escribir todavía el juego. Léelo: puedes seguir dando indicaciones o pedirle cambios antes de empezar. Cuando estés conforme, responde simplemente «implementar» en la misma conversación; el agente ya sabe que debe construir el juego, probar las reglas y ejecutar las verificaciones. Comprueba en Android Studio que dos personas puedan jugar por turnos, detectar una victoria o un empate y empezar otra partida. Esta pausa muestra que tú decides cuándo pasar del plan al código; no avances al tercer paso si solo tienes el plan.

3. **Corre «Prompt para crear modo 'jugador vs. computadora'» cuando la versión para dos jugadores ya funcione.** Ahora el agente añade la opción de jugar contra el dispositivo con Minimax y pruebas que cubren jugadas ganadoras, bloqueos y todas las estrategias humanas posibles. Revisa que las pruebas y la compilación realmente pasen, y prueba ambos modos en Android Studio. Este último paso muestra cómo delegar una funcionalidad más compleja sin perder el control sobre las reglas ni dar por cierta una afirmación como «la computadora nunca pierde» sin evidencia.

«Hello World» es el punto de partida, no el producto final: el aprendizaje está en cómo se pasa de una base verificable a una funcionalidad completa sin delegar el criterio técnico.

## Licencia

Los materiales originales de esta carpeta (textos, prompts e instrucciones) son de Emilio Navarro y están disponibles bajo [Creative Commons Atribución 4.0 Internacional (CC BY 4.0)](LICENSE.md). Puedes compartirlos y adaptarlos, incluso con fines comerciales, dando crédito al autor, enlazando la licencia e indicando si hiciste cambios.

El código de la app Android creada con estos prompts tendrá licencia MIT en su propio proyecto. Los archivos de instrucciones copiados desde esta carpeta conservan su licencia CC BY 4.0, no pasan a ser MIT.