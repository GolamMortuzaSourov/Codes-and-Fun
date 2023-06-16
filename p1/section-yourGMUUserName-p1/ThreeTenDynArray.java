// TO DO: add your implementation and JavaDocs.

public class ThreeTenDynArray<T> {
	//default initial capacity / minimum capacity
	private static final int MIN_CAPACITY = 2;
	
	//underlying array for storage -- you MUST use this for credit!
	//Do NOT change the name or type
	private T[] data;

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	
	
	@SuppressWarnings("unchecked")
	public ThreeTenDynArray() {
		// Constructor
		
		// Initial capacity of the storage should be MIN_CAPACITY
		// Hint: Can't remember how to make an array of generic Ts? It's in the textbook...
		

	}

	@SuppressWarnings("unchecked")
	public ThreeTenDynArray(int initCapacity) {
		// Constructor

		// Initial capacity of the storage should be initCapacity.

		// - Throw IllegalArgumentException if initCapacity is smaller than 
		//   MIN_CAPACITY 2
		// - Use this _exact_ error message for the exception
		//   (quotes are not part of the message):
		//    "Capacity must be at least 2!"
		
	}
	

	public int size() {	
		// Report the current number of elements
		// O(1)
		
		return -1; //default return, remove/change as needed
	}  
		
	public int capacity() { 
		// Report max number of elements of the current storage
		// (subject to change since this is a _dynamic_ )
		
		// O(1)
		
		return -1; //default return, remove/change as needed
	}


	public T set(int index, T value) {
		// Replace the item at the given index to be the given value.
		// Return the old item at that index.
		// Note: You cannot add new items (i.e. cannot increase size) with this method.
		
		// O(1)
		
		// - Throw IndexOutOfBoundsException if index is not valid
		// - Use this code to produce the correct error message for
		// the exception (do not use a different message):
		//	  "Index: " + index + " out of bounds!"
		
		// - Throw IllegalArgumentException if value is null. 
		// - Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		//    "Cannot include null values!"

		
		return null; //default return, remove/change as needed
		
	}

	public T get(int index) {
		// Return the item at the given index
		
		// O(1)
		
		// Use the exception (and error message) described in set()
		// for invalid indicies.
				
		return null; //default return, remove/change as needed
	}

	@SuppressWarnings("unchecked")
	public void append(T value) {
		// Append an element to the end of the storage.		
		// Double the capacity if no space available.
		// Code reuse! Consider using setCapacity (see below).
		
		// For a null value,  use the same exception and message 
		// as set(). 
		
		// You can assume we will never need to grow the capacity to a value 
		// beyond Integer.MAX_VALUE/4.  No need to check or test that boundary 
		// value when you grow the capacity.
		
		// Amortized O(1)
		
		// - Throw IllegalArgumentException if value is null. 
		// - Use the same error message as set().


	} 

	@SuppressWarnings("unchecked")
	public void insert(int index, T value) {
		// Insert the given value at the given index and shift elements if needed. 
		// You _can_ append items with this method.

		// Double capacity if no space available. 
		// Assume the same as append() for the upper bound of capacity.
		// Code reuse! Consider using setCapacity (see below).
		
		// For an invalid index or a null value,  use the same exception and message 
		// as set(). However, remember that the condition of the exception is
		// different (a different invalid range for indexes).
		
		// O(N) where N is the number of elements in the storage
		
		
	} 
	
	
	@SuppressWarnings("unchecked")
	public T remove(int index) {
		// Remove and return the element at the given index. Shift elements
		// to ensure no gap. Throw an exception when there is an invalid
		// index (see set(), get(), etc. above).
		
		// Halve capacity (rounding down) of the storage if the number of elements falls
		// below or at 1/4 of the capacity. 
		// However, capacity should NOT go below MIN_CAPACITY.
		// Code reuse! Consider using setCapacity (see below).
		
		// O(N) where N is the number of elements currently in the storage
		
		return null; //default return, remove/change as needed

	}  

	
	@SuppressWarnings("unchecked")
	public boolean setCapacity(int newCapacity) {
		// Change the max number of items allowed before next expansion to newCapacity.
		// No other changes to the current values in storage 
		// (i.e. they should all keep the same index).
		
		// Capacity should not be changed if:
		//   - newCapacity is below MIN_CAPACITY; or 
		//   - newCapacity is not large enough to accommodate current number of items
		
		// Return false if newCapacity cannot be applied; return true otherwise.
		// Special case: if newCapacity is identical to the current max number of items,
		// no need to change anything but still return true.
		
		// O(N) where N is the number of elements in the array
		
		return false; //default return, remove/change as needed
		
		
	}
	
