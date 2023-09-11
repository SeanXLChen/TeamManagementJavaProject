package bcsoccer;

import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 * View interface that defines the methods that the view must implement. This interface is used by
 * the controller to update the view.
 */
public interface InterfaceView {
  /**
   * Displays the view.
   */
  public void display();

  /**
   * Adds the given listener to the view's list of listeners.
   *
   * @param listener the listener to add
   */
  void addActionListener(ActionListener listener);

  /**
   * Gets the user's input (Firstname) from the view.
   *
   * @return Player's first name.
   */
  String getUserInputFirstName();

  /**
   * Gets the user's input (Lastname) from the view.
   *
   * @return Player's last name
   */
  String getUserInputLastName();

  /**
   * Gets the user's input (Year) from the view.
   *
   * @return Player's DOB(year)
   */
  int getUserInputYear();

  /**
   * Gets the user's input (Month) from the view.
   *
   * @return Player's DOB(month)
   */
  int getUserInputMonth();

  /**
   * Gets the user's input (Day) from the view.
   *
   * @return Player's DOB(day)
   */
  int getUserInputDay();

  /**
   * Gets the user's input (Position) from the view.
   *
   * @return Player's preferred position in String format
   */
  String getUserInputPosition();

  /**
   * Gets the user's input (Skill level) from the view.
   *
   * @return Player's skill level
   */
  int getUserInputSkillLevel();

  /**
   * Updates the number of players in the team.
   *
   * @param count the number of players in the team
   */
  void updateAddPlayerCount(int count);

  /**
   * Displays the given error message.
   *
   * @param message the error message
   */
  void displayErrorMessage(String message);

  /**
   * gets the add button from the view.
   *
   * @return the add button
   */
  JButton getAddButton();

  /**
   * gets the remove button from the view.
   *
   * @return the remove button
   */
  JButton getRemoveButton();

  /**
   * Updates the All Player List display in view.
   *
   * @param playersArray the array of All players info
   */
  void updatePlayersList(String[] playersArray);

  /**
   * Updates the Starting Lineup display in view.
   *
   * @param lineupArray the array of Starting Lineup info
   */
  void updateStartingLineupList(String[] lineupArray);

  /**
   * Updates the Remove Player ComboBox display in view.
   *
   * @param jerseyNumbers the list of jersey numbers
   */
  void updateRemovePlayerComboBox(List<String> jerseyNumbers);

  /**
   * Gets the user selected jersey number from the view.
   *
   * @return the user selected jersey number
   */
  int getSelectedJerseyNumber();

  /**
   * Gets the Remove Player ComboBox from the view.
   *
   * @return the Remove Player ComboBox
   */
  JComboBox<String> getRemovePlayerComboBox();
}