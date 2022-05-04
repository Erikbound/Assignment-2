
public class Notation {
	public Notation() {
		
	}
	
	public static double evaluatePostfixExpression (String postfixExpr) throws InvalidNotationFormatException{
		MyStack<String> stack = new MyStack<String>(postfixExpr.length());
		String second, first, value;
		double secondV, firstV, calc = 0;
		
		try {
			for(int i = 0; i < postfixExpr.length(); i++) {
				char current = postfixExpr.charAt(i);
				String c = String.valueOf(current);
					//Digit
				if((current >= 48 && current <= 57) || current == '('){
					stack.push(c);
				}
					//Operator
				else if (current == '+' || current == '-' || current == '*' || current == '/') {
					if (stack.isEmpty()) throw new InvalidNotationFormatException();
					second = (String) stack.pop();
					
					if (stack.isEmpty()) throw new InvalidNotationFormatException();
					first = (String) stack.pop();
					
					secondV = Double.valueOf(second);
					firstV = Double.valueOf(first);
					switch(current) {
						case '+': calc = firstV + secondV; break;
						case '-': calc = firstV - secondV; break;
						case '*': calc = firstV * secondV; break;
						case '/': calc = firstV / secondV; break;
					}
					value = String.valueOf(calc);
					stack.push(value);
				}
			}
			if (stack.size() > 1) {
				throw new InvalidNotationFormatException();
			}
		}
		catch(StackOverflowException | StackUnderflowException e) {
			e.getMessage();
		}
		
		String result = stack.toString();
		double resultNum = Double.valueOf(result);
		
		return resultNum;
	}

	public static String convertInfixToPostfix(String complexInfix) throws InvalidNotationFormatException{
		MyQueue<String> queue = new MyQueue<String>(complexInfix.length());
		MyStack<String> stack = new MyStack<String>(complexInfix.length());
		
		try {
			for(int i = 0; i < complexInfix.length(); i++) {
				char current = complexInfix.charAt(i);
				String c = String.valueOf(current);
					//Digit
				if(current >= 48 && current <= 57){
					queue.enqueue(c);
				}
					//Left Parenthesis
				else if(current == '('){
					stack.push(c);
				}
					//Right Parenthesis
				else if(current == ')'){
					while(!stack.top().equals("(")) {
						queue.enqueue(stack.pop());
						if (stack.isEmpty()) {
							throw new InvalidNotationFormatException();
						}
					}
					stack.pop();
				}
					//Operator
				else if(current == '+' || current == '-'){
					while(!stack.isEmpty() && (stack.top() == "+" || stack.top() == "-" || stack.top() == "*" || stack.top() == "/")) {
						queue.enqueue(stack.pop());
					}
					stack.push(c);
				}
				else if (current == '*' || current == '/') {
					while(!stack.isEmpty() && (stack.top() == "*" || stack.top() == "/")) {
						queue.enqueue(stack.pop());
					}
					stack.push(c);
				}
			}
				//Empty Stack
			while(!stack.isEmpty()) {
				queue.enqueue(stack.pop());
			}
		}
		catch (QueueOverflowException | StackUnderflowException | StackOverflowException e){
			e.getMessage();
		}
		
		return queue.toString();
	}

	public static String convertPostfixToInfix(String complexPostfix) throws InvalidNotationFormatException{
		MyStack<String> stack = new MyStack<String>(complexPostfix.length());
		String first, second, expr;
		
		
		try {
			for(int i = 0; i < complexPostfix.length(); i++) {
				char current = complexPostfix.charAt(i);
				String c = String.valueOf(current);
					//Digit
				if(current >= 48 && current <= 57){
					stack.push(c);
				}
					//Operator
				if (current == '+' || current == '-' || current == '*' || current == '/') {
					if (stack.isEmpty()) throw new InvalidNotationFormatException();
					second = (String) stack.pop();

					if (stack.isEmpty()) throw new InvalidNotationFormatException();
					first = (String) stack.pop();
					
					expr = "(" + first + c + second + ")";
					stack.push(expr);
				}
			}
			if (stack.size() > 1) {
				throw new InvalidNotationFormatException();
			}
		}
		catch(StackOverflowException | StackUnderflowException e) {
			e.getMessage();
		}
		
		
		return stack.toString();
	}
}
