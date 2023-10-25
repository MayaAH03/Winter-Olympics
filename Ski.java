/**
 * This class represents the path the skier will take based on certain requirements.
 * 
 * @author Maya Al Hourani #251241606
 *
 */

public class Ski {
	
	private BinaryTreeNode<SkiSegment> root;

	//Main constructor of the class.
	public Ski(String[] data) {
		
		//This array will hold the data from the parameter as SkiSegment objects.
		SkiSegment[] segments = new SkiSegment[data.length];
		
		//Sorting the data based on what Segment it represents. E.g jump segments are stored as JumpSegment objects.
		for (int i = 0; i < data.length ;i++) {
			
			if (data[i] == null) {
				segments[i] = null;
			}
			
			
			else if (data[i].contains("jump")) {
				segments[i] = new JumpSegment(String.valueOf(i),data[i]); 
			}
			
			
			else if (data[i].contains("slalom")) {
				segments[i] = new SlalomSegment(String.valueOf(i),data[i]);
			}
			
			
			else {
				segments[i] = new SkiSegment(String.valueOf(i),data[i]);
			}
			
		}
		
		//Creating a tree builder object to store the array segments' data in a tree.
		TreeBuilder<SkiSegment> dataTree = new TreeBuilder<SkiSegment>();
		LinkedBinaryTree<SkiSegment> dataTree2 = dataTree.buildTree(segments);
		
		
		//Storing the root of the tree just created in the instance variable made in the beginning. 
		root = dataTree2.getRoot();
		
	}
	
	
	//Return the tree's root node.
	public BinaryTreeNode<SkiSegment> getRoot(){
		return root;
	}
	
	
	//This is a recursive method used to go find the next segment for the skier to go to.
	public void skiNextSegment (BinaryTreeNode<SkiSegment> node,ArrayUnorderedList<SkiSegment> sequence) {		
		
		//Adding the node to the sequence.
		sequence.addToRear(node.getData());
		
		
		//Base case
		if (node.getLeft() == null && node.getRight() == null) {
	
		}
		
		
		//Adding the data in the parameter node to the end of the array. Making sure children aren't null.
		else if (node.getRight() == null) {
			skiNextSegment(node.getLeft(), sequence);
		}
		
		else if (node.getLeft() == null) {
			skiNextSegment(node.getRight(), sequence);
		}
		
		
		//Recursive method to decide path for skier based on what segment is in front.
		if (node.getLeft() != null && node.getRight() != null) {
			
			//If there is only one subsequent path, the skier must take it. (here the subsequent path is on the left) 
			if (node.getRight() == null) {
				skiNextSegment(node.getLeft(), sequence);
			}
			
			//If there is only one subsequent path, the skier must take it. (here the subsequent path is on the right) 
			else if (node.getLeft() == null) {
				skiNextSegment(node.getRight(), sequence);
			}
			
			//CASES WHEN TWO SUBSEQUENT PATHS AVAILABLE
			
			//JumpSegment Cases
			
			//If both contain jumps, it will compare heights, otherwise if they're equal it will go right.
			else if (node.getLeft().getData() instanceof JumpSegment && node.getRight().getData() instanceof JumpSegment) {
				
				//if right height is greater than the left it will take that path, or if they're equal it will also go right.
				if (((JumpSegment)node.getRight().getData()).getHeight()>= ((JumpSegment)node.getLeft().getData()).getHeight()){
					skiNextSegment(node.getRight(), sequence);
					
		
				}
				//Otherwise it will go left.
				else {
					skiNextSegment(node.getLeft(), sequence);
					
					}
					
				}
			
			//If only one contains jump, in this case on right contains jump.
			else if (node.getRight().getData() instanceof JumpSegment) {
				skiNextSegment(node.getRight(), sequence);
				
			}
			
			//If only one contains jump, in this case left contains jump.
			else if (node.getLeft().getData() instanceof JumpSegment) {
				skiNextSegment(node.getLeft(), sequence);
				
			}
			
			
			//Slalom Cases
			
			//If both contain slaloms, it will go to the one which is leeward.
			else if (node.getRight().getData() instanceof SlalomSegment && node.getLeft().getData() instanceof SlalomSegment) {
					
					//If right is in leeward direction it will take right.
					if(((SlalomSegment)node.getRight().getData()).getDirection().equals("L")) {
						skiNextSegment(node.getRight(), sequence);
					}
					
					
					//Otherwise we assume left contains leeward so we take left.
					else {
						skiNextSegment(node.getLeft(), sequence);
					}
					
			}
			
			//If only right contains slalom, check if its leeward, otherwise take left.
			else if (node.getRight().getData() instanceof SlalomSegment) {
				
				//Checking if its leeward, and if so taking it.
				if (((SlalomSegment)node.getRight().getData()).getDirection().equals("L")) {
					skiNextSegment(node.getRight(), sequence);
				}
				
				//Otherwise it will go left.
				else {
					skiNextSegment(node.getLeft(), sequence);
				}
			}
			
			//If only left contains slalom, check if its leeward, otherwise take right.
			else if (node.getLeft().getData() instanceof SlalomSegment) {
				
				//checking if its leeward, and if so taking it.
				if(((SlalomSegment)node.getLeft().getData()).getDirection().equals("L")) {
					skiNextSegment(node.getLeft(), sequence);
				}
				
				//Otherwise take right.
				else {
					skiNextSegment(node.getRight(), sequence);
				}
			}
			
			//Otherwise if both are regular segment, go right by default.
			else {
				skiNextSegment(node.getRight(), sequence);
			}
			
			}
			
			
		}
		
	}
