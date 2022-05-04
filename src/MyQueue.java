import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T>{

	int size;
	private int maxSize;
	
	private Node<T> front;
	private Node<T> last;

	public MyQueue() {
		size = 0;
		front = null;
		last = null;
	}
	
	public MyQueue(int i) {
		size = 0;
		maxSize = i;
		front = null;
		last = null;
	}

	private class Node<T>{
		
		private T data;
		private Node next;
		
		public Node(Object e){
			data = (T) e;
		}
		
		public T getData() {
			return data;
		}
		
		public void setData(Object e) {
			data = (T) e;
		}
		
		public Node<T> getNext() {
			return next;
		}
		
		public void setNext(Node newNode) {
			next = newNode;
		}
		
	}
	
	@Override
	public boolean isEmpty() {
		if (size == 0){
			return true;
		} else return false;
	}

	@Override
	public boolean isFull() {
		if (size == maxSize) {
			return true;
		}else return false;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T result = null;
			if (isEmpty()) {
				throw new QueueUnderflowException();
			}
			else {
				result = front.getData();
				front.setData(null);
				front = front.getNext();
				size--;
			}
		
		return result;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean enqueue(Object e) throws QueueOverflowException {
		
		Node<T> newNode = new Node<T>(e);
		
		boolean result = isFull();
		if (!result) { //If not full
			if (isEmpty()) {
				front = newNode;
			} else {
				last.setNext(newNode);
			}
			last = newNode;
			size++;
		}
		else {
			throw new QueueOverflowException();
		}

		return !result;
	}
	
	@Override
	public String toString() {
		String result = "";
		Node<T> current = front;
		
		for (int i = 0; i < size; i++) {
			result += ((String) current.data);
			current = current.next;
		}
		
		return result;
	}

	@Override
	public String toString(String delimiter) {
		String result = "";
		Node<T> current = front;
		
		for (int i = 0; i < size; i++) {
			result += ((String) current.data) + delimiter;
			current = current.next;
		}
		result = result.substring(0, result.length()-1);
		
		return result;
	}

	@Override
	public void fill(ArrayList list) {
		ArrayList<T> newList = list;
		
		for(int i = 0; i < newList.size(); i++) {
			try {
				enqueue(newList.get(i));
			} catch (QueueOverflowException e) {
				e.getMessage();
			}
		}
		
	}

}
