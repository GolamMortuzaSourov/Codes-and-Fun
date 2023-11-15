import java.util.Iterator;
/**
* A generic singly linked list implementation that stores elements of type T.
*
* @param <T> The type of elements stored in the linked list, which must implement the Comparable interface.
*/
public class SinglyLinkedList<T extends Comparable<T>> implements Iterable<T>
{
	/**
	* The head node of the singly linked list.
	*/
	private Node<T> head;
	/**
	* The head node of the singly linked list.
	*/
	private int size;
	/**
	* Constructs an empty SinglyLinkedList.
	*/
	public SinglyLinkedList() {
		head = null;
		size = 0;
	}

	/**
	* Adds a new element to the end of the linked list.
	*
	* @param value The element to add to the linked list.
	*/
	public void add(T value) {
		Node<T> newNode = new Node<>(value);

		if (head == null) {
			head = newNode;
		} else {
			Node<T> current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = newNode;
		}

		size++;
	}

	/**
	* Inserts a new element into the linked list at the beginning.
	*
	* @param newValue The element to insert into the linked list.
	*/
	public void insert(T newValue) {
		Node<T> newNode = new Node<>(newValue);

		if (head == null || newValue.compareTo(head.data) >= 0) {
			newNode.next = head;
			head = newNode;
		} else {
			Node<T> current = head;
			while (current.next != null && newValue.compareTo(current.next.data) < 0) {
				current = current.next;
			}
			newNode.next = current.next;
			current.next = newNode;
		}

		size++;
	}

	/**
	* Removes and returns the element at the specified index in the linked list.
	*
	* @param index The index of the element to remove.
	* @return The removed element.
	* @throws IndexOutOfBoundsException If the index is out of bounds.
	*/
	public T remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}

		if (index == 0) {
			T removedValue = head.data;
			head = head.next;
			size--;
			return removedValue;
		}

		Node<T> current = head;
		for (int i = 0; i < index - 1; i++) {
			current = current.next;
		}

		T removedValue = current.next.data;
		current.next = current.next.next;
		size--;
		return removedValue;
	}

	/**
	* Retrieves the element at the specified index in the linked list.
	*
	* @param index The index of the element to retrieve.
	* @return The element at the specified index.
	* @throws IndexOutOfBoundsException If the index is out of bounds.
	*/
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}

		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}

		return current.data;
	}

	/**
	* Get the size of the linked list.
	* @return The element at the specified index.
	*/
	public int size() {
		return size;
	}

	/**
	* Check if the linked list is empty.
	* @return The element at the specified index.
	*/
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	* Implement the iterator for the Iterable interface.
	* @return The element at the specified index.
	*/
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node<T> current = head;

			public boolean hasNext() {
				return current != null;
			}

			public T next() {
				if (!hasNext()) {
					return null;
				}
				T data = current.data;
				current = current.next;
				return data;
			}
		};
	}
	/**
	* Represents a node in the linked list that holds an element of type Z.
	*
	* @param <T> The type of element stored in the node.
	*/
	private class Node<T> {
		/**
		* The head node of the singly linked list.
		*/
		T data;
		/**
		* The head node of the singly linked list.
		*/
		Node<T> next;
		/**
		* Constructs a Node with the specified data.
		*
		* @param data The data to be stored in the Node.
		*/
		Node(T data) {
			this.data = data;
			this.next = null;
		}
	}
}
