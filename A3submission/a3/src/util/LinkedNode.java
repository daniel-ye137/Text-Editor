package util;
/**
 * A class that stores an element and points to the next node in a linked list.
 * @param <E> The class whose instances are being stored in the node
 */
public class LinkedNode<E> {
	LinkedNode<E> next;
	E data;
	/**
	 * Creates an empty node
	 */
	public LinkedNode() {
		next = null;
		data = null;
	}
	/**
	 * Creates a node for a linked list with specified data and pointer to next Node.
	 * @param entry object to be stored by the instance of a LinkedNode
	 */
	public LinkedNode(E entry, LinkedNode<E> link) {
		data = entry;
		next = link;
	}
	/**
	 * Sets the next node to a specified node.
	 * @param n the next node in a linked list
	 */
	public void setNext(LinkedNode<E> n) {
		next = n;
	}
	/**
	 * Sets the data being stored in the node to a specified Object.
	 * @param d the data to be stored in the node
	 */
	public void setData(E d) {
		data = d;
	}
	/**
	 * Returns the next node that the current node points to.
	 * @return whatever is stored in next
	 */
	public LinkedNode<E> getNext() {
		return next;
	}
	/**
	 * Returns data stored in a node.
	 * @return whatever is stored in data
	 */
	public Object getData() {
		return data;
	}
}
