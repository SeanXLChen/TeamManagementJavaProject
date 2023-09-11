package bcsoccer;

import java.util.List;

/**
 * This interface represents the methods offered by a team in a game of soccer.
 * A team can add and remove players, and generate the starting lineup.
 */
public interface Team {

  /**
   * Adds a player to the team.
   *
   * @param firstName         the player's first name
   * @param lastName          the player's last name
   * @param year              the year of the player's date of birth
   * @param month             the month of the player's date of birth
   * @param day               the day of the player's date of birth
   * @param preferredPosition the player's preferred position
   * @param skillLevel        the player's skill level
   * @throws IllegalArgumentException if the player is not under 10 years old
   */
  void addPlayer(String firstName, String lastName, int year, int month, int day,
      String preferredPosition, int skillLevel);

  /**
   * Removes a Player from the team.
   *
   * @param player the Player to be removed
   * @throws IllegalArgumentException if the Player is not in the team
   */
  void removePlayer(Player player);

  /**
   * Get team size.
   *
   * @return team size
   */
  int getTeamSize();

  /**
   * Generates the starting lineup of the team. The starting lineup consists of 1 goalie, 2
   * defenders, 3 midfielders, and 1 forward. The players are sorted by position (goalie, defender,
   * midfielder, forward). Each player in the starting lineup is assigned a jersey number.
   */
  void generateStartingLineup();

  /**
   * Returns a string representation of all the players in the team. The string includes each
   * player's first name, last name, and jersey number. The players are sorted in alphabetical order
   * by last name.
   *
   * @return a string representation of all the players in the team
   */
  String getAllPlayersList();

  /**
   * Returns a string representation of the starting lineup of the team. The string includes each
   * player's first name, last name, jersey number, and position. The players are sorted by position
   * (goalie, defender, midfielder, forward). Players with the same position are ordered
   * alphabetically by last name.
   *
   * @return a string representation of the starting lineup of the team
   */
  String getStartingLineupList();

  /**
   * Validate if team meets the requirements to generate starting lineup.
   *
   * @return true if team is valid, false otherwise
   */
  boolean validateTeam();

  /**
   * Get player by its jersey number.
   *
   * @param jerseyNumber jersey number
   * @return the player with the given jersey number, or null if no such player exists
   */
  Player getPlayerByJerseyNumber(int jerseyNumber);

  /**
   * Get all jersey numbers as string.
   *
   * @return list of jersey numbers as string
   */
  List<String> getAllJerseyNumbersAsString();
}