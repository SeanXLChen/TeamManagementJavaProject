package bcsoccer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;

/**
 * Controller class that implements the InterfaceController interface. This class is responsible for
 * handling the user's input and updating the model and view accordingly.
 */
public class Controller implements InterfaceController, ActionListener {
  private InterfaceView view;
  private Team model;

  /**
   * Constructor for the Controller class.
   *
   * @param view  the view object
   * @param model the model object
   */
  public Controller(InterfaceView view, Team model) {
    this.view = view;
    this.model = model;
    view.addActionListener(this); /* controller will listen to the view (callback) */
  }

  @Override public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();

    if (source == view.getAddButton()) {
      handleAddPlayer();
    } else if (source == view.getRemoveButton()) {
      handleRemovePlayer();
    }
  }

  /**
   * Handles the add player button event.
   *  - Get the player's information (from view)
   *  - Add the player to the team (model)
   *  - Update the JLists (view)
   */
  private void handleAddPlayer() {
    // Get the player's information from the view
    String firstName = view.getUserInputFirstName();
    String lastName = view.getUserInputLastName();
    int year = view.getUserInputYear();
    int month = view.getUserInputMonth();
    int day = view.getUserInputDay();
    String preferredPosition = view.getUserInputPosition();
    int skillLevel = view.getUserInputSkillLevel();

    try {
      // Add the player to the team
      model.addPlayer(firstName, lastName, year, month, day, preferredPosition, skillLevel);
      //
    } catch (IllegalArgumentException ex) {
      // Display error message
      view.displayErrorMessage(ex.getMessage());
    }

    // After adding a player, check if there are 10 players
    if (model.getTeamSize() >= 10) {
      model.generateStartingLineup();
      updateStartingLineup();
      updateDropdown();
    }

    // update all player list
    updateAllPlayerList();
    // update player count
    view.updateAddPlayerCount(model.getTeamSize());
  }

  /**
   * Handles the remove player button event.
   *  - Get the player's jersey number (from view)
   *  - Remove the player from the team (model)
   *  - Update the JLists (view)
   */
  private void handleRemovePlayer() {
    // Check if default item is selected
    JComboBox<String> removePlayerComboBox = view.getRemovePlayerComboBox();
    if (removePlayerComboBox.getSelectedIndex() == 0) {
      view.displayErrorMessage("Please select a valid jersey number to remove.");
      return;
    }

    // Check if removing player will make team size less than 10
    if (model.getTeamSize() <= 10) {
      view.displayErrorMessage("Cannot remove player. Team size must be at least 10.");
      return;
    }

    // if able to remove player
    int jerseyNumberSelected = view.getSelectedJerseyNumber();
    model.removePlayer(model.getPlayerByJerseyNumber(jerseyNumberSelected));

    // update latest dropdown available to be remove
    List<String> jerseyNumbers = model.getAllJerseyNumbersAsString();
    view.updateRemovePlayerComboBox(jerseyNumbers);
    model.generateStartingLineup();
    updateDropdown();
    updateStartingLineup();
    updateAllPlayerList();
    view.updateAddPlayerCount(model.getTeamSize());
  }

  /**
   * Updates the remove player dropdown (comboBox) in View.
   */
  private void updateDropdown() {
    // update latest dropdown available to be remove
    List<String> jerseyNumbers = model.getAllJerseyNumbersAsString();
    view.updateRemovePlayerComboBox(jerseyNumbers);
  }

  /**
   * Updates the starting lineup JList.
   */
  private void updateStartingLineup() {
    String[] lineupArray = model.getStartingLineupList().split("\n");
    view.updateStartingLineupList(lineupArray);
  }

  /**
   * Updates the all players JList.
   */
  private void updateAllPlayerList() {
    // Update the JLists
    String[] playersArray = model.getAllPlayersList().split("\n");
    view.updatePlayersList(playersArray);
  }

  @Override public void go() {
    view.display();
  }
}
