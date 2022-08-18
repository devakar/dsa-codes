import java.util.*;

class Node {
	int data;
	Node next;

	public Node(int key) {
		data = key;
		next = null;
	}
}

class SpecialNode {
	int data;
	SpecialNode down;
	SpecialNode right;

	public SpecialNode(int key) {
		data=key;
		down=null;
		right=null;
	}
}

class SpecialLinkedList {
	SpecialNode root;

	public SpecialLinkedList() {
		root = null;
	}

	public SpecialLinkedList(SpecialNode root) {
		this.root = root;
	}

	public SpecialNode getRoot() {
		return root;
	}

	public SpecialNode flatten(SpecialNode root) {
		if(root==null || root.right==null)
			return root;

		root.right=flatten(root.right);
		root=mergeRecursive(root, root.right);
		return root;
		
	}

	private SpecialNode mergeRecursive(SpecialNode root1, SpecialNode root2) {
		if(root1 == null)
			return root2;
		if(root2 == null)
			return root1;
		SpecialNode result;
		if(root1.data<root2.data) {
			result = root1;
			result.down=mergeRecursive(root1.down, root2);
		}else{
			result = root2;
			result.down=mergeRecursive(root1, root2.down);
		}
		result.right=null;
		return result;
	}

	public void printSpecialLinkedListDownWay() {
		if(root == null)
			return;
		while(root!=null) {
			System.out.print(root.data+" ");
			root=root.down;
		}
		System.out.println();
	}
}

class ArbitraryLinkedList {
	ArbitraryNode head;

	public ArbitraryLinkedList() {
		head = null;
	}

	public ArbitraryNode getHead() {
		return this.head;
	}

	public void setHead(ArbitraryNode head) {
		this.head = head;
	}

	public void createHardCodedList() {
		ArbitraryNode head = new ArbitraryNode(1);
		this.head = head;

		head.next = new ArbitraryNode(2);
		head.next.next = new ArbitraryNode(3);
		head.next.next.next = new ArbitraryNode(4);
		head.next.next.next.next = new ArbitraryNode(5);

		head.random = head.next.next.next;
		head.next.random = head;
		head.next.next.random = head.next.next.next.next;
		head.next.next.next.random = head.next.next;
		head.next.next.next.next.random = head.next;
	}

	public void printArbitraryLinkedListFromNextPointer() {
		ArbitraryNode cur = head;
		while(cur!=null){
			System.out.print(cur.data+" ");
			cur=cur.next;
		}
		System.out.println();
	}

	public void printArbitraryLinkedListFromRandomPointer() {
		ArbitraryNode cur = head;
		System.out.print(cur.data+" ");
		cur = cur.random;
		while(cur!=null && cur!=this.head){
			System.out.print(cur.data+" ");
			cur=cur.random;
		}
		System.out.println();
	}

	public ArbitraryLinkedList clone() {
		ArbitraryLinkedList result = new ArbitraryLinkedList();
		ArbitraryNode head = result.getHead();

		ArbitraryNode parentHead = this.head;

		// add additional nodes
		while(parentHead!=null) {
			ArbitraryNode newNode = new ArbitraryNode(parentHead.data);
			//System.out.println(parentHead+" "+parentHead.data);
			newNode.next=parentHead.next;

			parentHead.next=newNode;
			parentHead=newNode.next;
			//System.out.println(parentHead+" "+parentHead.data);
		}

		head = this.head.next;
		//System.out.println(head+" "+head.data);
		result.setHead(head);
		//result.printArbitraryLinkedListFromNextPointer();

		parentHead = this.head;
		while(parentHead!=null && parentHead.next!=null) {
			parentHead.next.random = parentHead.random.next; 
			parentHead = parentHead.next.next;
		}

		head = this.head.next;
		//System.out.println(head+" "+head.data);

		// remove the links
		parentHead = this.head;
		while(parentHead!=null && parentHead.next!=null){
			parentHead.next=parentHead.next.next;
			parentHead=parentHead.next;
		}
		//System.out.println(result.getHead()+" "+result.getHead().data);
		return result;
	}
} 

class ArbitraryNode {
	int data;
	ArbitraryNode next;
	ArbitraryNode random;

	public ArbitraryNode(int key) {
		data = key;
		next = null;
		random = null;
	}
}

class LinkedList {
	Node head;
	Node current;

	public LinkedList() {
		head = null;
	}

	public LinkedList(Node head) {
		this.head = head;
	}

	public Node getHead() {
		return head;
	}

	public void addAtBegining(int key) {
		Node newNode = new Node(key);
		newNode.next = head;
		head = newNode;
	}

