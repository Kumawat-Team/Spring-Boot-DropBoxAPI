package com.wli.dropboxapi.dropboxapi.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.DeleteArg;
import com.dropbox.core.v2.files.DeleteBatchLaunch;
import com.dropbox.core.v2.files.DeleteErrorException;
import com.dropbox.core.v2.files.DeleteResult;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderContinueErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.UploadErrorException;

public class Utils {

	static boolean pushFile(String pathname)
			throws FileNotFoundException, IOException, UploadErrorException, DbxException {
		File path = new File(pathname);
		System.out.println(path.getAbsolutePath() + "" + path.getName());
		try (FileInputStream in = new FileInputStream(path.getAbsolutePath())) {
			FileMetadata metadata = Config.client.files().uploadBuilder("/" + path.getName()).uploadAndFinish(in);
			System.out.println(metadata.toString());
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
			return false;
		}

	}

	static ArrayList<String> listOfFile() throws ListFolderContinueErrorException, DbxException {
		ArrayList<String> list = new ArrayList<String>();
		ListFolderResult result = Config.client.files().listFolder("");
		while (true) {
			for (Metadata metadata : result.getEntries()) {
				System.out.println(metadata.getPathLower());
				list.add(metadata.getPathLower());
				
			}

			if (!result.getHasMore()) {
				break;
			}

			result = Config.client.files().listFolderContinue(result.getCursor());
		}
		return list;

	}

	static boolean deleteFile(String path) throws DeleteErrorException, DbxException {

		DeleteResult deleteFile = Config.client.files().deleteV2(path);
		System.out.println(deleteFile.toString());

		return false;
	}

	public static boolean deleteBatch(List<DeleteArg> entries) {
		DeleteBatchLaunch deleteFile;
		try {
			deleteFile = Config.client.files().deleteBatch(entries);
			System.out.println(deleteFile.toString());
			return true;
		} catch (DbxApiException e) {
			e.printStackTrace();
			return false;

		} catch (DbxException e) {
			e.printStackTrace();
			return false;
		}

	}

}
