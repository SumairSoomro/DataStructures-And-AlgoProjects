	package structures;

	public class AVLTree <T extends Comparable<T>> implements BSTInterface<T> {
		protected BSTNode<T> root;
		private int size;
		private AVLTree<T> tree;
		
		

		public AVLTree() {
			root = null;
			size = 0;
		}


		public boolean isEmpty() {
			// DO NOT MODIFY
			return root == null;
		}

		public int size() {
			// DO NOT MODIFY
			return size;
		}

		public BSTNode<T> getRoot() {
			// DO NOT MODIFY
			return root;
		}
		
		public void printTree() {
			System.out.println("------------------------");
			if (root != null) root.printSubtree(0);
		}

		public boolean remove(T element) {
			// Do not need to implement this method.
			return false;
		}

		public T get(T element) {
			// Do not need to implement this method.
			return null;
		}

		public int height() {
			return height(this.root);
		}

		public int height(BSTNode<T> node) {
			// return the node height
			return node != null ? node.getHeight() : -1;
		}
		
		public void updateHeight(BSTNode<T> node) {
			// TODO: update node height to 1 + the maximal height between left and right subtree
			
			if (node == null) {
				return;
			}
			int leftHeight = -1;
			if (node.getLeft() != null) {
				leftHeight = node.getLeft().getHeight();
			}
			int rightHeight = -1;
			if (node.getRight() != null) {
				rightHeight = node.getRight().getHeight();
			}
			node.setHeight(Math.max(leftHeight, rightHeight) + 1);
		}
		
		public int balanceFactor(BSTNode<T> node) {
			// TODO: compute the balance factor by substracting left subtree height by right subtree height
			
			int leftHeight = -1; 
			if (node.getLeft() != null) {
				leftHeight = node.getLeft().getHeight();
			}
			int rightHeight = -1; 
			if (node.getRight() != null) {
				rightHeight = node.getRight().getHeight(); 
			}
			int bF = rightHeight-leftHeight;
			 return bF;
		}

		public BSTNode<T> rotateLeft(BSTNode<T> node) {
    		BSTNode<T> rightChild = node.getRight();
  			BSTNode<T> rightLeftChild = rightChild.getLeft();
			rightChild.setLeft(node);
    		node.setRight(rightLeftChild);
			if (node.getParent() != null) {
        		if (node.getParent().getLeft() == node) {
           			 node.getParent().setLeft(rightChild);
        		} 
				else {
            		node.getParent().setRight(rightChild);
        		}
    		} 		
			else {
        		root = rightChild;
    		}
			rightChild.setParent(node.getParent());
    		node.setParent(rightChild);
   			if (rightLeftChild != null) {
        		rightLeftChild.setParent(node);
    		}
			else{
   				updateHeight(node);
    			updateHeight(rightChild);

   				
			}
			return rightChild;
		}
		public BSTNode<T> rotateRight(BSTNode<T> node) {
			BSTNode<T> leftChild = node.getLeft();
			BSTNode<T> leftRightChild = leftChild.getRight();
		
		
			leftChild.setRight(node);
			node.setLeft(leftRightChild);
		
			
			if (node.getParent() != null) {
				if (node.getParent().getLeft() == node) {
					node.getParent().setLeft(leftChild);
				} else {
					node.getParent().setRight(leftChild);
				}
			} else {
			
				root = leftChild;
			}
		
			
			leftChild.setParent(node.getParent());
			node.setParent(leftChild);
			if (leftRightChild != null) {
				leftRightChild.setParent(node);
			}
			else{
		
				updateHeight(node);
				updateHeight(leftChild);
			}
		
			return leftChild;
		}
		
		

		// When inserting a new node, updating the height of each node in the path from root to the new node.
		// Check the balance factor of each updated height and run rebalance algorithm if the balance factor
		// is less than -1 or larger than 1 with following algorithm
		// 1. if the balance factor is less than -1
		//    1a. if the balance factor of left child is less than or equal to 0, apply right rotation
	    //    1b. if the balance factor of left child is larger than 0, apply left rotation on the left child,
		//        then apply right rotation
		// 2. if the balance factor is larger than 1
		//    2a. if the balance factor of right child is larger than or equal to 0, apply left rotation
	    //    2b. if the balance factor of right child is less than 0, apply right rotation on the right child,
		//        then apply left rotation
		public void add(T t) {
			BSTNode<T> node = new BSTNode<T>(t, null, null);
			AVLTreeInsert(node);
		}
		public void AVLTreeInsert(BSTNode<T> node) {
			if (root == null) {
				root = node;
				node.setParent(null);
				size++;
				return;
			}
		
			BSTNode<T> cur = root;
			while (cur != null) {
				if (node.getData().compareTo(cur.getData()) < 0) {
					if (cur.getLeft() == null) {
						cur.setLeft(node);
						node.setParent(cur);
						cur = null;
					} else {
						cur = cur.getLeft();
					}
				} else {
					if (cur.getRight() == null) {
						cur.setRight(node);
						node.setParent(cur);
						cur = null;
					} else {
						cur = cur.getRight();
					}
				}
			}
		
			node = node.getParent();
			while (node != null) {
				AVLTreeRebalance(node);
				node = node.getParent();
			}
			size++;
		}
		
	
		
		public void AVLTreeRebalance(BSTNode<T> node) {
			int balanceFactor = balanceFactor(node);
		
			if (balanceFactor >= 2) {
				if (balanceFactor(node.getRight()) < 0) {
					rotateRight(node.getRight());
				}
				rotateLeft(node);
			} else if (balanceFactor <= -2) {
				if (balanceFactor(node.getLeft()) > 0) {
					rotateLeft(node.getLeft());
				}
				rotateRight(node);
			}
		
			updateHeight(node);
		}
}

	
