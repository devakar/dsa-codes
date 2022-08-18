import java.util.*;

class Node {
	int data;
	Node left;
	Node right;
	Node next;

	public Node(int key) {
		data = key;
		left=null;
		right= null;
		next=null;
	}
}

class Tree {
	Node root;
	Node head;
	Node tail;

	public Tree() {
		root = null;
		head = null;
		tail = null;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public void connectNextIterative() {
		if(root == null)
			return;

		Queue<Node> q = new LinkedList<>();
		q.offer(root);

		while(!q.isEmpty()) {
			int n = q.size();
			Node prev = null;
			for(int i=0;i<n;i++) {
				Node cur = q.poll();
				if(prev != null) {
					prev.next = cur;
				}
				if(cur.left != null) {
					q.offer(cur.left);
				}
				if(cur.right != null) {
					q.offer(cur.right);
				}
				prev = cur;
			}
			prev.next = null;
		}
	}

	public void connectNextRecursive() {
		if(root == null)
			return;

		connectNextRecursiveUtil(root);
	}

	private void connectNextRecursiveUtil(Node root) {
		if(root == null)
			return;
		if(root.left != null) {

			root.left.next = root.right;
			connectNextRecursiveUtil(root.left);
		}

		if(root.right != null) {
			if(root.next != null)
				root.right.next = root.next.left;
			else
				root.right.next = null;
			connectNextRecursiveUtil(root.right);
		}
	}

	public void convertToDLL() {
		if(root == null)
			return;

		convertToDLLutil(root);
	}

	private void convertToDLLutil(Node root) {
		if(root == null)
			return;
		convertToDLLutil(root.left);
		if(head == null)
			head = root;

		root.left = tail;
		if(tail!=null) 
			tail.right=root;
		tail=root;

		convertToDLLutil(root.right);

	}

	public void printSideWays(Node node) {
		Node cur = node;
		while(cur!=null) {
			System.out.print(cur.data+" ");
			cur=cur.next;
		}
		System.out.println();
	}

	public void printRightWays() {
		Node cur = head;
		while(cur!=null) {
			System.out.print(cur.data+" ");
			cur=cur.right;
		}
		System.out.println();
	}
}

class SpecialBinaryTree {
	public static void main(String args[]) {
		Tree tree = new Tree();
		Node root = new Node(1);
		tree.setRoot(root);
		root.left=new Node(2);
		root.right=new Node(3);
		root.left.left=new Node(4);
		root.left.right=new Node(5);
		root.left.right.right=new Node(8);
		root.right.left=new Node(6);
		root.right.right=new Node(7);

		//tree.connectNextIterative();
		// tree.connectNextRecursive();
		// tree.printSideWays(root);
		// tree.printSideWays(root.left);
		// tree.printSideWays(root.left.left);
		tree.convertToDLL();
		tree.printRightWays();
	}
}