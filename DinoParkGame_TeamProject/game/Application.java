package game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.monash.fit2099.engine.*;
import game.Item.immovableitem.Bridge;
import game.Item.immovableitem.VendingMachine;
import game.dinoparkground.*;
import game.dinosaurs.*;
import game.dinosaurs.Pterodactyls;
import game.dinosaurs.Allosaur;

/**
 * The main class for the Jurassic World game.
 * @see game;
 * @see edu.monash.fit2099.engine;
 */
public class Application {

	/**
	 * Main class to execute this Game
	 */
	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new Lake());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		".....###.###....................................................................",
		".....#_____#.............................~......................................",
		".....#_____#....................................................................",
		".....###.###....................................................................",
		"................................................................................",
		"......................................+++.........................~.............",
		"................~......................++++.....................................",
		"...................................+++++........................................",
		".....................................++++++.....................................",
		"......................................+++...............~.......................",
		".....................................+++........................................",
		"................................................................................",
		"............+++.................................................................",
		".............+++++..............................................................",
		"...............++.........................~..............+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"......................~.................................~...............++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap gameMap = new DinoParkGameMap(groundFactory, map);
		world.addGameMap(gameMap);

		List<String> secondMap = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"................................................................................",
		".........................................~......................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"......................................+++.........................~.............",
		"................~......................++++.....................................",
		"...................................+++++........................................",
		".....................................++++++.....................................",
		"......................................+++...............~.......................",
		".....................................+++........................................",
		"................................................................................",
		"............+++.................................................................",
		".............+++++..............................................................",
		"...............++.........................~..............+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"......................~.................................~...............++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");

		GameMap secondGameMap = new DinoParkGameMap(groundFactory, secondMap);
		world.addGameMap(secondGameMap);

		// bridge in south map, to go to north map
		Bridge bridgeSouth = new Bridge();
		bridgeSouth.addAction(new MoveActorAction(secondGameMap.at(8, 24), "To North Map!"));
		gameMap.at(8,0).addItem(bridgeSouth);

		// bridge in north map, to go to south map
		Bridge bridgeNorth = new Bridge();
		bridgeNorth.addAction(new MoveActorAction(gameMap.at(8, 0), "To South Map!"));
		secondGameMap.at(8, 24).addItem(bridgeNorth);
		
		Actor player = new Player("Player", '@', 100);

		gameMap.at(8, 3).addItem(new VendingMachine());
		
		// Place a pair of stegosaurs in the middle of the map
		gameMap.at(30, 12).addActor(new Stegosaur(Gender.FEMALE));
//		gameMap.at(32, 12).addActor(new Stegosaur(Gender.MALE));

		gameMap.at(15, 10).addActor(new Pterodactyls(Gender.FEMALE, 10));
		gameMap.at(16, 10).addActor(new Pterodactyls(Gender.MALE, 5));
//		gameMap.at(15,12).addActor(new Allosaur());

		// Adds Brachiosaur herd
//		gameMap.at(36, 16).addActor(new Brachiosaur(Gender.FEMALE));
//		gameMap.at(37, 16).addActor(new Brachiosaur(Gender.FEMALE));
//		gameMap.at(36, 17).addActor(new Brachiosaur(Gender.MALE));
//		gameMap.at(37, 17).addActor(new Brachiosaur(Gender.MALE));

		// Updated Driver
		System.out.println("================================");
		System.out.println("| Welcome to Dinosaur Park Game |");
		System.out.println("================================");
		Scanner scanner = new Scanner(System.in);
		int option;
		do {
			System.out.println("--------------------------------");
			System.out.println("To play challenge game, enter: 1");
			System.out.println("To play sandbox game, enter: 2");
			System.out.println("To quit, enter: 3");
			System.out.println("--------------------------------");
			System.out.print("Enter your option: ");
			option = scanner.nextInt();
			switch (option){
				case 1:   // to play Challenge game
					world.addPlayer(player, gameMap.at(9, 4));
					Player.setEcoPoints(0);
					Player.setPlayerOfChallengeGame(true);
					System.out.println("--------------------------------");
					System.out.print("Please choose number of moves: ");
					int moves = scanner.nextInt();
					System.out.print("Please choose number of eco points: ");
					int ecoPoints = scanner.nextInt();
					Player.setEcoPointsNeeded(ecoPoints);
					Player.setMoves(moves);
					System.out.println("Challenge Game has started!");
					System.out.println("--------------------------------");
					world.run();
					System.out.println("--------------------------------");
					System.out.println("You earned " + Player.getEcoPoints() + " eco points in " + moves + " moves!");
					System.out.println("Eco points needed: " + ecoPoints);
					break;
				case 2:   // To play Sandbox game
					world.addPlayer(player, gameMap.at(9, 4));
					Player.setEcoPoints(10000);
					Player.setPlayerOfChallengeGame(false);
					System.out.println("--------------------------------");
					System.out.println("Sandbox Game has started!");
					System.out.println("--------------------------------");
					world.run();
					break;
				case 3:   // To quit game
					break;
			}
		} while (option != 3);

		System.out.println("Thank you for playing!");
	}
}