	public void printLinkedList() {
		Node cur = head;
		while(cur!=null) {
			System.out.print(cur.data+" ");
			cur = cur.next;
		}
		System.out.println();
	}

	public void reverseIterative() {
		if(head == null)
			return;

		Node prev = null;
		Node cur = head;
		Node next = null;

		while(cur!= null) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}

		head = prev;
	}

	public void reverseRecursive() {
		if(head == null)
			return;

		Node prev = null;
		Node cur = head;

		head = reverseRecursiveUtil(cur, prev);
	}

	public Node reverseRecursiveUtil(Node cur, Node prev) {
		if(cur == null)
			return cur;

		if(cur.next == null) {
			head = cur;
			cur.next = prev;
			return head;
		}

		Node next = cur.next;
		cur.next = prev;
		prev= cur;
		cur=next;
		reverseRecursiveUtil(cur, prev);
		return head;
	}

	public void reverseInGroupIterative(int k) {
		if(head == null)
			return;
		int l = 0;
		Node prev = null;
		Node cur = head;
		Node next = null;
		Node previousFirstInGroup = null;
		Node previousLastInGroup = null;
		Node firstInGroup = null;
		Node lastInGroup = null;
		boolean flag =true;

		while(cur!=null) {
			l=0;
			prev = null;
			next = null;
			firstInGroup = cur;
			while(l!=k && cur!=null) {
				next = cur.next;
				cur.next=prev;
				prev=cur;
				cur=next;
				l++;
			}

			if(flag) {
				head=prev;
				flag=false;
			}

			lastInGroup = prev;

			if(previousFirstInGroup != null) {
				previousFirstInGroup.next = lastInGroup;
			}

			previousFirstInGroup = firstInGroup;
			previousLastInGroup = lastInGroup;
		}
	}

	public void reverseInGroupRecursive(int k) {
		if(head == null)
			return;
		head = reverseInGroupRecursiveUtil(head, k);
	}

	public Node reverseInGroupRecursiveUtil(Node head, int k) {
		if(head == null)
			return head;

		Node prev=null;
		Node cur = head;
		Node next = null;

		int count =0;

		while(count!=k && cur!=null) {
			next = cur.next;
			cur.next=prev;
			prev=cur;
			cur=next;
			count++;
		}

		if(next != null) {
			head.next = reverseInGroupRecursiveUtil(cur, k);
		}
		return prev;
	}

	public void pairWiseSwapIterative() {
		if(head==null)
			return;

		Node cur = head;

		while(cur!=null && cur.next!=null) {
			int temp = cur.data;
			cur.data = cur.next.data;
			cur.next.data = temp;
			cur=cur.next.next;
		}
	}

	public void pairWiseSwapRecursive() {
		if(head==null)
			return;
		pairWiseSwapRecursiveUtil(head);
	}

	public void pairWiseSwapRecursiveUtil(Node head) {
		if(head==null || head.next==null)
			return;
		Node cur = head;
		int temp = cur.data;
		cur.data = cur.next.data;
		cur.next.data = temp;
		cur=cur.next.next;
		pairWiseSwapRecursiveUtil(cur);
	}

	public void rotateByTimes(int t) {
		if(head == null)
			return;
		Node cur = head;
		int count = 1;
		while(count<t && cur!=null) {
			cur =cur.next;
			count++;
		}
		Node next=cur.next;
		if(next == null)
			return;

		cur.next=null;
		cur=next;
		while(cur.next!=null) {
			cur=cur.next;
		}
		cur.next=head;
		head=next;
	}

	// utility harcoded to create loop, at least 6 element in LL
	public void createLoop() {
		if(head == null)
			return;
		Node last=head.next.next.next.next.next;
		//System.out.println(last+" "+last.data);
		Node loopStart = head.next.next.next;
		//System.out.println(loopStart+" "+loopStart.data);
		last.next=loopStart;
	}

	public boolean detectLoop() {
		if(head==null || head.next==null)
			return false;
		Node slow = head;
		Node fast = head;

		while(slow != null && fast != null && fast.next != null) {
			slow = slow.next;
			fast=fast.next.next;
			if(slow==fast)
				return true;
		}
		return false;
	}

	public void removeLoop() {
		if(head==null || head.next==null)
			return;
		Node slow = head;
		Node fast = head;
		boolean isloop = false; 

		while(slow != null && fast != null && fast.next != null) {
			slow = slow.next;
			fast=fast.next.next;
			if(slow==fast) {
				isloop = true;
				break;
			}
		}

		if(!isloop)
			return;

		slow = head;
		Node prev = fast;
		while(slow != null && fast != null && fast.next != null) {
			prev = fast;
			slow = slow.next;
			fast=fast.next;
			if(slow==fast) {
				break;
			}
		}

		prev.next=null;
	}

	public int nthFromEnd(int k) {
		if(head==null)
			return -1;

		Node slow = head;
		Node fast = head;
		int count = 0;
		while(count<k && fast!=null) {
			count++;
			fast = fast.next;
		}

		if(fast == null) {
			return -1;
		}

		while(fast!=null) {
			slow = slow.next;
			fast = fast.next;
		}

		return slow.data;
	}
	
	public int middleElement() {
		if(head == null)
			return -1;
		Node slow=head;
		Node fast = head;

		while(slow!=null && fast!=null && fast.next!=null){
			slow=slow.next;
			fast=fast.next.next;
		}
		return slow.data;
	}

	public int intersectionPoint(Node head1, Node head2) {
		if(head1 == null || head2 == null)
			return -1;

		int l1=0, l2=0;

		Node cur1=head1, cur2=head2;

		while(cur1!=null) {
			l1++;
			cur1=cur1.next;
		}

		while(cur2!=null) {
			l2++;
			cur2=cur2.next;
		}

		int diff=l1-l2;
		
		if(diff>0) {
			return intersectionPointUtil(head1, head2, diff);
		} else {
			return intersectionPointUtil(head2, head1, -1*diff);
		}
	}

	private int intersectionPointUtil(Node longHead, Node shortHead, int diff) {
		Node cur1=longHead, cur2=shortHead;
		for(int i=0;i<diff;i++){
			cur1=cur1.next;
		}

		while(cur1!=null && cur2!=null) {
				if(cur1==cur2) {
					return cur1.data;
				}
				cur1=cur1.next;
				cur2=cur2.next;
			}
			return -1;
	}

	public Node mergeRecursive(Node head1, Node head2) {
		if(head1==null)
			return head2;
		if(head2==null)
			return head1;

		Node head;
		if(head1.data<head2.data) {
			head1.next = mergeRecursive(head1.next, head2);
			head = head1;
		}
		else {
			head2.next = mergeRecursive(head1, head2.next);
			head=head2;
		}
		return head;
	}

	public Node mergeIterative(Node head1, Node head2) {
		if(head1 == null)
			return head2;

		if(head2 == null)
			return head1;

		Node cur1 = head1, cur2=head2;
		Node result=null;

		if(head1.data<head2.data) {
			result=head1;
			cur1=cur1.next;
		}
		else{
			result=head2;
			cur2=cur2.next;
		}
		Node cur = result;

		while(cur1!=null && cur2!=null) {
			if(cur1.data<cur2.data){
				cur.next=cur1;
				cur1=cur1.next;
			}else{
				cur.next=cur2;
				cur2=cur2.next;
			}
			cur=cur.next;
		}

		if(cur1!=null&&cur!=null) {
			cur.next=cur1;
		}

		if(cur2!=null&&cur!=null) {
			cur.next=cur2;
		}

		return result;
	}

	public Node sum(Node head1, Node head2) {
		if(head1==null)
			return head2;
		if(head2==null)
			return head1;

		LinkedList l1 = new LinkedList(head1);
		LinkedList l2 = new LinkedList(head2);
		l1.reverseRecursive();
		l2.reverseRecursive();

		Node cur1 = l1.getHead();
		Node cur2 = l2.getHead();

		System.out.println(cur1+" "+cur1.data);
		System.out.println(cur2+" "+cur2.data);

		Node result=null;
		int carry =0;

		while(cur1!=null || cur2!=null || carry==1) {
			int value = carry;
			if(cur1!=null) {
				value+=cur1.data;
				cur1=cur1.next;
			}
			if(cur2!=null){
				value+=cur2.data;
				cur2=cur2.next;
			}
			carry = value/10;
			value = value%10;

			Node newNode = new Node(value);
			newNode.next = result;
			result = newNode;
		}
		return result;
	}

	public Node mergeSort(Node head) {
		if(head==null || head.next==null)
			return head;

		Node slow=head, fast=head, prev=null;
		while(fast!=null && fast.next!=null) {
			prev=slow;
			slow=slow.next;
			fast=fast.next.next;
		}

		prev.next=null;
		Node left=mergeSort(head);
		Node right=mergeSort(slow);
		return mergeRecursive(left, right); 
	}

	public Node insertionSort(Node head){
		if(head==null||head.next==null)
			return head;

		Node cur=head, result=head;

		while(cur!=null){
			Node next=cur.next;
			result=insertionSortUtil(cur, result);
			cur=next;
		}
		return result;
	}

	private Node insertionSortUtil(Node head, Node result) {
		if(result==null || result.data>=head.data) {
			head.next=result;
			result=head;
		}else{
			Node cur = result;
			while(cur.next!=null && cur.next.data<head.data)
				cur=cur.next;
			head.next=cur.next;
			cur.next=head;
		}
		return result;
	}

	public boolean isPalindrome(Node head) {
		
		this.current = this.head;
		if(head == null)
			return true;
		boolean isp = isPalindrome(head.next);
		if(!isp)
			return false;

		isp = (this.current.data==head.data);
		System.out.println("isp : "+isp+"  "+"cur.data : "+this.current.data+"  "+"head.data : "+head.data);
		this.current=this.current.next;
		return isp;
	}

}


