package bcsoccer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class implements Team interface. It represents a team in a game of soccer. It contains a
 * list of Players and a set of jersey numbers. It can be used to create a team of Players.
 */
public class TeamImpl implements Team {

  // Constants for team's position size
  private static final int SIZE_GOALIE = 1;
  private static final int SIZE_DEFENDER = 2;
  private static final int SIZE_MIDFIELDER = 3;
  private static final int SIZE_FORWARD = 1;

  // Constants for team size
  private static final int SIZE_TEAM_MIN = 10;
  private static final int SIZE_TEAM_MAX = 20;

  // Constants for team player's age limit
  private static final int AGE_LIMIT = 10;

  /*
   * attributes
   */
  private final List<Player> playerList;
  private final Set<Integer> jnumberSet;
  private int teamSize;

  /**
   * Constructor for TeamImpl.
   */
  public TeamImpl() {
    this.playerList = new ArrayList<>();
    this.jnumberSet = new TreeSet<>();
    this.teamSize = 0;
  }

  @Override public void addPlayer(String firstName, String lastName, int year, int month, int day,
      String preferredPositionText, int skillLevel) throws IllegalArgumentException {

    Position preferredPosition;
    if ("Goalie".equals(preferredPositionText)) {
      preferredPosition = Position.GOALIE;
    } else if ("Defenders".equals(preferredPositionText)) {
      preferredPosition = Position.DEFENDERS;
    } else if ("Midfielders".equals(preferredPositionText)) {
      preferredPosition = Position.MIDFIELDERS;
    } else if ("Forward".equals(preferredPositionText)) {
      preferredPosition = Position.FORWARD;
    } else {
      throw new IllegalArgumentException("Invalid position.");
    }

    Player newPlayer = new PlayerImpl(firstName, lastName, year, month, day, preferredPosition,
        skillLevel);
    // check if player is under 10
    if (newPlayer.getAge() > AGE_LIMIT) {
      throw new IllegalArgumentException("Player need to be under age limit to be in team.");
    }
    this.playerList.add(newPlayer);
    this.teamSize++;
    // remove a player if exceed size (Currently 20)
    if (teamSize > SIZE_TEAM_MAX) {
      // Sort Players by skill level in ascending order
      playerList.sort(Comparator.comparingInt(Player::getSkillLevel));

      // Remove the player with the lowest skill level
      Player playerToRemove = playerList.get(0);
      this.playerList.remove(playerToRemove);
      jnumberSet.remove(playerToRemove.getJerseyNumber());
      this.teamSize--;
    }
  }

  @Override public void removePlayer(Player player) {
    this.playerList.remove(player);
    jnumberSet.remove(player.getJerseyNumber());
    this.teamSize--;
  }

  @Override public int getTeamSize() {
    return this.teamSize;
  }

  @Override public void generateStartingLineup() throws IllegalStateException {
    // only generate starting lineup if team is valid
    if (!this.validateTeam()) {
      throw new IllegalStateException(
          "Team can only have 10-20 Players. Cannot generate starting lineup.");
    }
    sortPlayersBySkill();
    assignPositions();
    System.out.println("Before assigning Jersey Numbers");
    assignJerseyNumbers();
    System.out.println("After assigning Jersey Numbers");
  }

  @Override public String getAllPlayersList() {
    StringBuilder sb = new StringBuilder();
    // Sort Players by last name
    sortPlayersByLastName();
    for (Player player : playerList) {
      sb.append(player.toString());
    }
    return sb.toString();
  }

  @Override public String getStartingLineupList() {
    // copy Players to new list
    List<Player> startingLineup = new ArrayList<>(playerList);
    // drop Players from bench
    for (Player player : playerList) {
      if (player.getAssignedPosition() == Position.BENCH) {
        startingLineup.remove(player);
      }
    }
    // Sort the starting lineup by position
    startingLineup.sort(Comparator.comparing(Player::getAssignedPosition));
    // set a string builder to build the string
    StringBuilder sb = new StringBuilder();
    for (Player player : startingLineup) {
      sb.append(player.toString());
    }
    return sb.toString();
  }

