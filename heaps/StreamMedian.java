import java.util.*;

class StreamMedian {
		
	public static void main(String args[]) {
		int arr[] = {50, 10, 30, 20, 40};
		int n = arr.length;
		median(arr, n);
	}

	static void median(int arr[], int n) {
		assert arr.length==n : "Invalid parameter"; 
		if(n==0)
			return;

		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(n, (a,b) -> b-a);

		maxHeap.offer(arr[0]);
		System.out.println(arr[0]);

		for(int i =1;i<n;i++) {
			if(maxHeap.size() > minHeap.size()) {
				if(arr[i] < maxHeap.peek()) {
					int top = maxHeap.poll();
					maxHeap.offer(arr[i]);
					minHeap.offer(top);
				} else {
					minHeap.offer(arr[i]);
				}

				System.out.println((maxHeap.peek()+minHeap.peek())/2.0);
			}
			else if(maxHeap.size() < minHeap.size()) {
				if(arr[i] > minHeap.peek()) {
					int top = minHeap.poll();
					minHeap.offer(arr[i]);
					maxHeap.offer(top);
				} else {
					maxHeap.offer(arr[i]);
				}

				System.out.println((maxHeap.peek()+minHeap.peek())/2.0);
			} 
			else {
				if(arr[i]<minHeap.peek()) {
					maxHeap.offer(arr[i]);
					System.out.println(maxHeap.peek());
				} else {
					minHeap.offer(arr[i]);
					System.out.println(minHeap.peek());
				}
			}
		}
	}
}