package com.wli.dropboxapi.dropboxapi.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.DeleteArg;
import com.dropbox.core.v2.files.DeleteErrorException;
import com.dropbox.core.v2.files.DeleteResult;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.wli.dropboxapi.dropboxapi.config.Config;
import com.wli.dropboxapi.dropboxapi.config.Utils;
import com.wli.dropboxapi.dropboxapi.model.DeleteBatchDTO;
import com.wli.dropboxapi.dropboxapi.model.FileDTO;
import com.wli.dropboxapi.dropboxapi.model.FileMultipleDTO;
import com.wli.dropboxapi.dropboxapi.services.DropBoxServices;

@Service
public class DropBoxServicesImpl implements DropBoxServices {

	@Value("${downloadPath}")
	private String downloadPath;

	@Override
	public boolean deleteBatch(DeleteBatchDTO deleteBatchDTO) {
		List<DeleteArg> entries = new ArrayList<DeleteArg>();
		for (String name : deleteBatchDTO.getFileName()) {
			DeleteArg deleteArg = new DeleteArg("/" + name);
			System.out.println(name);
			entries.add(deleteArg);
		}

		return Utils.deleteBatch(entries);
	}

	@Override
	public ArrayList<String> listOfFile() throws ListFolderErrorException, DbxException {
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

	@Override
	public boolean deleteFile(FileDTO deleteFileDTO) {

		DeleteResult deleteFile;
		try {
			deleteFile = Config.client.files().deleteV2("/" + deleteFileDTO.getFileName());
			System.out.println(deleteFile.toString());
			return true;
		} catch (DeleteErrorException e) {
			e.printStackTrace();
			return false;
		} catch (DbxException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean fileDownload(FileDTO downloadFileName) {
		try {
			File dir = new File(downloadPath + downloadFileName.getFileName());
			System.out.println(dir.exists());
			FileOutputStream downloadFile = new FileOutputStream(dir);

			try {
				FileMetadata metadata = Config.client.files().downloadBuilder("/" + downloadFileName.getFileName())
						.download(downloadFile);
				System.out.println(metadata);
				return true;
			} finally {
				downloadFile.close();
			}
		} catch (DbxException e) {
			e.printStackTrace();
			return false;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean fileDownloadMultiple(FileMultipleDTO downloadFiles) {
		for (String fileName : downloadFiles.getFileName()) {
			try {
				fileDownloadMultiple(fileName);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		}
		return true;
	}

	public boolean fileDownloadMultiple(String downloadFileName) {
		try {
			File dir = new File(downloadPath + downloadFileName);
			System.out.println(dir.exists());
			FileOutputStream downloadFile = new FileOutputStream(dir);

			try {
				FileMetadata metadata = Config.client.files().downloadBuilder("/" + downloadFileName)
						.download(downloadFile);
				System.out.println(metadata);
				return true;
			} finally {
				downloadFile.close();
			}
		} catch (DbxException e) {
			e.printStackTrace();
			return false;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean fileUpload(MultipartFile file) {

		// File path = new File(file.getInputStream());
		// System.out.println(path.getAbsolutePath() + "" + path.getName());
		try {
			FileMetadata metadata = Config.client.files().uploadBuilder("/" + file.getOriginalFilename())
					.uploadAndFinish(file.getInputStream());
			System.out.println(metadata.toString());
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
			return false;
		}
	}

}
