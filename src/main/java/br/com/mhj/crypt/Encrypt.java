package br.com.mhj.crypt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Encrypt {
	private SecretKey secretKey;
	private Cipher cipher;

	File fileIn;
	File fileOut;

	public Encrypt(File fileIn, File fileOut, SecretKey secretKey, Cipher cipher) {
		super();
		this.fileIn = fileIn;
		this.fileOut = fileOut;
		this.secretKey = secretKey;
		this.cipher = cipher;
	}

	public void encrypt(String content) throws InvalidKeyException, FileNotFoundException, IOException {
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] iv = cipher.getIV();
		try (FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
				CipherOutputStream cipherOutputStream = new CipherOutputStream(fileOutputStream, cipher)) {
			fileOutputStream.write(iv);
			cipherOutputStream.write(content.getBytes());
		}
	}

	public void decrypt()
			throws FileNotFoundException, IOException, InvalidKeyException, InvalidAlgorithmParameterException {
		String content;

		try (FileInputStream fileInputStream = new FileInputStream(fileIn)) {
			byte[] fileIv = new byte[16];
			fileInputStream.read(fileIv);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(fileIv));

			try (CipherInputStream cipherIn = new CipherInputStream(fileInputStream, cipher);
					InputStreamReader inputrReader = new InputStreamReader(cipherIn);
					BufferedReader reader = new BufferedReader(inputrReader)) {
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				content = sb.toString();
			}
		}
	}
}
