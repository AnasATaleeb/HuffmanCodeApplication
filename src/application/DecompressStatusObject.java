package application;

public class DecompressStatusObject {
	private String  code;
	private char value;
	
	
	public DecompressStatusObject() {
		super();
	}
	public DecompressStatusObject(String code, char value) {
		super();
		this.code = code;
		this.value = value;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public char getValue() {
		return value;
	}
	public void setValue(char value) {
		this.value = value;
	}
	
	
	
}
