package sg.danielneutrinos;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Cryptopals {

    public static void main(String[] args) {

        //Set 1 Challenge 1
        byte[] a = ByteUtils.hexStringToByteArray(
                "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d");
        System.out.println("Set 1 Challenge 1:");
        System.out.println(new String(Base64.getEncoder().encode(a)));
        System.out.print(System.lineSeparator());

        //Set 1 Challenge 2
        System.out.println("Set 1 Challenge 2:");
        byte[] input = ByteUtils.hexStringToByteArray("1c0111001f010100061a024b53535009181c");
        byte[] use = ByteUtils.hexStringToByteArray("686974207468652062756c6c277320657965");
        String computed = ByteUtils.bytesToHex(ByteUtils.xorByteArray(input, use));
        System.out.println(computed);
        System.out.print(System.lineSeparator());

        //Set 1 Challenge 3
        System.out.println("Set 1 Challenge 3:");
        String test = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736";
        byte[] testBytes = ByteUtils.hexStringToByteArray(test);
        Map<Character, Integer> scoreMatrix = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            String out = new String((ByteUtils.xorByteArray(testBytes, (byte) i)), Charset.forName("UTF-8"));
            scoreMatrix.put((char)i, StringUtils.computeScore(out));
        }
        scoreMatrix = StringUtils.sortMap(scoreMatrix);
        // print top five possible text
        System.out.println("Top 5 possible ciphers");
        int count = 5;
        for (Map.Entry<Character, Integer> entry : scoreMatrix.entrySet()) {
            if (count == 0) break;
            System.out.println(String.format("Cipher: %s Score: %s", entry.getKey(), entry.getValue()));
            String out = new String((ByteUtils.xorByteArray(testBytes, (byte) ((char) entry.getKey()))),
                    Charset.forName("UTF-8"));
            System.out.println(out);
            count--;
        }
        System.out.print(System.lineSeparator());
    }



}
