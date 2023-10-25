/**
 * This class is used to create the binary trees used in this program by using a queue-based approach.
 * 
 * @author Maya Al Hourani #251241606
 *
 */

public class TreeBuilder<T> {
	
	public LinkedBinaryTree<T> buildTree (T[] data){
		
	//Initializing the 2 queues that will be used.
	LinkedQueue<T> dataQueue = new LinkedQueue<T>();
	LinkedQueue<BinaryTreeNode<T>> parentQueue = new LinkedQueue<BinaryTreeNode<T>>();
	
	//All elements from data to be added to dataQueue.
	for (int i = 0; i < data.length; i++ ) {
		dataQueue.enqueue(data[i]);
	}
		
	//Creating a LinkedBinaryTree object to use as root node
	//And storing the first element of dataQueue.
	LinkedBinaryTree<T> rootTree = new LinkedBinaryTree<T>(new BinaryTreeNode<T>(dataQueue.dequeue()));
	
		
	//Enqueuing the root node to the parentQueue.
	parentQueue.enqueue(rootTree.getRoot());
	
	
	//While loop to create children
	while (!(dataQueue.isEmpty())) {
		T a = dataQueue.dequeue();
		T b = dataQueue.dequeue();
		BinaryTreeNode<T> parent = parentQueue.dequeue();
		
		if (a != null) {
			BinaryTreeNode<T> aNode = new BinaryTreeNode<T>(a);
			parent.setLeft(aNode);
			parentQueue.enqueue(aNode);
		}
		
		
		if (b != null) {
			BinaryTreeNode<T> bNode = new BinaryTreeNode<T>(b);
			parent.setRight(bNode);
			parentQueue.enqueue(bNode);
		}
		
	}
	
	return rootTree;
	}
	
	
}
