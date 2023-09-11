package u10;

/**
 * This interface represents the methods offered by a player in a game of soccer.
 * it represents a player in a game of soccer.
 */
public interface Player {
  /**
   * Return the first name of this player.
   *
   * @return the first name of this player
   */
  String getFirstName();

  /**
   * Return the last name of this player.
   *
   * @return the last name of this player
   */
  String getLastName();

  /**
   * get the player's date of birth in String format.
   *
   * @return the player's date of birth in String format YYYY-MM-DD
   */
  String getDOB();

  /**
   * get the player's age.
   *
   * @return the player's age
   */
  int getAge();

  /**
   * get the player's preferred position.
   *
   * @return the player's preferred position
   */
  Position getPreferredPosition();

  /**
   * get the player's assigned position.
   *
   * @return the player's assigned position
   */
  Position getAssignedPosition();

  /**
   * set the player's assigned position.
   *
   * @param position the player's assigned position
   */
  public void setAssignedPosition(Position position);

  /**
   * get the player's jersey number.
   *
   * @return the player's jersey number
   */
  int getJerseyNumber();

  /**
   * set the player's jersey number.
   *
   * @param jerseyNumber the player's assigned jersey number
   * @throws IllegalArgumentException if the jersey number is not between 1 and 20
   */
  public void setJerseyNumber(int jerseyNumber);

  /**
   * get the player's skill level based on the coach's assessment. Skill level is between 1 and 5
   * (1 = lowest skill level, 5 = highest skill level)
   *
   * @return the player's skill level
   */
  int getSkillLevel();

  /**
   * Override toString method to print player information
   *
   * @return player information (first name, last name, DOB, jersey number, assigned position)
   */
  @Override String toString();
}
