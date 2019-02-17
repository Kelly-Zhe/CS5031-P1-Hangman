import java.util.InputMismatchException;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, "UTF-8");
        GameState gamestate = null;
        CommandOpts opts;

        opts = new CommandOpts(args);

        doStuff(sc, opts, gamestate);
    }

    static void doStuff(Scanner sc, CommandOpts opts, GameState gamestate) {

        boolean correct;

        if (opts.wordsource.equals("")) {

            System.out.println("  1. Counties");
            System.out.println("  2. Countries");
            System.out.println("  3. Cities");

            System.out.print("Pick a category:");
            String word = null;
            int category;
            while (word == null) {
                try {
                    category = sc.nextInt();
                    if (category > 0 && category < 4) {
                        word = Words.randomWord(category);
                        break;
                    } else {
                        System.out.println("please enter valid number(1-3).");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("please enter valid number(1-3).");
                    sc.nextLine();
                }
            }

            gamestate = new GameState(word, opts.maxguesses, opts.maxhints);
        } else {
            gamestate = new GameState(Words.randomWord(opts.wordsource), opts.maxguesses, opts.maxhints);
        }

        while (!gamestate.won() && !gamestate.lost()) {
            gamestate.showWord(gamestate.word);

            System.out.println("Guesses remaining: " + gamestate.guessesWrong);

            correct = gamestate.guessLetter();

            if (correct) {
                System.out.println("Good guess!");
            }
            if (!correct) {
                System.out.println("Wrong guess!");
            }
        }

        if (gamestate.won()) {
            System.out.println("Well done!");
            System.out.println("You took " + gamestate.guessesTook + " guesses");
        } else {
            System.out.println("You lost! The word was " + gamestate.word);
        }
    }
}
