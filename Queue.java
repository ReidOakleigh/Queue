package QueueLab;
/**************************************************************
* Name        : Array-based Queue with wraparound
* Author      : Rumbi Chinhamhora
* Created     : 2/14/2021
* Course      : CIS 152 Data Structures
* Version     : 1.0
* OS          : Windows 10
* Language    : Java
* Copyright   : This is my own original work based on
*               specifications issued by our instructor
* Description : Create an array based Queue
*               Input: Items to be added to queue
*               Output: Items in queue or removed from queue
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or 
* unmodified. I have not given other fellow student(s) access to
* my program.         
***************************************************************/

public class Queue {
	// Members
	private int head, tail, size, maxSize;
	private String queue[];

	/*
	 * Queue default constructor; default sis = 5
	 */
	public Queue() {
		this.maxSize = 5;
		this.head = 0;
		this.tail = -1;
		this.maxSize = 0; 
		this.queue = new String[maxSize];
	}

	/*
	 * @param maxSize : int - sets maximum size of the queue
	 */
	public Queue(int maxSize) {
		this.maxSize = maxSize;
		this.head = 0;
		this.tail = -1;
		this.size = 0;
		this.queue = new String[maxSize];
	}

	/*
	 * This method adds item to tail of the queue, unless tail is at the end, then it wraps around.
	 * @param item : String
     * @throws QueueFullException
	 */
	public void enqueue(String item) throws QueueFullException {
		if (isFull())
			throw new QueueFullException(); 
			
		if (tail + 1 == maxSize) { 				// Wraparound?
			for (int i = 0; i < maxSize; i++) {	// search for empty element at the front of the array
				if (queue[i] == null) {
					tail = i - 1; 
					break;
				}
			}
		} 
		tail++; 					// advance the tail
		queue[tail] = item; 		// stores item at tail position
		size++;						// increments number of items in array
	}

	/*
	 * This method removes and returns element at head of queue
	 * @return : String - returns element at head of queue
     * @throws QueueEmptyException
	 */
	public String dequeue() throws QueueEmptyException {
		String item = null;
		if (isEmpty())
			throw new QueueEmptyException(); 
		
		item = queue[head]; 			// Retrieve the item at the head of the queue
		queue[head] = null; 			// Blanks the queue position
		if (head + 1 == maxSize) {		// Wraparound?
			head = 0; 					// reset head to front of the array
		} else {
			head++; 					// Advances head
		}
		size--; 						// decrements number of items in array
	
		
		return item; 					// returns element
	}

	/*
	 * This method checks if the queue is empty.
	 * @return : boolean - true If queue is empty, else false
	 */
	public boolean isEmpty() {
		return size == 0;			// No elements in queue
	}

	/*
	 * This method checks if the queue is full.
	 * @return : boolean - true if queue is full, else false
	 */
	public boolean isFull() {
		return size == maxSize;		// Queue is full
	}

	/*
	 * This method returns the number of elements in the queue.
	 * @return : int - number of elements in queue
	 */
	public int size() {
		return size;
	}

	/*
	 * This method returns a list of queue items from head to tail one per line.
	 * @return : String - items in queue
	 * @throws QueueEmptyException
	 */
	public String printQueue() throws QueueEmptyException {
		StringBuilder sb = new StringBuilder();
		if (isEmpty()) {
			throw new QueueEmptyException();
		}
		
		if (tail < head) {
			for (int i = head; i < maxSize; i++) {
				sb.append(queue[i]).append("\n");
			}
			for (int i =0; i <= tail; i++) {
				sb.append(queue[i]).append("\n");
			}
		} else {
			for (int i = head; i <= tail; i++) {
				sb.append(queue[i]).append("\n");
			}
		}
		
		return sb.toString();
	}

	/*
	 * This method returns element at head of queue without removing it.
	 * @return : String - element at head of queue
	 * @throws StackEmptyException
	 */
	public String peek() throws QueueEmptyException {
		if (isEmpty())
			throw new QueueEmptyException(); 
	
		return queue[head]; 
	}

}
