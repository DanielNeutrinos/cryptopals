package sg.danielneutrinos;

import java.util.*;
import java.util.stream.Collectors;

public class StringUtils {

    //ETAOIN SHRDLU
    private static final List<Character> english =
            new ArrayList<>(Arrays.asList('E', 'T', 'A', 'O', 'I', 'N', 'S', 'H', 'R', 'D', 'L', 'U'));
    public static int computeScoreSimple(String rawString) {
        String testString = rawString.replaceAll("[^a-zA-Z]", "").toUpperCase();
        int score = 0;
        for (int i = 0; i < testString.length(); i++) {
            if (english.contains(testString.charAt(i))) {
                score++;
            }
        }
        return score;
    }

    public static int computeScore(String rawString) {
        Map<Character, Integer> sorted = sortMap(getFrequencyMap(rawString
                .replaceAll("[^a-zA-Z]", "").toUpperCase()));
        int score = 0;
        List<Character> keys = new ArrayList<>(sorted.keySet());
        for (int i = 0; i < english.size(); i++) {
            Character c;
            try {
                c = keys.get(i);
            } catch (IndexOutOfBoundsException e) {
                break;
            }
            if (c == null) break;
            if (english.contains(c)) score += 10;
            if (c.equals(english.get(i))) {
                score++;
            }
        }
        return score;
    }

    private static Map<Character, Integer> getFrequencyMap(String input) {
        Map<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (result.containsKey(character)) {
                result.put(character, result.get(character) + 1);
            } else {
                result.put(character, 1);
            }
        }
        return result;
    }

    public static Map<Character, Integer> sortMap(Map<Character, Integer> frequencyMap) {
        return frequencyMap.entrySet()
                .stream().sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));
    }
}
