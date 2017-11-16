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
	private Stack<Character> contentStack = new Stack<Character>();
	/**
	 * 状态栈
	 */
	private Stack<Integer> stateStack = new Stack<Integer>();
	/**
	 * 预测分析表
	 */
	private static final PredictTable PPT = new PredictTable();

	/**
	 * 读入输入的文件
	 * 
	 * @param path
	 *            输入文件路径
	 * @throws IOException
	 */
	public void readFromFile(String path) throws IOException {
		ArrayList<Character> allCharInFile = new ArrayList<Character>();
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
			String line = null;
			while ((line = bf.readLine()) != null) {
				char[] charInOneLine = line.toCharArray();
				for (char c : charInOneLine) {
					allCharInFile.add(c);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File in " + path + " not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Fail to read the file");
			e.printStackTrace();
		}
		scanCharInFile(allCharInFile);
	}

	/**
	 * 核心分析子程序
	 * 
	 * @param allCharInFile
	 */
	public void scanCharInFile(ArrayList<Character> allCharInFile) {
		contentStack.push('$');
		stateStack.push(0);
		for (int i = 0; i < allCharInFile.size(); i++) {
			char currentChar = allCharInFile.get(i);
			int currentState = stateStack.peek();
			int nextState = PPT.getNextState(currentState, currentChar);
			if (nextState == -1) {
				System.out.println("Error occurs");
				break;
			} else if (nextState == 0) {
				System.out.println("Finished");
				break;
			} else if (nextState > 0) {
				stateStack.push(nextState);
				contentStack.push(currentChar);
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
		try {
			r.readFromFile("D:/lab.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
