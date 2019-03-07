package br.com.mhj.encode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Base64.Decoder;

public class Decode {

	File fileIn;
	File fileOut;
	
	public Decode(File fileIn, File fileOut) {
		super();
		this.fileIn = fileIn;
		this.fileOut = fileOut;
	}

	public void decode() throws IOException {
		Decoder
		decoder = Base64.getDecoder();
		FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
		InputStream inputStream = decoder.wrap(new FileInputStream(fileIn));
		try {
			int _byte;
			while ((_byte = inputStream.read()) != -1) {
				fileOutputStream.write(_byte);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			fileOutputStream.close();
			inputStream.close();
		}

	}

}
