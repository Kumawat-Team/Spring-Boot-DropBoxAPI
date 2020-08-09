package com.wli.dropboxapi.dropboxapi.model;

import java.util.ArrayList;

public class ListOfFilesDropBox {
	private ArrayList<String> list;

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "ListOfFilesDropBox [list=" + list + "]";
	}
}
