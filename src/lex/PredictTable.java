package lex;

public class PredictTable {
	private int[] I0 = { 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 4, -1, -1, -1, 1, 3, -1 };
	private int[] I1 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1 };
	private int[] I2 = { -1, 5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
	private int[] I3 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 6, -1, -1, -1, -1, -1, -1, -1 };
	private int[] I4 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 4, -1, -1, -1, -1, 7, -1 };
	private int[] I5 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 11, -1, 9, -1, 10, 8 };
	private int[] I6 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 12, -1, -1, 10, -1 };
	private int[] I7 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -9, -1, -1, -1, -1, -1, -1, -1 };
	private int[] I8 = { -1, -1, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
	private int[] I9 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, 13, 14, -1, -1, -1, -1, -1, -1, -1, -1 };
	private int[] I10 = { -1, -1, -1, -1, -1, 15, 16, 17, 18, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
	private int[] I11 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 11, -1, -1, -1, 19, -1 };
	private int[] I12 = { -1, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -2, -1, -1, -1, -1 };
	private int[] I13 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 11, -1, -1, -1, 20, -1 };
	private int[] I14 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 11, -1, -1, -1, 21, -1 };
	private int[] I15 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 11, -1, 22, -1, 10, -1 };
	private int[] I16 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 11, -1, 23, -1, 10, -1 };
	private int[] I17 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 11, -1, 24, -1, 10, -1 };
	private int[] I18 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 11, -1, 25, -1, -1, -1 };
	private int[] I19 = { -1, -1, -10, -1, -1, -10, -10, -10, -10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
	private int[] I20 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 4, -1, -1, -1, 27, 3, -1 };
	private int[] I21 = { -1, -1, -1, -1, -7, -1, -1, -1, -1, -1, -1, -1, -1, -1, -7, -1, -1, -1, -1 };
	private int[] I22 = { -1, -1, -1, -1, -8, -1, -1, -1, -1, -1, -1, -1, -1, -1, -8, -1, -1, -1, -1 };
	private int[] I23 = { -1, -1, -1, -1, -3, -1, -1, -1, -1, -3, -3, -1, -1, -1, -3, -1, -1, -1, -1 };
	private int[] I24 = { -1, -1, -1, -1, -4, -1, -1, -1, -1, -4, -4, -1, -1, -1, -4, -1, -1, -1, -1 };
	private int[] I25 = { -1, -1, -1, -1, -5, -1, -1, -1, -1, -5, -5, -1, -1, -1, -5, -1, -1, -1, -1 };
	private int[] I26 = { -1, -1, -1, -1, -6, -1, -1, -1, -1, -6, -6, -1, -1, -1, -6, -1, -1, -1, -1 };
	private int[] I27 = { -1, -1, -1, -1, -7, -1, -1, -1, -1, -7, -7, -1, -1, -1, -7, -1, -1, -1, -1 };
	private int[] I28 = { -1, -1, -1, -1, -501, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1 };

	/**
	 * 预测分析表
	 */
	private int[][] stateTable = { I0, I1, I2, I3, I4, I5, I6, I7, I8, I9, I10, I11, I12, I13, I14, I15, I16, I17, I18,
			I19, I20, I21, I22, I23, I24, I25, I26, I27, I28 };
	/**
	 * (0) S’→S (1) S→w(C){S} (2) S→A=E (3) E→B+E (4) E→B-E (5) E→B*E (6) E→B/E (7)
	 * C→E>B (8) C→E<B (9)A→charA (10)B→digitB 产生式右部长度
	 */
	private int[] lengthOfProducts = { 1, 7, 3, 3, 3, 3, 3, 3, 3, 2, 2 };

	/**
	 * 产生式左部符号
	 */
	private char[] leftPartOfProduct = { 'S', 'S', 'S', 'E', 'E', 'E', 'E', 'C', 'C', 'A', 'B' };

	public int getLengthOfProduct(int indexOfProduct) {
		return lengthOfProducts[-indexOfProduct];
	}

	public char getLeftPartOfProduct(int indexOfProduct) {
		return leftPartOfProduct[-indexOfProduct];
	}

	/**
	 * 查表
	 * 
	 * @return
	 */
	public int getNextState(int currentState, char currentChar) {
		int index = 0;
		char[] column = { 'w', '(', ')', '{', '}', '+', '-', '*', '/', '<', '>', '=', '^', '^', '$', 'E', 'S', 'A', 'B',
				'C' };
		if (currentChar >= '0' && currentChar <= '9')
			return stateTable[currentState][13];
		if ((currentChar >= 'a' && currentChar <= 'z' && currentChar != 'w'))
			return stateTable[currentState][12];
		for (int columnNumber = 0; columnNumber < column.length; columnNumber++) {
			if (column[columnNumber] == currentChar) {
				index = columnNumber;
				break;
			}
		}
		if (index == 0 && currentChar != 'w') {
			System.out.println("Error occurs");
		}
		return stateTable[currentState][index];
	}
}