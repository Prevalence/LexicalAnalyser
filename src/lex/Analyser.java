package lex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

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
				// 在读入的时候注意将空格都处理掉
				char[] charInOneLine = line.replaceAll(" ", "").toCharArray();
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
		
	}

	public static void main(String[] args) {
		System.out.println(Analyser.readFromFile("C:\\Users\\njdx\\Desktop\\LexicalAnalyse\\src\\Token.java"));
	}

}
