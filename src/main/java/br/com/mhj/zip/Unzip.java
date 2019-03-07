package br.com.mhj.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip {

	File fileIn;
	File fileOut;

	public Unzip(File fileIn, File fileOut) {
		super();
		this.fileIn = fileIn;
		this.fileOut = fileOut;
	}

	public void unzipFile() throws IOException {
		byte[] buffer = new byte[1024];
		ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(fileIn));
		ZipEntry zipEntry = zipInputStream.getNextEntry();
		try {
			while (zipEntry != null) {
				File newFile = newFile(fileOut, zipEntry);
				FileOutputStream fileOutputStream = new FileOutputStream(newFile);
				int len;
				while ((len = zipInputStream.read(buffer)) > 0) {
					fileOutputStream.write(buffer, 0, len);
				}
				fileOutputStream.close();
				zipEntry = zipInputStream.getNextEntry();
			}
		} catch (IOException e) {
			throw e;
		} finally {
			zipInputStream.closeEntry();
			zipInputStream.close();
		}

	}

	private File newFile(File dirDestination, ZipEntry zipEntry) throws IOException {
		File destFile = new File(dirDestination, zipEntry.getName());
		String destDirPath = dirDestination.getCanonicalPath();
		String destFilePath = destFile.getCanonicalPath();

		if (!destFilePath.startsWith(destDirPath + File.separator)) {
			throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());

		}
		
		return destFile;

	}

}
