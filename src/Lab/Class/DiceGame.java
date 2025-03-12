package Lab.Class;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("serial")
class NumberRollDiceNotEqualException extends RuntimeException {
	public NumberRollDiceNotEqualException(String message) {
		super(message);
	}
}

@SuppressWarnings("serial")
class InvalidDiceException extends RuntimeException {
	public InvalidDiceException(String message) {
		super(message);
	}
}

public class DiceGame {
	public static void diceGame() {
		try {
			List<Integer> bobDices = readDices("src/resources/bob.txt");
			System.out.println("Bob's dices: " + bobDices);

			List<Integer> aliceDices = readDices("src/resources/alice.txt");
			System.out.println("Alice's dices: " + aliceDices);

			String winner = getWinner(bobDices, aliceDices);
			if (winner == null) {
				System.out.println("Two people have equal points");
			} else {
				System.out.println("The winner is " + winner);
			}
		} catch (InvalidDiceException | NumberRollDiceNotEqualException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("The file does not exist: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("The file contains non-numeric data");
		}
	}

	public static List<Integer> readDices(String fileName) throws FileNotFoundException, InputMismatchException {
		System.out.println("Reading file: " + fileName);
		List<Integer> dices = new ArrayList<>();

		try (Scanner scanner = new Scanner(new FileReader(fileName))) {
			while (scanner.hasNext()) {
				if (!scanner.hasNextInt()) {
					throw new InputMismatchException();
				}
				int dice = scanner.nextInt();
				if (dice < 1 || dice > 6) {
					throw new InvalidDiceException("Invalid dice value: " + dice + " in file " + fileName);
				}
				dices.add(dice);
			}
		}
		return dices;
	}

	public static int sumOfDice(List<Integer> dices) {
		return dices.stream().mapToInt(Integer::intValue).sum();
	}

	public static String getWinner(List<Integer> bobDices, List<Integer> aliceDices) {
		if (bobDices.size() != aliceDices.size()) {
			throw new NumberRollDiceNotEqualException("Number of dice rolls not equal");
		}

		int sumBob = sumOfDice(bobDices);
		int sumAlice = sumOfDice(aliceDices);

		if (sumBob == sumAlice) {
			return null;
		}
		return (sumBob > sumAlice) ? "Bob" : "Alice";
	}
}
