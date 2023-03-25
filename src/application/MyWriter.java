package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MyWriter {
	private File file;
	private FileOutputStream fout;
	private FileChannel channel;
	private ByteBuffer buff;
	private String currentBits;
	private int counterOfBytes = 0;

	public MyWriter(File file) {
		super();
		this.file = file;

		try {
			fout = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		channel = fout.getChannel();
		buff = ByteBuffer.allocate(1024);
		currentBits = "";
 
	}

	// printing the header status to the file
	// printing the signiture, extension size, extension, total size ,header size
	// ,and the headerTree
	// used
	public void printHeaderStatus(Header header, String headerTree) {
		buff.put(new byte[] { (byte) 'h', (byte) 'u', (byte) 'f' });
		buff.put(header.getExtensionSize());

		putString(header.getExtension());

		buff.putInt(header.getTotalSize());
		buff.putInt(header.getHeaderSize());

		putHeaderTree(headerTree);
		buff.flip();
		try {
			channel.write(buff);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		buff.clear();

	}

	// turning the string into array of bytes and putting it into the file
	// used
	public void putString(String str) {

		byte ar[] = new byte[str.length()];
		for (int i = 0; i < str.length(); i++) {
			ar[i] = (byte) str.charAt(i);
		}

		buff.put(ar);
	}

	// putting the header tree in the file
	// used
	public void putHeaderTree(String s) {

		String temp = "";

		for (int i = 0; i < s.length(); i++) {
			temp += s.charAt(i);

			if ((i + 1) % 8 == 0) {

				int tInt = Integer.parseInt(temp, 2);
				buff.put((byte) tInt);
				temp = "";
			}
		}

	}

	// this method is used to put the new represintation of the chars in the
	// compressed file
	// it puts 8 bytes at a time
	// used
	public void putCompressedFileData(String code) {
		currentBits += code;
		if (currentBits.length() >= 8) {
			String temp = currentBits.substring(0, 8);
			currentBits = currentBits.substring(8);
			int x = Integer.parseInt(temp, 2);

			byte b = (byte) x;
			buff.put(b);
			counterOfBytes++;
		}
		if (counterOfBytes == 8) {
			try {
				buff.flip();
				channel.write(buff);

				buff.clear();
				counterOfBytes = 0;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// cleaning the remaining bits if found
	// used
	public void cleanTheCurrentBits() {

		while (currentBits.length() >= 8) {
			String temp = currentBits.substring(0, 8);
			currentBits = currentBits.substring(8);
			int x = Integer.parseInt(temp, 2);
			byte b = (byte) x;
			buff.put(b);
			buff.flip();
			try {
				channel.write(buff);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buff.clear();
		}
		// right padding zeros if it was less than 8
		if (currentBits.length() > 0) {
			currentBits = rightPad8(currentBits);
			buff.put((byte) Integer.parseInt(currentBits, 2));
			buff.flip();
			try {
				channel.write(buff);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buff.clear();

		}
	}

	// right padding to the byte
	// used
	String rightPad8(String txt) {
		while (txt.length() < 8)
			txt = txt + "0";
		return txt;
	}

}
