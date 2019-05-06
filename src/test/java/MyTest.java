import org.junit.Assert;
import org.junit.Test;
import sg.danielneutrinos.ByteUtils;

import java.util.Base64;

public class MyTest {

    @Test
    public void challenge1Set1() {
        String hex = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
        String answer = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t";
        String base64 = new String(Base64.getEncoder().encode(ByteUtils.hexStringToByteArray(hex)));
        Assert.assertEquals(answer, base64);
    }

    @Test
    public void challenge1Set2() {
        String hexIn = "1c0111001f010100061a024b53535009181c";
        String hexUse = "686974207468652062756c6c277320657965";
        String answer = "746865206b696420646f6e277420706c6179";

        byte[] step1 = ByteUtils.xorByteArray(ByteUtils.hexStringToByteArray(hexIn),
                ByteUtils.hexStringToByteArray(hexUse));
        String hexComputed = ByteUtils.bytesToHex(step1);
        Assert.assertEquals(answer, hexComputed.toLowerCase());
    }

    @Test
    public void xorBytes() {
        byte[] input = {(byte) 23, (byte) 1, (byte) 87, (byte) 74, (byte) 56};
        byte[] use = {(byte) 42, (byte) 42, (byte) 42, (byte) 42, (byte) 42};
        byte[] answer = ByteUtils.xorByteArray(input, use);
        byte[] test = ByteUtils.xorByteArray(input, (byte) 42);
        Assert.assertArrayEquals(answer, test);
    }
}
