package dataGenerators;

import java.util.Random;


/**
 * Utility methods to generate random strings.
 *
 * @author vignan
 * @since 10/11/22
 */
public class RandomStrings {
    // Always use the accessors to access these members
    private static Random rng;
    private static char[] lettersAtoZ;

    /**
     * This method will get random word with specific length and with specified characters
     *
     * @param lengthOfWord length od the word
     * @param alphabet     character sequence
     * @return random word
     */
    public static String generateRandomWordUsingAlphabet(int lengthOfWord, char[] alphabet) {
        final Random rng = getRng();
        final char[] randomWord = new char[lengthOfWord];
        for (int i = 0; i < lengthOfWord; i++)
            randomWord[i] = alphabet[rng.nextInt(alphabet.length)];

        return new String(randomWord);
    }

    private static Random getRng() {
        if (null == rng)
            rng = new Random();

        return rng;
    }

    private static char[] getLettersAtoZ() {
        if (null == lettersAtoZ) {
            lettersAtoZ = new char['z' - 'a' + 1];
            int i = 0;
            for (char c = 'a'; c <= 'z'; c++, i++)
                lettersAtoZ[i] = c;
        }

        return lettersAtoZ;
    }

    /**
     * This method will get random word with specific length
     *
     * @param lengthOfWord length of the word
     * @return random word
     */
    public String generateRandomWord(int lengthOfWord) {
        return generateRandomWordUsingAlphabet(lengthOfWord, getLettersAtoZ());
    }

}


