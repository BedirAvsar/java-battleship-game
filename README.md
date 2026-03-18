# Java Battleship Game  
*A console-based implementation of the classic Battleship game with turn-based multiplayer logic and state-driven game flow.*

![Java](https://img.shields.io/badge/Java-Backend-orange)
![Gradle](https://img.shields.io/badge/Build-Gradle-blue)
![CLI](https://img.shields.io/badge/Interface-CLI-lightgrey)

---

## The 'Why' & Real-World Use Case

Turn-based systems require strict control over state transitions, input validation, and rule enforcement. This project simulates a two-player Battleship game, focusing on how game state is managed and validated in a deterministic environment.

The concepts implemented here—such as **state management, input validation, and rule-driven execution**—are directly applicable to backend systems, workflow engines, and transactional applications.

---

## Architecture & Technical Decisions

- **State-Driven Game Flow**
  - The game progresses through clearly defined states:
    - Ship placement
    - Player turns
    - Hit/miss evaluation
    - Win condition

- **Grid-Based Data Structure**
  - 10x10 matrix representing the game board
  - Tracks ship positions, hits, and misses

- **Input Parsing & Validation**
  - Coordinates are parsed and validated
  - Prevents invalid placements and duplicate shots

- **Separation of Responsibilities**
  - Game logic is separated from input/output handling
  - Improves readability and maintainability

- **Turn-Based Logic**
  - Alternating player turns
  - Ensures fair and consistent gameplay

---

## Tech Stack

- Java  
- Gradle  
- CLI (Console-based interaction)  

---

## Getting Started

### Clone the repository
```bash id="k92kdl"
git clone https://github.com/BedirAvsar/java-battleship-game.git
cd java-battleship-game
```

### Build the project
```bash id="dk21ld"
./gradlew build
```

### Run the game
```bash id="sdlk21"
./gradlew run
```

> If `run` task is not configured, you can execute the main class manually via your IDE.

---

## Usage

### Game Flow

1. Player 1 places ships  
2. Player 2 places ships  
3. Players take turns shooting  
4. Game continues until all ships are sunk  

### Controls

| Action | Input |
|--------|------|
| Place ship | Coordinates (e.g., A1 A5) |
| Shoot | Single coordinate (e.g., B3) |

### Game Rules

- 10×10 board  
- Ships must be placed in straight lines  
- Ships cannot overlap  
- Hits and misses are tracked  
- First player to sink all ships wins  

---

## What I Learned

This project focuses on **core programming fundamentals using Java** rather than frameworks.

### Key Takeaways

- Designing a **state-driven system**
- Implementing **grid-based algorithms**
- Writing robust **input validation logic**
- Managing **turn-based workflows**
- Structuring a clean CLI application

### Biggest Challenge

The most challenging part was managing **consistent game state across turns**, especially ensuring that ship placement, hit detection, and win conditions were all correctly synchronized without introducing logical bugs.

---

This project demonstrates the ability to design and implement a **rule-based interactive system**, emphasizing clean logic, correctness, and maintainability.
