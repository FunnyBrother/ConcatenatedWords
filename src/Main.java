import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.findAllConcatenatedWordsInADict(main.readFromFile());
    }

    private String[] readFromFile() {
        List<String> wordsFromFile = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("words.txt"))) {
            String sCurrentLine;
            wordsFromFile = new ArrayList<>();

            while ((sCurrentLine = bufferedReader.readLine()) != null) {
                wordsFromFile.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordsFromFile != null ? wordsFromFile.toArray(new String[0]) : new String[0];
    }

    private void findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            System.exit(0);
        }
        HashSet<String> dict = new HashSet<>();
        Arrays.sort(words, Comparator.comparingInt(String::length));

        int countFormWords = 0;
        for (String word : words) {
            if (canForm(word, dict)) {
                if(res.size() == 2) {
                    res.clear();
                }
                res.add(word);
                countFormWords++;
            }
            dict.add(word);
        }

        System.out.println("First word: " + res.get(1) + ", length =  " + res.get(1).length());
        System.out.println("Second word: " + res.get(0) + ", length =  " + res.get(0).length());
        System.out.println("Count concatenated words: " + countFormWords);
    }

     private boolean canForm(String word, HashSet<String> dict) {
         if (dict.isEmpty()) {
             return false;
         }

         boolean[] dp = new boolean[word.length() + 1];
         dp[0] = true;
         for (int i = 1; i <= word.length(); i++) {
             for (int j = 0; j < i; j++) {
                 if (!dp[j]) {
                     continue;
                 }
                 String str = word.substring(j, i);
                 if (dp[j] && dict.contains(str)) {
                     dp[i] = true;
                     break;
                 }
             }
         }
         return dp[word.length()];
     }
}
