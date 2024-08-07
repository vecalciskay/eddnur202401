package basedatos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private Connection conn;

    private static Conexion instancia = null;

    public static Conexion obtenerOCrear() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    private Conexion() {
        conn = null;
    }

    public Connection conectar() {
        try {
            if (conn != null && conn.isValid(10)) {
                return conn;
            }

            String servidor = "localhost";
            String bd = "edd";
            String usuario = "root";
            String clave = "krane345";
            int port = 6603;

            String strConexion = "jdbc:mysql://" + servidor + "/" +
                    bd + "?" +
                    "user=" + usuario +
                    "&password=" + clave;
            if (port != 3306 && port != 0)
                strConexion = "jdbc:mysql://" + servidor + ":" + port + "/" +
                    bd + "?" +
                    "user=" + usuario +
                    "&password=" + clave;

            conn = DriverManager.getConnection(strConexion);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            conn = null;
        }
        return conn;
    }

    public void desconectar() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = null;
            return;
        }

        conn.close();
    }
}
