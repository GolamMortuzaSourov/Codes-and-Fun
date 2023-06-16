/**

ScoreBoard class is a representation of a player's ScoreBoard in a game.

@author Golam Mortuza Sourov
@param <T> type of the class
*/
public class ThreeTenDynArray<T> {
	/**
	* game count.
	*/
	private static final int MIN_CAPACITY = 2;
	/**
	* game count.
	*/
	private T[] data;
	/**
	* game count.
	*/
	private int size;
	/**
	* size of the array.
	*/
	@SuppressWarnings("unchecked")
	public ThreeTenDynArray() {
		this.data = (T[]) new Object[MIN_CAPACITY];
		this.size = 0;
	}
	/**
	* size of the array.
	* @param initCapacity initial capacity
	*/
	@SuppressWarnings("unchecked")
	public ThreeTenDynArray(int initCapacity) {
		if (initCapacity < MIN_CAPACITY) {
			throw new IllegalArgumentException("Capacity must be at least 2!");
		}
		this.data = (T[]) new Object[initCapacity];
		this.size = 0;
	}
	/**
	* size of the array.
	* @return size of the array
	*/
	public int size() {
		return size;
	}
	/**
	* size of the array.
	* @return size of the array
	*/
	public int capacity() {
		return data.length;
	}
	/**
	* size of the array.
	* @param index index number
	* @param value value in the index
	* @return size of the array
	*/
	public T set(int index, T value) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + " out of bounds!");
		}
		if (value == null) {
			throw new IllegalArgumentException("Cannot include null values!");
		}
		T old = data[index];
		data[index] = value;
		return old;
	}
	/**
	* size of the array.
	* @param index index number
	* @return size of the array
	*/
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + " out of bounds!");
		}
		return data[index];
	}
	
	/**
	* size of the array.
	* @param value what value
	*/
	public void append(T value) {
		if (value == null) {
			throw new IllegalArgumentException("Cannot include null values!");
		}
		if (size == data.length) {
			setCapacity(data.length * 2);
		}
		data[size++] = value;
	}
	/**
	* size of the array.
	* @param index indexes
	* @param value value
	*/
	public void insert(int index, T value) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + " out of bounds!");
		}
		if (value == null) {
			throw new IllegalArgumentException("Cannot include null values!");
		}
		if (size == data.length) {
			setCapacity(data.length * 2);
		}
		System.arraycopy(data, index, data, index + 1, size - index);
		data[index] = value;
		size++;
	}
	/**
	* size of the array.
	* @param index indexes
	* @return size of the array
	*/
	@SuppressWarnings("unchecked")
	public T remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		}
		
		T item = data[index];
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		size--;
		
		if (size <= data.length / 4 && data.length / 2 >= MIN_CAPACITY) {
			setCapacity(data.length / 2);
		}
		
		return item;
	}
	
	/**
	* size of the array.
	* @param newCapacity indexes
	* @return size of the array
	*/
	@SuppressWarnings("unchecked")
	public boolean setCapacity(int newCapacity) {
		if (newCapacity < MIN_CAPACITY || newCapacity < size) {
			return false;
		}
		if (newCapacity == this.data.length) {
			return true;
		}
		T[] newData = (T[]) new Object[newCapacity];
		System.arraycopy(this.data, 0, newData, 0, size);
		this.data = newData;
		return true;
	}
	
	/**
	* size of the array.
	* @param value value
	* @return size of the array
	*/
	public int firstIndexOf(T value) {
		if (size == 0) return 0;
		
		for (int i = 0; i < size; i++) {
			if (value.equals(data[i])) {
				return i;	
			}
		}
		
		return -1;
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
	
	/**
	* size of the array.
	* @return size of the array
	*/
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
	/**
	* size of the array.
	* @param args argument
	*/
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
