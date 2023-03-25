package application;

public class StatusObject {
	String  code;
	char value;
	int freq;
	byte length;

	public StatusObject() {
		super();
		code = "-1";

	}

	public StatusObject(String code, char value, int freq, byte length) {
		super();
		this.code = code;
		this.value = value;
		this.freq = freq;
		this.length = length;
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

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public byte getLength() {
		return length;
	}

	public void setLength(byte length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "StatusObject [code=" + code + ", value=" + value + ", freq=" + freq + ", length=" + length + "]";
	}

}
