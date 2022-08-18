import java.util.*;
import javafx.util.*;
import java.lang.*;

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

class Tree{
	Node root;

	public Tree() {
		root= null;
	}

	public Node getRoot() {
		return this.root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public void preOrderRecursive() {
		if(root == null)
			return;
		preOrderRecursiveUtil(root);
		System.out.println();
	}

	private void preOrderRecursiveUtil(Node root) {
		if(root ==null)
			return;

		System.out.print(root.data+" ");
		preOrderRecursiveUtil(root.left);
		preOrderRecursiveUtil(root.right);
	}

	public void preOrderIterative() {
		if(root == null)
			return;
		Stack<Node> s = new Stack<Node>();
		s.push(root);
		while(!s.isEmpty()) {
			Node cur = s.pop();
			System.out.print(cur.data+" ");
			if(cur.right != null)
				s.push(cur.right);
			if(cur.left != null)
				s.push(cur.left);
		}

		System.out.println();
	}

	public void inOrderRecursive() {
		if(root == null)
			return;

		inOrderRecursiveUtil(root);
		System.out.println();
	}

	private void inOrderRecursiveUtil(Node root) {
		if(root == null)
			return;
		inOrderRecursiveUtil(root.left);
		System.out.print(root.data+" ");
		inOrderRecursiveUtil(root.right);
	}

	public void inOrderIterative() {
		if(root == null)
			return;

		Stack<Node> s=new Stack<Node>();
		Node start = root;
		while(start!=null) {
			s.push(start);
			start=start.left;
		}

		while(!s.isEmpty()) {
			Node cur = s.pop();
			System.out.print(cur.data+" ");
			Node right = cur.right;
			
			while(right!=null) {
				s.push(right);
				right=right.left;
			}
		}
		System.out.println();
	}

	public void postOrderRecursive() {
		if(root == null)
			return;

		postOrderRecursiveUtil(root);
		System.out.println();
	}

	private void postOrderRecursiveUtil(Node root) {
		if(root == null)
			return;
		postOrderRecursiveUtil(root.left);
		postOrderRecursiveUtil(root.right);
		System.out.print(root.data+" ");
	}

	public void postOrderIterativeUsingTwoStacks() {
		if(root == null)
			return;
		Stack<Node> s1 = new Stack<>();
		Stack<Node> s2 = new Stack<>();

		s1.push(root);
		while(!s1.isEmpty()) {
			Node cur = s1.pop();
			s2.push(cur);

			if(cur.left != null)
				s1.push(cur.left);
			if(cur.right != null)
				s1.push(cur.right);
		}

		while(!s2.isEmpty()) {
			System.out.print(s2.pop().data+" ");
		}

		System.out.println();
	}

	public void postOrderIterativeUsingOneStack() {
		if(root == null)
			return;

		Stack<Node> s = new Stack<>();

		Node prev=null;
		s.push(root);
		while(!s.isEmpty()) {
			Node cur = s.peek();
			// go down
			if(prev==null || prev.left==cur || prev.right==cur) {
				// go down in left side
				if(cur.left!=null)
					s.push(cur.left);
				//go down in right side
				else if(cur.right!=null)
					s.push(cur.right);
				else
					System.out.print(s.pop().data+" ");
			} else if(cur.left==prev) {
				// go up towards parent's right
				if(cur.right!=null)
					s.push(cur.right);
				else
					System.out.print(s.pop().data+" ");
			} else if(cur.right==prev){
				// can't go much right, as its already right
				System.out.print(s.pop().data+" ");
			} else {
				System.out.print(s.pop().data+" ");
			}
			// capturing last visted node, to get info about upwards direction
			prev=cur;
		}
		System.out.println();
	}

	public void levelOrder() {
		if(root == null)
			return;

		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		int size = q.size();
		while(!q.isEmpty()) {
			size = q.size();
			int count =0;
			while(!q.isEmpty() && count<size) {
				Node cur = q.poll();
				System.out.print(cur.data+" ");
				if(cur.left != null)
					q.add(cur.left);
				if(cur.right != null)
					q.add(cur.right);
				count++;
			}
		}
		System.out.println();
	}

	public void leftViewRecursive() {
		if(root == null)
			return;
		MaximumLevel maxLevel = new MaximumLevel();
		int level = 1;
		leftViewRecursiveUtil(root, maxLevel, level);
		System.out.println();
	}

	private void leftViewRecursiveUtil(Node root, MaximumLevel maxLevel, int level) {
		if(root == null)
			return;
		if(maxLevel.getMaximumLevel() < level) {
			System.out.print(root.data+" ");
			maxLevel.setMaximumLevel(level);
		}
		
		leftViewRecursiveUtil(root.left, maxLevel, level+1);
		leftViewRecursiveUtil(root.right,  maxLevel, level+1);
	}

	public void letViewIterative() {
		if(root == null)
			return;
		Queue<Node> q= new LinkedList<>();
		q.add(root);

		while(!q.isEmpty()) {
			int size=q.size();
			for(int i=0;i<size;i++) {
				Node cur = q.poll();
				if(i==0) {
					System.out.print(cur.data+" ");
				}
				if(cur.left!=null)
					q.add(cur.left);
				if(cur.right!=null)
					q.add(cur.right);
			}
		}
		System.out.println();
	}

	public void rightViewRecursive() {
		if(root == null)
			return;
		MaximumLevel maxLevel = new MaximumLevel();
		int level=1;
		rightViewRecursiveUtil(root, maxLevel, level);
		System.out.println();
	}

	private void rightViewRecursiveUtil(Node root, MaximumLevel maxLevel, int level) {
		if(root == null)
			return;

		if(maxLevel.getMaximumLevel()<level) {
			System.out.print(root.data+" ");
			maxLevel.setMaximumLevel(level);
		}

		rightViewRecursiveUtil(root.right, maxLevel, level+1);
		rightViewRecursiveUtil(root.left, maxLevel, level+1);
	}

	public void rightViewIterative() {
		if(root == null) 
			return;

		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				Node cur=q.poll();
				if(i==size-1) {
					System.out.print(cur.data+" ");
				}
				if(cur.left != null)
					q.add(cur.left);
				if(cur.right != null)
					q.add(cur.right);
			}
		}
		System.out.println();
	}

	public void topViewRecursive() {
		if(root == null)
			return;
		Map<Integer, Pair<Node, Integer>> map = new TreeMap<>();
		int horizontalDistance = 0;
		int level = 0;
		topViewRecursiveUtil(root, map, horizontalDistance, level);
		for(Map.Entry<Integer, Pair<Node, Integer>> entry : map.entrySet()) {
			System.out.print(entry.getValue().getKey().data+" ");
		}
		System.out.println(); 
	}

	private void topViewRecursiveUtil(Node root, Map<Integer, Pair<Node, Integer>> map, int horizontalDistance, int level) {
		if(root == null) 
			return;
		if(!map.containsKey(horizontalDistance))
			map.put(horizontalDistance, new Pair(root, level));
		else if(map.containsKey(horizontalDistance) && map.get(horizontalDistance).getValue()>level)
			map.put(horizontalDistance, new Pair(root, level));

		topViewRecursiveUtil(root.left, map, horizontalDistance-1, level+1);
		topViewRecursiveUtil(root.right, map, horizontalDistance+1, level+1);
	}

	public void topviewIterative() {
		if(root == null)
			return;
		Map<Integer, Node> map = new TreeMap<>();
		Queue<SpecialNode> q = new LinkedList<>();
		q.add(new SpecialNode(root, 0));
		map.put(0, root);
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				SpecialNode sNode = q.poll();
				Node cur = sNode.getNodeValue();
				int horizontalDistance = sNode.getHorizontalDistanceValue();
				if(!map.containsKey(horizontalDistance)) {
					map.put(horizontalDistance, cur);
				}

				if(cur.left != null) 
					q.add(new SpecialNode(cur.left, horizontalDistance-1));

				if(cur.right != null)
					q.add(new SpecialNode(cur.right, horizontalDistance+1));
			}
		}

		for(Map.Entry<Integer, Node> entry : map.entrySet()) {
			System.out.print(entry.getValue().data+" ");
		}
		System.out.println();
	}

	public void bottomViewRecursive() {
		if(root == null)
			return;

		Map<Integer, Pair<Node, Integer>> map = new TreeMap<>();
		int horizontalDistance = 0;
		int level = 0;
		bottomViewRecursiveUtil(root, map, horizontalDistance, level);
		for(Map.Entry<Integer, Pair<Node, Integer>> entry : map.entrySet()) {
			System.out.print(entry.getValue().getKey().data+ " ");
		}
		System.out.println(); 
	}

	private void bottomViewRecursiveUtil(Node root, Map<Integer, Pair<Node, Integer>> map, int horizontalDistance, int level) {
		if(root == null)
			return;

		if(!map.containsKey(horizontalDistance)) 
			map.put(horizontalDistance, new Pair(root, level));
		else if(map.get(horizontalDistance).getValue()<level)
			map.put(horizontalDistance, new Pair(root, level));
		bottomViewRecursiveUtil(root.left, map, horizontalDistance-1, level+1);
		bottomViewRecursiveUtil(root.right, map, horizontalDistance+1, level+1);
	}

	public void bottomViewIterative() {
		if(root == null)
			return;

		Map<Integer, Node> map = new TreeMap<>();

		Queue<SpecialNode> q = new LinkedList<>();
		q.add(new SpecialNode(root, 0));

		while(!q.isEmpty()){
			int n = q.size();
			for(int i=0;i<n;i++) {
				SpecialNode sNode = q.poll();
				Node cur = sNode.getNodeValue();
				int horizontalDistance = sNode.getHorizontalDistanceValue();

				map.put(horizontalDistance, cur);

				if(cur.left != null) {
					q.add(new SpecialNode(cur.left, horizontalDistance-1));
				}

				if(cur.right != null) {
					q.add(new SpecialNode(cur.right, horizontalDistance+1));
				}
			}
		}

		for(Map.Entry<Integer, Node> entry : map.entrySet()) 
			System.out.print(entry.getValue().data+" ");
		System.out.println();
	}

	public void verticalOrderRecursive() {
		if(root == null)
			return;
		Map<Integer, List<Node>> map = new TreeMap<>();
		int horizontalDistance = 0;
		verticalOrderRecursiveUtil(root, map, horizontalDistance);
		for(Map.Entry<Integer, List<Node>> entry : map.entrySet()) {
			for(Node node : entry.getValue())
				System.out.print(node.data+" ");
			System.out.println();
		}
	}

	private void verticalOrderRecursiveUtil(Node root, Map<Integer, List<Node>> map, int horizontalDistance) {
		if(root == null)
			return;

		if(!map.containsKey(horizontalDistance)) {
			List<Node> list = new ArrayList<>();
			list.add(root);
			map.put(horizontalDistance, list);
		} else {
			List<Node> list = map.get(horizontalDistance);
			list.add(root);
			map.put(horizontalDistance, list);
		}

		verticalOrderRecursiveUtil(root.left, map, horizontalDistance-1);
		verticalOrderRecursiveUtil(root.right, map, horizontalDistance+1);
	}

	public void verticalOrderIterative() {
		if(root == null) 
			return;
		Map<Integer, List<Node>> map = new TreeMap<>();

		Queue<SpecialNode> q  = new LinkedList<>();
		q.add(new SpecialNode(root, 0));

		while(!q.isEmpty()) {
			int n= q.size();
			for(int i=0;i<n;i++){
				SpecialNode sNode = q.poll();
				Node cur = sNode.getNodeValue();
				int horizontalDistance = sNode.getHorizontalDistanceValue();

				if(!map.containsKey(horizontalDistance)) {
					List<Node> list = new ArrayList<>();
					list.add(cur);
					map.put(horizontalDistance, list);
				} else {
					List<Node> list = map.get(horizontalDistance);
					list.add(cur);
					map.put(horizontalDistance, list);
				}

				if(cur.left!= null) 
					q.add(new SpecialNode(cur.left, horizontalDistance-1));
				if(cur.right!=null)
					q.add(new SpecialNode(cur.right, horizontalDistance+1));
			}
		}

		for(Map.Entry<Integer, List<Node>> entry : map.entrySet()) {
			List<Node> nodes = entry.getValue();

			for(Node node : nodes)
				System.out.print(node.data+" ");
			System.out.println();
		}
	}

	public void boundryOrder() {
		if(root == null)
			return;
		System.out.print(root.data+" ");

		printLeftBoundry(root.left);
		printLeaves(root);
		printRightBoundry(root.right);
	}

	public void printLeftBoundry(Node root) {
		if(root == null|| (root.left==null && root.right==null))
			return;
		System.out.print(root.data+" ");
		if(root.left != null)
			printLeftBoundry(root.left);
		else if(root.right != null)
			printLeftBoundry(root.right);

	}

	public void printRightBoundry(Node root) {
		if(root == null || (root.left==null && root.right==null))
			return;
		System.out.print(root.data+" ");
		if(root.right != null)
			printRightBoundry(root.right);
		else if(root.left != null)
			printRightBoundry(root.left);
	}

	public void printLeaves(Node root) {
		if(root == null)
			return;
		printLeaves(root.left);
		if(root.left == null && root.right == null)
			System.out.print(root.data+" ");
		printLeaves(root.right);
	}

	public void spiralOrderIterative() {
		if(root == null)
			return;
		Deque<Node> dq = new ArrayDeque<>();
		boolean isReverse = true;
		dq.offer(root);
		while(!dq.isEmpty()) {
			int n = dq.size();
			for(int i=0;i<n;i++) {
				if(!isReverse) {
					Node cur = dq.pollFirst();
					System.out.print(cur.data+" ");
					if(cur.left != null) 
						dq.offerLast(cur.left);
					if(cur.right!=null)
						dq.offerLast(cur.right);
				}else {
					Node cur = dq.pollLast();
					System.out.print(cur.data+" ");
					if(cur.right!=null)
						dq.offerFirst(cur.right);
					if(cur.left != null) 
						dq.offerFirst(cur.left);
					
				}
			}
			isReverse = !isReverse;
		}
		System.out.println();
	}

	public void spiralOrderRecursive() {
		if(root == null)
			return;

		int height = getHeight();
		int level=0;
		boolean isReverse = true;

		for(int i=1;i<=height;i++) {
			printLevelNodes(root, i, isReverse);
			isReverse = !isReverse;
		}
		System.out.println();
	}

	private void printLevelNodes(Node root, int level, boolean isReverse) {
		if(root == null)
			return;

		if(level==1)
			System.out.print(root.data+" ");
		else if(level>1){
			if(!isReverse){
				printLevelNodes(root.left, level-1, isReverse);
				printLevelNodes(root.right, level-1, isReverse);
			} else {
				printLevelNodes(root.right, level-1, isReverse);
				printLevelNodes(root.left, level-1, isReverse);
			}
		}
	}

	public void diagonalFromRight() {
		if(root == null);
		Map<Integer, List<Node>> map = new TreeMap<>();
		int slope = 0;

		diagonalFromRightUtil(root, map, slope);

		for(Map.Entry<Integer, List<Node>> entry : map.entrySet()) {
			for(Node node : entry.getValue())
				System.out.print(node.data+" ");
			System.out.println();
		}
		
	}

	private void diagonalFromRightUtil(Node root, Map<Integer, List<Node>> map, int slope) {
		if(root == null)
			return;
		if(!map.containsKey(slope)) {
			List<Node> list = new ArrayList<>();
			list.add(root);
			map.put(slope, list);
		} else {
			List<Node> list = map.get(slope);
			list.add(root);
			map.put(slope, list);
		}
		diagonalFromRightUtil(root.left, map, slope+1);
		diagonalFromRightUtil(root.right, map, slope);
		
	}

	public void diagonalFromLeft() {
		if(root == null)
			return;
		Map<Integer, List<Node>> map = new TreeMap<>();
		int slope =0;
		diagonalFromLeftUtil(root, map, slope);
		for(Map.Entry<Integer, List<Node>> entry : map.entrySet()) {
			for(Node node : entry.getValue())
				System.out.print(node.data+" ");
			System.out.println();
		}
	}

	private void diagonalFromLeftUtil(Node root, Map<Integer, List<Node>> map, int slope) {
		if(root == null)
			return;
		if(!map.containsKey(slope)) {
			List<Node> list = new ArrayList<>();
			list.add(root);
			map.put(slope, list);
		} else {
			List<Node> list = map.get(slope);
			list.add(root);
			map.put(slope, list);
		}
		diagonalFromLeftUtil(root.right, map, slope+1);
		diagonalFromLeftUtil(root.left, map, slope);
		
	}

	public int getNumberOfLeafNodes() {
		if(root == null)
			return 0;
		return getNumberOfLeafNodesUtil(root);
	}

	private int getNumberOfLeafNodesUtil(Node root) {
		if(root == null)
			return 0;
		if(root.left == null && root.right ==null)
			return 1;
		int l = getNumberOfLeafNodesUtil(root.left);
		int r = getNumberOfLeafNodesUtil(root.right);
		return l+r;
	}

	public int getHeight() {
		if(root == null)
			return 0;
		return getHeightUtil(root);
	}

	private int getHeightUtil(Node root) {
		if(root == null)
			return 0;
		int lh = getHeightUtil(root.left)+1;
		int rh = getHeightUtil(root.right)+1;

		return lh>rh?lh:rh;
	}

	public int diameter() {
		if(root == null)
			return 0;

		MaximumLevel height = new MaximumLevel();

		return diameterUtil(root, height);
	}

	private int diameterUtil(Node root, MaximumLevel height) {
		MaximumLevel lh = new MaximumLevel();
		MaximumLevel rh = new MaximumLevel();

		if(root == null) {
			height.setMaximumLevel(0);
			return 0;
		}

		int dl = diameterUtil(root.left, lh);
		int dr = diameterUtil(root.right, rh);

		height.setMaximumLevel(Math.max(lh.getMaximumLevel(), rh.getMaximumLevel())+1);

		return Math.max(Math.max(dl, dr), lh.getMaximumLevel()+rh.getMaximumLevel());
	}

	public int maxPathSumBetweenTwoLeaf() {
		if(root == null)
			return 0;

		MaximumLevel result = new MaximumLevel();
		result.setMaximumLevel(Integer.MIN_VALUE);
		maxPathSumBetweenTwoLeafUtil(root, result);
		return result.getMaximumLevel();
	}

	private int maxPathSumBetweenTwoLeafUtil(Node root, MaximumLevel result) {
		if(root == null)
			return 0;

		if(root.left==null && root.right==null) 
			return root.data;

		int lSum = maxPathSumBetweenTwoLeafUtil(root.left, result);
		int rSum = maxPathSumBetweenTwoLeafUtil(root.right, result);

		result.setMaximumLevel(Math.max(result.getMaximumLevel(), lSum+rSum+root.data));

		return Math.max(lSum+root.data, rSum+root.data);
	}

	public int maxPathSumBetweenAnyTwoNode() {
		if(root == null)
			return 0;

		MaximumLevel result = new MaximumLevel();
		result.setMaximumLevel(Integer.MIN_VALUE);
		maxPathSumBetweenAnyTwoNodeUtil(root, result);
		return result.getMaximumLevel();
	}

	private int maxPathSumBetweenAnyTwoNodeUtil(Node root, MaximumLevel result) {
		if(root == null)
			return 0;

		int lSum = maxPathSumBetweenAnyTwoNodeUtil(root.left, result);
		int rSum = maxPathSumBetweenAnyTwoNodeUtil(root.right, result);

		int maxOfLeftAndRight = Math.max(lSum, rSum);
		int maxWithroot = Math.max(root.data, root.data+maxOfLeftAndRight);
		int maxWithAll = Math.max(root.data+lSum+rSum, maxWithroot);

		result.setMaximumLevel(Math.max(result.getMaximumLevel(), maxWithAll));

		return maxWithroot;
	}

	public boolean isHeightBalanced() {
		if(root == null)
			return true;

		MaximumLevel height = new MaximumLevel();
		return isHeightBalancedUtil(root, height);
	}

	private boolean isHeightBalancedUtil(Node root, MaximumLevel height) {
		if(root == null) {
			height.setMaximumLevel(0);
			return true;
		}
		MaximumLevel lh = new MaximumLevel();
		MaximumLevel rh = new MaximumLevel();

		boolean lb = isHeightBalancedUtil(root.left, lh);
		boolean rb = isHeightBalancedUtil(root.right, rh);

		height.setMaximumLevel(Math.max(lh.getMaximumLevel(), rh.getMaximumLevel()) + 1);

		if(Math.abs(lh.getMaximumLevel()-rh.getMaximumLevel())>1)
			return false;
		return lb&&rb;
	}

	public boolean isIdentical(Node second) {
		if(root == null && second == null)
			return true;
		if(root == null || second == null)
			return false;

		return isIdenticalUtil(root, second);
	}

	private boolean isIdenticalUtil(Node first, Node second) {
		if(first == null && second == null)
			return true;
		if(first == null || second == null)
			return false;

		return (first.data == second.data)
			&& isIdenticalUtil(first.left, second.left)
			&& isIdenticalUtil(first.right, second.right);

	}

	public boolean isMirror() {
		if(root == null)
			return true;

		return isMirrorUtil(root.left, root.right);
	}

	private boolean isMirrorUtil(Node left, Node right) {
		if(left == null && right == null)
			return true;
		if(left == null || right == null)
			return false;

		return (left.data == right.data)
			&& isIdenticalUtil(left.left, right.right)
			&& isIdenticalUtil(left.right, right.left);
	}

	public Node lca(Node first, Node second) {
		if(root == null)
			return null;

		return lcaUtil(root, first, second);
	}

	private Node lcaUtil(Node root, Node first, Node second) {
		if(root == null)
			return null;

		if(root == first || root == second)
			return root;

		Node l = lcaUtil(root.left, first, second);
		Node r = lcaUtil(root.right, first, second);

		if(l!=null && r!=null)
			return root;

		if(l!=null)
			return l;
		else 
			return r;
	}

	public Node deserialize(String serializedTree) {
		if(serializedTree == null)
			return null;

		String [] nodes = serializedTree.split(",");
		int n = nodes.length;
		if(n==0)
			return null;

		MaximumLevel position = new MaximumLevel();

		return deserializeUtil(nodes, position);
	}

	private Node deserializeUtil(String [] nodes, MaximumLevel position) {
		if(nodes[position.getMaximumLevel()].equals("#"))
			return null;

		Node root = new Node(Integer.parseInt(nodes[position.getMaximumLevel()]));

		position.setMaximumLevel(position.getMaximumLevel()+1);
		root.left = deserializeUtil(nodes, position);
		position.setMaximumLevel(position.getMaximumLevel()+1);
		root.right = deserializeUtil(nodes, position);
		return root;
	}

	public String serialize() {
		if(root == null)
			return "";

		Stack<Node> s = new Stack<>();
		List<String> result = new ArrayList<>();

		s.push(root);
		while(!s.isEmpty()) {
			Node cur = s.pop();
			if(cur == null)
				result.add("#");
			else {
				result.add(""+cur.data);
				s.push(cur.right);
				s.push(cur.left);
			}
		}
		return String.join(",", result);
	}

	public void printPath() {
		List<List<Integer>> result = path();

		for(int i=0;i<result.size();i++) {
			for(int j=0;j<result.get(i).size();j++) {
				System.out.print(result.get(i).get(j)+" ");
			}
			System.out.println();
		}
	}

	public List<List<Integer>> path() {
		List<List<Integer>> result = new ArrayList<>();

		if(root == null) {
			return result;
		} 

		pathUtil(root, new LinkedList<Integer>(), result);
		return result;
	}

	private void pathUtil(Node root, LinkedList<Integer> temp, List<List<Integer>> result) {
		if(root == null) {
			return;
		}

		if(root.left==null && root.right == null) {
			temp.add(root.data);
			result.add(new ArrayList<>(temp));
			//temp.remove(Integer.valueOf(root.data));
			temp.removeLast();
			return;
		}

		temp.add(root.data);

		pathUtil(root.left, temp, result);

		pathUtil(root.right, temp, result);

		//temp.remove(Integer.valueOf(root.data)); // when ArrayList has been used, or remove(temp.size()-1);
		temp.removeLast();
	}
}

