package u10;

import java.util.Calendar;

/**
 * This class implements Player interface. 
 * It represents a player in a game of soccer.
 * It will be used by the Team class to create a team of players.
 */
public class PlayerImpl implements Player {

  // attributes
  private final String firstName;
  private final String lastName;
  private final int yearOfDOB;
  private final int monthOfDOB;
  private final int dayOfDOB;
  private final Position preferredPosition;
  private Position assignedPosition;
  private final int skillLevel;
  private int jerseyNumber;

  /**
   * Constructor for PlayerImpl
   *
   * @param firstName         first name of the player
   * @param lastName          last name of the player
   * @param yearOfDOB         year of birth of the player
   * @param monthOfDOB        month of birth of the player
   * @param dayOfDOB          day of birth of the player
   * @param preferredPosition preferred position of the player
   * @param skillLevel        skill level of the player
   * @throws IllegalArgumentException if any of the following conditions are true: skill level is
   *                                  not between 1 and 5 year of birth is not between 1900 and 2023
   *                                  month of birth is not between 1 and 12 day of birth is not
   *                                  between 1 and 31 preferred position is not one of the 4
   *                                  positions
   */
  public PlayerImpl(String firstName, String lastName, int yearOfDOB, int monthOfDOB, int dayOfDOB,
      Position preferredPosition, int skillLevel) throws IllegalArgumentException {
    // skill level must be between 1 and 5
    if (skillLevel < 1 || skillLevel > 5) {
      throw new IllegalArgumentException("Skill level must be between 1 and 5.");
    }
    // year of birth must be between 1900 and 2023
    if (yearOfDOB < 1900 || yearOfDOB > 2023) {
      throw new IllegalArgumentException("Year of birth must be between 1900 and 2023.");
    }
    // month of birth must be between 1 and 12
    if (monthOfDOB < 1 || monthOfDOB > 12) {
      throw new IllegalArgumentException("Month of birth must be between 1 and 12.");
    }
    // day of birth must be between 1 and 31
    if (dayOfDOB < 1 || dayOfDOB > 31) {
      throw new IllegalArgumentException("Day of birth must be between 1 and 31.");
    }
    // preferred position must be one of the 4 positions
    if (preferredPosition != Position.GOALIE && preferredPosition != Position.DEFENDERS
        && preferredPosition != Position.MIDFIELDERS && preferredPosition != Position.FORWARD) {
      throw new IllegalArgumentException("Preferred position must be one of the 4 positions.");
    }
    this.firstName = firstName;
    this.lastName = lastName;
    this.yearOfDOB = yearOfDOB;
    this.monthOfDOB = monthOfDOB;
    this.dayOfDOB = dayOfDOB;
    this.preferredPosition = preferredPosition;
    this.assignedPosition = Position.BENCH; // initialize assigned position to BENCH
    this.skillLevel = skillLevel;
    this.jerseyNumber = 0; // initialize jersey number to 0
  }

  @Override public String getFirstName() {
    return this.firstName;
  }

  @Override public String getLastName() {
    return this.lastName;
  }

  @Override public String getDOB() {
    return this.yearOfDOB + "-" + this.monthOfDOB + "-" + this.dayOfDOB;
  }

  @Override public int getAge() {
    Calendar now = Calendar.getInstance();  // use Calendar class to get current year, month, and day
    int currentYear = now.get(Calendar.YEAR);
    int currentMonth = now.get(Calendar.MONTH) + 1; // add 1 because Calendar.MONTH is 0-indexed
    int currentDay = now.get(Calendar.DAY_OF_MONTH);

    int age = currentYear - this.yearOfDOB;

    if (currentMonth
        < this.monthOfDOB) { // if current month is less than month of DOB, decrement age
      age--;
    } else if (currentMonth
        == this.monthOfDOB) { // if current month is equal to month of DOB, check day
      if (currentDay < this.dayOfDOB) { // if current day is less than day of DOB, decrement age
        age--;
      }
    }

    return age;
  }

  @Override public Position getPreferredPosition() {
    return this.preferredPosition;
  }

  @Override public Position getAssignedPosition() {
    return this.assignedPosition;
  }

  @Override public void setAssignedPosition(Position position) {
    this.assignedPosition = position;
  }

  @Override public int getJerseyNumber() {
    return this.jerseyNumber;
  }

  @Override public void setJerseyNumber(int jerseyNumber) throws IllegalArgumentException {
    if (jerseyNumber < 1 || jerseyNumber > 20) {
      throw new IllegalArgumentException("Jersey number must be between 1-20");
    }
    this.jerseyNumber = jerseyNumber;
  }

  @Override public int getSkillLevel() {
    return this.skillLevel;
  }

  @Override public String toString() {
    return this.firstName + " " + this.lastName + ", " + this.getDOB() + ", Jersey Number: "
        + this.jerseyNumber + " Position: " + this.assignedPosition + "\n";
  }
}