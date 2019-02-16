import java.util.ArrayList;
import java.util.Scanner;

// The game state
public class GameState {
    public String word;
    public int GuessesTook;//number of guess took
    public int wrong;//remaining guess
    public int HintsLeft;//hints left

    ArrayList<Character> got;//the letters not contain in word
    ArrayList<Character> not;//the letters contain in word

    public Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public GameState(String target, int maxguesses, int maxhints) {
        //GuessesTook: max guess number
        //HintsLeft:max hints number
        this.word = target;
        not = new ArrayList<Character>();
        got = new ArrayList<Character>();

        for (int i = 0; i < target.length(); ++i) {
            if (!not.contains(Character.toLowerCase(target.charAt(i))))
                not.add(Character.toLowerCase(target.charAt(i)));
        }
        //System.out.println(missing);

        this.GuessesTook = 0;
        wrong = maxguesses;
        this.HintsLeft = maxhints;
    }

    void showWord(String word) {
        for (int i = 0; i < word.length(); ++i) {
            if (got.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
            } else {
                System.out.print("-");
            }
        }
        System.out.println("");
        // System.out.println(missing);
    }

    boolean guessLetter() {
        int i;
        char letter;

        System.out.print("Guess a letter or word (? for a hint): ");

        String str = sc.next().toLowerCase();

        if (str.length() > 1) {
            if (str == word) {
                not.clear();
                return true;
            } else return false;
        }

        letter = str.charAt(0);

        if (letter == '?') {
            hint();
            return false;
        }

        for (i = 0; i < not.size(); ++i) { // Loop over the not got
            if (not.get(i) == letter) {
                not.remove(i);
                got.add(letter);
                GuessesTook++;
                return true;
            }
        }

        GuessesTook++; // One more guess
        wrong--;
        return false;
    }

    boolean won() {
        if (not.size() == 0) return true;
        else return false;
    }

    boolean lost() {
        if (not.size() > 0 && wrong == 0) return true;
        else return false;
    }

    void hint() {
        if (HintsLeft == 0) {
            System.out.println("No more hints allowed");
        }

        System.out.print("Try: ");
        System.out.println(not.get((int) (Math.random() * not.size())));
    }
}
