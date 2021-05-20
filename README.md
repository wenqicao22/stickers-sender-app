# Stick It To 'Em

Group assignment for CS5520

## Team members

- Donghui Zhang ([@z1395890573](https://github.ccs.neu.edu/z1395890573)) - zhang.dong@northeastern.edu
- Wenqi Cao ([@wenqi7722](https://github.ccs.neu.edu/wenqi7722)) - cao.wenqi@northeastern.edu
- Yutianqi Fu ([@yfu018](https://github.ccs.neu.edu/yfu018)) - fu.yuti@northeastern.edu
- Zhi Wen ([@zeft](https://github.ccs.neu.edu/zeft)) - wen.zhi@northeastern.edu

## Installation

1. Connect to an android device.
2. Enable adb debugging on your device following the [instruction](https://developer.android.com/studio/command-line/adb#Enabling).
3. Use adb to install the APK on an emulator or a connected device with the following command:
   ```
   adb install [path_to_apk]
   ```

## Usage

_TODO_

# Development Guideline

1. Fork the project.
2. Clone locally.
   ```
   git clone https://github.ccs.neu.edu/[username]/Stick-It-To-Em.git
   ```
3. Create an upstream remote and sync your local copy before you branch.
   ```
   git remote add upstream https://github.ccs.neu.edu/CS5520-Chatime/Stick-It-To-Em.git
   ```
4. Branch for each separate piece of work.
5. Do the work, write good commit messages following [Chris Beams commit guidelines](https://chris.beams.io/posts/git-commit/).
6. Push to your origin repository.
7. Create a new PR in GitHub.
8. Respond to any code review feedback.

## Code Style
Please follow [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html). An Android Studio plugin can be found [here](https://github.com/google/google-java-format#intellij-android-studio-and-other-jetbrains-ides).

## Third-party Library used

- [Butterknife](https://github.com/JakeWharton/butterknife) for view injection.
