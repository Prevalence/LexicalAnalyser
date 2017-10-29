package lex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 核心分析器
 * 
 * @author njdx
 *
 */
public class Analyser {
	/**
	 * 保留字数组
	 */
	private static final String[] REVERSED_WORDS = { "void", "class", "public", "private", "protected", "for", "if",
			"else", "while", "do", "switch", "case", "final", "static", "int", "double", "char", "boolean", "String",
			"new", "try", "catch", "static", "return", "this", "main" };
	/**
	 * 操作符数组
	 */
	private static final String[] OPERATORS = { "+", "-", "*", "/", ">", "<", "==", "=", ">=", "<=", "+=", "-=", "*=",
			"/=", "&&", "||", "|", "&", "!", "!=" };
	/**
	 * 标点符号数组
	 */
	private static final String[] PUNCTUATION = { "{", "}", ";", ".", "(", ")", "[", "]", ":", "\"", "," };

	/**
	 * 状态转换表，形式为----------------------------------------------------- | | - | . |
	 * digit | letter | other -----------------------------------------------------
	 * | 0 | | 1 | | 2 | | 3 | | | | | | |
	 * 
	 */
	private static final int[][] STATE_TABLE = { { -1, 501, 503 }, { 502, 502, -1 }, { 502, 502, -1 }, { -1, -1, -1 } };

	/**
	 * 读入输入的文件
	 * 
	 * @param path
	 *            输入文件路径
	 */
	public static char[] readFromFile(String path) {
		char[] allCharInFile = new char[2000];
		int index = 0;
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
			String line = null;
			while ((line = bf.readLine()) != null) {
				char[] charInOneLine = line.toCharArray();
				for (char c : charInOneLine) {
					allCharInFile[index++] = c;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File in " + path + " not found");
			e.printStackTrace();
			return new char[0];
		} catch (IOException e) {
			System.out.println("Fail to read the file");
			e.printStackTrace();
			return new char[0];
		}
		return allCharInFile;
	}

	/**
	 * 核心分析子程序 扫描文件字符数组，处理决定所属Token
	 * 
	 * @param allCharInFile
	 *            读完文件并删去空格后得到的字符数组
	 * @return
	 */
	public static void scanCharInFile(char[] allCharInFile) {
		// 临时容器，用以装载读取的字符串，方便识别结果的呈现
		String tempContent = "";
		int index = 0;
		int currentState = 0;
		while (index < allCharInFile.length) {
			char currentChar = allCharInFile[index];
			// 查表得无下一个可能的状态时，分error和结束两种可能
			int nextState = lookUpStateTable(currentState, currentChar);
			if (nextState == -1) {
				if (isEndState(currentState))
					output(currentState, tempContent);
				else
					output(-1, tempContent);
				// 无论出错与否，都多读了一个字符
				index--;
				currentState = 0;
				tempContent = "";
			} else {
				currentState = nextState;
				tempContent += String.valueOf(currentChar);
				if (index == allCharInFile.length - 1)
					output(currentState, tempContent);
			}
			index++;
		}
	}

	/**
	 * 查询状态转换表
	 * 
	 * @param currentState
	 *            现在的状态
	 * @param currentChar
	 *            现在读取的字符
	 * @return 将转换的状态
	 */
	private static int lookUpStateTable(int currentState, char currentChar) {
		if (currentState > 500) {
			currentState -= 500;
		}
		if (isDigit(currentChar))
			return STATE_TABLE[currentState][0];
		if (isLetter(currentChar))
			return STATE_TABLE[currentState][1];
		else
			return STATE_TABLE[currentState][2];
	}

	/**
	 * 判断当前状态是否是终态，表中终态都设置一个偏移量为500
	 * 
	 * @param currentState
	 *            当前状态
	 * @return
	 */
	private static boolean isEndState(int currentState) {
		if (currentState > 500)
			return true;
		else
			return false;
	}

	/**
	 * 判断字符是否是数位
	 * 
	 * @param i
	 * @return
	 */
	private static boolean isDigit(char currentChar) {
		return (currentChar >= '0' && currentChar <= '9');
	}

	/**
	 * 判断字符是否为字母
	 * 
	 * @param i
	 * @return
	 */
	private static boolean isLetter(char currentChar) {
		return ((currentChar >= 'a' && currentChar <= 'z') || (currentChar >= 'A') && (currentChar <= 'Z'));
	}

	private static boolean isReveredWords(String tempContent) {
		for (String s : REVERSED_WORDS) {
			if (s.equals(tempContent))
				return true;
		}
		return false;
	}

	/**
	 * 参照终态的值来判断Token的类型，输出内容，并且将tempContainer重置
	 * 
	 * @param currentState
	 */
	private static void output(int currentState, String tempContent) {
		Token token = null;
		if (currentState == 501) {
			if (isReveredWords(tempContent)) {
				token = new Token("ReversedWords", tempContent, "none");
			} else {
				token = new Token("ID", tempContent, "none");
			}

		} else if (currentState == 502) {
			if (isReveredWords(tempContent)) {
				token = new Token("ReversedWords", tempContent, "none");
			} else {
				token = new Token("ID", tempContent, "none");
			}
		} else if (currentState == 503)
			token = new Token("signal", tempContent, "none");
		assert (token != null);
		System.out.println("Token{type: " + token.getType() + " content: " + token.getContent() + " error: "
				+ token.getErrorMessage());
	}

	public static void main(String[] args) {
		char[] test = "int i".toCharArray();
		Analyser.scanCharInFile(test);
	}

}
