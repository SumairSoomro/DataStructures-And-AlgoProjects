package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.xml.transform.ErrorListener;


public class BinarySearchTree<T extends Comparable<T>> implements
		BSTInterface<T> {
	protected BSTNode<T> root;

	public boolean isEmpty() {
		return root == null;
	}

	public int getSize() {
		// TODO
		return getSizeHelper(root);
	}
	public int getSizeHelper(BSTNode<T> root){
		BSTNode<T> curr = root; 
		if (curr == null) {
            return 0;
        } else {
           
            return getSizeHelper(curr.getLeft()) + getSizeHelper(curr.getRight()) + 1;
        }
    }
	


	public boolean contains(T t) {
		// TODO
		return containsHelper(root, t);
	}
	public boolean containsHelper(BSTNode<T> root, T key){
		BSTNode<T> cur = root;
		if(key == null){
			throw new NullPointerException();
		}
		while (cur != null) {
			if (key.compareTo(cur.getData()) == 0) {
				return true;
			} else if (key.compareTo(cur.getData()) < 0) {
				cur = cur.getLeft();
			} else {
				cur = cur.getRight();
			}
		}
		return false;
	}

	public boolean removeElement(T t) {
		// TODO
		if(t == null){
			throw new NullPointerException();
		}
		return 	remHelper(root, t);
	}
	 public boolean remHelper(BSTNode<T> root, T key ){
		
		if (root == null) {
			return false;
		}
		BSTNode<T> parent = null;
		BSTNode<T> current = root;
   	    boolean found = false;
		while (current != null) {
			if (key.compareTo(current.getData() ) < 0 ) {
				parent = current;
				current = current.getLeft();
			} else if (key.compareTo(current.getData() ) > 0) {
				parent = current;
				current = current.getRight();
			} else {
				found = true;
				break;
			}
		}
		if (!found) {
			return false;
		}
		if (current.getLeft() == null && current.getRight() == null) {
        	if (parent == null) {
            	
            	root = null;
        	} else if (parent.getLeft() == current) {
        
            	parent.setLeft(null);
        }	 else {
            
            	parent.setRight(null);
        	}

    	}
		else if (current.getLeft() == null || current.getRight() == null) {
			BSTNode<T> child;
			if (current.getLeft() != null) {
    			child = current.getLeft();
			} else {
    			child = current.getRight();
			}
	
			if (parent == null) {
				
				root = child;
			} else if (parent.getLeft() == current) {
				
				parent.setLeft(child);
			} else {
				
				parent.setRight(child);
			}
		}
		else {
			
			BSTNode<T> successor = getMinHelp(current.getRight());
			
			current.setData(successor.getData());
			
			remHelper(current.getRight(), successor.getData());
		}
	
		return true;

	}

	public T getHighestValueFromSubtree(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getData();
		} else {
			return getHighestValueFromSubtree(node.getRight());
		}
	}

	public T getLowestValueFromSubtree(BSTNode<T> node) {
		// TODO
		if(node == null){
			throw new NullPointerException();
			

		}
		if (node.getLeft() == null) {
			return node.getData();
		} else {
			return getLowestValueFromSubtree(node.getLeft());
		}
	}

	private BSTNode<T> removeRightmostFromSubtree(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getLeft();
		} else {
			node.setRight(removeRightmostFromSubtree(node.getRight()));
			if (node.getRight() != null){
				node.getRight().setParent(node);
			}
			return node;
		}
	}

	public BSTNode<T> removeLeftmostFromSubtree(BSTNode<T> node) {
		// TODO
		if (node == null) {
			throw new IllegalArgumentException();
		}
	
		if (node.getLeft() == null) {
			return node.getRight();
		} else {
			node.setLeft(removeLeftmostFromSubtree(node.getLeft()));
			return node;
		}
	}

	public T getElement(T t) {
		// TODO
		return getEleHelper(root, t);
		


	}
	public T getEleHelper(BSTNode<T> root, T key){
		BSTNode<T> cur = root;
		if(key == null){
			throw new NullPointerException();
		}
		while (cur != null) {
			if (key.compareTo(cur.getData()) == 0) {
				return cur.getData();
			} else if (key.compareTo(cur.getData()) < 0) {
				cur = cur.getLeft();
			} else {
				cur = cur.getRight();
			}
		}
		return null;
	}


	public void addElement(T t) {
		// TODO
		root = addEleHelper(root, t);
		

	
	
	}
	public BSTNode<T> addEleHelper(BSTNode<T> root, T key){
		if (key == null) {
			throw new NullPointerException();
		}
		BSTNode<T> node = root;
		if(node == null){
			BSTNode<T> curr = new BSTNode<T>(key, null, null);
			node = curr;
		}
		else if (key.compareTo(node.getData()) < 0){
			node.setLeft(addEleHelper(root.getLeft(), key));
		}
		else if (key.compareTo(node.getData()) > 0){
			node.setRight(addEleHelper(root.getRight(), key));
		}

	
	return node;

	}

	

	@Override
	public T getMin() {
		// TODO
		if(root == null){
			return null;
		}
		BSTNode<T> cur;
		cur = getMinHelp(root);
		return cur.getData();
	}
	public BSTNode<T> getMinHelp(BSTNode<T> root) {
		BSTNode<T> current = root;
		while (current.getLeft() != null) {
			current = current.getLeft();
		}
		return current;
	}


	@Override
	public T getMax() {
		// TODO
		if(root == null){
			return null;
		}
		BSTNode<T> cur;
		cur = getMaxHelp(root);
		return cur.getData();
	}
	public BSTNode<T> getMaxHelp(BSTNode<T> root) {
		BSTNode<T> current = root;
		while (current.getRight() != null) {
			current = current.getRight();
		}
		return current;
	}

	@Override
	public int height() {
		// TODO
	return heightHelper(root);
		
	}
	public int heightHelper(BSTNode<T> root){
		BSTNode<T> curr = root;
		if (curr == null){
			return -1; // return -1 for empty tree
		}
		else {
			int lDepth = heightHelper(curr.getLeft());
			int rDepth = heightHelper(curr.getRight());
	
			if (lDepth > rDepth){
				return (lDepth + 1);
			}
			else{
				return (rDepth + 1);
			}
		}
	}

	public Iterator<T> preorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		preorderTraversal(root, queue);
		return queue.iterator();
	}
	
	private void preorderTraversal(BSTNode<T> node, Queue<T> queue) {
		if (node == null) {
			return;
		}
	
		queue.add(node.getData()); 
	
		
		preorderTraversal(node.getLeft(), queue);
		preorderTraversal(node.getRight(), queue);
	}
	


	public Iterator<T> inorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, root);
		return queue.iterator();
	}

	private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			inorderTraverse(queue, node.getLeft());
			queue.add(node.getData());
			inorderTraverse(queue, node.getRight());
		}
	}

	public Iterator<T> postorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		postorderTraversal(root, queue);
		return queue.iterator();
	}
	
	private void postorderTraversal(BSTNode<T> node, Queue<T> queue) {
		if (node == null) {
			return;
		}
	
		// recursively traverse left and right subtrees in postorder
		postorderTraversal(node.getLeft(), queue);
		postorderTraversal(node.getRight(), queue);
	
		queue.add(node.getData()); // visit the node
	}

	@Override
	public boolean equals(BSTInterface<T> other) {
		if (other == null) {
			throw new NullPointerException("other cannot be null");
		}
	
		return sameTreeHelper(this.getRoot(), other.getRoot());
	}
	
	private boolean sameTreeHelper(BSTNode<T> node, BSTNode<T> otherNode) {
		if (node == null && otherNode == null) {
			return true;
		} else if (node == null || otherNode == null) {
			return false;
		} else {
			int num = node.getData().compareTo(otherNode.getData());
			return (num == 0) && sameTreeHelper(node.getLeft(), otherNode.getLeft()) && sameTreeHelper(node.getRight(), otherNode.getRight());
		}
	}

	@Override
	public boolean sameValues(BSTInterface<T> other) {
		if (other == null) {
			throw new NullPointerException();
		}
	
		Iterator<T> thisIterator = inorderIterator();
		Iterator<T> otherIterator = other.inorderIterator();
	
		while (thisIterator.hasNext() && otherIterator.hasNext()) {
			T thisValue = thisIterator.next();
			T otherValue = otherIterator.next();
			boolean found = false;
			if (thisValue.equals(otherValue)) {
				found = true;

			}
			if(!found){
				return false;
			}
			
		}
		return !thisIterator.hasNext() && !otherIterator.hasNext();
	
	}
	
	@Override
	public int countRange(T min, T max) {
    	// TODO
		return countRangeHelper(min,max,root);
	}
	public int countRangeHelper(T min, T max, BSTNode<T> curr){
		
		if (curr == null) {
			return 0;
		}
		
		int count = 0;
		
		
		if (curr.getData().compareTo(min) > 0 && curr.getData().compareTo(max) < 0) {
			count++;
		}
		
		
		count += countRangeHelper(min, max, curr.getLeft()) + countRangeHelper(min, max, curr.getRight());
		
		return count;

	}


	@Override
	public BSTNode<T> getRoot() {
        // DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}

	public static void main(String[] args) {
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			BSTInterface<String> tree = new BinarySearchTree<String>();
			for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
				tree.addElement(s);
			}
			Iterator<String> iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.preorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.postorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();

			System.out.println(tree.removeElement(r));

			iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
		}

		BSTInterface<String> tree = new BinarySearchTree<String>();
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			tree.addElement(r);
		}
		System.out.println(tree.getSize());
		System.out.println(tree.height());
		System.out.println(tree.countRange("a", "g"));
		System.out.println(tree.countRange("c", "f"));
	}
}