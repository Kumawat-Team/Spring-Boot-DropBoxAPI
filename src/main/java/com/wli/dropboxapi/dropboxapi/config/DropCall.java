package com.wli.dropboxapi.dropboxapi.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderContinueErrorException;

public class DropCall {

	public static void main(String[] args)
			throws ListFolderContinueErrorException, DbxException, FileNotFoundException, IOException {

		//Utils.listOfFile();
		try
        {
			File dir = new File("F:\\getting started.pdf");
			System.out.println(dir.exists());
            //output file for download --> storage location on local system to download file
          //  OutputStream downloadFile = new FileOutputStream(dir);
            FileOutputStream downloadFile = new FileOutputStream(dir);
            
            try
            {
            FileMetadata metadata = Config.client.files().downloadBuilder("/getting started.pdf")
                    .download(downloadFile);
            System.out.println(metadata);
            }
            finally
            {
                downloadFile.close();
            }
        }
        //exception handled
        catch (DbxException e)
        {
        	e.printStackTrace();
            //error downloading file
           // JOptionPane.showMessageDialog(null, "Unable to download file to local system\n Error: " + e);
        }
        catch (IOException e)
        {
        	e.printStackTrace();
            //error downloading file
          //  JOptionPane.showMessageDialog(null, "Unable to download file to local system\n Error: " + e);
        }
		
		
		
//		
//		List<DeleteArg> entries= new ArrayList<DeleteArg>();
//		DeleteArg deleteArg = new DeleteArg("/map.smmx");
//		entries.add(deleteArg);
//		Utils.deleteBatch(entries);
		
		// Utils.deleteFile("/kamleshknstext.txt");
//		Boolean fileStatus = Utils
//				.pushFile("F:\\FractalFrog\\dropbox\\dropboxapi\\src\\main\\resources\\kamleshknstext.txt");
//		System.out.println(fileStatus);

	}

}
