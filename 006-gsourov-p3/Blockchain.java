import java.util.Iterator;
/**
* Represents a blockchain, a chain of blocks containing transactions.
*/

public class Blockchain implements Iterable<Block>
{
	/**
	* The size of the object, indicating the count or quantity it represents.
	*/
	private SinglyLinkedList<Block> blist;
	/**
	* The size of the object, indicating the count or quantity it represents.
	*/
	private int size =0;
	/**
	* The size of the object, indicating the count or quantity it represents.
	*/
	private int cumufee = 0;
	/**
	* Initializes a new blockchain with a priority queue of transactions and a threshold value.
	*
	* @param queue     The priority queue of transactions to populate the blockchain.
	* @param threshold The threshold value to determine block creation.
	*/
	public Blockchain(PriorityLine<Transaction> queue, int threshold)
	{

		blist = new SinglyLinkedList<>();

		Iterator<Transaction> i = queue.iterator();

		Iterator<Transaction> i2 = queue.iterator();

		Block b = new Block();
		while(i.hasNext()){
			Transaction tempt = i.next();
			cumufee = cumufee + tempt.getFee();
			if(this.cumufee<=threshold){
				b.addTransaction(tempt);
				if( !(i.hasNext()) ){
					blist.insert(b);
					this.size++;
					this.cumufee=0;
					b = new Block();
				}
			}else{
				blist.insert(b);
				this.size++;
				this.cumufee=0;
				b = new Block();
			}
		}
	}
	/**
	* Returns an iterator for iterating over the blocks in the blockchain.
	*
	* @return An iterator for the blocks in the blockchain.
	*/
	public Iterator<Block> iterator(){
		return new Iterator<Block>(){
			/**
			* A local iterator for iterating over blocks within the blockchain.
			*/
			private int current = 0;
			/**
			* Checks if there is a next element to be iterated in the collection.
			*
			* @return true if there is a next element, false otherwise.
			*/
			public  boolean hasNext(){
				return current < Blockchain.this.size;
			}
			/**
			* Retrieves the next element from the collection during iteration.
			*
			* @return The next element in the collection.
			*/
			public Block next(){
				return Blockchain.this.blist.get(current++);
			}
		};

	}
}
