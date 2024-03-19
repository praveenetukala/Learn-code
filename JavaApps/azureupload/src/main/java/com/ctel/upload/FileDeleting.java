package com.ctel.upload;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileDeleting {
	Logger logger = LoggerFactory.getLogger(FileDeleting.class);

	public void fileDeleting() {
		logger.info("delete Service calling {} ");
		File f = new File("D:\\Success");
		// Getting the file from SuccessFolder which are Uploaded to Azure

		File[] listOfFiles = f.listFiles();
		logger.info("iterate the files from the Success Folder {}");
		for (File files : f.listFiles()) {
			// Deleting the file
 			boolean t = files.delete();
			logger.info("deleted file name {} " + files.getName());
			logger.info("delete file response {} " + t);

		}
	}

}
