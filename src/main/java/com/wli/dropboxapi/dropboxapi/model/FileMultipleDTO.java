package com.wli.dropboxapi.dropboxapi.model;

import java.util.ArrayList;

public class FileMultipleDTO {
	private ArrayList<String> fileName;

	public ArrayList<String> getFileName() {
		return fileName;
	}

	public void setFileName(ArrayList<String> fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "DeleteBatchDTO [fileName=" + fileName + "]";
	}

}
