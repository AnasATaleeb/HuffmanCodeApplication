package application;

import java.io.File;
import java.io.IOException;

import javafx.stage.Stage;

public class Decompress {
	
	//this method is the main method of decompressing 
	public static DecompressStatusObject[] DecompressMainMethod(File compressedFile) {

		try {

			Header header = new Header();
			
			MyReader reader=new MyReader(compressedFile);
			
			String returnedString=reader.readHeader(header);
			if(returnedString==null) {
				return null;
			}
			String directory = compressedFile.getParent();

			File realFile = new File(directory + "\\" + FileMethods.getFileAbsName(compressedFile)+"."+  header.getExtension());
			

			try {
				boolean result = realFile.createNewFile();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
			DecompressionTreeNode root = Decompress.rebuildingTheTreeFromString(returnedString);
			printTree(root, "", true);

			root.prepairingCode("");
			

			DecompressStatusObject decompressStatusArray[] = new DecompressStatusObject[256];
			// prepared code table
			root.preparingStatusTable(decompressStatusArray);
//			for (int i = 0; i < decompressStatusArray.length; i++) {
//				if(decompressStatusArray[i]==null) {
//					decompressStatusArray[i]=new DecompressStatusObject("-1",(char)(i));
//				}
//			}
			
			reader.readFromThecompressedToDeCompress(header.getTotalSize(), root, realFile);
			
			
			
			return decompressStatusArray;
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	 
	
	//this method is used to rebuild the tree from a string which was read from the compressed file 
	
	//traversing through the string 
	//if the char is 1 we push the next  (8bits) char as into the stack 
	//if the char is 0 and the stack has more than 1 we pop two and make the first one the right child and the second one is the left child of a new node
	//if the char is 0 and the stack has no more than 1 ,then it's the root
	public static DecompressionTreeNode rebuildingTheTreeFromString(String str) {
		System.out.println(str);
		Stack stack = new Stack();
		
		for (int i = 0; i < str.length(); i++) {
			
			if (str.charAt(i) == '1') {
				char x = (char) Integer.parseInt(str.substring(i + 1, i + 9), 2);
				DecompressionTreeNode node = new DecompressionTreeNode();
				node.setValue(x);
				stack.push(node);
				System.out.println("---------------------------------------------------------------------");
				printTree(node, "", true);
				System.out.println("---------------------------------------------------------------------");

				
				i += 8;

			} else {
				if (stack.getCounter() == 1) {
					
					return (DecompressionTreeNode) stack.pop();
				} else {
					DecompressionTreeNode node = new DecompressionTreeNode();
					DecompressionTreeNode right = (DecompressionTreeNode) stack.pop();
					DecompressionTreeNode left = (DecompressionTreeNode) stack.pop();
					node.setRight(right);
					node.setLeft(left);
					System.out.println("---------------------------------------------------------------------");
					printTree(node, "", true);
					System.out.println("---------------------------------------------------------------------");


					stack.push(node);
				}
			}

		}
		// TODO:
		
		return null;
	}
	
	static void printTree(DecompressionTreeNode node, String prefix, boolean isLeft) {
		  if (node == null) {
		    return;
		  }

		  printTree(node.getRight(), prefix + (isLeft ? "|   " : "    "), false);
		  System.out.println(prefix + (isLeft ? "`-- " : "|-- ") + node.getValue());
		  printTree(node.getLeft(), prefix + (isLeft ? "    " : "|   "), true);
		}
}