  @Override public boolean validateTeam() {
    // check if team size is within range
    return this.teamSize >= SIZE_TEAM_MIN && this.teamSize <= SIZE_TEAM_MAX;
  }

  @Override public Player getPlayerByJerseyNumber(int jerseyNumber) {
    for (Player player : playerList) {
      if (player.getJerseyNumber() == jerseyNumber) {
        return player;
      }
    }
    return null;  // return null if no player with the given jersey number is found
  }

  /**
   * sort playerList by player's last name.
   */
  private void sortPlayersByLastName() {
    playerList.sort(Comparator.comparing(Player::getLastName));
  }

  /**
   * sort playerList by player's skill level (highest to lowest).
   */
  private void sortPlayersBySkill() {
    // Sort Players by skill level use comparator
    playerList.sort((p1, p2) -> Integer.compare(p2.getSkillLevel(), p1.getSkillLevel()));
  }

  /**
   * Set each player its assigned Position based on its preferred position and skill level. First
   * assign top skill player to its preferred position when available. Then assign remaining
   * positions (G -> D -> M -> F) based on skill level.
   */
  private void assignPositions() {

    // Initialize available positions
    int availableGoalies = SIZE_GOALIE;
    int availableDefenders = SIZE_DEFENDER;
    int availableMidfielders = SIZE_MIDFIELDER;
    int availableForwards = SIZE_FORWARD;

    // clear each position to BENCH
    for (Player player : playerList) {
      player.setAssignedPosition(Position.BENCH);
    }

    // Assign positions based on preferred positions first
    for (Player player : playerList) {
      if (player.getAssignedPosition()
          == Position.BENCH) { // check if player has not been assigned a position

        // assign top skill player to its preferred position when available
        if (player.getPreferredPosition() == Position.GOALIE && availableGoalies > 0) {
          player.setAssignedPosition(Position.GOALIE);
          availableGoalies--;
        } else if (
            player.getPreferredPosition() == Position.DEFENDERS && availableDefenders > 0) {
          player.setAssignedPosition(Position.DEFENDERS);
          availableDefenders--;
        } else if (player.getPreferredPosition() == Position.MIDFIELDERS
            && availableMidfielders > 0) {
          player.setAssignedPosition(Position.MIDFIELDERS);
          availableMidfielders--;
        } else if (player.getPreferredPosition() == Position.FORWARD && availableForwards > 0) {
          player.setAssignedPosition(Position.FORWARD);
          availableForwards--;
        }
      }
    }

    // Assign remaining positions (G -> D -> M -> F) based on skill level
    for (Player player : playerList) {
      if (player.getAssignedPosition()
          == Position.BENCH) { // check if player has not been assigned a position
        if (availableGoalies > 0) {
          player.setAssignedPosition(Position.GOALIE);
          availableGoalies--;
        } else if (availableDefenders > 0) {
          player.setAssignedPosition(Position.DEFENDERS);
          availableDefenders--;
        } else if (availableMidfielders > 0) {
          player.setAssignedPosition(Position.MIDFIELDERS);
          availableMidfielders--;
        } else if (availableForwards > 0) {
          player.setAssignedPosition(Position.FORWARD);
          availableForwards--;
        }
      }
    }

  }

  /**
   * Assign jersey numbers (1-20) to Players randomly.
   */
  private void assignJerseyNumbers() {
    //    long seed = 111;  // choose a seed value
    jnumberSet.clear();
    Random random = new Random();
    for (Player player : playerList) {
      int jerseyNumber = random.nextInt(20) + 1;
      while (jnumberSet.contains(jerseyNumber)) {
        System.out.println("Trying jersey number: " + jerseyNumber); // Add this line
        jerseyNumber = random.nextInt(20) + 1;
      }
      player.setJerseyNumber(jerseyNumber); // assign jersey number to player
      jnumberSet.add(jerseyNumber); // add jersey number to tracking set
    }
  }

  @Override public List<String> getAllJerseyNumbersAsString() {
    List<String> jerseyNumbers = new ArrayList<>();
    for (Integer number : jnumberSet) {
      jerseyNumbers.add(number.toString());
    }
    return jerseyNumbers;
  }
}