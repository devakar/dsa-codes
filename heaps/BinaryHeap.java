import java.util.*;

class BinaryHeap<T> {

	final int DEFAULT_CAPACITY = 11;
	int capacity;
	int size;
	int counter;
	T top;
	T heap[]; 

	public BinaryHeap() {
		capacity = DEFAULT_CAPACITY;
		size = 0;
		counter = 0;
		heap = new T [capacity];
		top = null;
	}

	public BinaryHeap(int cap) {
		capacity = cap;
		size = 0;
		counter = 0;
		heap = new T [capacity];
		top = null;
	}

	


	public static void main(String args[]) {

	}
}