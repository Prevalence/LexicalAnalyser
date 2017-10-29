package lex;

/**
 * 最后输出的Token类
 * 
 * @author njdx
 *
 */
public class Token {
	/**
	 * 识别出的内容类型
	 */
	private String type;
	/**
	 * 识别的具体内容
	 */
	private String content;
	/**
	 * 错误信息
	 */
	private String errorMessage;

	public Token(String type, String content, String errorMessage) {
		super();
		this.type = type;
		this.content = content;
		this.errorMessage = errorMessage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
