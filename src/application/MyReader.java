package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MyReader {
	private File file;
	private FileInputStream fin;
	private FileChannel channel;
	private ByteBuffer buff;

	public MyReader(File file) {
		super();
		this.file = file;

		try {
			fin = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		channel = fin.getChannel();
		buff = ByteBuffer.allocate(8);

	}

	
	//reading the header from the compressed file using byteBuffer 
	public String readHeader(Header header) throws IOException {

		// reading "huf" signiture
		String headerSig = "";
		String headerTree = "";
		try {
			buff = ByteBuffer.allocate(3);
			channel.read(buff);
			buff.flip();
			byte[] ar = buff.array();
			buff.clear();
			

			for (int i = 0; i < ar.length; i++) {
				headerSig += (char) ar[i];
			}
		
			if(!headerSig.equals("huf")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Error in the file ");
				alert.setContentText("This isn't a huf file compressed by this system");
				alert.showAndWait();
				return null;
			}
		

		// reading the size of the extension
		buff = ByteBuffer.allocate(1);
		channel.read(buff);
		buff.flip();
		byte extensionSize = buff.get();
		buff.clear();

		// reading the extension
		buff = ByteBuffer.allocate(extensionSize);
		channel.read(buff);
		buff.flip();
		ar = buff.array();
		String extension = "";
		for (int i = 0; i < extensionSize; i++) {
			extension += (char) ar[i];
		}
		buff.clear();

		// reading the size of the file
		buff = ByteBuffer.allocate(4);
		channel.read(buff);
		buff.flip();
		int totalSize = buff.getInt();
		buff.clear();

		// reading the size of the header
		buff = ByteBuffer.allocate(4);
		channel.read(buff);
		buff.flip();
		int headerSize = buff.getInt();
		buff.clear();

		header.setExtensionSize(extensionSize);
		header.setHeaderSize(headerSize);
		header.setTotalSize(totalSize);
		header.setExtension(extension);
		header.setSig(headerSig);
	

		
		buff = ByteBuffer.allocate(headerSize);
		channel.read(buff);
		buff.flip();

		byte headerTreeInBytes[] = buff.array();
		headerTree = ConversionsAndCompletions.fromArrayOfBytesToString(headerTreeInBytes);
		buff.clear();
		
	
		
		}catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error in the file ");
			alert.setContentText("This isn't a huf file compressed by this system");
			

			alert.showAndWait();
			return null;
		}

		return headerTree;

	}

	
	
	//this method is remaking the original file 
	//reads from the compressed file and convert the chars to the original representation of bits
	public void readFromThecompressedToDeCompress(int theRealFileSize, DecompressionTreeNode rootNode, File realFile)
			throws IOException {

		buff=ByteBuffer.allocate(512);
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(realFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		FileChannel outChannel = fout.getChannel();
		ByteBuffer outBuffer = ByteBuffer.allocate(512);

		int counter = 0;
		
		buff.clear();
		
		channel.read(buff);
		
		String myString = "";
		buff.flip();
		myString+=ConversionsAndCompletions.toBitString(buff.get());
		DecompressionTreeNode tempNode = rootNode;
		int i = 0;
		boolean bool=false;

		while (counter < theRealFileSize) {
		
			tempNode = rootNode;
			while (!tempNode.isLeaf()) {
				
				if (i == myString.length()) {
                    if (!buff.hasRemaining()) {
                    	buff.clear();
                    	
                        if(channel.read(buff)<1)
                        	bool=true;
                        	
                        buff.flip();   
                    }
                    	if(bool)
                    		break;
                    	myString = ConversionsAndCompletions.toBitString(buff.get());
                    
                    i = 0;
                }
 
				if (myString.charAt(i) == '0') {
					tempNode = tempNode.getLeft();

				} else {
					tempNode = tempNode.getRight();
				}
				i++;
			}

			myString = myString.substring(i);
			i = 0;
			
			
			if (tempNode.getValue()>= 128) {

				outBuffer.put((byte) (tempNode.getValue() - 256));
				
				
			} else {
				outBuffer.put((byte) (tempNode.getValue()));
				
			}

			counter++;
			if (counter%512==0) {
				outBuffer.flip();
				outChannel.write(outBuffer);
				outBuffer.clear();

			}
		}
		
		if(outBuffer.hasRemaining()) {
			outBuffer.flip();
			outChannel.write(outBuffer);
		}
		outChannel.close();
		channel.close();
		
	}

}
