package structures;
import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {
	private Node<T> front;
	private Node<T> rear;
	int size;
	public Queue() {
		front = null;
		rear = null;
		size = 0;	
    	       // TODO 1
    }
	
	public Queue(Queue<T> other) {
            // TODO 2
		front = null;
		rear = null;
		size = 0;
		Node<T> current = other.front;
		while (current != null) {
			enqueue(current.data);
			current = current.next;
		}
		
	}

	@Override
	public boolean isEmpty() {
            // TODO 3
        return (front == null); 
	}

	@Override
	public int getSize() {
            // TODO 4
		return size;
	}

	@Override
	public void enqueue(T element) {
            // TODO 5
		if (element == null) {
			throw new IllegalArgumentException();
		}
		Node<T>newNode = new Node<>(element);
		if(front == null){
			front =  newNode;
			rear = newNode;
		}
		else{
			rear.next = newNode;
			rear = newNode;
		}
        size++;

	}

	@Override
	public T dequeue() throws NoSuchElementException {
            // TODO 6
			
			if(isEmpty()){
			throw new NoSuchElementException();
		}
		else{
			Node<T>newNode = front;
			front = front.next;
			size--;
			return newNode.data;
			
		}
		
	}

	@Override
	public T peek() throws NoSuchElementException {
            // TODO 7
            if(isEmpty()){
				throw new NoSuchElementException();
			}
			else{
				return front.data;
			}
	}

	
	@Override
	public UnboundedQueueInterface<T> reversed() {
            // TODO 8
			if (isEmpty()) {
				throw new IllegalStateException();
			}
			UnboundedQueueInterface<T> reversed = new Queue<T>();
		
			Node<T> current = front;
			while (current != null) {
				reversed.enqueue(current.data);
				current = current.next;
			}
			return reversed;

	}
}

class Node<T> {
	public T data;
	public Node<T> next;
	public Node(T data) { this.data=data;}
	public Node(T data, Node<T> next) {
		this.data = data; this.next=next;
	}
}