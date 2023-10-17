package util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LerPDF {
	public static byte[] lerConteudoPDF(String caminhoArquivo) throws IOException {
	    File file = new File(caminhoArquivo);
	    if (!file.exists()) {
	        throw new IOException("Arquivo PDF não encontrado.");
	    }

	    try (FileInputStream fileInputStream = new FileInputStream(file);
	         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

	        int bytesRead;
	        byte[] buffer = new byte[1024];

	        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
	            byteArrayOutputStream.write(buffer, 0, bytesRead);
	        }

	        return byteArrayOutputStream.toByteArray();
	    }
	}

}
