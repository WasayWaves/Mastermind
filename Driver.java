package assignment2;
import java.util.*;

public class Driver {

    public static void main(String[] args)
    {
//      System.out.println("Welcome to Mastermind. Here are the rules.\n");
//      System.out.println("This is a text version of the classic board game Mastermind.\n");
//       System.out.print("The computer will think of a secret code. The code consists of "+ GameConfiguration.pegNumber+ "\ncolored pegs.The pegs MUST be one of six colors: blue, green,"+ "\n" + " orange, purple, red, or yellow. A color may appear more than once in"+ "\n" +" the code. You try to guess what colored pegs are in the code and" + "\n" + "what order they are in. After you make a valid guess the result"+"\n" +"(feedback) will be displayed.\n\nThe result consists of a black peg for each peg you have guessed" + "\n" +"exactly correct (color and position) in your guess. For each peg in" + "\n" +"the guess that is the correct color, but is out of position, you get" + "\n" +"a white peg. For each peg, which is fully incorrect, you get no"+ "\n" + "feedback.\n\nOnly the first letter of the color is displayed. B for Blue, R for" +"\n"+"Red, and so forth. When entering guesses you only need to enter the" +"\n" + "first character of each color as a capital letter.\n\nYou have 12 guesses to figure out the secret code or you lose" +"\n"+"the game.   ");

       boolean testingmode=false;

       if(args.length>0 && args[0].equals("1"))
       {
           testingmode=true;
       }
        Scanner scanner = new Scanner(System.in);

        System.out.print("Are you ready to play (Y/N): ");

        String input = scanner.nextLine();
        System.out.println();
        //System.out.println(input);
        if (!input.equals("Y")) {
            return;
        }
        Game game = new Game(testingmode);
        game.runGame(scanner);

    }
}
