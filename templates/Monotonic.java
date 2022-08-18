//https://leetcode.com/discuss/study-guide/2347639/a-comprehensive-guide-and-template-for-monotonic-stack-based-problems


// Template for monotonic stack 
void build(int[] arr) {

	// stack stores index of element
	Stack<Integer> s = new Stack<>();

	for(int i=0;i<arr.length;i++) {

		while(!s.isEmpty() && requiredOppositeCondition(arr[i], arr[s.top()])) {
			int ele = s.pop();
			// do something with top element
		} 

		//dealing with previous
		if() {

		}

		s.push(i);
	}
}

// For Decreasing problems, use increasing stack
// For Increasing problems, use decreasing stack

void test(int[] arr){
	int n = arr.length;
        
    Stack<Integer> s = new Stack<>();
    int[] nextGreater = new int[n];
    int[] prevGreater = new int[n];
    
    Arrays.fill(nextGreater, -1);
    Arrays.fill(prevGreater, -1);
    
    for(int i=0;i<n;i++) {
        while(!s.isEmpty() && nums[s.peek()] < nums[i]) {
            nextGreater[s.pop()]= nums[i];
        }
        
        if(!s.isEmpty()) {
            prevGreater[i] =nums[s.peek()];
        }
        
        s.push(i);
    }
}
