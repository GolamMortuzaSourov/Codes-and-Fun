/**
* Represents a Merkle tree, a data structure used in blockchain technology to efficiently verify the integrity of data.
*/

public class MerkleTree
{
	/**
	* The size of the object, indicating the count or quantity it represents.
	*/
	private int size =0;
	/**
	* The size of the object, indicating the count or quantity it represents.
	*/
	private Utilities ut = new Utilities();
	/**
	* The size of the object, indicating the count or quantity it represents.
	*/
	private int height =0;
	/**
	* The size of the object, indicating the count or quantity it represents.
	*/
	private int in =0;
	/**
	* The size of the object, indicating the count or quantity it represents.
	*/
	private SinglyLinkedList<String> ss = new SinglyLinkedList<>();
	/**
	* The size of the object, indicating the count or quantity it represents.
	*/
	private Node<String> root;
	/**
	* The size of the object, indicating the count or quantity it represents.
	*/
	private Node<String> current;
	/**
	* Constructs a Merkle tree for the transactions in a block.
	*
	* @param block The block containing the transactions to construct the Merkle tree from.
	*/
	public MerkleTree(Block block)
	{
		for(Transaction t : block){
			if(size==0){
				ss.insert(ut.cryptographicHashFunction(t.toString()));
				block.setRootHash(ut.cryptographicHashFunction(t.toString()));
				root = new Node<String>(ut.cryptographicHashFunction(t.toString()));
				size++;
			}else{
				Node<String> t1 = new Node<>(ut.cryptographicHashFunction(t.toString()));
				buildmerkle(root,t1);
			}
		}
	}
	/**
	* Builds a Merkle tree given two transaction objects.
	*
	* @param rt The first transaction.
	* @param tr The second transaction.
	*/
	private void buildmerkle(Node<String> rt, Node<String> tr){
		current = rt;
		if(rt.getright()== null && rt.getleft()==null){
			rt.setleft(tr);
		}else if(rt.getright()== null && rt.getleft()!=null){
			rt.setright(tr);
		}else if(rt.getleft()== null && rt.getright()!=null){
			rt.setright(tr);
		}else{
			buildmerkle(rt.getleft(),tr);
			height++;
		}
	}

	/**
	* Calculates and returns the height of the Merkle tree.
	*
	* @return The height of the Merkle tree.
	*/
	public int height()
	{
		return height;
	}

	/**
	* Calculates and returns the number of inner nodes in the Merkle tree.
	*
	* @return The number of inner nodes in the Merkle tree.
	*/
	public int innerNodes()
	{
		return (2*height);
	}

	/**
	* Performs a breadth-first traversal of the Merkle tree and returns a list of values at each level.
	*
	* @return A singly linked list containing values at each level of the Merkle tree.
	*/
	public SinglyLinkedList<String> breadthFirstTraversal()
	{
		return ss;
	}

	/**
	* Extracts a Merkle proof for a specific transaction in the Merkle tree.
	*
	* @param order The transaction for which to extract the Merkle proof.
	* @return A singly linked list containing the Merkle proof for the transaction.
	*/
	public SinglyLinkedList<String> depthFirstTraversal(Order order)
	{
		return ss;
	}

	/**
	* Extracts a Merkle proof for a specific transaction in the Merkle tree.
	*
	* @param t The transaction for which to extract the Merkle proof.
	* @return A singly linked list containing the Merkle proof for the transaction.
	*/
	public SinglyLinkedList<String> extractProof(Transaction t)
	{
		return ss;
	}
	/**
	* Represents a node in the linked list that holds an element of type Z.
	*
	* @param <P> The type of element stored in the node.
	*/
	private class Node<P> {
		/**
		* The size of the object, indicating the count or quantity it represents.
		*/
		String data;
		/**
		* The size of the object, indicating the count or quantity it represents.
		*/
		Node<P> left;
		/**
		* The size of the object, indicating the count or quantity it represents.
		*/
		Node<P> right;

		/**
		* Constructs a Node with the specified data.
		*
		* @param data The data to be stored in the Node.
		*/
		Node(String data) {
			this.data = data;
			this.left = null;
			this.right=null;
		}
		/**
		* Sets the right child node of the current node to the specified data.
		*
		* @param r The data to be set as the right child of the current node.
		*/
		private void setright(Node<P> r){
			right=r;
		}
		/**
		* Sets the right child node of the current node to the specified data.
		*
		* @return r The data to be set as the right child of the current node.
		*/
		private String getdata(){
			return data;
		}
		/**
		* Sets the right child node of the current node to the specified data.
		*
		* @param l The data to be set as the right child of the current node.
		*/
		private void setleft(Node<P> l){
			left =l;
		}
		/**
		* Retrieves the left child data of the current node.
		*
		* @return The data stored in the left child of the current node.
		*/
		private Node<P> getleft(){
			return left;
		}
		/**
		* Retrieves the left child data of the current node.
		* @return The data stored in the left child of the current node.
		*/
		private Node<P> getright(){
			return right;
		}
	}
}
