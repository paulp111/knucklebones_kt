# Knucklebones

Knucklebones is a strategic dice game inspired by the minigame in *Cult of the Lamb*. This Android application, written in Kotlin, offers a single-player experience where users compete against a computer opponent. Players strategically place dice values on a 3x3 grid to maximize their score and disrupt their opponent's strategy.

## Features

- **Interactive Gameplay**: Players roll dice and place them on their grid, aiming to score higher than their opponent.
- **Multiplication Mechanic**: Matching dice in a column multiply the score for that column.
- **Destruction Mechanic**: Place dice matching your opponent’s in the same column to remove their dice.
- **Instructions Screen**: Clear guidelines for new players.
- **Splash Screen**: Immersive introduction with an animated video.

## App Design

### Splash Screen

The splash screen features an animated video that sets the tone of the game. If the video completes or after a 5-second fallback, the app navigates to the main menu.

### Main Menu

The main menu allows the user to:
- Enter their name
- Start the game
- Access the instructions

### Game Screen

The game screen displays:
- A 3x3 grid for both the player and the computer
- Current scores for both players
- A button to roll the dice and display the result
- Turn-based gameplay mechanics

### Instructions Screen

The instructions screen provides:
- A description of the game mechanics
- Rules for gameplay
- Strategic tips

### Visuals

![Splash Screen](file-YWicsyV6Q7iMA421bAfk8d)
![Main Menu](file-Gj7cU9WcgFippWAwWNePJX)
![Instructions](file-4tYbrzo6GGyjCak9hHtRqk)
![Gameplay](file-B6yaHHsLFZRPDB9NYz5wzH)

## Code Overview

### Core Files

#### `MainActivity.kt`
- Manages navigation between the main menu, game activity, and instructions activity.

#### `GameActivity.kt`
- Implements game logic, including dice rolls, score calculation, and turn handling.
- Features a grid system for both player and computer with clickable buttons.

#### `SplashActivity.kt`
- Displays an animated splash screen video and navigates to the main menu.

#### `InstructionActivity.kt`
- Hosts the instructions page with gameplay rules and tips.

### Resources

- **Layouts**: XML files for each activity’s user interface.
- **Drawables**: Backgrounds and graphical assets, including the splash video.
- **Themes**: Custom themes and colors for a polished look.

## Gameplay Rules

1. **Objective**: Score more points than your opponent.
2. **Turn-Based Play**: Players take turns rolling a dice and placing it in a column.
3. **Multiplication**: Dice with the same value in a column are multiplied.
4. **Destruction**: Match your dice to your opponent's in the same column to remove theirs.

## How to Build and Run

1. Clone this repository.
2. Open the project in Android Studio.
3. Sync Gradle files.
4. Run the app on an emulator or physical device.

## Future Improvements

- Multiplayer mode (online and local)
- Enhanced AI difficulty levels
- Customizable themes and dice styles

---

Enjoy playing Knucklebones and may your strategy outshine your opponent's!

