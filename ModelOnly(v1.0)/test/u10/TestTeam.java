package u10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.beans.Transient;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the Team interface.
 */
public class TestTeam {
  Team firstTeam;
  Team secondTeam;

  /**
   * Set up two teams for testing.
   */
  @Before public void setUp() {
    firstTeam = new TeamImpl();
    firstTeam.addPlayer("S", "Chen", 2015, 8, 26, Position.GOALIE, 5);
    firstTeam.addPlayer("B", "Bob", 2015, 8, 26, Position.GOALIE, 4);
    firstTeam.addPlayer("J", "Smith", 2015, 8, 26, Position.MIDFIELDERS, 3);
    firstTeam.addPlayer("A", "Ana", 2015, 8, 26, Position.FORWARD, 2);
    firstTeam.addPlayer("C", "Don", 2015, 8, 26, Position.GOALIE, 4);
    firstTeam.addPlayer("D", "Ethan", 2015, 8, 26, Position.GOALIE, 3);
    firstTeam.addPlayer("E", "Frank", 2015, 8, 26, Position.DEFENDERS, 4);
    firstTeam.addPlayer("C", "Gothic", 2015, 8, 26, Position.MIDFIELDERS, 3);
    firstTeam.addPlayer("A", "Hugh", 2015, 8, 26, Position.FORWARD, 5);
    firstTeam.addPlayer("G", "Ian", 2015, 8, 26, Position.FORWARD, 1);
    firstTeam.addPlayer("H", "Jen", 2015, 8, 26, Position.GOALIE, 2);
    firstTeam.addPlayer("I", "Kathy", 2015, 8, 26, Position.DEFENDERS, 2);

    secondTeam = new TeamImpl();
    secondTeam.addPlayer("S", "Chen", 2015, 8, 26, Position.GOALIE, 5);
    secondTeam.addPlayer("B", "Bob", 2015, 8, 26, Position.GOALIE, 3);
  }

  /**
   * Test the constructor.
   */
  @Test public void testConstructor() {
    Team newTeam = new TeamImpl();
    assertEquals(0, newTeam.getTeamSize());
  }

  /**
   * Test the addPlayer method.
   */
  @Test public void testAddPlayer() {
    assertEquals(12, firstTeam.getTeamSize());
    assertEquals(2, secondTeam.getTeamSize());
  }

  /**
   * Test the addPlayer method with invalid age input.
   */
  @Test(expected = IllegalArgumentException.class) public void testAddPlayerOverAgeLimit() {
    firstTeam.addPlayer("S", "Chen", 2012, 8, 1, Position.GOALIE, 5);
  }

  /**
   * Test the addPlayer method with invalid year input. all inputs error are already tested inside
   * TestPlayer class
   */
  @Test(expected = IllegalArgumentException.class) public void testAddPlayerYearTooLow() {
    firstTeam.addPlayer("S", "Chen", 1000, 8, 26, Position.GOALIE, 5);
  }

  /**
   * Test the removePlayer method.
   */
  @Test public void testRemovePlayer() {
    firstTeam.generateStartingLineup();
    Player sean = firstTeam.getPlayerByJerseyNumber(14);
    assertEquals("Chen", sean.getLastName());
    assertEquals(14, sean.getJerseyNumber());
    firstTeam.removePlayer(sean);
    assertEquals(11, firstTeam.getTeamSize());
    assertEquals(null, firstTeam.getPlayerByJerseyNumber(14));
  }

  /**
   * Test the generateStartingLineup method.
   */
  @Test public void testGenerateStartingLineup() {
    firstTeam.generateStartingLineup();

    assertEquals("S Chen, 2015-8-26, Jersey Number: 14 Position: GOALIE\n"
            + "E Frank, 2015-8-26, Jersey Number: 1 Position: DEFENDERS\n"
            + "I Kathy, 2015-8-26, Jersey Number: 7 Position: DEFENDERS\n"
            + "B Bob, 2015-8-26, Jersey Number: 18 Position: MIDFIELDERS\n"
            + "J Smith, 2015-8-26, Jersey Number: 5 Position: MIDFIELDERS\n"
            + "C Gothic, 2015-8-26, Jersey Number: 6 Position: MIDFIELDERS\n"
            + "A Hugh, 2015-8-26, Jersey Number: 11 Position: FORWARD\n",
        firstTeam.getStartingLineupList());

    assertEquals(12, firstTeam.getTeamSize());

    assertEquals("A Ana, 2015-8-26, Jersey Number: 15 Position: BENCH\n"
            + "B Bob, 2015-8-26, Jersey Number: 18 Position: MIDFIELDERS\n"
            + "S Chen, 2015-8-26, Jersey Number: 14 Position: GOALIE\n"
            + "C Don, 2015-8-26, Jersey Number: 10 Position: BENCH\n"
            + "D Ethan, 2015-8-26, Jersey Number: 13 Position: BENCH\n"
            + "E Frank, 2015-8-26, Jersey Number: 1 Position: DEFENDERS\n"
            + "C Gothic, 2015-8-26, Jersey Number: 6 Position: MIDFIELDERS\n"
            + "A Hugh, 2015-8-26, Jersey Number: 11 Position: FORWARD\n"
            + "G Ian, 2015-8-26, Jersey Number: 16 Position: BENCH\n"
            + "H Jen, 2015-8-26, Jersey Number: 19 Position: BENCH\n"
            + "I Kathy, 2015-8-26, Jersey Number: 7 Position: DEFENDERS\n"
            + "J Smith, 2015-8-26, Jersey Number: 5 Position: MIDFIELDERS\n",
        firstTeam.getAllPlayersList());
  }

