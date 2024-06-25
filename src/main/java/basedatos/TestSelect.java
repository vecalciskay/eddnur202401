package basedatos;

import java.sql.*;

public class TestSelect {
    public static void main(String[] args) {
        Connection conn = null;

        try {
            String servidor = "localhost";
            String bd = "prog3";
            String usuario = "root";
            String clave = "root";

            String strConexion = "jdbc:mysql://" + servidor + "/" +
                    bd + "?" +
                    "user=" + usuario +
                    "&password=" + clave;

            conn = DriverManager.getConnection(strConexion);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        Statement stmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT id, nombre, alturacm, pesokg FROM personas";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                int objId = rs.getInt("id");
                String objNombre = rs.getString("nombre");
                int objAlturacm = rs.getInt("alturacm");
                float objPesokg = rs.getFloat("pesokg");

                System.out.println("( " + objId + ", " +
                        objNombre + ", " +
                        objAlturacm + ", " +
                        objPesokg);
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }

        try {
            conn.close();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
