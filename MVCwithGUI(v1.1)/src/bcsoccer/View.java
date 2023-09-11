package bcsoccer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;


/**
 * View class that implements the InterfaceView interface.
 * This class is responsible for displaying the user interface and handling the user's input.
 * View class uses Java Swing to create the user GUI.
 */
public class View extends JFrame implements InterfaceView {
  private final JButton addButton;  // Declare addButton as a member variable
  private final JComboBox<String> removePlayerComboBox;
  private final JButton removeButton;  // Declare removeButton as a member variable

  private final JLabel addPlayerHeader;

  private final JTextField firstNameField;
  private final JTextField lastNameField;
  private final JComboBox<Integer> yearComboBox;
  private final JComboBox<Integer> monthComboBox;
  private final JComboBox<Integer> dayComboBox;
  private final JComboBox<String> positionComboBox;
  private final JComboBox<Integer> skillLevelSlider;

  private final JList<String> playersList;
  private final DefaultListModel<String> playersListModel;

  private final JList<String> startingLineupList;
  private final DefaultListModel<String> startingLineupListModel;

  /**
   * Constructor for the View class.
   */
  public View() {
    setTitle("BC Soccer Team Builder System");
    setSize(800, 900);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // Top Panel
    JPanel topPanel = new JPanel(new GridLayout(3, 1));
    JLabel welcomeLabel = new JLabel("Welcome to the BC soccer team builder system!",
        SwingConstants.CENTER);
    welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
    topPanel.add(welcomeLabel);

    JLabel introLabel = new JLabel(
        "<html>&nbsp;&nbsp;<b>Introduction:</b> Enter players info to start add player "
            + "and generate starting line up. \n "
            + "You need at least 10 players to build a team!</html>",
        SwingConstants.LEFT);
    topPanel.add(introLabel);

    JPanel playersInfoPanel = new JPanel(new GridLayout(3, 1));
    playersInfoPanel.add(new JLabel(
        "<html>&nbsp;&nbsp;<b>To add player:</b> type player info in Add Player (left) pane "
            + "and click add.</html>"));
    playersInfoPanel.add(new JLabel(
        "<html>&nbsp;&nbsp;<b>To remove player:</b> select from dropdown in "
            + "Remove Player (right) pane and click remove.</html>"));
    playersInfoPanel.add(new JLabel("   "));
    topPanel.add(playersInfoPanel);

    add(topPanel, BorderLayout.NORTH);

    // Input Panel
    JPanel inputPanel = new JPanel(new GridLayout(1, 2)); // Split into left and right

    // Left Panel: Add Player
    JPanel addPlayerPanel = new JPanel();
    addPlayerPanel.setLayout(new BoxLayout(addPlayerPanel, BoxLayout.Y_AXIS));
    inputPanel.add(addPlayerPanel);

    // Header for Add Player Panel
    addPlayerHeader = new JLabel("Players added: 0");
    //    addPlayerHeader.setFont(new Font("Arial", Font.BOLD, 14));
    addPlayerPanel.add(addPlayerHeader);

    // First Name
    JPanel firstNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    firstNamePanel.add(new JLabel("First Name:"));
    firstNameField = new JTextField(15); // 15 columns wide
    firstNameField.setMaximumSize(firstNameField.getPreferredSize()); // Limit the maximum width
    firstNamePanel.add(firstNameField);
    addPlayerPanel.add(firstNamePanel);

    // Add a small vertical gap
    addPlayerPanel.add(Box.createVerticalStrut(5));  // Adjust the number 5 to your preference

    // Last Name
    JPanel lastNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    lastNamePanel.add(new JLabel("Last Name:"));
    lastNameField = new JTextField(15); // 15 columns wide
    lastNameField.setMaximumSize(lastNameField.getPreferredSize()); // Limit the maximum width
    lastNamePanel.add(lastNameField);
    addPlayerPanel.add(lastNamePanel);

    // Date of Birth
    JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    dobPanel.add(new JLabel("Date of Birth:"));

    // ComboBox for Year
    yearComboBox = new JComboBox<>();
    for (int i = 2000; i <= 2022; i++) {  // 你可以根据需要调整年份范围
      yearComboBox.addItem(i);
    }
    dobPanel.add(yearComboBox);

    // ComboBox for Month
    monthComboBox = new JComboBox<>();
    for (int i = 1; i <= 12; i++) {
      monthComboBox.addItem(i);
    }
    dobPanel.add(monthComboBox);

    // ComboBox for Day
    dayComboBox = new JComboBox<>();
    for (int i = 1; i <= 31; i++) {
      dayComboBox.addItem(i);
    }
    dobPanel.add(dayComboBox);

    addPlayerPanel.add(dobPanel);

    // Preferred Position
    JPanel positionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    positionPanel.add(new JLabel("Preferred Position:"));

    positionComboBox = new JComboBox<>(
        new String[] { "Goalie", "Defenders", "Forward", "Midfielders" });
    positionPanel.add(positionComboBox);

    addPlayerPanel.add(positionPanel);

    // Skill Level
    JPanel skillLevelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    skillLevelPanel.add(new JLabel("Skill Level:"));
    skillLevelSlider = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5 });
    skillLevelPanel.add(skillLevelSlider);
    JLabel yourLabel = new JLabel("1 is lowest, 5 is highest");
    yourLabel.setForeground(Color.GRAY);  // This will change the text color to red
    skillLevelPanel.add(yourLabel);

    addPlayerPanel.add(skillLevelPanel);

    addButton = new JButton("Add");
    addPlayerPanel.add(addButton);

    // Set border with larger title for Add Player Panel
    TitledBorder addPlayerBorder = BorderFactory.createTitledBorder(
        BorderFactory.createEtchedBorder(), "Add Player");
    addPlayerBorder.setTitleFont(
        new Font("Arial", Font.BOLD, 16)); // Set the font size to 16, you can adjust as needed
    addPlayerPanel.setBorder(addPlayerBorder);

    inputPanel.add(addPlayerPanel);

    // Right Panel: Remove Player
    JPanel removePlayerPanel = new JPanel();
    removePlayerPanel.setLayout(new BoxLayout(removePlayerPanel, BoxLayout.Y_AXIS));

    //    removePlayerPanel.add(new JLabel("Select Player to Remove:"));
    removePlayerComboBox = new JComboBox<>(new String[] {
        "Remove Player by Jersey Number" }); // You'll populate this with player names
    removePlayerPanel.add(removePlayerComboBox);

    removeButton = new JButton("Remove");
    removePlayerPanel.add(removeButton);

    // Set border with larger title for Remove Player Panel
    TitledBorder removePlayerBorder = BorderFactory.createTitledBorder(
        BorderFactory.createEtchedBorder(), "Remove Player");
    removePlayerBorder.setTitleFont(
        new Font("Arial", Font.BOLD, 16)); // Set the font size to 16, you can adjust as needed
    removePlayerPanel.setBorder(removePlayerBorder);

    inputPanel.add(removePlayerPanel);

    add(inputPanel, BorderLayout.CENTER);

    // Players List Panel
    JPanel playersListPanel = new JPanel(new GridLayout(0, 1)); // 0 rows means any number of rows
    add(playersListPanel, BorderLayout.SOUTH);

    // 创建一个新的JPanel来容纳输入面板
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

    // 将输入面板添加到新的JPanel中
    centerPanel.add(inputPanel);

    // Set an initial preferred size for both JLists:
    Dimension initialSizeAll = new Dimension(400, 400);

    // All Players
    playersListModel = new DefaultListModel<>();
    playersList = new JList<>(playersListModel);
    playersList.setPreferredSize(initialSizeAll);
    JScrollPane playersListScrollPane = new JScrollPane(playersList);
    TitledBorder allPlayersTitle = BorderFactory.createTitledBorder("All Players");
    allPlayersTitle.setTitleFont(new Font("Arial", Font.BOLD, 14));
    playersList.setBorder(allPlayersTitle);

    centerPanel.add(playersListScrollPane);
    // Starting Lineup
    startingLineupListModel = new DefaultListModel<>();
    startingLineupList = new JList<>(startingLineupListModel);
    TitledBorder startingLineupTitle = BorderFactory.createTitledBorder("Starting Lineup");
    startingLineupTitle.setTitleFont(new Font("Arial", Font.BOLD, 14));
    startingLineupList.setBorder(startingLineupTitle);
    Dimension initialSizeL = new Dimension(400, 200);
    startingLineupList.setPreferredSize(initialSizeL);
    JScrollPane startingLineupScrollPane = new JScrollPane(startingLineupList);
    centerPanel.add(startingLineupScrollPane);

    // 将新的JPanel添加到BorderLayout.CENTER区域
    add(centerPanel, BorderLayout.CENTER);

  }

  @Override public void display() {
    this.setVisible(true);
  }

  @Override public void addActionListener(ActionListener listener) {
    addButton.addActionListener(listener);
    removeButton.addActionListener(listener);
  }

  @Override public String getUserInputFirstName() {
    return firstNameField.getText();
  }

  @Override public String getUserInputLastName() {
    return lastNameField.getText();
  }

  @Override public int getUserInputYear() {
    return (Integer) yearComboBox.getSelectedItem();
  }

  @Override public int getUserInputMonth() {
    return (Integer) monthComboBox.getSelectedItem();
  }

  @Override public int getUserInputDay() {
    return (Integer) dayComboBox.getSelectedItem();
  }

  @Override public String getUserInputPosition() {
    return (String) positionComboBox.getSelectedItem();
  }

  @Override public int getUserInputSkillLevel() {
    return (Integer) skillLevelSlider.getSelectedItem();
  }

  @Override public void displayErrorMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  @Override public JButton getAddButton() {
    return addButton;
  }

  @Override public JButton getRemoveButton() {
    return removeButton;
  }

  @Override public void updateAddPlayerCount(int count) {
    addPlayerHeader.setText("Players added: " + count);
  }

  @Override public void updatePlayersList(String[] playersArray) {
    playersListModel.clear();
    //    playersListModel.addElement("All Player List:");

    for (String eachLine : playersArray) {
      playersListModel.addElement(eachLine);
    }

    // Adjust the visible row count based on the number of items
    playersList.setVisibleRowCount(playersListModel.getSize());

    if (playersList.getParent() instanceof JViewport) {
      JViewport viewport = (JViewport) playersList.getParent();
      if (viewport.getParent() instanceof JScrollPane) {
        JScrollPane scrollPane = (JScrollPane) viewport.getParent();
        scrollPane.revalidate();
        scrollPane.repaint();
      }
    }
  }

  @Override public void updateStartingLineupList(String[] lineupArray) {
    startingLineupListModel.clear();
    //    startingLineupListModel.addElement("Starting Lineup");
    for (String player : lineupArray) {
      startingLineupListModel.addElement(player);
    }
  }

  @Override public void updateRemovePlayerComboBox(List<String> jerseyNumbers) {
    removePlayerComboBox.removeAllItems();
    removePlayerComboBox.addItem("Remove Player by Jersey Number"); // default item
    for (String jerseyNumber : jerseyNumbers) {
      removePlayerComboBox.addItem(jerseyNumber);
    }
  }

  @Override public int getSelectedJerseyNumber() {
    return Integer.parseInt(removePlayerComboBox.getSelectedItem().toString());
  }

  @Override public JComboBox<String> getRemovePlayerComboBox() {
    return removePlayerComboBox;
  }
}