  /**
   * Test the getAllPlayersList method output before generateStartingLineup is called.
   */
  @Test public void testGetAllPlayersListBeforeGenerate() {
    assertEquals("A Ana, 2015-8-26, Jersey Number: 0 Position: BENCH\n"
        + "B Bob, 2015-8-26, Jersey Number: 0 Position: BENCH\n"
        + "S Chen, 2015-8-26, Jersey Number: 0 Position: BENCH\n"
        + "C Don, 2015-8-26, Jersey Number: 0 Position: BENCH\n"
        + "D Ethan, 2015-8-26, Jersey Number: 0 Position: BENCH\n"
        + "E Frank, 2015-8-26, Jersey Number: 0 Position: BENCH\n"
        + "C Gothic, 2015-8-26, Jersey Number: 0 Position: BENCH\n"
        + "A Hugh, 2015-8-26, Jersey Number: 0 Position: BENCH\n"
        + "G Ian, 2015-8-26, Jersey Number: 0 Position: BENCH\n"
        + "H Jen, 2015-8-26, Jersey Number: 0 Position: BENCH\n"
        + "I Kathy, 2015-8-26, Jersey Number: 0 Position: BENCH\n"
        + "J Smith, 2015-8-26, Jersey Number: 0 Position: BENCH\n", firstTeam.getAllPlayersList());
  }

  /**
   * Test the getAllPlayersList method output after generateStartingLineup is called.
   */
  @Test public void testGetAllPlayersListAfterGenerate() {
    firstTeam.generateStartingLineup();
    assertEquals("A Ana, 2015-8-26, Jersey Number: 15 Position: BENCH\n"
            + "B Bob, 2015-8-26, Jersey Number: 18 Position: MIDFIELDERS\n"
            + "S Chen, 2015-8-26, Jersey Number: 14 Position: GOALIE\n"
            + "C Don, 2015-8-26, Jersey Number: 10 Position: BENCH\n"
            + "D Ethan, 2015-8-26, Jersey Number: 13 Position: BENCH\n"
            + "E Frank, 2015-8-26, Jersey Number: 1 Position: DEFENDERS\n"
            + "C Gothic, 2015-8-26, Jersey Number: 6 Position: MIDFIELDERS\n"
            + "A Hugh, 2015-8-26, Jersey Number: 11 Position: FORWARD\n"
            + "G Ian, 2015-8-26, Jersey Number: 16 Position: BENCH\n"
            + "H Jen, 2015-8-26, Jersey Number: 19 Position: BENCH\n"
            + "I Kathy, 2015-8-26, Jersey Number: 7 Position: DEFENDERS\n"
            + "J Smith, 2015-8-26, Jersey Number: 5 Position: MIDFIELDERS\n",
        firstTeam.getAllPlayersList());
  }

  /**
   * Test the getStartingLineupList method output before generateStartingLineup is called.
   */
  @Test public void testGetStartingLineupListBeforeGenerate() {
    assertEquals("", firstTeam.getStartingLineupList());
  }

  /**
   * Test the getStartingLineupList method output after generateStartingLineup is called.
   */
  @Test public void testGetStartingLineupListAfterGenerate() {
    firstTeam.generateStartingLineup();
    assertEquals("S Chen, 2015-8-26, Jersey Number: 14 Position: GOALIE\n"
            + "E Frank, 2015-8-26, Jersey Number: 1 Position: DEFENDERS\n"
            + "I Kathy, 2015-8-26, Jersey Number: 7 Position: DEFENDERS\n"
            + "B Bob, 2015-8-26, Jersey Number: 18 Position: MIDFIELDERS\n"
            + "J Smith, 2015-8-26, Jersey Number: 5 Position: MIDFIELDERS\n"
            + "C Gothic, 2015-8-26, Jersey Number: 6 Position: MIDFIELDERS\n"
            + "A Hugh, 2015-8-26, Jersey Number: 11 Position: FORWARD\n",
        firstTeam.getStartingLineupList());
  }

  /**
   * Test the generateStartingLineup method when there are not enough players.
   *
   * @throws IllegalStateException when there are not enough players
   */
  @Test(expected = IllegalStateException.class) public void testGenerateStartingLineupNotEnoughPlayers() {
    secondTeam.generateStartingLineup();
  }

  /**
   * Test the getPlayerByJerseyNumber method.
   */
  @Test public void testGetPlayerByJerseyNumber() {
    firstTeam.generateStartingLineup();
    assertEquals("Chen", firstTeam.getPlayerByJerseyNumber(14).getLastName());
    assertEquals("Bob", firstTeam.getPlayerByJerseyNumber(18).getLastName());
    assertEquals("Smith", firstTeam.getPlayerByJerseyNumber(5).getLastName());
    assertEquals("Ana", firstTeam.getPlayerByJerseyNumber(15).getLastName());
    assertEquals("Don", firstTeam.getPlayerByJerseyNumber(10).getLastName());
    assertEquals("Ethan", firstTeam.getPlayerByJerseyNumber(13).getLastName());
    assertEquals("Frank", firstTeam.getPlayerByJerseyNumber(1).getLastName());
    assertEquals("Gothic", firstTeam.getPlayerByJerseyNumber(6).getLastName());
    assertEquals("Hugh", firstTeam.getPlayerByJerseyNumber(11).getLastName());
    assertEquals("Ian", firstTeam.getPlayerByJerseyNumber(16).getLastName());
    assertEquals("Jen", firstTeam.getPlayerByJerseyNumber(19).getLastName());
    assertEquals("Kathy", firstTeam.getPlayerByJerseyNumber(7).getLastName());
  }

  /**
   * Test the testValidateTeam method.
   */
  @Test public void testValidateTeam() {
    assertTrue(firstTeam.validateTeam());
    assertFalse(secondTeam.validateTeam());
  }

}
