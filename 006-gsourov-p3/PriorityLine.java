import java.util.Iterator;
/**
* A generic priority queue (PriorityLine) that stores elements of type T, where T must implement the Comparable interface.
*
* @param <T> The type of elements stored in the priority queue.
*/
public class PriorityLine<T extends Comparable<T>> implements Iterable<T>
{
	/**
	* A singly linked list used as a priority queue.
	*/
	private SinglyLinkedList<T> pq;
	/**
	* Constructs an empty priority line.
	*/
	public PriorityLine()
	{
		pq = new SinglyLinkedList<>();
	}

	/**
	* Enqueues an element into the priority queue, adjusting its position based on its priority.
	*
	* @param element The element to enqueue into the priority queue.
	*/
	public void enqueue(T element)
	{
		if(pq.size()==0){
			pq.add(element);
		}else{
			pq.insert(element);
		}
	}

	/**
	* Dequeues and returns the element with the highest priority from the priority queue.
	*
	* @return The element with the highest priority.
	* @throws NoSuchElementException If the priority queue is empty.
	*/
	public T dequeue()
	{
		return pq.remove(0);
	}

	/**
	* Returns the number of elements currently in the priority queue.
	*
	* @return The number of elements in the priority queue.
	*/
	public int size()
	{
		return pq.size();
	}

	/**
	* Checks if the priority queue is empty.
	*
	* @return true if the priority queue is empty, false otherwise.
	*/
	public boolean isEmpty()
	{
		if(pq.size()==0){
			return true;
		}else{
			return false;
		}
	}

	/**
	* Retrieves the element with the highest priority from the priority queue without removing it.
	*
	* @return The element with the highest priority.
	* @throws NoSuchElementException If the priority queue is empty.
	*/
	public T peek()
	{
		return this.pq.get(0);
	}

	/**
	* Returns an iterator for iterating over the elements in the priority queue.
	*
	* @return An iterator for the elements in the priority queue.
	*/
	public Iterator<T> iterator() {
		return new CustomIterator();
	}
	/**
	* A custom iterator for iterating over the elements in the priority queue.
	*/
	private class CustomIterator implements Iterator<T> {
		/**
		* An integer representing the current position or index.
		*/
		private int current = 0;
		/**
		* Checks if there is a next element in the iteration.
		*
		* @return true if there is a next element, false otherwise.
		*/
		public boolean hasNext() {
			return current < PriorityLine.this.pq.size();
		}
		/**
		* Retrieves and returns the next element in the iteration.
		*
		* @return The next element in the iteration.
		*/
		public T next() {
			if (!hasNext()) {
				throw new IllegalStateException("No more elements.");
			}
			return PriorityLine.this.pq.get(current++);
		}
	}
}
