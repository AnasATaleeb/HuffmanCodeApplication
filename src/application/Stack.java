package application;

public class Stack{
	
	private Node head;
	private int counter;

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void push(Object o) {
		insertFirst(o);
	}

	public Object pop() {
		return deleteFirst();

	}

	public Object peek() {
		if (head == null) {
			System.out.println("the stack is empty");
			return null;

		} else {
			return head.value;
			
		}
	}

	public void insertFirst(Object value) {
		Node node = new Node(value);
		if (head == null) {
			head = node;

		} else {
			node.setNext(head);
			head = node;

		}

		counter++;

	}
	public Object deleteFirst() {
		if (head == null) {
			System.out.println("the list is empty");
			return null;
		}
			else {
			Object o = head.value;
			head = head.getNext();

			counter--;
			return o;

		}

	}

	public void traversing() {
		Node temp = head;
		while (temp != null) {
			System.out.print(temp.getValue() + "->");
			temp = temp.getNext();

		}
		System.out.println();
	}
}
