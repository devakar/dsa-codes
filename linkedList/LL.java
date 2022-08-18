import java.util.*;

class LinkedList {
	Node head;

	public LinkedList() {
		head = null;
	}

	public void addAtfirst(int key) {
		Node newNode = new Node(key);
		newNode.next = head;
		if(head != null) {
			head.prev = newNode;
		}
		head = newNode;
	}

	public void addAtLast(int key) {
		Node newNode = new Node(key);
		if(head == null) {
			head = newNode;
			return;
		}
		Node cur = head;
		while(cur.next != null) {
			cur = cur.next;
		}
		cur.next = newNode;
		newNode.prev = cur;
	}

	public void addAtPosition(int key, int pos) {
		int curPos = 0;
		Node cur = head;

		while(curPos < pos && cur != null && cur.next != null) {
			curPos++;
			cur = cur.next;
		}

		if(curPos < pos || cur == null) {
			return;
		}

		Node newNode = new Node(key);
		newNode.next = cur.next;
		newNode.prev = cur;
		cur.next.prev = newNode;
		cur.next = newNode;
	}

	public void deleteAtFirst() {
		if(head == null)
			return;
		Node next = head.next;
		head.next = null;
		next.prev = null;
		head = next;
	}

	public void deleteAtLast() {
		if(head == null)
			return;
		Node cur = head;
		while(cur.next != null) {
			cur = cur.next;
		}

		Node prev = cur.prev;
		cur.prev = null;
		if(prev!=null)
			prev.next = null;
	}

	public void deleteAtPosition(int pos) {
		if(head == null)
			return;
		int curPos = 0;
		Node cur = head;
		while(curPos<pos && cur.next!=null) {
			curPos++;
			cur = cur.next;
		}

		if(curPos<pos)
			return;

		Node prev = cur.prev;
		Node next = cur.next;

		prev.next = next;
		if(next!=null)
			next.prev = prev;

		cur.prev = null;
		cur.next = null;
	}

	public void printLinkedList() {
		Node cur = head;
		while(cur != null) {
			System.out.println(cur.data+" ");
			cur = cur.next;
		}
		System.out.println();
	}

	public void printLinkedListInReverse() {
		Node cur = head;
		while(cur.next != null) {
			cur = cur.next;
		}

		while(cur != null) {
			System.out.println(cur.data+" ");
			cur = cur.prev;
		}
		System.out.println();
	}

	public void reverseIterative() {

	}

	public void reverseRecursive() {

	}

	public void reverseIterativeInGroup(int k) {

	}

	public void reverseRecursiveInGroup(int k) {
		
	}
}

class Node {
	int data;
	Node prev;
	Node next;

	public Node (int key) {
		data = key;
		prev = null;
		next = null;
	}
}

public class LL {
	public static void main(String args[]) {
		LinkedList ll = new LinkedList();
		ll.addAtfirst(1);
		ll.addAtfirst(2);
		ll.addAtLast(3);
		ll.addAtLast(4);
		ll.addAtPosition(5, 2);
		ll.deleteAtFirst();
		ll.deleteAtLast();
		ll.deleteAtPosition(2);
		ll.printLinkedList();
		ll.printLinkedListInReverse();
	}
}

