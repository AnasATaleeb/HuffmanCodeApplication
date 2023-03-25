package application;

public class HeapNode {
	private HeapNode left;
	private HeapNode right;
	private int freq;
	private byte value;
	private String code;
	private byte length;
	private boolean isData;

	public HeapNode() {
		super();
	}

	public HeapNode(int freq, byte value) {
		super();
		this.freq = freq;
		this.value = value;

	}

	public HeapNode(HeapNode left, HeapNode right, int value) {
		super();
		this.left = left;
		this.right = right;
		this.freq = value;
	}

	public HeapNode getLeft() {
		return left;
	}

	public void setLeft(HeapNode left) {
		this.left = left;
	}

	public HeapNode getRight() {
		return right;
	}

	public void setRight(HeapNode right) {
		this.right = right;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int value) {
		this.freq = value;
	}

	public byte getValue() {
		return value;
	}

	public void setValue(byte value) {
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public byte getLength() {
		return length;
	}

	public void setLength(byte length) {
		this.length = length;
	}

	public boolean isData() {
		return isData;
	}

	public void setData(boolean isData) {
		this.isData = isData;
	}

	// This Method is used to generate the new code
	// by appending 0 to the code of the left node
	// and appending 1 to the code of the right node
	// used
	public void prepairingCode(String code, byte length) {

		length++;
		if (this.left != null) {

			this.left.setCode(code + "0");
			this.left.setLength(length);

			this.left.prepairingCode(code + "0", length);

		}

		if (this.right != null) {

			this.right.setCode(code + "1");
			this.right.setLength(length);

			this.right.prepairingCode(code + "1", length);

		}

	}

	// this method is used to put the data of the generated tree in the status array
	// to display it as a table
	// used
	public void preparingStatusTable(StatusObject[] statusArray) {

		if (this.left != null) {

			if (this.left.isData) {

				if (this.left.value < 0) {
					statusArray[this.left.value + 256] = new StatusObject(left.code, (char) (left.value + 256),
							left.freq, left.length);
				} else

					statusArray[this.left.value] = new StatusObject(left.code, (char) left.value, left.freq,
							left.length);
			}
			this.left.preparingStatusTable(statusArray);

		}

		if (this.right != null) {
			if (this.right.isData) {
				if (this.right.value < 0)
					statusArray[this.right.value + 256] = new StatusObject(right.code, (char) (right.value + 256),
							right.freq, right.length);
				else
					statusArray[this.right.value] = new StatusObject(right.code, (char) right.value, right.freq,
							right.length);
			}
			this.right.preparingStatusTable(statusArray);

		}

	}

	public void preOrderLeaves() {
		if (this.left != null)
			this.left.preOrderLeaves();

		if (this.right != null)
			this.right.preOrderLeaves();

		if (this.left == null && this.right == null) {
			System.out.print("1" + (char) this.value);

		} else {
			System.out.print("0");
		}

	}

	// this method is used to traverse the tree based on the post order
	// appending 1 if the node is leaf followed by the leaf value in binary
	// else appending 0 if the node is non leaf
	// used
	public String postOrderCodingInFile() {

		String str = "";
		if (this.left == null && this.right == null) {

			str += "1";
			String toAdd = String.format("%8s", Integer.toBinaryString((this.value + 256) % 256)).replace(' ', '0');
			str += toAdd;
		} else {
			if (this.left != null) {
				str += this.left.postOrderCodingInFile();
			}
			if (this.right != null) {
				str += this.right.postOrderCodingInFile();
			}
			str += "0";

		}
		return str;
	}

	// this method is used to display an understandable version of the header
	// it uses the post order traversal
	// if the visited node is leaf we put 1 followed by the value of the leaf
	// else if the node is non leaf we put 0
	// used
	public String postOrderCodingInHeaderTextArea() {

		String str = "";
		if (this.left == null && this.right == null) {

			str += "1";

			str += (char) this.value;
		} else {
			if (this.left != null) {
				str += this.left.postOrderCodingInHeaderTextArea();
			}
			if (this.right != null) {
				str += this.right.postOrderCodingInHeaderTextArea();
			}
			str += "0";

		}
		return str;
	}

	// this method counts the non leaf nodes in the tree
	// used
	public int countingTheRoots() {
		int count = 0;
		if (this.left != null || this.right != null) {
			count = 1;
			count += this.left.countingTheRoots();
			count += this.right.countingTheRoots();
			return count;
		} else {
			return 0;
		}

	}

}
