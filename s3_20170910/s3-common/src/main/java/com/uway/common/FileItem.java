package com.uway.common;

import java.io.Serializable;


public class FileItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fileName;  

	private byte[] content;

	private String mimeType;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

}
