package com.ctel.upload;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.azure.storage.blob.models.BlobRequestConditions;
import com.azure.storage.blob.models.BlockBlobItem;
import com.azure.storage.blob.options.BlobParallelUploadOptions;

import net.minidev.json.parser.JSONParser;

public class FileUploading {

	Logger logger = LoggerFactory.getLogger(FileUploading.class);

	public String UploadingFile(String blobClient, File files, String errorFolder) {

		logger.info("upload method calling {} ");

		// Azure Configuration
		// String credentials =
		// "DefaultEndpointsProtocol=https;AccountName=bpclcontainerblob;AccountKey=dkJgOwwxapuE9G0OekzFMII9eZ/HBQSJ9QBUrzsRTviaOkUnYe2DsCUZn9aigdeNWh0HoYoFzc99+AStFN8krw==;EndpointSuffix=core.windows.net";
		String credentials = "DefaultEndpointsProtocol=https;AccountName=bpclcontainerblob;AccountKey=UjU3sn9MrjAjQRBJh8PUg1lAyGc99Bh+Tehwe41IRFtyfQx5u9XpzmEyZGpUkkeJUOu40gNZ7+/u+ASttgWm0A==;EndpointSuffix=core.windows.net";
		BlobContainerClient container = new BlobContainerClientBuilder().connectionString(credentials)
				.containerName("bpcl-demo-container").buildClient();

		System.out.println("Blob Method");

		try {

			JSONParser parser = new JSONParser();

			FileReader reader = new FileReader(files);

			Object obj = parser.parse(reader);

			System.out.println("Blob Reader");

			// Checking blobname in Azureconatainer
			BlobClient blob = container.getBlobClient(blobClient);

			if (blob.exists()) {

				logger.info("Blob Already existed ,Status code is : " + HttpStatus.CONFLICT);
				reader.close();
				return "blob already existed";
			}
			if (!blob.exists()) {// If blobname not exist

				// Setting the Upload Conditions for Uploading method
				ByteArrayInputStream dataStream = new ByteArrayInputStream(obj.toString().getBytes());
				BlobParallelUploadOptions options = new BlobParallelUploadOptions(dataStream);
				options.setRequestConditions(new BlobRequestConditions().setIfNoneMatch("*"));

				com.azure.core.http.rest.Response<BlockBlobItem> res = blob.uploadWithResponse(options, null,
						com.azure.core.util.Context.NONE);

				logger.info("blob response {} " + res.getStatusCode());

				if (res.getStatusCode() == 201) {
					// File uploaded Successfully and close the file
					reader.close();
					return "Success";
				} else {
					logger.info("file not uploaded to azure");

					/*
					 * File not Uploaded to Azure. File moving to ErrorFolder
					 */
					String s = errorFolder.concat("/") + files.getName();
					logger.info("File Moving to Error Folder");
					Path temp = Files.move(Paths.get(files.toString()), Paths.get(s));
					logger.info("Error Folder {} " + s);

					return "Failed";
				}

			}
		} catch (Exception e) {
			logger.info("catch Method");
			logger.info("The system cannot find the file specified ----" + e.getMessage());
		}
		return credentials;

	}

}
