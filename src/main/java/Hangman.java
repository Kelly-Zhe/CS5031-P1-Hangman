import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameState gameState = null;
        CommandOpts opts;

        opts = new CommandOpts(args);

        doStuff(sc, opts, gameState);
    }

    static void doStuff(Scanner sc, CommandOpts opts, GameState gameState) {

        boolean correct;

        if (opts.wordsource == "") {

            System.out.println("  1. Counties");
            System.out.println("  2. Countries");
            System.out.println("  3. Cities");

            System.out.print("Pick a category:");

            gameState = new GameState(Words.randomWord(sc.nextInt()), opts.maxguesses, opts.maxhints);
        } else {
            gameState = new GameState(Words.randomWord(opts.wordsource), opts.maxguesses, opts.maxhints);
        }

        while (!gameState.won() && !gameState.lost()) {
            gameState.showWord(gameState.word);

            System.out.println("Guesses remaining: " + gameState.wrong);

            correct = gameState.guessLetter();

            if (correct) System.out.println("Good guess!");
            if (!correct) System.out.println("Wrong guess!");
        }

        if (gameState.won()) {
            System.out.println("Well done!");
            System.out.println("You took " + gameState.GuessesTook + " guesses");
        } else {
            System.out.println("You lost! The word was " + gameState.word);
        }
    }
}
