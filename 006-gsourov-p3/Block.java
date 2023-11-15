import java.util.Iterator;
/**
* Represents a block in a blockchain, containing a collection of transactions.
*
*/
public class Block implements Comparable<Block>, Iterable<Transaction>
{
	/**
 * The size of the object, indicating the count or quantity it represents.
 */
	private int size =0;
	/**
	 * The size of the object, indicating the count or quantity it represents.
	 */
	private SinglyLinkedList<Transaction> tlist;
	/**
	 * The size of the object, indicating the count or quantity it represents.
	 */
	private String roothash;
	/**
	* Constructs an empty Block with no transactions.
	*/
	public Block()
	{
		tlist = new SinglyLinkedList<>();
	}
	/**
	* Retrieves the singly linked list of transactions stored in the block.
	*
	* @return The singly linked list of transactions in the block.
	*/
	private SinglyLinkedList<Transaction> getlist(){
		return tlist;
	}

	/**
	* Adds a transaction to the block.
	*
	* @param t The transaction to add to the block.
	*/
	public void addTransaction(Transaction t)
	{
		tlist.insert(t);
		size++;
	}

	/**
	* Returns the number of transactions in the block.
	*
	* @return The number of transactions in the block.
	*/
	public int numOfTransactions()
	{
		return size;
	}

	/**
	* Returns the number of transactions in the block.
	*
	* @return The number of transactions in the block.
	*/
	public String getRootHash()
	{
		return roothash;
	}

	/**
	* Sets the root hash for the block to the specified hash code.
	*
	* @param hashCode The hash code to set as the root hash for the block.
	*/
	public void setRootHash(String hashCode)
	{
		roothash = hashCode;
	}
	/**
	* Compares this block to another block based on their properties.
	*
	* @param b The block to compare to.
	* @return A negative integer, zero, or a positive integer if this block is considered less than,
	*         equal to, or greater than the specified block, respectively.
	*/
	public int compareTo(Block b){
		return this.numOfTransactions() - b.numOfTransactions();
	}
	/**
	* Returns an iterable collection of transactions stored in the block.
	*
	* @return An iterable collection of transactions.
	*/
	public Iterator<Transaction> iterator() {
		return new Iterator<Transaction>(){
			int current =0;
			public boolean hasNext(){
				return current<Block.this.size;
			}
			public Transaction next(){
				return Block.this.tlist.get(current++);
			}
		};
	}

}