	public int firstIndexOf(T value) {
		// Return the index of the first occurrence of value or -1 if not found. 
		// NOTES: - Remember null is not a valid item in list.
		//        - Remember to use .equals for comparison of non-null values.
		// O(N) where N is the number of elements in the list
		
		return -2; //default return, remove/change as needed
	}
	
	//******************************************************
	//*******     BELOW THIS LINE IS PROVIDED code   *******
	//*******             Do NOT edit code!          *******
	//*******		   Remember to add JavaDoc		 *******
	//******************************************************

	@Override
	public String toString() {
		//This method is provided. Add JavaDoc and comments.
		
		StringBuilder s = new StringBuilder("[");
		for (int i = 0; i < size(); i++) {
			s.append(get(i));
			if (i<size()-1)
				s.append(", ");
		}
		s.append("]");
		return s.toString().trim();
		
	}
	
	//******************************************************
	//*******     BELOW THIS LINE IS TESTING CODE    *******
	//*******      Edit it as much as you'd like!    *******
	//*******		Remember to add JavaDoc			 *******
	//******************************************************
	

	public String toStringDebug() {
		//This method is provided for debugging purposes
		//(use/modify as much as you'd like), it just prints
		//out the ThreeTenDynArray details for easy viewing.
		StringBuilder s = new StringBuilder("ThreeTenDynArray with " + size()
			+ " items and a capacity of " + capacity() + ":");
		for (int i = 0; i < size(); i++) {
			s.append("\n  ["+i+"]: " + get(i));
		}
		return s.toString().trim();
		
	}
	
	public static void main(String args[]){
		//These are _sample_ tests. If you're seeing all the "yays" that's
		//an excellend first step! But it does NOT guarantee your code is 100%
		//working... You may edit this as much as you want, so you can add
		//own tests here, modify these tests, or whatever you need!

		//create a ThreeTenDynArray of integers
		ThreeTenDynArray<Integer> nums = new ThreeTenDynArray<>();
		if((nums.size() == 0) && (nums.capacity() == MIN_CAPACITY)){
			System.out.println("Yay 1");
		}

		//append some numbers 
		for(int i = 0; i < 3; i++) {
			nums.append(i*3 % 2);
		}
		//uncomment to check details
		//System.out.println(nums);
		
		//checking
		if(nums.size() == 3 && nums.get(2) == 0 && nums.capacity() == 4
			&& nums.firstIndexOf(1) == 1 && nums.firstIndexOf(0) == 0
			&& nums.firstIndexOf(6) == -1 ){
			System.out.println("Yay 2");
		}
		
		//create a ThreeTenDynArray of strings
		ThreeTenDynArray<String> title = new ThreeTenDynArray<>();
		
		//insert some strings
		title.insert(0,"using");
		title.insert(0,"problem");
		title.insert(1,"solving");
		title.insert(3,"c");
		
		//checking and replace
		if (title.get(0).equals("problem") && title.set(3,"java").equals("c") 
			&& title.size() == 4 && title.capacity() == 4){
			System.out.println("Yay 3");
		}
		
		title.insert(0,"&");
		title.insert(0,"structures");
		title.insert(0,"data");
		//uncomment to check details
		//System.out.println(title);
		
		//delete 
		if (title.capacity() == 8 && title.remove(5).equals("using") &&
			title.remove(title.size()-1).equals("java")){
			System.out.println("Yay 4");
		}

		//change capacity 
		if (!title.setCapacity(4) && !title.setCapacity(1) 
			&& title.setCapacity(20) && title.capacity()==20){
			System.out.println("Yay 5");
		}
		
		//remove and shrinking
		if (title.remove(2).equals("&") && title.capacity()==10){
			System.out.println("Yay 6");
		}
				
		//exception checking
		try{
			title.append(null);
		}
		catch (IllegalArgumentException ex){
			if (ex.getMessage().equals("Cannot include null values!")){
				System.out.println("Yay 7");			
			}
		}
	}
		

}