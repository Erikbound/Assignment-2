import java.util.ArrayList;


public class MyStack<T> implements StackInterface{

	int size = 0;
	private int maxSize;
	
	private Node front;
	private Node last;

	public MyStack() {
		size = 0;
		front = null;
		last = null;
	}
	
	public MyStack(int i) {
		size = 0;
		maxSize = i;
		front = null;
		last = null;
	}
	
	public MyStack(int size, int maxSize, Node<T> front, Node<T> last) {
		this.size = size;
		this.maxSize = maxSize;
		this.front = front;
		this.last = last;
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
		
		public Node getNext() {
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
	public Object pop() throws StackUnderflowException {
		T result = (T) top();
		
		if (isEmpty()) {
			throw new StackUnderflowException();
		}
		else {
			front.setData(null);
			front = front.getNext();
			size--;
		}
		return result;
	}

	@Override
	public Object top() throws StackUnderflowException {
		T result = null;
		if (isEmpty()) {
			throw new StackUnderflowException();
		}
		else {
			result = (T) front.getData();
		}
		
		return result;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public boolean push(Object e) throws StackOverflowException {

		Node<T> newNode = new Node<T>(e);
		
		boolean result = isFull();
		Node<T> oldFront = front;
		
		if (!result) { //If not full
			front = newNode;
			front.setNext(oldFront);
			size++;
		}
		else {
			throw new StackOverflowException();
		}

		return !result;
	}

	@Override
	public String toString() {
		String result = "";
		int newSize = size;
		
		for(int i = 0; i < newSize; i++) {
			try {
				result = pop() + result;
			} catch (StackUnderflowException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public String toString(String delimiter) {
		String result = "";
		Node<T> current = last;
		
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
				push(newList.get(i));
			} catch (StackOverflowException e) {
				e.getMessage();
			}
		}
		
	}

}
