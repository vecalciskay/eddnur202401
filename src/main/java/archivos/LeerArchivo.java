package archivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class LeerArchivo {
    public static void main(String[] args) throws IOException {
        String f = "c:/tmp/algo.txt";

        BufferedReader lector = new BufferedReader(new FileReader(f));
        while(lector.ready()) {
            String linea = lector.readLine();
            System.out.println(linea);
        }

        lector.close();

        //String[] lineas = Files.readAllLines(f);
    }
}
