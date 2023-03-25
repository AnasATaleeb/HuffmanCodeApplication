package application;

public class Header {
	
	private String sig="huf";
	private String extension;
	private byte extensionSize;
	private int headerSize;
	private int totalSize;
	private int compSize;
	
	
	
	public Header() {
		super();
	}
	public Header(String extension, byte extensionSize, int headerSize) {
		super();
		this.extension = extension;
		this.extensionSize = extensionSize;
		this.headerSize = headerSize;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public byte getExtensionSize() {
		return extensionSize;
	}
	public void setExtensionSize(byte extensionSize) {
		this.extensionSize = extensionSize;
	}
	public int getHeaderSize() {
		return headerSize;
	}
	public void setHeaderSize(int headerSize) {
		this.headerSize = headerSize;
	}
	
	public String getSig() {
		return sig;
	}
	public void setSig(String sig) {
		this.sig = sig;
	}
	
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public int getCompSize() {
		return compSize;
	}
	public void setCompSize(int compSize) {
		this.compSize = compSize;
	}
	@Override
	public String toString() {
		return "Header [sig=" + sig + ", extension=" + extension + ", extensionSize=" + extensionSize + ", headerSize="
				+ headerSize + ", totalSize=" + totalSize + "]";
	}
	
	
}
