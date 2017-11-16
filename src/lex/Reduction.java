package lex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Reduction {
	/**
	 * 内容栈
	 */
	private Stack<Token> contentStack = new Stack<Token>();
	/**
	 * 状态栈
	 */
	private Stack<Integer> stateStack = new Stack<Integer>();
	/**
	 * 预测分析表
	 */
	private static final PredictTable PPT = new PredictTable();

	/**
	 * 核心分析子程序
	 * 
	 * @param allCharInFile
	 */
	public void scanTokens(ArrayList<Token> tokens) {
		contentStack.push(new Token("dollarR", "$R", "none"));
		stateStack.push(0);
		for (int i = 0; i < tokens.size(); i++) {
			Token currentToken = tokens.get(i);
			int currentState = stateStack.peek();
			int nextState = PPT.getNextState(currentState, currentToken);
			if (nextState == -1) {
				System.out.println("Error occurs");
				break;
			} else if (nextState == 0) {
				System.out.println("Finished");
				break;
			} else if (nextState > 0) {
				stateStack.push(nextState);
				contentStack.push(currentToken);
			} else {
				if (nextState == -501) {
					nextState += 500;
				}
				int lengthOfProduct = PPT.getLengthOfProduct(nextState);
				for (int j = 0; j < lengthOfProduct; j++) {
					stateStack.pop();
					contentStack.pop();
				}
				contentStack.push(PPT.getLeftPartOfProduct(currentState));
				char topContent = contentStack.peek();
				int topState = stateStack.peek();
				nextState = PPT.getNextState(topState, topContent);
				if (nextState == -1) {
					System.out.println("Error occurs");
					break;
				}
				stateStack.push(nextState);
				i--;
			}
		}
	}

	public static void main(String[] args) {
		Reduction r = new Reduction();
		Analyser a = new Analyser();
		try {
			r.scanTokens(a.readFromFile("lab.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
