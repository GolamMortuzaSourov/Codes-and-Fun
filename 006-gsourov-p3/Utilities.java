import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
/**
* This utility class provides various helper methods for common tasks.
*/
public final class Utilities
{
	/**
	* Loads a list of transactions from a specified program file and returns a PriorityLine
	* containing those transactions.
	*
	* @param pgmFile The path to the program file containing transaction data.
	* @return A PriorityLine containing the loaded transactions.
	* @throws FileNotFoundException If the program file specified by `pgmFile` is not found.
	* @throws IOException If there is an issue reading the program file.
	*/
	public static PriorityLine<Transaction> loadTransactions(String pgmFile)
	{
		try{
			File file = new File(pgmFile);
			Scanner sc = new Scanner(file);
			PriorityLine<Transaction> pt = new PriorityLine<>();
			while(sc.hasNextLine()){
				String sender="";
				String receiever="";
				int amount=0;
				int fee=0;
				int count =1;
				String temp =sc.nextLine();
				Scanner s = new Scanner(temp);
				while(s.hasNext()){
					if(count==1){
						sender = s.next();
						count++;
					}else if(count==2){
						receiever = s.next();
						count++;
					}else if(count ==3){
						amount = Integer.parseInt(s.next());
						count++;
					}else if(count==4){
						fee = Integer.parseInt(s.next());
						count=1;
					}
				}
				Transaction t = new Transaction(sender,receiever,amount,fee);
				pt.enqueue(t);
			}
			return pt;
		}catch(FileNotFoundException e){
			System.err.println("File Not Found");
		}
		return null;
	}

	/**
	 * Verifies the authenticity of a transaction using a Merkle proof and the block's root hash.
	 *
	 * @param t             The transaction to be verified.
	 * @param proof         A singly linked list containing the Merkle proof for the transaction.
	 * @param blockRootHash The root hash of the block that the transaction is supposed to be a part of.
	 * @return true if the transaction is verified successfully, false otherwise.
	 */
	public static boolean verifyTransaction(Transaction t, SinglyLinkedList<String> proof, String blockRootHash)
	{
		String thash = cryptographicHashFunction(t.toString());
		int index=0;
		while(index<proof.size()){
			String phash = proof.get(index);
			thash = cryptographicHashFunction(thash,phash);
			index++;
		}
		if(thash==blockRootHash){
			return true;
		}else{
			return false;
		}
	}

	/**
	* This method takes an input string and calculates its cryptographic hash.
	*
	* @param input The input string to be hashed.
	* @return The cryptographic hash of the input string as a hexadecimal string.
	* @throws NoSuchAlgorithmException If the cryptographic hash algorithm is not available.
	*/
	public static String cryptographicHashFunction(String input)
	{
		StringBuilder hexString = null;

		try
		{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
			hexString = new StringBuilder(2 * encodedhash.length);
			for (int i = 0; i < encodedhash.length; i++)
			{
				String hex = Integer.toHexString(0xff & encodedhash[i]);
				if(hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}

		return hexString.toString();
	}

	/**
	* This method takes two input strings and calculates their combined cryptographic hash.
	*
	* @param input1 The first input string to be included in the hash.
	* @param input2 The second input string to be included in the hash.
	* @return The cryptographic hash of the combined input strings as a hexadecimal string.
	* @throws NoSuchAlgorithmException If the cryptographic hash algorithm is not available.
	*/
	public static String cryptographicHashFunction(String input1, String input2)
	{
		StringBuilder hexString = null;

		try
		{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash1 = digest.digest(input1.getBytes(StandardCharsets.UTF_8));
			byte[] encodedhash2 = digest.digest(input2.getBytes(StandardCharsets.UTF_8));
			hexString = new StringBuilder(2 * encodedhash1.length);
			for (int i = 0; i < encodedhash1.length; i++)
			{
				String hex = Integer.toHexString(0xff & (encodedhash1[i] ^ encodedhash2[i]) );
				if(hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		return hexString.toString();
	}
}
