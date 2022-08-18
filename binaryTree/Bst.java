import java.util.*;

class Node {
	int data;
	Node left;
	Node right;

	public Node(int key) {
		data = key;
		left=null;
		right=null;
	}
}

class Tree {
	Node root;

	public Tree() {
		root =null;
	}

	public Node getRoot() {
		return this.root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public void inOrder() {
		inOrderUtil(root);
		System.out.println();
	}

	private void inOrderUtil(Node root) {
		if(root == null)
			return;
		inOrderUtil(root.left);
		System.out.print(root.data+" ");
		inOrderUtil(root.right);
	}

	public boolean isBst() {
		if(root == null)
			return true;
		return isBstUtil(root, null, null);
	}

	private boolean isBstUtil(Node root, Node min, Node max) {
		if(root == null)
			return true;

		if(min != null && min.data>root.data)
			return false;
		if(max != null && max.data<root.data)
			return false;	 

		return isBstUtil(root.left, min, root) && isBstUtil(root.right, root, max);
	}

	public Node lca(int n1, int n2) {
		if(root == null)
			return null;

		return lcaUtil(root, n1, n2);
	}

	private Node lcaUtil(Node root, int n1, int n2) {
		if(root == null)
			return null;

		if(n1<root.data && n2<root.data)
			return lcaUtil(root.left, n1, n2);
		if(n1>root.data && n2>root.data)
			return lcaUtil(root.right, n1, n2);
		return root;
	}

	public int floor(int x) {
		if(root == null)
			return -1;

		return floorUtil(root, x);
	}

	private int floorUtil(Node root, int x) {
		if(root == null)
			return -1;

		if(root.data > x) 
			floorUtil(root.left, x);
		int floor = floorUtil(root.right, x);

		return (floor <=x && floor !=-1)?floor:root.data;
	}

	public int ceil(int x) {
		if(root == null)
			return -1;

		return ceilUtil(root, x);
	}

	private int ceilUtil(Node root, int x) {
		if(root == null)
			return -1;

		if(root.data<x)
			ceilUtil(root.right, x);
		int ceil = ceilUtil(root.left, x);

		return (ceil>=x && ceil !=-1)?ceil:root.data;
	}

	public int kthLargest(int k) {
		if(root == null)
			return -1;

		Result count = new Result();
		Result result = new Result(-1);

		kthLargestUtil(root, result, count, k);
		return result.getResult();
	}

	private void kthLargestUtil(Node root, Result result, Result count, int k) {
		if(root == null || count.getResult()>k){
			return;
		}


		kthLargestUtil(root.right, result, count, k);

		count.setResult(count.getResult()+1);

		if(count.getResult()==k) {
			result.setResult(root.data);
			return;
		}

		kthLargestUtil(root.left, result, count, k);

	}

	public int sizeOfLargestBstInTree() {
		if(root == null)
			return -1;

		NodeInfo info = sizeOfLargestBstInTreeUtil(root);
		return info.size;
	}

	private NodeInfo sizeOfLargestBstInTreeUtil(Node root) {
		if(root == null) {
			return new NodeInfo();
		}

		NodeInfo left = sizeOfLargestBstInTreeUtil(root.left);
		NodeInfo right = sizeOfLargestBstInTreeUtil(root.right);

		NodeInfo cur = new NodeInfo();

		cur.min = Math.min(Math.min(left.min, right.min), root.data);
		cur.max = Math.max(Math.max(left.max, right.max), root.data);

		cur.isBst = root.data>left.max && root.data<right.min && left.isBst && right.isBst;

		if(cur.isBst) 
			cur.size = left.size + right.size + 1;
		else
			cur.size = Math.max(left.size, right.size);

		return cur;

	}

	public Node mergeTwoBst(Node node1, Node node2) {
		if(node1 == null && node2 == null)
			return null;
		if(node1==null)
			return node2;
		if(node2==null)
			return node1;


		List<Integer> first = new ArrayList<> ();
		createArrayFromBst(node1, first);
		List<Integer> second = new ArrayList<> ();
		createArrayFromBst(node2, second);

		List<Integer> merged = mergeTwoSortedArray(first, second);

		Node root = createBstFromSortedArray(merged);
		return root;
	}

	public void createArrayFromBst(Node root, List<Integer> list) {
		if(root == null)
			return;
		createArrayFromBst(root.left, list);
		list.add(root.data);
		createArrayFromBst(root.right, list);
	}

	public List<Integer> mergeTwoSortedArray(List<Integer> list1, List<Integer> list2) {
		if(list1 == null && list2 == null)
			return new ArrayList<Integer>();
		if(list1==null)
			return list2;
		if(list2==null)
			return list1;

		int n1 = list1.size();
		int n2 = list2.size();
		List<Integer> merged = new ArrayList<>();

		int i=0;
		int j=0;

		while(i<n1 && j<n2) {
			if(list1.get(i) < list2.get(j)) {
				merged.add(list1.get(i));
				i++;
			} else {
				merged.add(list2.get(j));
				j++;
			}
		}

		while(i<n1) {
			merged.add(list1.get(i));
			i++;
		}

		while(j<n2) {
			merged.add(list2.get(j));
			j++;
		}

		return merged;
	}

	public Node createBstFromSortedArray(List<Integer> list) {
		if(list == null)
			return null;
		int n = list.size();
		if(n<=0)
			return null;

		int l = 0;
		int r = n-1;

		return createBstFromSortedArrayUtil(list, l, r);
	}

	private Node createBstFromSortedArrayUtil(List<Integer> list, int l, int r) {
		if(r<l)
			return null;

		int mid = l + ((r-l)/2);

		Node root = new Node(list.get(mid));

		root.left = createBstFromSortedArrayUtil(list, l, mid-1);
		root.right = createBstFromSortedArrayUtil(list, mid+1, r);
		return root;
	}
}

class NodeInfo {
	int min;
	int max;
	int size;
	boolean isBst;

	public NodeInfo() {
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		size = 0;
		isBst = true;
	}

	public NodeInfo(int min, int max, int size, boolean isBst) {
		this.min = min;
		this.max = max;
		this.size = size;
		this.isBst = isBst;
	}
}

class Result {
	int result;

	public Result() {
		result = 0;
	}

	public Result(int res) {
		this.result = res;
	}

	public int getResult() {
		return this.result;
	}

	public void setResult(int res) {
		this.result = res;
	}
}

class Bst {
	public static void main(String args[]) {
		Tree tree = new Tree();
		Node root = new Node(50);
		tree.setRoot(root);
		root.left = new Node(30);
		root.right = new Node(70);
		root.left.left = new Node(20);
		root.left.right = new Node(40);
		root.right.left = new Node(60);
		root.right.right = new Node(80); 
		tree.inOrder();
		System.out.println(tree.isBst());
		System.out.println(tree.lca(40,60).data);
		System.out.println(tree.floor(100));
		System.out.println(tree.ceil(10));
		System.out.println(tree.kthLargest(80));
        System.out.println(tree.sizeOfLargestBstInTree());

        Tree tree2 = new Tree();
		Node root2 = new Node(55);
		tree2.setRoot(root2);
		root2.left = new Node(35);
		root2.right = new Node(75);
		root2.left.left = new Node(25);
		root2.left.right = new Node(45);
		root2.right.left = new Node(65);
		root2.right.right = new Node(85); 
		tree2.inOrder();
		System.out.println(tree2.isBst());

		Node merged = tree.mergeTwoBst(root, root2);
		Tree tree3 = new Tree();
		tree3.setRoot(merged);
		tree3.inOrder();
		System.out.println(tree3.isBst());
	}
}