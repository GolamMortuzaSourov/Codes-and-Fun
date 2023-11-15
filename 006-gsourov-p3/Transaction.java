/**
* Represents a financial transaction with specific details such as amount, date, and description.
* This class implements the Comparable interface to allow for comparisons based on transaction dates.
*/
public class Transaction implements Comparable<Transaction>
{
	/**
	* The head node of the singly linked list.
	*/
	private String sender;
	/**
	* The head node of the singly linked list.
	*/
	private String receiver;
	/**
	* The head node of the singly linked list.
	*/
	private int amount;
	/**
	* The head node of the singly linked list.
	*/
	private int fee;
	/**
	* Creates a new Transaction with the specified sender, receiver, amount, and fee.
	*
	* @param sender   The sender's identifier or account.
	* @param receiver The receiver's identifier or account.
	* @param amount   The amount of the transaction.
	* @param fee      The transaction fee to be deducted.
	*/
	public Transaction(String sender, String receiver, int amount, int fee)
	{
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.fee = fee;
	}
	/**
	* Returns a string representation of the Transaction.
	*
	* @return A string containing the sender, receiver, amount, and fee of the Transaction.
	*/
	public String toString()
	{
		return String.format("%s %s %d %d", sender, receiver, amount, fee);
	}
	/**
	* Retrieves the transaction fee associated with this Transaction.
	*
	* @return The transaction fee in integer format.
	*/
	public int getFee()
	{
		return fee;
	}
	/**
	* Compares this Transaction to another Transaction based on their attributes.
	*
	* @param t The Transaction to compare to.
	* @return A negative integer, zero, or a positive integer if this Transaction is considered
	*         less than, equal to, or greater than the specified Transaction, respectively.
	*/
	public int compareTo(Transaction t){
		return this.fee - t.getFee();
	}
}
