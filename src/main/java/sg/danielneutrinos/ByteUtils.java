package sg.danielneutrinos;

public class ByteUtils {

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static byte[] hexStringToByteArray(String s) throws NumberFormatException {
        if (s.length() < 2 || s.length() % 2 != 0) { throw new NumberFormatException("Invalid hex string"); }
        s = s.toUpperCase();
        byte[] bytes = new byte[s.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            int index = i * 2;
            int j = Integer.parseInt(s.substring(index, index + 2), 16);
            bytes[i] = (byte) j;
        }
        return bytes;
    }

    public static byte[] xorByteArray(byte[] in, byte use) {
        byte[] temp = new byte[in.length];
        for (int i = 0; i < in.length; i++) {
            temp[i] = use;
        }
        return xorByteArray(in, temp);
    }

    public static byte[] xorByteArray(byte[] in, byte[] use) {
        if (in.length != use.length) throw new IllegalArgumentException("byte arrays different lengths");
        byte[] out = new byte[in.length];
        for (int i = 0; i < in.length; i++) {
            out[i] = (byte) (in[i] ^ use[i]);
        }
        return out;
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
