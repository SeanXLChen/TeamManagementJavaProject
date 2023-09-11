package bcsoccer;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the Player interface.
 */
public class TestGetDateOfBirth {
  Player p1;
  Player p2;

  @Before public void setUp() {
    p1 = new PlayerImpl("John", "Doe", 2013, 1, 1, Position.DEFENDERS, 3);
    p2 = new PlayerImpl("Jane", "Doe", 2013, 1, 1, Position.DEFENDERS, 3);
  }

  /**
   * Test the constructor will throw IllegalArgumentException when input year is invalid.
   */
  @Test
      (expected = IllegalArgumentException.class) public void testPlayerInvalidBirthYear() {
    new PlayerImpl("John", "Doe", 2024, 1, 1, Position.DEFENDERS, 3);
  }

  /**
   * Test the constructor will throw IllegalArgumentException when input month is invalid.
   */
  @Test
      (expected = IllegalArgumentException.class) public void testPlayerInvalidBirthMonth() {
    new PlayerImpl("John", "Doe", 2013, 13, 1, Position.DEFENDERS, 3);
  }

  /**
   * Test the constructor will throw IllegalArgumentException when input day is invalid.
   */
  @Test
      (expected = IllegalArgumentException.class) public void testPlayerInvalidBirthDay() {
    new PlayerImpl("John", "Doe", 2013, 1, 32, Position.DEFENDERS, 3);
  }

  /**
   * Test the constructor will throw IllegalArgumentException when input skill level is above
   * allowed range.
   */
  @Test
      (expected = IllegalArgumentException.class) public void testPlayerInvalidSkillLevel() {
    new PlayerImpl("John", "Doe", 2013, 1, 1, Position.DEFENDERS, 100);
  }

  /**
   * Test the constructor will throw IllegalArgumentException when input firstname is blank.
   */
  @Test
      (expected = IllegalArgumentException.class) public void testPlayerInvalidFirstname() {
    new PlayerImpl("", "Doe", 2013, 1, 1, Position.DEFENDERS, 5);
  }

  /**
   * Test the constructor will throw IllegalArgumentException when input lastname is blank.
   */
  @Test
      (expected = IllegalArgumentException.class) public void testPlayerInvalidLastname() {
    new PlayerImpl("John", "   ", 2013, 1, 1, Position.DEFENDERS, 5);
  }

  /**
   * Test the constructor will throw IllegalArgumentException when input skill level is below
   * allowed range.
   */
  @Test
      (expected = IllegalArgumentException.class) public void testPlayerInvalidSkillLevel2() {
    new PlayerImpl("John", "Doe", 2013, 1, 1, Position.DEFENDERS, -1);
  }

  /**
   * Test the constructor will throw IllegalArgumentException when input preferred position is
   * invalid.
   */
  @Test
      (expected = IllegalArgumentException.class) public void testPlayerInvalidPreferredPosition() {
    new PlayerImpl("John", "Doe", 2013, 1, 1, Position.BENCH, 3);
  }

  /**
   * Test the constructor when input is valid.
   */
  @Test public void testPlayerValid() {
    new PlayerImpl("John", "Doe", 2013, 1, 1, Position.DEFENDERS, 3);
  }

  /**
   * Test the getFirstName method.
   */
  @Test public void testGetFirstName() {
    assertEquals("John", p1.getFirstName());
  }

  /**
   * Test the getLastName method.
   */
  @Test public void testGetLastName() {
    assertEquals("Doe", p1.getLastName());
  }

  /**
   * Test the getDOB method.
   */
  @Test public void testGetDateOfBirth() {
    assertEquals("2013-1-1", p1.getdob());
  }

  /**
   * Test the getAge method.
   */
  @Test public void testGetAge() {
    assertEquals(10, p1.getAge());
  }

  /**
   * Test the getSkillLevel method.
   */
  @Test public void testGetSkill() {
    assertEquals(3, p1.getSkillLevel());
  }

  /**
   * Test the getPreferredPosition method.
   */
  @Test public void testGetPreferredPosition() {
    assertEquals(Position.DEFENDERS, p1.getPreferredPosition());
  }

  /**
   * Test the getJerseyNumber method.
   */
  @Test public void testGetJerseyNumber() {
    assertEquals(0, p1.getJerseyNumber());
    p1.setJerseyNumber(3);
    assertEquals(3, p1.getJerseyNumber());
  }

  /**
   * Test the setJerseyNumber method.
   */
  @Test public void testSetJerseyNumber() {
    p2.setJerseyNumber(19);
    assertEquals(19, p2.getJerseyNumber());
  }

  /**
   * Test the getAssignedPosition method.
   */
  @Test public void testGetPosition() {
    assertEquals(Position.BENCH, p1.getAssignedPosition());
  }

  /**
   * Test the setAssignedPosition method.
   */
  @Test public void testSetPosition() {
    assertEquals(Position.BENCH, p2.getAssignedPosition());
    p2.setAssignedPosition(Position.FORWARD);
    assertEquals(Position.FORWARD, p2.getAssignedPosition());
  }

  /**
   * Test the toString method.
   */
  @Test public void testToString() {
    assertEquals("John Doe, 2013-1-1, Jersey Number: 0 Position: BENCH\n", p1.toString());
  }
}
