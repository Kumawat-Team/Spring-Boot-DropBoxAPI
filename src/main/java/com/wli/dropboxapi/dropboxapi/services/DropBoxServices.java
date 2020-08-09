package com.wli.dropboxapi.dropboxapi.services;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.wli.dropboxapi.dropboxapi.model.DeleteBatchDTO;
import com.wli.dropboxapi.dropboxapi.model.FileDTO;
import com.wli.dropboxapi.dropboxapi.model.FileMultipleDTO;

public interface DropBoxServices {

	boolean deleteBatch(DeleteBatchDTO deleteBatchDTO);

	boolean deleteFile(FileDTO deleteFileDTO);
	
	boolean fileDownload(FileDTO downloadFile);

	boolean fileDownloadMultiple(FileMultipleDTO downloadFiles);

	ArrayList<String> listOfFile() throws ListFolderErrorException, DbxException;
	
	boolean fileUpload(MultipartFile file);
}
