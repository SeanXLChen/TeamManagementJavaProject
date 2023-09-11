package u10;

import java.util.Scanner;

/**
 * Driver class for the soccer game.
 * It will prompt the coach to enter players' information and generate a team of players.
 */
public class Main {
  public static void main(String[] args) {
    // Create a new team
    Team team = new TeamImpl();

    // Create a Scanner object for user input
    Scanner scanner = new Scanner(System.in);

    System.out.println("Welcome to youth soccer team manage system: ");

    // Add players to the team
    outerLoop:
    // This is the label for the outer loop
    while (true) {
      System.out.println(
          "\nEnter 'a' to add player | 'g' to start generating team | 'r' to start over |'q' to exit the program");

      String command = scanner.nextLine().trim().toLowerCase();

      switch (command) {
        case "g":
          if (team.getTeamSize() < 10) {
            System.out.println("You must enter at least 10 players. Please continue.");
            continue;
          } else {
            break outerLoop; // This breaks out of the outer loop
          }
        case "r":
          team = new TeamImpl(); // reset the team
          continue;
        case "q":
          System.out.println("Exiting the program...");
          scanner.close();
          System.exit(0);
        case "a":
          break;
        default:
          System.out.println("Invalid command. Please enter 'a', 'r', 'q', or 'continue'.");
          continue;
      }

      System.out.println("Enter information for player " + (team.getTeamSize() + 1));

      System.out.print("First name: ");
      while (!scanner.hasNext("[a-zA-Z]*")) {
        System.out.println("That's not a valid name! Try again.");
        scanner.next(); // discard the invalid input
        scanner.nextLine(); // consume the leftover newline
      }
      String firstName = scanner.nextLine();

      System.out.print("Last name: ");
      while (!scanner.hasNext("[a-zA-Z]*")) {
        System.out.println("That's not a valid name! Try again.");
        scanner.next(); // discard the invalid input
        scanner.nextLine(); // consume the leftover newline
      }
      String lastName = scanner.nextLine();

      System.out.print("Year of birth: ");
      while (!scanner.hasNextInt()) {
        System.out.println("That's not a valid year! Try again.");
        scanner.next(); // discard the invalid input
      }
      int year = scanner.nextInt();
      scanner.nextLine(); // consume the leftover newline

      System.out.print("Month of birth: ");
      while (!scanner.hasNextInt()) {
        System.out.println("That's not a valid month! Try again.");
        scanner.next(); // discard the invalid input
      }
      int month = scanner.nextInt();
      scanner.nextLine(); // consume the leftover newline

      System.out.print("Day of birth: ");
      while (!scanner.hasNextInt()) {
        System.out.println("That's not a valid day! Try again.");
        scanner.next(); // discard the invalid input
      }
      int day = scanner.nextInt();
      scanner.nextLine(); // consume the leftover newline

      System.out.print(
          "Preferred position (1 for GOALIE, 2 for DEFENDER, 3 for MIDFIELDER, 4 for FORWARD): ");
      while (!scanner.hasNextInt()) {
        System.out.println("That's not a valid position! Try again.");
        scanner.next(); // discard the invalid input
      }
      int positionInput = scanner.nextInt();
      scanner.nextLine(); // consume the leftover newline
      Position preferredPosition = Position.values()[positionInput - 1];

      System.out.print("Skill level (1-5): ");
      while (!scanner.hasNextInt()) {
        System.out.println("Not a valid skill level! Try again.");
        scanner.next(); // discard the invalid input
      }
      int skillLevel = scanner.nextInt();
      scanner.nextLine(); // consume the leftover newline

      try {
        // Add the player to the team
        team.addPlayer(firstName, lastName, year, month, day, preferredPosition, skillLevel);
        System.out.println("Successfully add player " + team.getTeamSize());
      } catch (IllegalArgumentException e) {
        System.out.println("Error adding player: " + e.getMessage());
        continue;
      }

      if (team.getTeamSize() == 20) {
        System.out.println("Maximum number of players reached.");
        break;
      }
    }

    // Assign positions and jersey numbers
    team.generateStartingLineup();

    // Print out the lists of all players and the starting lineup
    System.out.println("All players:");
    System.out.println(team.getAllPlayersList());
    System.out.println("Starting lineup:");
    System.out.println(team.getStartingLineupList());

    // Close the scanner
    scanner.close();
  }
}