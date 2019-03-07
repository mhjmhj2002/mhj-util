package br.com.mhj.encode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Base64.Encoder;


public class Encode {
	
	File fileIn;
	File dirOut;
	
	public Encode(File fileIn, File dirOut) {
		super();
		this.fileIn = fileIn;
		this.dirOut = dirOut;
	}

	public void encode() throws IOException {	
		Encoder encoder = Base64.getEncoder();
		FileInputStream fileInputStream = new FileInputStream(fileIn);
		OutputStream outputStream = encoder.wrap(new FileOutputStream(dirOut));
		try {
		
		int _byte;
		
			while( (_byte = fileInputStream.read()) != -1 ) {
				outputStream.write(_byte);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			fileInputStream.close();
			outputStream.close();
		}
	}
}
