package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LerPDF {
    public static byte[] lerArquivoPDF(String caminhoDoPDF) throws IOException {
        Path path = Paths.get(caminhoDoPDF);
        return Files.readAllBytes(path);
    }
}
