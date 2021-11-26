import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RPNCalculator {
	// Initialize float memory
	private static float M;
	
	// Initialize stack for calculator
	public static Stack<String> stack = new Stack<String>();
	
	// Constructor
	public RPNCalculator() {
		M = 0;
	}
	
	// Runs RPNCalculator functions
	// Stops when "stop" is typed
	public static void main(String[] args) {
		
		float operand1 = 0;
		float operand2 = 0;
		
		String input = "";
		
		Scanner sc = new Scanner(System.in);
		
		boolean active = true;
		while(active) {
				
			System.out.println("Enter input (operand, operator, or function), or type in stop");
			input = sc.nextLine();			
			if (isOpOrFun(input) == false) {				
				Pattern p = Pattern.compile("\\d*\\.?\\d+|\\d+\\.?\\d*");
				Matcher m = p.matcher(input);
				
				while(m.find()) {
					enter(m.group());
				}
			}
			else {
				switch(input) {
				case "+":
					operand2 = toFloat(pop());
					operand1 = toFloat(pop());
					enter(operation(operand1, operand2, Operator.add));
					break;
				case "-":
					operand2 = toFloat(pop());
					operand1 = toFloat(pop());
					enter(operation(operand1, operand2, Operator.sub));
					break;
				case "/":
					operand2 = toFloat(pop());
					operand1 = toFloat(pop());
					enter(operation(operand1, operand2, Operator.div));
					break;
				case "*":
					operand2 = toFloat(pop());
					operand1 = toFloat(pop());
					enter(operation(operand1, operand2, Operator.mul));
					break;
				case "POW":
					operand2 = toFloat(pop());
					operand1 = toFloat(pop());
					enter(operation(operand1, operand2, Operator.pow));
					break;
				case "%":
					operand2 = toFloat(pop());
					operand1 = toFloat(pop());
					enter(operation(operand1, operand2, Operator.perc));
					break;
				case "SQRT":
					operand1 = toFloat(pop());
					enter(operation(operand1, 0, Operator.sqrt));
					break;
				case "1/x":
					operand1 = toFloat(pop());
					enter(operation(operand1, 0, Operator.frac));
					break;
				case "M+":
					operand1 = toFloat(readTop());
					addM(operand1);
					break;
				case "M-":
					operand1 = toFloat(readTop());
					subM(operand1);
					break;
				case "MR":
					recallM();
					break;
				case "MC":
					clearM();
					break;
				case "POP":
					pop();
					break;
				case "SWAP":
					swap();
					break;
				case "AC":
					ac();
					break;
				case "C":
					c();
					break;
				case "stop":
					active = false;
					break;
				default:
					System.out.println("Enter correct values");
					break;
				}
			}
			showStack();
		}	
	}
	
	// Returns true if input is an operator or function, otherwise false
	public static boolean isOpOrFun(String s) {
		String[] ops = {"+","-","/","*","%","MR","MC","M+","M-","POP","SWAP","AC","C","stop","SQRT","1/x","POW"};
		boolean state = false;
		
		for(int i = 0; i < ops.length; i++) {
			if(s.contentEquals(ops[i])) {
				state = true;
			}
		}
		return state;
	}

	// Returns a String value dependent 
	// on the operands, and operation/function (not related to float memory)
	public static String operation(float operand1, float operand2, Operator operation) {
	    String result = "";
	    switch (operation) {
	    case add:
	    	result = toString(operand1 + operand2);
	    	break;
	    case sub:
	    	result = toString(operand1 - operand2);
	    	break;
	    case div:
	    	result = toString(operand1 / operand2);
	    	break;
	    case mul:
	    	result = toString(operand1 * operand2);
	    	break;
	    case frac:
	    	result = toString(1 / operand1);
	    	break;
	    case perc:
	    	result = toString(operand1 * operand2 / 100);
	    	break;
	    case sqrt:
	    	result = toString((float) Math.sqrt(operand1));
	    	break;
	    case pow:
	    	result = toString((float) Math.pow(operand1, operand2));
		default:
			break;
	    }
	    return result;	
	}

	// Pushes the String input onto the stack
	public static String enter(String s) {
		stack.push(s);
		return stack.peek();
	}
	
	// Responsible for clearing the stack for RPNtestt
	public static void clear() {
		if (!stack.isEmpty()) {
			stack.pop();
			stack.push("0");
		}
	}
	
	// Removes the right-most char of the String value at the top of the stack
	public static void c() {
		String input = readTop();
		pop();
		String s = input.substring(0, input.length()-1);
		if (s.contentEquals("")) {
			enter("0");
		}
		else {
			enter(s);	
		}		
	}
	
	// Removes all strings in the stack
	public static void ac() {
		while(!stack.isEmpty()) {
			stack.pop();
		}
	}
	
	// Returns the top String value of the stack
	public static String readTop() {
		if (!stack.isEmpty()) {
			return stack.peek();
		}
		else {
			return "0";
		}
	}
	
	// Pops the top String value of the stack and returns it
	public static String pop() {
		if (!stack.isEmpty()) {
			return stack.pop();
		}
		else {
			return "0";
		}
	}
	
	// Converts a String value to a Float value
	public static float toFloat(String s) {
		return Float.parseFloat(s); 
	}
	
	// Converts a Float value to a String value
	public static String toString(float input) {
		return Float.toString(input);
	}
	
	// Swaps the top, and second-to-top String values of the stack
	public static boolean swap() {
		if (stack.size() >= 2) {
			String input1 = stack.pop();
			String input2 = stack.pop();
			stack.push(input1);
			stack.push(input2);
			return true;
		}
		else {
			return false;
		}
	}
	
	// Adds a Float value to the float memory's value
	public static void addM(float input1) {
		M = M + input1;
	}
	
	// Subtracts a Float value from the float memory's value
	public static void subM(float input1) {
		M = M - input1;
	}
	
	// Sets the float memory's value to 0
	public static void clearM() {
		M = 0;
	}
	
	// Returns the value of the float memory
	public static float getM() {
		return M;
	}
	
	// Recalls the value of the float memory to be the 
	// value of the top of the stack
	public static void recallM() {
		if (!stack.isEmpty()) {
			stack.pop();
			stack.push(Float.toString(getM()));	
		}
		else {
			stack.push(Float.toString(getM()));
		}
	}
	
	// Shows the contents of the stack with the topmost value at the bottom
	public static String showStack() {
		String show = "";

		if(!stack.isEmpty()) {
			System.out.println();
			for (int i = 0; i < stack.size(); i++) { 
				show = show + stack.get(i) +"\n";
				System.out.println(stack.get(i));
			}	
		}	
		return show;
	}	


}
