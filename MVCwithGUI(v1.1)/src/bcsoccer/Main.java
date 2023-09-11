package bcsoccer;

/**
 * Run a Soccer Team Manager System GUI.
 */
public class Main {
  /**
   * Run a TicTacToe game interactively on the console.
   *
   * @param args not used
   */

  //  public static void main(String[] args) {
  //   new TicTacToeController(new InputStreamReader(System.in),
  //       System.out).playGame(new TicTacToeModel());
  // }
  public static void main(String[] args) {
    Team m = new TeamImpl();               // Model
    InterfaceView v = new View();    // View
    InterfaceController c = new Controller(v, m); // Controller
    c.go();
  }
}