class SpecialNode {
	Node node;
	int horizontalDistance;

	public SpecialNode(Node node, int horizontalDistance) {
		this.node = node;
		this.horizontalDistance = horizontalDistance;
	}

	public Node getNodeValue(){
		return this.node;
	}

	public int getHorizontalDistanceValue(){
		return this.horizontalDistance;
	}
}


class MaximumLevel{
	int maxLevel;
	public MaximumLevel() {
		maxLevel = 0;
	}

	public int getMaximumLevel() {
		return maxLevel;
	}

	public void setMaximumLevel(int level) {
		maxLevel = level;
	}
}

class BinaryTree {
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
		// tree.preOrderRecursive();
		// tree.inOrderRecursive();
		// tree.postOrderRecursive();
		// tree.preOrderIterative();
		// tree.inOrderIterative();
		// tree.postOrderIterativeUsingTwoStacks();
		// tree.postOrderIterativeUsingOneStack();
		// tree.levelOrder();
		// tree.leftViewRecursive();
		// tree.letViewIterative();
		// tree.rightViewRecursive();
		// tree.rightViewIterative();
		// tree.topViewRecursive();
		// tree.topviewIterative();
		// tree.bottomViewRecursive();
		// tree.bottomViewIterative();
		// tree.verticalOrderRecursive();
		// tree.verticalOrderIterative();
		// tree.boundryOrder();
		// System.out.println();
		// tree.printLeftBoundry(root.left);
		// System.out.println();
		// tree.printLeaves(root);
		// System.out.println();
		// tree.printRightBoundry(root.right);
		// System.out.println();
		// tree.spiralOrderIterative();
		// System.out.println(tree.getHeight());
		// tree.spiralOrderRecursive();
		// tree.diagonalFromRight();
		// tree.diagonalFromLeft();
		// System.out.println(tree.getNumberOfLeafNodes());
		// System.out.println(tree.diameter());
		// System.out.println(tree.maxPathSumBetweenTwoLeaf());
		// System.out.println(tree.maxPathSumBetweenAnyTwoNode());
		// System.out.println(tree.isHeightBalanced());
		// System.out.println(tree.isIdentical(root));
		// System.out.println(tree.isMirror());
		// System.out.println(tree.lca(root.left.left, root.left.right.right).data);
		// System.out.println(tree.serialize());
		// Node dRoot = tree.deserialize(tree.serialize());
		// Tree dTree = new Tree();
		// dTree.setRoot(dRoot);
		// dTree.inOrderRecursive();
		tree.printPath();
	}
}