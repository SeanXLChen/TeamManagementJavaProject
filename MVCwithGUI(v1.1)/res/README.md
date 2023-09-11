# BC Soccer Team Builder System

![Static Badge](https://img.shields.io/badge/powered_by-Java_Swing-blue?label=Powered%20by)
![Static Badge](https://img.shields.io/badge/design_using-IntelliJ-green?logo=intellijidea)
![Static Badge](https://img.shields.io/badge/Design_Using-VScode-purple?logo=visualstudiocode)



Xiaolai Chen | 
CS5004 Final Project | 2023-Summer

## About/Overview
The BC Soccer Team Builder System is designed to assist coaches and team managers to organizing
and managing teams member's basic info and positions assigned. The goal is to help manager easily add and delete players, as well as automatically generate the 7x7 starting lineup and assign random jersey number (1-20).


## Features
- Add Player: Easily input Player details (name, DOB, preferred position, skill level)
- Remove Player: Quickly remove a Player by his/her jersey number
- Generate & View Starting Lineup: Automatically generate a starting lineup once at least 10 players are added (ordered by position)
- View All Players: View a list of all players in team (ordered by last name)


## How To Run

Please ensure you have Java installed. Otherwise you can download it from [official java page](https://www.java.com/en/)

At project root folder, in terminal use command
```
cd res
```
navigate to /res folder and find file [soccer_part2.jar](soccer_part2.jar)
then use command
```
java -jar soccer_part2.jar
```
or directly use IntelliJ or other IDE to run this JAR file.

## How to Use the Program

1. Use the left pane **Add Player** to input Player info and click **Add** to load Player into the team.
    - If added more than 20 players, team will drop the Player with lowest skill level to maintain 20 Player's cap.
2. Use the right pane **Remove Player** to remove Player by their Jersey Number
    - This feature is only available after team is formed (has 10+ players)
3. All Player List will update everytime Player is successfully added
4. Starting Lineup will update once team is formed (has 10+ players)



<img src="app_screenshot.png" width="450" height="500">

## Design Changes
- `v1.0` Model + Terminal Driver
    1. Update UML from initial draft
    2. Use Terminal as interface
- `v1.1` M + V + C
    1. Following MVC framwork, retire terminal driver and use Java Swing to design the GUI frontend for better user experience.
        - Added `InterfaceController.java` and `Controller.java` as the interface and concrete class for controller in MVC
        - Added `InterfaceView.java` and `View.java` as the interface and concrete class for view in MVC.
        - controller will only interact with `Team.java` Team interface from Model for MVC best practice. 
    2. `TeanImpl.assignJerseyNumbers()` delete the hard fixed random seed and now will trully randomly reassign all Jersey number every time new Player add in.
    3. `TeamImpl.addPlayer()` method now take in `Position` pamameter not as enum but as string value and convert to Position enum type
    4. Add a new method `TeamImpl.getAllJerseyNumbersAsString()` to feed into front end remove Player's dropdown jersey number selection.
    5. Before each iteration of generating starting lineup, first reset all assigned Player position to `Position.BENCH`
    6. In Test case, remove test `TestGenerateStartingLineup()` and related methods because now the numbers are truly random now and hard to test.


## Assumptions
the Starting Lineup generated will follow the [2-3-1](https://youthsoccer101.net/7v7-formations/) formation of 7x7 youth soccer.
> - 1 Goalie
> - 2 Defenders
> - 3 Midfielders
> - 1 Forward

A team requires at least 10 players to generate a starting lineup.

A team can manage at most 20 players.

Players have unique jersey numbers from 1 to 20.

The skill level is rated on a scale of 1 to 5 (lowest to highest).



## Limitations
- Everytime a new Player is added, all Players' `Jersey Number` will be changed.
- Team has a max size of 20
- Player will only be assigned their `Position` and `Jersey Number` after team has mininum of 10 players.
- Each time user **Add** & **Remove** will regenerate players' `Position` and `Jersey Number` again.
- User can't **Remove** Player when team size is 10.
- The program does not support saving team data between sessions.
- The starting lineup generation does not consider Player injuries or availability.

## Citations
- “Trail: Creating a GUI With Swing (The JavaTM Tutorials).” https://docs.oracle.com/javase/tutorial/uiswing/
- C. McKenzie, “How to run a Jar file,” Coffee Talk: Java, News, Stories and Opinions, Jun. 09, 2022. https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Run-JAR-file-example-windows-linux-ubuntu
- “基本撰写和格式语法 - GitHub 文档,” GitHub Docs. https://docs.github.com/zh/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax
- “Shields.io | shields.io.” https://shields.io/
