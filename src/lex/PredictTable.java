package lex;

public class PredictTable {
	// (, ), {, }, <, >, if,while,id,digit,+, =, $R, S, A, C
	private int[] I0 = { -1, -1, -1, -1, -1, -1, 2, 3, 4, -1, -1, -1, -1, 1, -1, -1 };
	private int[] I1 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, -1, -1 };
	private int[] I2 = { 5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1 };
	private int[] I3 = { 6, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1 };
	private int[] I4 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 7, -1, -1, 1, -1, -1 };
	private int[] I5 = { -1, -1, -1, -1, -1, -1, -1, -1, 11, 10, -1, -1, -1, 1, 9, 8 };
	private int[] I6 = { -1, -1, -1, -1, -1, -1, -1, -1, 11, 10, -1, -1, -1, 1, 9, 12 };
	private int[] I7 = { 5, -1, -1, -1, -1, -1, -1, -1, 11, 10, -1, -1, -1, 1, 13, -1 };
	private int[] I8 = { -1, 14, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1 };
	private int[] I9 = { -1, -1, -1, -1, 16, 15, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1 };
	private int[] I10 = { -1, -1, -1, -4, -4, -4, -1, -1, -1, -1, -1, -1, -4, 1, -1, -1 };
	private int[] I11 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 17, -1, -1, 1, -1, -1 };
	private int[] I12 = { -1, 18, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1 };
	private int[] I13 = { -1, -1, -1, -3, -1, -1, -1, -1, -1, -1, -1, -1, -3, 1, -1, -1 };
	private int[] I14 = { -1, -1, 19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1 };
	private int[] I15 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, 20, -1, -1, -1, 1, -1, -1 };
	private int[] I16 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, 21, -1, -1, -1, 1, -1, -1 };
	private int[] I17 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, 22, -1, -1, -1, 1, -1, -1 };
	private int[] I18 = { -1, -1, 23, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1 };
	private int[] I19 = { -1, -1, -1, -1, -1, -1, 2, 3, 4, -1, -1, -1, 0, 24, -1, -1 };
	private int[] I20 = { -1, -1, -1, -1, -1, -6, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1 };
	private int[] I21 = { -1, -1, -1, -1, -1, -7, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1 };
	private int[] I22 = { -1, -1, -1, -5, -5, -5, -1, -1, -1, -1, -1, -1, -5, 1, -1, -1 };
	private int[] I23 = { -1, -1, -1, -1, -1, -1, 2, 3, 4, -1, -1, -1, -1, 25, -1, -1 };
	private int[] I24 = { -1, -1, -1, 26, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1 };
	private int[] I25 = { -1, -1, -1, 27, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1 };
	private int[] I26 = { -1, -1, -1, -501, -1, -1, -1, -1, -1, -1, -1, -1, -501, -1, -1, -1 };
	private int[] I27 = { -1, -1, -1, -502, -1, -1, -1, -1, -1, -1, -1, -1, -502, 1, -1, -1 };

	/**
	 * 预测分析表
	 */
	private int[][] stateTable = { I0, I1, I2, I3, I4, I5, I6, I7, I8, I9, I10, I11, I12, I13, I14, I15, I16, I17, I18,
			I19, I20, I21, I22, I23, I24, I25, I26, I27 };
	/**
	 * (0) S’→S (1) S→if(C){S} (2) S→while(C){S} (3) S→id=A (4) A→digit (5)
	 * A→id+digit (6) C→A>digit (7) C→A<digit 产生式右部长度
	 */
	private int[] lengthOfProducts = { 1, 7, 7, 3, 1, 3, 3, 3 };

	/**
	 * 产生式左部符号
	 */
	private char[] leftPartOfProduct = { 'S', 'S', 'S', 'S', 'A', 'A', 'C', 'C' };

	public int getLengthOfProduct(int indexOfProduct) {
		return lengthOfProducts[-indexOfProduct];
	}

	public char getLeftPartOfProduct(int indexOfProduct) {
		return leftPartOfProduct[-indexOfProduct];
	}

	/**
	 * 查表 // (, ), {, }, <, >, if,while,id,digit,+, =, $R, S, A, C
	 * 
	 * @return
	 */
	public int getNextState(int currentState, Token currentToken) {
		String type = currentToken.getType();
		switch (type) {
		case "S":
			return stateTable[currentState][13];
		case "A":
			return stateTable[currentState][14];
		case "C":
			return stateTable[currentState][15];
		case "ID":
			return stateTable[currentState][8];
		case "positive Integer":
			return stateTable[currentState][9];
		case "positive float":
			return stateTable[currentState][9];
		default:
			break;
		}
		String content = currentToken.getContent();
		switch (content) {
		case "(":
			return stateTable[currentState][0];
		case ")":
			return stateTable[currentState][1];
		case "{":
			return stateTable[currentState][2];
		case "}":
			return stateTable[currentState][3];
		case "<":
			return stateTable[currentState][4];
		case ">":
			return stateTable[currentState][5];
		case "if":
			return stateTable[currentState][6];
		case "while":
			return stateTable[currentState][7];
		case "+":
			return stateTable[currentState][10];
		case "=":
			return stateTable[currentState][11];
		case "$R":
			return stateTable[currentState][12];
		default:
			break;
		}
		System.out.println("Can't find the column");
		return -1;
	}
}
