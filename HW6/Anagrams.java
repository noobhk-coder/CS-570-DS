import java.io.*;
import java.util.*;

/***
 * Class Anagram to create a AnagramTable for anagram words
 */
public class Anagrams {

    final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,
            67, 71, 73, 79, 83, 89, 97, 101};
    Map<Character, Integer> letterTable;
    Map<Long, ArrayList<String>> anagramTable;

    /***
     * Constructor which will create HashMap for letterTable and anagramTable
     * It will also call the method buildLetterTable
     */
    public Anagrams() {

        letterTable = new HashMap<Character, Integer>();
        anagramTable = new HashMap<Long, ArrayList<String>>();
        buildLetterTable();
    }

    /**
     * Builds the letterTable Hash Map table
     */
    private void buildLetterTable() {

        Character[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (int i = 0; i < 26; i++) {
            letterTable.put(letters[i], primes[i]);
        }

    }

    /***
     * This method adds the word into anagramTable Hash Map table
     * @param s denotes the string/word which needs to be added into the table
     */
    private void addWord(String s) {

        if (anagramTable.containsKey(myHashCode(s))) {
            ArrayList<String> value = anagramTable.get(myHashCode(s));
            value.add(s);
            anagramTable.replace(myHashCode(s), value);
        } else {
            ArrayList<String> value = new ArrayList<String>();
            value.add(s);
            anagramTable.put(myHashCode(s), value);
        }

    }

    /***
     * This method is called by the addWord method to calculate the Hash code which needs to be used as a key
     * while adding into the anagramTable
     * @param s the word which will be added as a value to the Map table
     * @return the Hash code which will be used as a key while adding into the table
     */
    private Long myHashCode(String s) {

        Long result = 1l;
        // long result = 1;
        for (int i = 0; i < s.length(); i++) {
            result = result * letterTable.get(s.charAt(i));
        }

        return result;
    }

    /**
     * This method processes a file line wise which contains words (one per line) and calls the addWord method
     * where the words are added to anagramTable
     *
     * @param s contains the file name which needs to be processed
     * @throws IOException throws exception if read error
     */
    public void processFile(String s) throws IOException {

        FileInputStream fstream = new FileInputStream(s);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while ((strLine = br.readLine()) != null) {
            this.addWord(strLine);
        }
        br.close();

    }

    /**
     * This method will find out the key with maximum number of values
     *
     * @return will return an array list which contains the Hashcode and number of words associated to it.
     */
    private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {

        ArrayList<Map.Entry<Long, ArrayList<String>>> result = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
        int max = 0;
        for (Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()) {
            if (entry.getValue().size() > max) {
                result.clear();
                result.add(entry);
                max = entry.getValue().size();
            } else if (entry.getValue().size() == max) {
                result.add(entry);
            }
        }

        return result;
    }


    public static void main(String[] args) {

        Anagrams a = new Anagrams();
        final long startTime = System.nanoTime();
        try {
            a.processFile("words_alpha.txt");
        } catch (IOException e1) {

            e1.printStackTrace();
        }

        ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
        final long estimatedTime = System.nanoTime() - startTime;
        final double seconds = ((double) estimatedTime / 1000000000);
        System.out.println("Time: " + seconds);
        System.out.println("List of max anagrams: " + maxEntries);

    }
}
