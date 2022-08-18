import java.util.*;

class Heap {

	public void heapSort(int arr []) {
		int n = arr.length;
		if(n<=0)
			return;

		// As last n/2 nodes are leaf nodes which are laready heapified no need to heapify
		for(int i = n/2-1; i>=0; i--) 
			heapify(arr, n, i);


		// first element is max, so need to replace with last element
		// and heapfiy first element wrt array until second last element
		for(int i = n-1; i>0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;

			heapify(arr, i, 0);
		}
	}

	public void heapify(int arr[], int n, int i) {

		// if viusalize tree with index 0 as root,
		// then left should have 1 and right should have 1
		int l = 2*i+1;
		int r = 2*i+2;


		//ith is root element and should be max
		// if ith is less than left element, than swap them, 
		// and heapify from left element  
		if(l<n && arr[i]<arr[l]) {
			// swap ith and lth element,  and set  i=l
			int temp = arr[i];
			arr[i] = arr[l];
			arr[l] = temp;
			heapify(arr, n, l);
		}
		//ith is root element and should be max
		// if ith is less than right element, than swap them, 
		// and heapify from right element 
		if(r<n && arr[i]<arr[r]) {
			// swap ith and rth element, set i=r
			int temp = arr[i];
			arr[i] = arr[r];
			arr[r] = temp;
			heapify(arr, n, r);
		}
	}

	public static void main(String args[]) {
		int arr[] = { 12, 11, 13, 5, 6, 7 };
        int n = arr.length;
  
        Heap heap = new Heap();
        heap.heapSort(arr);
  
        System.out.println("Sorted array is ");
        printArray(arr);
        
	}

	static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}