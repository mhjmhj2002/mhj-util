package br.com.mhj.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

	File fileIn;
	File fileOut;

	public Zip(File fileIn, File fileOut) {
		super();
		this.fileIn = fileIn;
		this.fileOut = fileOut;
	}

	public void zipFile() throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
		ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
		FileInputStream fileInputStream = new FileInputStream(fileIn);
		try {
			ZipEntry zipEntry = new ZipEntry(fileIn.getName());
			zipOutputStream.putNextEntry(zipEntry);
			byte[] bytes = new byte[1024];
			int length;
			while ((length = fileInputStream.read()) >= 0) {
				zipOutputStream.write(bytes, 0, length);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			zipOutputStream.close();
			fileInputStream.close();
			fileOutputStream.close();
		}
	}
}
