package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import QueueLab.Queue;
import QueueLab.QueueEmptyException;
import QueueLab.QueueFullException;

class QueueLabTest {

	@Test
	void testCreateQueue() {
		// ARRANGE
		Queue myQueue = new Queue(1);
		boolean actual;
		// ACT
		actual = myQueue.isEmpty();
		// ASSERT
		assertTrue(actual);
	}

	@Test
	void testIsEmptyTrue() {
		// ARRANGE
		Queue myQueue = new Queue(1);
		boolean actual;
		// ACT
		actual = myQueue.isEmpty();
		// ASSERT
		assertTrue(actual);
	}

	@Test
	void testIsEmptyFalse() throws QueueFullException {
		// ARRANGE
		Queue myQueue = new Queue(1);
		String item = "Java is Fun!";
		boolean actual;
		// ACT
		myQueue.enqueue(item);
		actual = myQueue.isEmpty();
		// ASSERT
		assertFalse(actual);
	}

	@Test
	void testIsFullTrue() throws QueueFullException {
		// ARRANGE
		Queue myQueue = new Queue(1);
		String item = "Java is Fun!";
		boolean actual;
		// ACT
		myQueue.enqueue(item);
		actual = myQueue.isFull();
		// ASSERT
		assertTrue(actual);
	}

	@Test
	void testIsFullFalse() throws QueueFullException {
		// ARRANGE
		Queue myQueue = new Queue(1);
		boolean actual;
		// ACT
		actual = myQueue.isFull();
		// ASSERT
		assertFalse(actual);
	}

	@Test
	void testEnqueue() throws QueueFullException, QueueEmptyException {
		// ARRANGE
		Queue myQueue = new Queue(2);
		String item = "QueueItem";
		String actual, expected;
		expected = "QueueItem1";
		// ACT
		myQueue.enqueue(item + "1");
		actual = myQueue.peek();
		// ASSERT
		assertEquals(expected, actual);
	}
	
	@Test
	void testEnqueueDequeue() throws QueueFullException, QueueEmptyException {
		// ARRANGE
		Queue myQueue = new Queue(3);
		String item = "QueueItem";
		String actual, expected;
		expected = "QueueItem3";
		// ACT
		myQueue.enqueue(item + "1");
		myQueue.enqueue(item + "2");
		myQueue.dequeue();
		myQueue.dequeue();
		myQueue.enqueue(item + "3");		
		actual = myQueue.peek();
		// ASSERT
		assertEquals(expected, actual);
	}	

	@Test
	void testEnqueueFullQueue() throws QueueFullException {
		// ARRANGE
		Queue myQueue = new Queue(1);
		String item = "QueueItem";
		myQueue.enqueue(item);
		// ACT
		// ASSERT
		assertThrows(QueueFullException.class, () -> myQueue.enqueue(item));
	}

	@Test
	void testWrapAround() throws QueueFullException, QueueEmptyException {
		// ARRANGE
		Queue myQueue = new Queue(2);
		String item = "QueueItem";
		String actual, expected;
		expected = "QueueItem3";
		// ACT
		myQueue.enqueue(item + "1");
		myQueue.enqueue(item + "2");
        myQueue.dequeue();
		myQueue.enqueue(item + "3");
		myQueue.dequeue(); 
		actual = myQueue.peek();
		// ASSERT
		assertEquals(expected, actual);
	}
	
	@Test
	void testDequeue() throws QueueFullException, QueueEmptyException {
		// ARRANGE
		Queue myQueue = new Queue(3);
		String item = "QueueItem";
		String actual, expected;
		expected = item+"3";
		myQueue.enqueue(item+"1");
		myQueue.dequeue();
		myQueue.enqueue(item+"2");
		myQueue.enqueue(item+"3");
		myQueue.dequeue();
		myQueue.enqueue(item+"4");
		// ACT
		actual = myQueue.dequeue();
		// ASSERT
		assertEquals(expected, actual);
	}
	
	@Test
	void testDequeueEmptyQueue() {
		// ARRANGE
		Queue myQueue = new Queue(1);
		// ACT
		// ASSERT
		assertThrows(QueueEmptyException.class, () -> myQueue.dequeue());
	}
	
	@Test
	void testSizeZero() {
		// ARRANGE
		Queue myQueue = new Queue(2);
		int actual, expected;
		expected = 0;
		// ACT
		actual = myQueue.size();
		// ASSERT
		assertEquals(expected, actual);
	}

	@Test
	void testSizeNonZero() throws QueueFullException, QueueEmptyException {
		// ARRANGE
		Queue myQueue = new Queue(3);
		String item = "QueueItem";
		int actual, expected;
		expected = 2;
		// ACT
		myQueue.enqueue(item + "1");
		myQueue.enqueue(item + "2");
		myQueue.enqueue(item + "3");
		myQueue.dequeue();
		actual = myQueue.size();
		// ASSERT
		assertEquals(expected, actual);
	}

	@Test
	void testPeek() throws QueueEmptyException, QueueFullException {
		// ARRANGE
		Queue myQueue = new Queue(1);
		String item = "QueueItem";
		String actual, expected;
		// ACT
		myQueue.enqueue(item);
		expected = item;
		actual = myQueue.peek();
		// ASSERT
		assertEquals(expected, actual);
	}

	@Test
	void testPeekEmptyQueue() {
		// ARRANGE
		Queue myQueue = new Queue(1);
		// ACT
		// ASSERT
		assertThrows(QueueEmptyException.class, () -> myQueue.peek());
	}
	
	@Test
	void testPrintQueueEmptyQueue() {
		// ARRANGE
		Queue myQueue = new Queue(1);
		// ACT
		// ASSERT
		assertThrows(QueueEmptyException.class, () -> myQueue.printQueue());
	}
	
	@Test
	void testPrintQueue() throws QueueEmptyException, QueueFullException {
		// ARRANGE
		Queue myQueue = new Queue(2);
		String item = "QueueItem";
		String actual, expected;
		expected = "QueueItem1\nQueueItem2\n";
		// ACT
		myQueue.enqueue(item + "1");
		myQueue.enqueue(item + "2");
		actual = myQueue.printQueue();
		// ASSERT
		assertEquals(expected, actual);
	}
	
	@Test
	void testPrintQueueWrapAround() throws QueueFullException, QueueEmptyException {
		// ARRANGE
		Queue myQueue = new Queue(2);
		String item = "QueueItem";
		String actual, expected;
		expected = "QueueItem2\nQueueItem3\n";
		// ACT
		myQueue.enqueue(item + "1");
		myQueue.enqueue(item + "2");
		myQueue.dequeue();
		myQueue.enqueue(item + "3");
		actual = myQueue.printQueue();
		// ASSERT
		assertEquals(expected, actual);
	}


}