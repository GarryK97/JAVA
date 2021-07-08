package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import game.action.WinGameAction;
import game.action.QuitGameAction;

/**
 * Class representing the Player.
 * @see Actor
 */
public class Player extends Actor {
	private static int ecoPoints = 10000;
	private static int moves;
	private static int ecoPointsNeeded;
	private static boolean playerOfChallengeGame;
	private Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	/**
	 * Shows the menu that Player can currently take
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return returns the menu
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if (playerOfChallengeGame){  // means game mode is Challenge mode
			moves--;
			System.out.println("Moves left: " + moves);
			System.out.println("Current eco point: " + ecoPoints);
			if (winOrLose() != null){
				return winOrLose();
			}
		}
		// Handle multi-turn Actions
		actions.add(new QuitGameAction());
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Adds points to Player
	 * @param point_increase Points to add
	 */
	public static void add_points(int point_increase){
		ecoPoints += point_increase;
	}

	/**
	 * Deduct points from Player
	 * @param points_deduction Points to deduct
	 * @return true if the player has enough points to deduct, else false
	 */
	public static Boolean deduct_points(int points_deduction) {
		if (ecoPoints - points_deduction >= 0){
			ecoPoints -= points_deduction;
			return true;
		}
		return false;
	}

	/**
	 * Sets eco points of player according to argument.
	 * @param ecoPoints eco points to set
	 */
	public static void setEcoPoints(int ecoPoints) {
		Player.ecoPoints = ecoPoints;
	}

	/**
	 * Sets eco points needed by player to win game according to argument.
	 * @param ecoPointsNeeded eco points needed to win game
	 */
	public static void setEcoPointsNeeded(int ecoPointsNeeded) {
		Player.ecoPointsNeeded = ecoPointsNeeded;
	}

	/**
	 * Set number of moves which player can make
	 * @param moves total moves limit
	 */
	public static void setMoves(int moves) {
		Player.moves = moves+1;
	}

	/**
	 * Sets variable playerOfChallengeGame to true of game mode is Challenge, false for Sandbox mode.
	 * @param playerOfChallengeGame  true if player is of Challenge game mode, false if player is of Sandbox game mode.
	 */
	public static void setPlayerOfChallengeGame(boolean playerOfChallengeGame) {
		Player.playerOfChallengeGame = playerOfChallengeGame;
	}

	/**
	 * Returns total eco points of the player,
	 * @return total eco points of the player
	 */
	public static int getEcoPoints() {
		return ecoPoints;
	}

	/**
	 * Returns the eco points needed to win Challenge game mode
	 * @return eco points needed to win
	 */
	public static int getEcoPointsNeeded() {
		return ecoPointsNeeded;
	}

	/**
	 * Returns the result of Challenge game mode.
	 * @return WinGameAction if moves are finished, otherwise return null.
	 */
	public Action winOrLose(){
		if (moves == 0){
			return new WinGameAction();
		}
		return null;
	}
}
