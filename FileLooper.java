package com.me.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileLooper {

	public static void main(String[] args) throws IOException {
		final String TARGET_FOLDER = "thePNGfiles";
		final String SOURCE_FOLDER = "F:/NoahsArk2/Pics/Bob's iPhone/";

		File rootfile = new File(SOURCE_FOLDER);
		File[] rootfiles = rootfile.listFiles();

		// Loop inside the root folder
		for (int a = 0; a < rootfiles.length; a++) {
			if (rootfiles[a].isDirectory()) {

				// Loop inside the folder
				// File file = new File("F:/NoahsArk2/Pics/Bob's iPhone/2016-11");
				File file = new File(rootfiles[a].getAbsolutePath());
				// Reading directory contents
				File[] files = file.listFiles();

				int j = 0;
				for (int i = 0; i < files.length; i++) {
					if (files[i].getName().endsWith(".PNG")) {
						File afile = new File(files[i].getAbsolutePath());
						File targetFolder = new File(files[i].getParent() + "/" + TARGET_FOLDER);
						if (!targetFolder.exists()) {
							targetFolder.mkdir();
						}

						File bfile = new File(files[i].getParent() + "/" + TARGET_FOLDER + "/" + files[i].getName());
						// System.out.println("afile : " + afile);
						// System.out.println("bfile : " + bfile);
						copyFile(afile, bfile);
						System.out.println("(" + j + ")" + "File is copied successful!");

					}
				}
			}
		}
	}

	public static void copyFile(File afile, File bfile) {
		InputStream inStream = null;
		OutputStream outStream = null;

		try {

			inStream = new FileInputStream(afile);
			outStream = new FileOutputStream(bfile);

			byte[] buffer = new byte[1024];

			int length;
			// copy the file content in bytes
			while ((length = inStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}

			inStream.close();
			outStream.close();

			// delete the original file
			// afile.delete();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
