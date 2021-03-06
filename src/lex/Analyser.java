package lex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
	private static final String[] PUNCTUATION = { "{", "}", ";", "(", ")", "[", "]", ":", "\"", ",", " ", ".", "\t",
			"\n" };

	/**
	 * 状态转换表
	 * 
	 */
	private static final int[][] STATE_TABLE = { { 504, 501, 503, 505, -1 }, { 502, 502, -1, -1, -1 },
			{ 502, 502, -1, -1, -1 }, { -1, -1, -1, -1, -1 }, { 504, -1, -1, -1, 6 }, { 508, -1, -1, -1, -1 },
			{ 507, -1, -1, -1, -1 }, { 507, -1, -1, -1, -1 }, { 508, -1, -1, -1, 9 }, { 510, -1, -1, -1, -1 },
			{ 510, -1, -1, -1, -1 } };
	/**
	 * Token流
	 */
	private static ArrayList<Token> tokens = new ArrayList<Token>();

	/**
	 * 读入输入的文件
	 * 
	 * @param path
	 *            输入文件路径
	 * @throws IOException
	 */
	public ArrayList<Token> readFromFile(String path) throws IOException {
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
		return tokens;
	}

	/**
	 * 核心分析子程序 扫描文件字符数组，处理决定所属Token
	 * 
	 * @param allCharInFile
	 *            读完文件并删去空格后得到的字符数组
	 * @return
	 * @throws IOException
	 */
	public static void scanCharInFile(ArrayList<Character> allCharInFile) throws IOException {
		// 临时容器，用以装载读取的字符串，方便识别结果的呈现
		String tempContent = "";
		int index = 0;
		int currentState = 0;
		while (index < allCharInFile.size()) {
			char currentChar = allCharInFile.get(index);
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
				if (index == allCharInFile.size() - 1)
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
		if (isDigit(currentChar)) {
			return STATE_TABLE[currentState][0];
		} else if (isLetter(currentChar)) {
			return STATE_TABLE[currentState][1];
		} else if (currentChar == '-') {
			return STATE_TABLE[currentState][3];
		} else if (currentChar == '.') {
			if (currentState == 4 || currentState == 8)
				return STATE_TABLE[currentState][4];
			else
				return STATE_TABLE[currentState][2];
		} else
			return STATE_TABLE[currentState][2];

	}

	/**
	 * 参照终态的值来判断Token的类型，输出内容
	 * 
	 * @param currentState
	 * @throws IOException
	 */
	private static void output(int currentState, String tempContent) throws IOException {
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
		} else if (currentState == 503 || currentState == 505) {
			if (isOperators(tempContent)) {
				token = new Token("Operators", tempContent, "none");
			} else if (isPunctuation(tempContent)) {
				if (tempContent.equals(" "))
					tempContent = "Space";
				if (tempContent.equals("\n"))
					tempContent = "Enter";
				if (tempContent.equals("\t"))
					tempContent = "Tab";
				token = new Token("Punctuation", tempContent, "none");
			} else {
				token = new Token("unknown", tempContent, "unknown");
			}
		} else if (currentState == 504) {
			token = new Token("positive Integer", tempContent, "none");
		} else if (currentState == 507) {
			token = new Token("positive float", tempContent, "none");
		} else if (currentState == 508) {
			token = new Token("negative Integer", tempContent, "none");
		} else if (currentState == 510) {
			token = new Token("positive float", tempContent, "none");
		}
		tokens.add(token);

	}

	/**
	 * 判断当前状态是否是终态，表中终态都设置一个偏移量为500,例如,对于第四行的状态,号码为503
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
	 * 是否为操作符
	 * 
	 * @param tempContent
	 * @return
	 */
	private static boolean isOperators(String tempContent) {
		for (String s : OPERATORS) {
			if (s.equals(tempContent))
				return true;
		}
		return false;
	}

	/**
	 * 是否为标点符号
	 * 
	 * @param tempContent
	 * @return
	 */
	private static boolean isPunctuation(String tempContent) {
		for (String s : PUNCTUATION) {
			if (s.equals(tempContent))
				return true;
		}
		return false;
	}

}
