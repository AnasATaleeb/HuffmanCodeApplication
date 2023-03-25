package application;

public class Heap {
	private int size;
	private int index;
	private HeapNode ar[];

	public Heap() {
		super();

	}

	public Heap(int size, HeapNode array[], int index) {
		super();
		ar = array;
		this.size = size;
		this.index = index;
		buildHeap(array);
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public HeapNode[] getAr() {
		return ar;
	}

	public void setAr(HeapNode[] ar) {
		this.ar = ar;
	}

	public void insert(HeapNode x) {
		if (isFull()) {
			System.out.println("the heap is full");
			return;
		}
		int i = (++index);

		while (i > 1 && x.getFreq() < ar[i / 2].getFreq()) {

			ar[i] = ar[i / 2];
			i /= 2;
		}
		ar[i] = x;

	}

	public void buildHeap(HeapNode ar[]) {
		int starting = index / 2;
		for (int i = starting; i >= 1; i--) {
			heapify(i);
		}
	}

	public void heapify(int i) {
		int min = i;

		if ((i * 2) <= index && ar[min].getFreq() > ar[i * 2].getFreq()) {
			min = i * 2;
		}
		if ((i * 2 + 1) <= index && ar[min].getFreq() > ar[i * 2 + 1].getFreq()) {
			min = i * 2 + 1;
		}

		if (ar[min] != ar[i]) {
			HeapNode temp = ar[min];
			ar[min] = ar[i];
			ar[i] = temp;
			heapify(min);
		}

	}

	public HeapNode deleteMin() {
		int i, child;
		HeapNode min, last;
		if (isEmpty()) {
			System.out.println("it's empty");
			return null;
		}

		min = this.ar[1];
		last = this.ar[index--];
		for (i = 1; i * 2 <= index; i = child) {
			child = i * 2;
			if ((child != index) && this.ar[child + 1].getFreq() < ar[child].getFreq())
				child++;
			if (last.getFreq() > ar[child].getFreq()) {
				ar[i] = ar[child];
			} else {
				break;
			}
		}
		ar[i] = last;
		return min;

	}

	public boolean isEmpty() {
		if (index == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isFull() {
		if (index + 1 == size) {
			return true;
		} else {
			return false;
		}
	}

	

}