class LL2 {
	public static void main(String args[]) {
		LinkedList ll = new LinkedList();
		ll.addAtBegining(1);
		ll.addAtBegining(2);
		ll.addAtBegining(3);
		ll.addAtBegining(4);
		ll.addAtBegining(5);
		ll.addAtBegining(6);
		ll.addAtBegining(7);
		//ll.printLinkedList();
		//ll.reverseIterative();
		//System.out.println(ll.head+" "+ll.head.data);
		ll.printLinkedList();
		/*ll.reverseRecursive();
		ll.printLinkedList();
		ll.reverseInGroupIterative(3);
		ll.printLinkedList();
		ll.reverseInGroupRecursive(3);
		ll.printLinkedList();
		ll.pairWiseSwapIterative();
		ll.printLinkedList();
		ll.pairWiseSwapRecursive();
		ll.printLinkedList();
		ll.rotateByTimes(4);
		ll.printLinkedList();*/
		/*LinkedList ll2= new LinkedList();
		ll2.addAtBegining(1);
		ll2.addAtBegining(2);
		ll2.addAtBegining(3);
		ll2.addAtBegining(4);
		ll2.addAtBegining(5);
		ll2.addAtBegining(6);
		ll2.reverseRecursive();
		ll2.printLinkedList();
		ll2.createLoop();
		System.out.println(ll.detectLoop());
		System.out.println(ll2.detectLoop());
		ll2.removeLoop();
		ll2.printLinkedList();
		ll.removeLoop();
		ll.printLinkedList();
		System.out.println(ll.nthFromEnd(2));
		System.out.println(ll.middleElement());*/
		LinkedList ll3 =new LinkedList();
		ll3.addAtBegining(7);
		ll3.addAtBegining(8);
		ll3.addAtBegining(9);
		ll3.addAtBegining(10);
		ll3.reverseRecursive();
		ll3.printLinkedList();
		Node head3 = ll3.getHead();
		Node head1 = ll.getHead();
		/*head3.next.next.next.next=head1.next.next.next;
		ll3.printLinkedList();
		System.out.println(ll.intersectionPoint(head1,head3));*/
		//Node mergedHead = ll.mergeRecursive(head1, head3);
		/*Node mergedHead = ll.mergeIterative(head1, head3);
		LinkedList ll4 = new LinkedList(mergedHead);
		ll4.printLinkedList();*/


		/*SpecialNode root = new SpecialNode(5);
		root.down =  new SpecialNode(7);
		root.down.down =  new SpecialNode(8);;
		root.down.down.down =  new SpecialNode(30);

		root.right= new SpecialNode(10);
		root.right.down= new SpecialNode(20);

		root.right.right= new SpecialNode(19);
		root.right.right.down = new SpecialNode(22);
		root.right.right.down.down = new SpecialNode(50);
		SpecialLinkedList sl = new SpecialLinkedList(root);
		sl.flatten(root);
		sl.printSpecialLinkedListDownWay();*/
		
		// Node sumHead = ll.sum(head1, head3);
		// LinkedList ll4 = new LinkedList(sumHead);
		// ll4.printLinkedList();

		//Node head=ll.mergeSort(head1);
		// Node head=ll.insertionSort(head1);
		// LinkedList ll5 =new LinkedList(head);
		// ll5.printLinkedList();

		// LinkedList p_ll = new LinkedList();
		// p_ll.addAtBegining(1);
		// p_ll.addAtBegining(2);
		// p_ll.addAtBegining(1);
		// p_ll.printLinkedList();
		// System.out.println(p_ll.isPalindrome(p_ll.getHead()));

		ArbitraryLinkedList all = new ArbitraryLinkedList();
		all.createHardCodedList();
		all.printArbitraryLinkedListFromNextPointer();
		all.printArbitraryLinkedListFromRandomPointer(); 
		ArbitraryLinkedList all2 = all.clone();
		//System.out.println(all2.getHead()+" "+all2.getHead().data);
		all2.printArbitraryLinkedListFromNextPointer();
		all2.printArbitraryLinkedListFromRandomPointer();
		all.printArbitraryLinkedListFromNextPointer();
		all.printArbitraryLinkedListFromRandomPointer(); 

	}
}