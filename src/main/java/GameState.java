import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// The game state
public class GameState {
    public String word;
    public int guessesTook;//number of guess took
    public int guessesWrong;//remaining guess
    public int hintsLeft;//hints left

    ArrayList<Character> guessedLetter;//got
    ArrayList<Character> notGuessedLetter;//not
    ArrayList<Character> hintsLetter;

    public Scanner sc = new Scanner(System.in, "UTF-8").useDelimiter("\n");

    public GameState(String target, int maxguesses, int maxhints) {
        this.word = target;
        notGuessedLetter = new ArrayList<Character>();
        guessedLetter = new ArrayList<Character>();

        for (int i = 0; i < target.length(); ++i) {
            if (!notGuessedLetter.contains(Character.toLowerCase(target.charAt(i))))
                notGuessedLetter.add(Character.toLowerCase(target.charAt(i)));
        }
        hintsLetter = new ArrayList<>(notGuessedLetter);

        this.guessesTook = 0;
        guessesWrong = maxguesses;
        this.hintsLeft = maxhints;
    }

    void showWord(String word) {
        for (int i = 0; i < word.length(); ++i) {
            if (guessedLetter.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
            }  else if(word.charAt(i) == ' '){
                System.out.print(" ");
            }else{
                System.out.print("-");
            }
        }
    }

    boolean guessLetter() {
        int i;
        char letter;

        System.out.print("Guess a letter or word (? for a hint): ");
        Pattern pattern;
        pattern = Pattern.compile("[a-zA-Z?]+");
        String str = sc.next().toLowerCase();
        Matcher matcher = pattern.matcher(str);


        if (!matcher.matches()){
            System.out.println("Enter a valid letter/word");
            return guessLetter();
        }

        if (str.length() > 1) {
            if (str.equals(word.toLowerCase())) {
                notGuessedLetter.clear();
                hintsLetter.clear();
                return true;
            } else {
                guessesTook++;
                guessesWrong--;
                return false;
            }
        }

        letter = str.charAt(0);

        if (letter == '?') {
            hint();
            return guessLetter();
        }

        for (i = 0; i < notGuessedLetter.size(); ++i) { // Loop over the notGuessedLetter guessedLetter
            if (Character.toLowerCase(notGuessedLetter.get(i)) == Character.toLowerCase(letter)) {
                guessedLetter.add(notGuessedLetter.get(i));
                notGuessedLetter.remove(i);
                guessesTook++;
                if (hintsLetter.contains(letter)){
                    hintsLetter.remove(notGuessedLetter.get(i));
                }
                return true;
            }
        }

        guessesTook++; // One more guess
        guessesWrong--;
        return false;
    }

    boolean won() {
        return notGuessedLetter.size() == 0;
    }

    boolean lost() {
        return notGuessedLetter.size() > 0 && guessesWrong == 0;
    }

    void hint() {
        Character hint;
        if (hintsLeft == 0) {
            System.out.println("No more hints allowed");
            return;
        }
        if (hintsLeft > 0) {
            hintsLeft--;
        }
        System.out.print("Try: ");
        hint = hintsLetter.get((int) (Math.random() * notGuessedLetter.size()));
        hintsLetter.remove(hint);
        System.out.println(Character.toLowerCase(hint));
        System.out.println(" (hints left: " + hintsLeft + ")");


    }
}
