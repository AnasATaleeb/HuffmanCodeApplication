package application;

public class DecompressionTreeNode {
	private char value;
	private String code;
	private DecompressionTreeNode right;
	private DecompressionTreeNode left;

	public DecompressionTreeNode() {
		super();
	}

	public DecompressionTreeNode(char value, String code) {
		super();
		this.value = value;
		this.code = code;
	}

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public DecompressionTreeNode getRight() {
		return right;
	}

	public void setRight(DecompressionTreeNode right) {
		this.right = right;
	}

	public DecompressionTreeNode getLeft() {
		return left;
	}

	public void setLeft(DecompressionTreeNode left) {
		this.left = left;
	}

	// This Method is used to generate the new code
	// by appending 0 to the code of the left node
	// and appending 1 to the code of the right node
	// used
	public void prepairingCode(String code) {

		if (this.left != null) {

			this.left.setCode(code + "0");

			this.left.prepairingCode(code + "0");

		}

		if (this.right != null) {

			this.right.setCode(code + "1");

			this.right.prepairingCode(code + "1");

		}

	}

	// this method is used to put the data of the generted tree in the status array
	// to display it as a table
	// used
	public void preparingStatusTable(DecompressStatusObject[] statusArray) {

		if (this.left != null) {

			if (this.left.left == null && this.left.right == null) {

				if (this.left.value < 0)
					statusArray[this.left.value + 256] = new DecompressStatusObject(left.code, (char) left.value);
				else
					statusArray[this.left.value] = new DecompressStatusObject(left.code, (char) left.value);

			}

			this.left.preparingStatusTable(statusArray);

		}

		if (this.right != null) {
			if (this.right.left == null && this.right.right == null) {

				if (this.right.value < 0)
					statusArray[this.right.value + 256] = new DecompressStatusObject(right.code, (char) right.value);
				else {
					statusArray[this.right.value] = new DecompressStatusObject(right.code, (char) right.value);
				}

			}
			this.right.preparingStatusTable(statusArray);
		}

	}

	// to check if the node is leaf
	public boolean isLeaf() {
		if (this.left == null && this.right == null) {
			return true;
		} else {
			return false;
		}
	}

}
