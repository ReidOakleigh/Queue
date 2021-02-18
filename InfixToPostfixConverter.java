import java.util.Scanner;
import java.util.Stack;

/**************************************************************
* Name        : Infix to Postfix Converter
* Author      : Rumbi Chinhamhora
* Created     : 4/24/2019
* Course      : CIS 152 Data Structures
* Version     : 1.0
* OS          : Windows 10
* Language	  : Java
* Copyright   : This is my own original work based on
*               specifications issued by our instructor
* Description : This program overall description here
*               Input:  list and describe
*               Output: list and describe
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or 
* unmodified. I have not given other fellow student(s) access to
* my program.         
***************************************************************/

public class InfixToPostfixConverter {
	private final static String operators = "+-*/^()";
	private final static int[] precedence = {0,0,1,1,2,3,3};
	private static final String DIVIDER = "********************************************************";
	private final Stack<Character> postfixStack = new Stack<>();
	private final static String[] examples = {"2+3*4", "a*b+5", 
			"(1+2)*7", "a*b/c", "(a/(b-c+d))*(e-a)*c", "a/b-c+d*e-a*c"};
	
	/*
	 * This is the entry point of the Java program. It instantiates an object of
	 * this class abd immediately call the go method.
	 * @param args : String[] - command line arguments, if any.
	 */
	public static void main(String[] args) {
		new InfixToPostfixConverter().go();
	}

	/*
	 * This method converts and prints the example infix expressions to postfix
	 * expressions and the allows the operator to enter an infix expression and
	 * then converts and prints it.
	 */
	private void go() {
		System.out.println(DIVIDER);
		for (String str : examples) {
			printPostfix(str, convertInfix(str));
		}
		Scanner in = new Scanner(System.in);
		System.out.println();
		System.out.print("Enter infix notation with no spaces: ");
		String str = in.next();	// Suggestion (a+b)*c^2
		in.close();
		System.out.println();
		printPostfix(str, convertInfix(str));
	}

	/*
	 * This method will convert an infix expression to a postfix expression.
	 * @param str : String - infix expression
	 * @return : String - postfix expression
	 */
	private String convertInfix(String str) {
		char[] in = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char ch : in) {
			int index = operators.indexOf(ch);
			if (index != -1) processOperator(ch, sb);
			else sb.append(ch);
		}
		while (!postfixStack.isEmpty()) {
			sb.append(postfixStack.pop());
		}
		return sb.toString();
	}

	/*
	 * This method processes operators.
	 * @param ch : char - the operator
	 * @param sb : StringBuilder - building the postfix expression
	 */
	private void processOperator(char ch, StringBuilder sb) {
		if (ch == '(') {
			postfixStack.push(ch);
			return;
		} 
		if (ch == ')') {
			processParen(sb);
			return;
		} 
		while (!postfixStack.isEmpty()) {
			char top = postfixStack.pop();
			if (top == '(') {
				postfixStack.push(top);
				break;
			}
			int precedence1 = precedence[operators.indexOf(ch)];
			int precedence2 = precedence[operators.indexOf(top)];
			if (precedence2 < precedence1) {
				postfixStack.push(top);
				break;
			}
			sb.append(top);
		}
		postfixStack.push(ch);
	}

	/*
	 * This method processes the right parenthesis.
	 * @param sb : StringBuilder - building the postfix expression
	 */
	private void processParen(StringBuilder sb) {
		while (!postfixStack.isEmpty()) {
			char ch = postfixStack.pop();
			if (ch == '(') break;
			sb.append(ch);
		}
	}

	/*
	 * This method prints the infix and postfix expressions.
	 * @param infix : the infix expression
	 * @param postfix : the postfix expression
	 */
	private void printPostfix(String infix, String postfix) {
		System.out.println("Infix: " + infix);
		System.out.println("Postfix: " + postfix);
		System.out.println(DIVIDER);
	}
}
