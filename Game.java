package assignment2;

import java.util.Scanner;

public class Game {
    private int guessesLeft;
    private final Board board;
    private boolean gameWon, testing;

    public Game(boolean testingmode) {
        this.testing = testingmode;
        this.guessesLeft = GameConfiguration.guessNumber;
        this.board = new Board();
        this.gameWon = false;
    }

    public void runGame(Scanner scanner) {
        //  Scanner scanner = new Scanner(System.in);

        System.out.print("Generating secret code ...");
        String secretCode = SecretCodeGenerator.getInstance().getNewSecretCode();

        if (testing)
            System.out.print("(for this example, the secret code is " + secretCode + ")");
        System.out.println('\n');

        String guess = "";
        int flag = 0;
        while (guessesLeft > 0 && !gameWon) {
            if (flag == 1) {
                System.out.println("What is your next guess?");
            } else {
                System.out.println("You have " + guessesLeft + " guesses left.");
                System.out.println("What is your next guess?");
            }

            System.out.println("Type in the characters for your guess and press enter.");
            System.out.print("Enter guess: ");
            guess = scanner.nextLine();
            System.out.println();
            if (guess.equals("HISTORY")) {
                board.printHistory();
                continue;
            }

            Feedback feedback = board.addGuess(guess, secretCode);

            if (feedback != null) {
                flag = 0;
                guessesLeft--;
                if (guessesLeft==0 && !feedback.isCorrect())
                {
                    break;
                }
                System.out.print(guess + " -> " + feedback);
                if (feedback.isCorrect()) {
                    gameWon = true;
                    System.out.println(" - You win!");
                    System.out.print("Are you ready for another game (Y/N):");

                    String input = scanner.nextLine();
                    System.out.println();

                    if (!input.equals("Y")) {
                        return;
                    } else {
                        // Start a new game
                        Game gg = new Game(false);
                        gg.runGame(scanner);
                    }
                } else {
                    System.out.println();
                }


            } else {
                flag = 1;
                System.out.println(guess + " -> INVALID GUESS");
                System.out.println();
            }
            if (guessesLeft == 0) {
                System.out.println("Sorry, you are out of guesses. You lose, boo-hoo.");
                System.out.println();
                System.out.print("Are you ready for another game (Y/N):");



                String input = scanner.nextLine();
                System.out.println();

                if (!input.equals("Y")) {
                    return;
                }
                Game gg = new Game(false);
                gg.runGame(scanner);
            }
        }
        if (guessesLeft == 0) {
            System.out.println("Sorry, you are out of guesses. You lose, boo-hoo.");
            System.out.println();

            System.out.print("Are you ready for another game (Y/N):");


            String input = scanner.nextLine();
            System.out.println();

            if (!input.equals("Y")) {
                return;
            }
            Game gg = new Game(false);
            gg.runGame(scanner);
        }
    }
}
