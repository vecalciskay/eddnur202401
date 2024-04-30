package redes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * https://github.com/vecalciskay/eddnur202401
 */
public class TestSocketServer {
    private static Logger logger = LogManager.getRootLogger();
    public static void main(String[] args) throws IOException {
        int puerto = 6894;
        ServerSocket srv = new ServerSocket(puerto);
        logger.info("Creado el server socket que va a escuchar en el puerto " + puerto);

        logger.info("Esperando una conexion ...");
        Socket clt = srv.accept();

        logger.info("Se acaba de conectar alguien");
        BufferedReader lecturador = new BufferedReader(
                new InputStreamReader(clt.getInputStream()));

        String linea = lecturador.readLine();
        logger.info("<<< " + linea);

        while(!linea.equals("FIN")) {
            linea = lecturador.readLine();
            logger.info("<<< " + linea);
        }

        logger.info("Cerramos todo");
        clt.close();
        srv.close();
    }
}
