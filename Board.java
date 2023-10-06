package assignment2;


import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<String> guesses;
    private final List<Feedback> feedbacks;

    public Board() {
        this.guesses = new ArrayList<>();
        this.feedbacks = new ArrayList<>();
    }

    public Feedback addGuess(String guess, String secretCode) {
        if (isValidGuess(guess)) {
            Feedback feedback = new Feedback(guess, secretCode);
            guesses.add(guess);
            feedbacks.add(feedback);
            return feedback;
        }
        return null;
    }

    public void printHistory() {
      //  System.out.println("The history of all guesses and replies for this game:");
        for (int i = 0; i < guesses.size(); i++) {
            System.out.println(guesses.get(i) + "\t\t" + feedbacks.get(i));
        }
    }

    private boolean isValidGuess(String guess)
    {
        if(guess.length()!=GameConfiguration.pegNumber)
        {
            return false;
        }
        else if(guess.equals(guess.toLowerCase()))
        {
            return false;
        }
        else
        {
            int c=0;
            for(int i=0;i<guess.length();i++)
            {
                char ch=guess.charAt(i);
                if(ch=='B' || ch=='G' || ch=='O' || ch=='P' || ch=='R' || ch=='Y')
                {
                    c++;
                }
            }
            return c == 4;
        }
    }
}

class Feedback {
    private final int blacks;
    private final int whites;

    public Feedback(String guess, String secretCode) {
        int blacks = 0;
        int whites = 0;

        boolean[] us = new boolean[guess.length()];
        boolean[] sec = new boolean[secretCode.length()];



        // Count blacks
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == secretCode.charAt(i)) {
                blacks++;
                us[i] = true;
                sec[i] = true;
            }
        }

        // Count whites
        for (int i = 0; i < guess.length(); i++) {
            for (int j= 0; j< secretCode.length();j++ ){
                if (guess.charAt(i) == secretCode.charAt(j) && !sec[j] && !us[i]) {
                    whites++;
                    us[i] = true;
                    sec[j] = true;
                    break;
                }

            }

        }
        this.blacks = blacks;
        this.whites = whites;
    }

    public boolean isCorrect() {
        return blacks == 4;
    }

    @Override
    public String toString() {
        return blacks + "B_" + whites + "W";
    }
}

