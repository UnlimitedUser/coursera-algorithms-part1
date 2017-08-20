import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	private class Node<Item> {
		Item value;
		Node<Item> next;
		Node<Item> prev;
	}

	private Node<Item> first;
	private Node<Item> last;
	private int size;

	public Deque()                           // construct an empty deque 
	{ size = 0; }
	
	public boolean isEmpty()                 // is the deque empty?
	{ return size == 0; }
	
	public int size()                        // return the number of items on the deque
	{ return size; }
	
	private void checkNull(Item item)
	{
		if (item == null)
			throw new java.lang.IllegalArgumentException("null item");
	}

	public void addFirst(Item item)          // add the item to the front
	{
		checkNull(item);
		Node<Item> oldFirst = first;
		first = new Node<Item>();
		first.value = item;
		first.next = oldFirst;
		if (isEmpty()) last = first;
		else           oldFirst.prev = first;
		size++;
	}

	public void addLast(Item item)           // add the item to the end
	{
		checkNull(item);
		Node<Item> oldLast = last;
		last = new Node<Item>();
		last.value = item;
		last.prev = oldLast;
		if (isEmpty()) first = last;
		else           oldLast.next = last;
		size++;
	}
	
	private void checkEmpty()
	{
		if (isEmpty())
			throw new java.util.NoSuchElementException("empty deque");
	}

	public Item removeFirst()                // remove and return the item from the front
	{
		checkEmpty();
		Item value = first.value;
		first = first.next;
		size--;
		if (isEmpty()) last = null;
		else           first.prev = null;
		return value;
	}

	public Item removeLast()                 // remove and return the item from the end
	{
		checkEmpty();
		Item value = last.value;
		last = last.prev;
		size--;
		if (isEmpty()) first = null;
		else           last.next = null;
		return value;
	}

	private class DequeueIterator implements Iterator<Item>
	{
		private Node<Item> i = first;

		public boolean hasNext() { return i.next != null; }
		public void remove()     { throw new java.lang.UnsupportedOperationException("remove not supported"); }
		public Item next()       
		{
			Item ret = i.value;
			i = i.next;
			if (i == null) throw new java.util.NoSuchElementException("last element reached element");
			return ret;
		}
	}

	public Iterator<Item> iterator()         // return an iterator over items in order from front to end
	{ return new DequeueIterator(); }
	
	public static void main(String[] args)   // unit testing (optional)
	{  }

}