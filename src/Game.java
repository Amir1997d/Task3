import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        if (args.length < 3 || args.length % 2 == 0 || !areDistinct(args)) {
            if (args.length < 3) {
                System.out.println("You have pass at least 3 arguments!");
                System.out.println("Please provide an odd number >= 3 of non-repeating strings as arguments.");
            }
            else if (args.length % 2 == 0) {
                System.out.println("The number of passed arguments must be an odd number\n" +
                        "Example: java Main arg1 arg2 arg3");
                System.out.println("Please provide an odd number >= 3 of non-repeating strings as arguments.");
            }
            else if (!areDistinct(args)) {
                System.out.println("You have to pass different arguments!\n" +
                        "Example: java Main arg1 arg2 arg3");
                System.out.println("Incorrect input. Please provide an odd number >= 3 of non-repeating strings as arguments.");
            }
            return;
        }

        List<String> moves = new ArrayList<>(Arrays.asList(args));
        KeyGenerator keyGenerator = new KeyGenerator();
        byte[] key = keyGenerator.generateKey();

        String computerMove = getRandomMove(moves);
        String hmac = HMACCalculator.calculateHMAC(computerMove, key);

        System.out.println("HMAC: " + hmac);

        System.out.println("Available moves:");
        for (int i = 0; i < moves.size(); i++) {
            System.out.println((i + 1) + " - " + moves.get(i));
        }
        System.out.println("0 - exit");
        System.out.println("? - help");

        int userChoice;
        do {
            userChoice = getUserChoice(moves.size());
            if (userChoice == -1) {
                printHelpTable(moves);
            } else if (userChoice != 0) {
                int computerChoice = moves.indexOf(computerMove) + 1;
                String result = determineResult(userChoice, computerChoice, moves);
                System.out.println("Your move: " + moves.get(userChoice - 1));
                System.out.println("Computer move: " + computerMove);
                System.out.println(result);
                System.out.println("HMAC key: " + bytesToHex(key));
            }
        } while (userChoice != 0);
    }

    private static boolean areDistinct(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i].equals(arr[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    private static String getRandomMove(List<String> moves) {
        int randomIndex = (int) (Math.random() * moves.size());
        return moves.get(randomIndex);
    }

    private static int getUserChoice(int numMoves) {
        System.out.print("Enter your move: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("?")) {
            return -1;
        }

        try {
            int choice = Integer.parseInt(input);
            if (choice >= 0 && choice <= numMoves) {
                return choice;
            } else {
                System.out.println("Invalid input. Please enter a valid move number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid move number.");
        }
        return getUserChoice(numMoves);
    }

    private static String determineResult(int userChoice, int computerChoice, List<String> moves) {
        MoveComparator moveComparator = new MoveComparator(moves);
        return moveComparator.determineWinner(userChoice, computerChoice);
    }

    private static void printHelpTable(List<String> moves) {
        HelpTableGenerator helpTableGenerator = new HelpTableGenerator(moves);
        helpTableGenerator.generateHelpTable();
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
