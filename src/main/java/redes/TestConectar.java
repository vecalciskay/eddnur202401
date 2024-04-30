package redes;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class TestConectar {
    public static void main(String[] args) throws IOException {

        int puert = 6894;
        String ip = "127.0.0.1";

        Socket srv = new Socket(ip, puert);

        PrintWriter salida = new PrintWriter(srv.getOutputStream());

        salida.println("hugo");
        salida.println("paco");
        salida.println("luis");

        salida.println("FIN");

        salida.flush();

        salida.close();
    }
}
