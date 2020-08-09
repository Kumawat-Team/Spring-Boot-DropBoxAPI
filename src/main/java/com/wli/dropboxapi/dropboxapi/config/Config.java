package com.wli.dropboxapi.dropboxapi.config;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

public class Config {

	private static String ACCESS_TOKEN = "lXusybDkLNAAAAAAAAAAAcNMUKM7rBxkONown-i22ZPTqD9QwhcAKz5_-4b--Faz";

	private static DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
	public static DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
	
	
	
//
//		// Get current account info
//		FullAccount account = client.users().getCurrentAccount();
//		System.out.println(account.getName().getDisplayName());
//
//
//
//		try (FileInputStream in = new FileInputStream("C:\\Users\\saurabhdr\\Desktop\\text.txt")) {
//			FileMetadata metadata = client.files().uploadBuilder("/test.txt").uploadAndFinish(in);
//		}

}
