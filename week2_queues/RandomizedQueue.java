import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private int numberOfItems;
	private int size;
	private Item[] arr;

	public RandomizedQueue()                 // construct an empty randomized queue
	{
		size = 1;
		arr = (Item[]) new Object[size];
	}

	public boolean isEmpty()                 // is the queue empty?
	{ return numberOfItems == 0; }

	public int size()                        // return the number of items on the queue
	{ return numberOfItems; }

	private void resize(int s)
	{
		Item[] tmp = (Item[]) new Object[s];
		for (int a = 0; a < numberOfItems; a++) tmp[a] = arr[a];
		arr = tmp;
		size = s;
	}

	private void checkNull(Item item)
	{
		if (item == null)
			throw new java.lang.IllegalArgumentException("null item");
	}

	public void enqueue(Item item)           // add the item
	{
		checkNull(item);
		if (numberOfItems == size) resize(size * 2);
		arr[numberOfItems++] = item;
	}

	private void checkEmpty()
	{
		if (isEmpty())
			throw new java.util.NoSuchElementException("empty deque");
	}

	public Item dequeue()                    // remove and return a random item
	{
		checkEmpty();
		int index = StdRandom.uniform(numberOfItems);
		Item ret = arr[index];
		arr[index] = arr[numberOfItems - 1];
		arr[numberOfItems - 1] = null;
		numberOfItems--;
		if (numberOfItems < size / 4) resize(size / 2);
		return ret;
	}

	public Item sample()                     // return (but do not remove) a random item
	{ return arr[StdRandom.uniform(numberOfItems)]; }

	private class RandomizedQueueIterator implements Iterator<Item>
	{
		private int i = 0;
		private Item[] rand;

		public RandomizedQueueIterator()
		{
			rand = (Item[]) new Object[numberOfItems];
			for (int a = 0; a < numberOfItems; a++) {
				rand[a] = arr[a];
			}
			StdRandom.shuffle(rand);
		}
		public boolean hasNext() { return i < numberOfItems; }
		public void remove()     { throw new java.lang.UnsupportedOperationException("remove not supported"); }
		public Item next()       
		{
			i++;
			if (i == numberOfItems) throw new java.util.NoSuchElementException("last element reached element");
			return rand[i];
		}
	}

	public Iterator<Item> iterator()         // return an independent iterator over items in random order
	{ return new RandomizedQueueIterator(); }

	public static void main(String[] args)   // unit testing (optional)
	{ }

}