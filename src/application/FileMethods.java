package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

public class FileMethods {

	// This method read from the real file to count the frequencies
	// Returns an array of size 256 that contains the freqs
	// used
	public static int[] readToCompress() {
		if (Main.file == null) {
			return null;
		}

		int ar[] = new int[256];

		RandomAccessFile aFile;
		try {
			aFile = new RandomAccessFile(Main.file, "r");
			FileChannel inChannel = aFile.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(256);

			try {
				while (inChannel.read(buffer) > 0) {
					buffer.flip();
					for (int i = 0; i < buffer.limit(); i++) {
						byte tempByte = buffer.get();
						if (tempByte < 0) {
							ar[tempByte + 256]++;
						} else
							ar[tempByte]++;

					}
					buffer.clear();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				inChannel.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				aFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ar;

	}

	// this method returns the extension of the file
	// used
	public static String getFileExtension(File file) {
		String fileName = file.getName();

		int index = fileName.lastIndexOf('.');
		String extension = "";
		if (index > 0) {
			extension = fileName.substring(index + 1);
			return extension;

		} else {
			return null;
		}
	}

	// getting the name of the file without the extension
	// used
	public static String getFileAbsName(File file) {
		String fileName = file.getName();

		int index = fileName.lastIndexOf('.');
		String name = "";
		if (index > 0) {
			name = fileName.substring(0, index);
			return name;

		} else {
			return null;
		}
	}

	// this method is used to read from the file for the second time
	// putting the new represintation of the chars in the file
	public static void readToEncode(MyWriter writer, StatusObject ar[]) {
		if (Main.file == null) {
			return;
		}

		RandomAccessFile aFile;
		try {
			aFile = new RandomAccessFile(Main.file, "r");
			FileChannel inChannel = aFile.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(64);

			try {
				String checking = "";
				System.out.println("encode:");
				while (inChannel.read(buffer) > 0) {
					buffer.flip();

					for (int i = 0; i < buffer.limit(); i++) {
						byte tempByte = buffer.get();

						try {
							if (tempByte < 0) {
								writer.putCompressedFileData(ar[tempByte + 256].code);
								System.out.print(ar[tempByte + 256].code);
							} else {
								writer.putCompressedFileData(ar[tempByte].code);
								System.out.print(ar[tempByte].code);
							}

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					buffer.clear();

				} 

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				inChannel.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				aFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	

}
