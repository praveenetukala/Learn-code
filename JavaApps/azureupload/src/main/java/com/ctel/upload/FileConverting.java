package com.ctel.upload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileConverting {

	Logger logger = LoggerFactory.getLogger(FileConverting.class);

	public void FileConvert() throws IOException {
		logger.info("calling FileConvert service {} ");
		String file = "D:/Upload";
		String folder = "D:/Success";
		String ErrorFolder = "D:/Error";
		String blobClient = null;

		File f = new File(file);
		File f1 = new File(folder);
		File f2 = new File(ErrorFolder);
		/*
		 * Checking the folders are present or not in directory
		 */

		if (f.exists()) {

			if (f1.exists()) {

				if (f2.exists()) {

					File[] listOfFiles = f.listFiles();
					if (listOfFiles.length > 0) {

						logger.info("files size {} " + listOfFiles.length);
						logger.info("iterating the files {}");
						for (File files : f.listFiles()) {

							logger.info("file name" + files.getName());
							logger.info("file path" + files.getPath());
							logger.info("file" + files);

							String successf = folder.concat("/") + files.getName();
							logger.info("success folder {} " + successf);

							Random random = new Random();
							int randomNumber = random.nextInt(900) + 100;
							logger.info("random generate no {} " + randomNumber);

							String s = files.getName();
							/*
							 * getting file name without extension from given path
							 */

							int pos = s.lastIndexOf('.');
							String s1 = s.substring(0, pos);

							/*
							 * logger.info("pose" + pos); if (pos > -1) { logger.info("If block {} ");
							 * 
							 * logger.info("Splitting {} " + s.substring(0, pos)); } else {
							 * 
							 * logger.info("else block {} " + s); }
							 */

							// Setting the Blobname
							// blobClient = s1 + randomNumber;
							blobClient = s1;
							logger.info("blob clientname {} " + blobClient);
							FileUploading fileup = new FileUploading();

							/*
							 * Calling the upload method passing params(blobname,file,errorFolder)
							 */
							String RES = fileup.UploadingFile(blobClient, files, ErrorFolder);

							logger.info("file uploaded response {} " + RES);

							if (RES.equals("Success")) {

								// Moving the Uploaded File to SuccessFolder
								Path temp = Files.move(Paths.get(files.toString()), Paths.get(successf));
								logger.info("moved to the success folder {} " + temp);

								FileDeleting filedelete = new FileDeleting();
								filedelete.fileDeleting();

							} else {
								logger.info("not uploaded to the success folder {}");

							}

						}
					} else {
						logger.info("There no files to Upload Azure");

					}

				} else {
					logger.info("Error Folder not found in local {} " + f2.getPath());

				}

			} else {
				logger.info("success Folder not found in local {} " + f1.getPath());

			}

		} else {
			logger.info("Upload Folder not found in local {} " + f.getPath());

		}

	}

}
