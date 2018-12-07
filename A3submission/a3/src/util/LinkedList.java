package util;
/**
 * A class that stores a list of elements in nodes where each node points to a node containing the next element in the list.
 * @param <E> The class whose instances are stored as elements
 */
public class LinkedList<E> {
	
	private LinkedNode<E> head;
	private LinkedNode<E> tail;
	private int size;
	/**
	 * Creates an empty LinkedList object with size 0.
	 */
	public LinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	/**
	 * Returns the size of the linked list
	 * @return the size of the linked list
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Adds a piece of data to be stored at the beginning of the linked list.
	 * @param entry the data to be stored in a node at the start of the list
	 */
	public void addAtHead(E entry) {
		LinkedNode<E> temp = new LinkedNode<E>(entry, null);
		size++;
		if(head == null) {
			head = temp;
			tail = head;
		} else {
			temp.setNext(head);
			head = temp;
		}
	}
	/**
	 * Adds a piece of data to be stored at the end of the linked list.
	 * @param entry the data to be stored in a node at the end of the list
	 */
	public void addAtTail(E entry) {
		LinkedNode<E> temp = new LinkedNode<E>(entry, null);
		size++;
		if(head == null) {
			head = temp;
			tail = head;
		} else {
			tail.setNext(temp);
			tail = temp;
		}
	}
	/**
	 * Deletes a node at a specified index in the linked list.
	 * @param index the index of the node being deleted
	 */
	public E deleteAt(int index) {
		if (index == 0) 
        {
			LinkedNode temp = head;
            head = head.getNext();
            size--; 
            return (E)temp.data;
        }
        if (index == size - 1) 
        {
        	LinkedNode temp = tail;
            LinkedNode<E> temp1 = head;
            LinkedNode<E> temp2 = head;
            while (temp1 != tail)
            {
                temp2 = temp1;
                temp1 = temp1.getNext();
            }
            tail = temp2;
            tail.setNext(null);
            size --;
            return (E)temp.data;
        }
        LinkedNode<E> ptr = head;
        LinkedNode<E> ret = getNode(index);
        for (int i = 1; i < size - 1; i++) 
        {
            if (i == index) 
            {
                LinkedNode<E> temp = ptr.getNext();
                temp = temp.getNext();
                ptr.setNext(temp);
                break;
            }
            ptr = ptr.getNext();
        }
        return ret.data;
	}
	/**
	 * Returns an element at a given position in the linked list.
	 * @param pos the index of the node within the list
	 * @return the node at a specified index
	 */
    public LinkedNode<E> getNode(int pos) {
    	LinkedNode<E> temp = head;
    	for (int i = 0; i < pos; i++) {
    		temp = temp.getNext();
    	}
    	return temp;
    }
 
}    

	
