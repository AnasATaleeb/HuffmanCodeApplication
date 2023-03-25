package application;

public class ConversionsAndCompletions {

	// padding 0 to the right of the string to make the size divisible by 8
	// used
	public static String completeHeaderTreeString(String str) {
		while (str.length() % 8 != 0) {
			str += "0";
		}
		return str;
	}

	// from array of bytes to binaryString
	public static String fromArrayOfBytesToString(byte[] ar) {
		String str = "";
		for (int i = 0; i < ar.length; i++) {
			String temp = String.format("%8s", Integer.toBinaryString(ar[i] & 0xFF)).replace(' ', '0');
			temp = leftPad8(temp);
			str += temp;
		}
		return str;
	}

	// converts byte to bit string
	public static String toBitString(byte b) {
		final char[] bits = new char[8];
		final byte byteval = b;
		int bytei = 0 << 3;
		int mask = 0x1;
		for (int j = 7; j >= 0; j--) {
			final int bitval = byteval & mask;
			if (bitval == 0) {
				bits[bytei + j] = '0';
			} else {
				bits[bytei + j] = '1';
			}
			mask <<= 1;
		}
		return String.valueOf(bits);
	}

	// left padding
	static String leftPad8(String txt) {
		while (txt.length() < 8)
			txt = "0" + txt;
		return txt;
	}
}
