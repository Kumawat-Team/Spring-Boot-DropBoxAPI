package com.wli.dropboxapi.dropboxapi.model;

public class FileDTO {
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "DeleteFile [fileName=" + fileName + "]";
	}

}
