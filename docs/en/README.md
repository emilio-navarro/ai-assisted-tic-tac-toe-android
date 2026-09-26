# Android Example: From Idea to App with AI Agents

This example uses a Tic-Tac-Toe game to show how to direct software development with AI agents. The goal is not to produce a complex game or simply ask for code: it is to give the agent context and persistent instructions, divide the work into stages, review its decisions, and verify the result with tests, builds, and an actual run. That is what **agent-led development** (or *agentic development*) means here. Follow the steps to create your own copy of the Android project.

## Tools

- **Visual Studio Code (VS Code)** with GitHub Copilot in agent mode: run the prompts in sequence, inspect the changes, and launch Gradle commands from the terminal.
- **Android Studio**: set up the SDK and emulator, open the Android project, and run the app to check what a person sees and can do. Building and testing from the terminal does not replace this visual check.

## Steps to Build the App

The three prompts are in order in [tic-tac-toe-game-creation.md](tic-tac-toe-game-creation.md). Run them in VS Code with GitHub Copilot in agent mode. Before moving to the next step, review the agent's work and verify its reported results; if a test or build fails, resolve it at that stage.

1. **Run “Prompt to Create the Initial Android Project.”** Before creating anything, the agent will ask where to save the project. Choose a folder you can access and let it prepare the app with a “Hello World” screen, the persistent instructions in [copilot-instructions.md](copilot-instructions.md) and [CLAUDE.md](CLAUDE.md), and the tests and license specified in the prompt. When it finishes, verify that `./gradlew test` and `./gradlew assembleDebug` passed, then open the project in Android Studio and run the app. We start small to confirm the environment works: there is no game yet, but we have a foundation we can run and verify.

2. **Run “Prompt to Create the Initial Tic-Tac-Toe Game” in the new project.** The agent will finish with a plan and will not write the game yet. Review it; you can give further direction or request changes before work begins. When you are satisfied, reply “implement” in the same conversation; the agent will know to build the game, test the rules, and run the checks. In Android Studio, verify that two people can take turns, detect a win or draw, and start another game. This pause demonstrates that you decide when to move from planning to code; do not proceed to step three if you only have a plan.

3. **Run “Prompt to Create Player vs. Computer Mode” once the two-player version works.** The agent now adds the option to play against the device using Minimax, with tests covering winning moves, blocks, and every possible human strategy. Confirm that the tests and build really pass, then try both modes in Android Studio. This final step shows how to delegate a more complex feature without losing control of the rules or accepting a claim like “the computer never loses” without evidence.

“Hello World” is the starting point, not the finished product: the learning is in moving from a verifiable foundation to a complete feature without delegating technical judgment.

## License

The original materials in this folder (text, prompts, and instructions) are by Emilio Navarro and are available under [Creative Commons Attribution 4.0 International (CC BY 4.0)](LICENSE.md). You may share and adapt them, including commercially, as long as you credit the author, link to the license, and indicate whether you made changes.

The Android app code generated using these prompts will have an MIT license in its own project. Instruction files copied from this folder retain their CC BY 4.0 license; they do not become MIT-licensed.