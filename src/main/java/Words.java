import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Words {

    static String[] countiesList = {"Argyll and Bute", "Caithness", "Kingdom of Fife", "East Lothian", "Highland", "Dumfries and Galloway", "Renfrewshire", "Scottish Borders", "Perth and Kinross"};
    static String[] countriesList = {"Scotland", "England", "Wales", "Northern Ireland", "Ireland", "France", "Germany", "Netherlands", "Spain", "Portugal", "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece"};
    static String[] citiesList = {"St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth", "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk"};

    static ArrayList<String> customwords;

    public static String randomWord(int category) {
        if (category == 1) {
            return countiesList[(int) (Math.random() * countiesList.length)];
        } else if (category == 2) {
            return countriesList[(int) (Math.random() * countriesList.length)];
        } else if (category == 3) {
            return citiesList[(int) (Math.random() * citiesList.length)];
        } else {
            System.out.println("Please enter a valid number.");
            return "";
        }
    }

    public static String randomWord(String wordsource) {
        String line;
        customwords = new ArrayList<String>();
        Pattern p = Pattern.compile("[a-zA-Z^\\s]+");
        boolean valid;

        try {
            //FileReader file = new FileReader(wordsource);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(wordsource), StandardCharsets.UTF_8));
            // BufferedReader reader = new BufferedReader(file);
            while ((line = reader.readLine()) != null) {
                Matcher matcher = p.matcher(line);
                valid = matcher.matches();
                if (valid) {
                    customwords.add(line);
                } else {
                    return null;
                }
            }
            return customwords.get((int) (Math.random() * customwords.size()));
        } catch (FileNotFoundException e) {
            System.out.println("File error");
            return "";
        } catch (IOException e) {
            System.out.println("IO error");
            return "";
        }

    }

    public static int getCounties() {
        return 1;
    }

    public static int getCountries() {
        return 2;
    }

    public static int getCities() {
        return 3;
    }

    public String[] getCountiesList() {
        return countiesList.clone();
    }

    public String[] getCountriesList() {
        return countriesList.clone();
    }

    public String[] getCitiesList() {
        return citiesList.clone();
    }

}
