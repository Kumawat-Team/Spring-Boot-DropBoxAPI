package com.wli.dropboxapi.dropboxapi.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.wli.dropboxapi.dropboxapi.model.DeleteBatchDTO;
import com.wli.dropboxapi.dropboxapi.model.FileDTO;
import com.wli.dropboxapi.dropboxapi.model.FileMultipleDTO;
import com.wli.dropboxapi.dropboxapi.services.DropBoxServices;

@RestController
@RequestMapping(path = "/dropbox/v1")
public class DropBoxController {

	@Autowired
	DropBoxServices dropBoxServices;

	@DeleteMapping("/deleteBatch")
	boolean deleteBatch(@RequestBody DeleteBatchDTO deleteBatchDTO) {
		System.out.println(deleteBatchDTO.toString());
		return dropBoxServices.deleteBatch(deleteBatchDTO);
	}

	@DeleteMapping
	boolean deleteFile(@RequestBody FileDTO deleteFileDTO) {
		System.out.println(deleteFileDTO.toString());
		return dropBoxServices.deleteFile(deleteFileDTO);
	}

	@GetMapping("/list")
	ArrayList<String> listOfFiles() throws ListFolderErrorException, DbxException {
		return dropBoxServices.listOfFile();
	}

	@PostMapping("/download")
	boolean download(@RequestBody FileDTO downloadFile) {
		return dropBoxServices.fileDownload(downloadFile);
	}

	@PostMapping("/downloadMultiple")
	boolean downloadMultiple(@RequestBody FileMultipleDTO downloadFiles) {
		return dropBoxServices.fileDownloadMultiple(downloadFiles);
	}

	@PostMapping("/upload")
	boolean fileUpload(@RequestParam("file") MultipartFile file) {
		System.out.println(file);
		dropBoxServices.fileUpload(file);
		return true;
	}

	@PostMapping("/uploadMultiple")
	boolean fileUploadMultiple(@RequestParam("files") MultipartFile[] files) {
		System.out.println(files);
		Arrays.asList(files).stream().forEach(file -> {
			dropBoxServices.fileUpload(file);
		});
		return true;
	}

	@GetMapping("/test")
	String listOfFiless() {
		return "test";
	}

}
