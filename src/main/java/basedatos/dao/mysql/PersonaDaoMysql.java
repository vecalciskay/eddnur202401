package basedatos.dao.mysql;

import basedatos.dao.Conexion;
import basedatos.dao.PersonaDao;
import basedatos.dto.PersonaDto;
import cadenas.Lista;

import java.sql.*;

public class PersonaDaoMysql extends PersonaDao {
    public Lista<PersonaDto> get() {
        Lista<PersonaDto> resultado = new Lista<>();
        Connection conn = null;

        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.obtenerOCrear().conectar();

            String query = "SELECT id, nombre, alturacm, pesokg FROM personas";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                int objId = rs.getInt("id");
                String objNombre = rs.getString("nombre");
                int objAlturacm = rs.getInt("alturacm");
                float objPesokg = rs.getFloat("pesokg");

                PersonaDto dto =
                        new PersonaDto(objId,objNombre,objAlturacm, objPesokg);
                resultado.insert(dto);
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

        return resultado;
    }

    public PersonaDto insert(PersonaDto obj) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.obtenerOCrear().conectar();
            String query = "INSERT INTO personas (id,nombre, alturacm, pesokg) " +
                    " VALUES ( ?, ?, ?, ? )";

            stmt = conn.prepareStatement(query);
            stmt.setInt(1, obj.getId());
            stmt.setString(2, obj.getNombre());
            stmt.setInt(3, obj.getAlturacm());
            stmt.setFloat(4, obj.getPesokg());

            stmt.executeUpdate();
            return obj;
        } catch(SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }

    public PersonaDto update(PersonaDto obj) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.obtenerOCrear().conectar();
            String query = "UPDATE personas SET nombre = ?, " +
                    " alturacm = ?, " +
                    " pesokg = ? " +
                    "WHERE id = ?";

            stmt = conn.prepareStatement(query);
            stmt.setInt(4, obj.getId());
            stmt.setString(1, obj.getNombre());
            stmt.setInt(2, obj.getAlturacm());
            stmt.setFloat(3, obj.getPesokg());

            stmt.executeUpdate();
            return obj;
        } catch(SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
    }

    public int delete(Integer id) {
        try {
            Connection conn = Conexion.obtenerOCrear().conectar();

            String query = "DELETE FROM personas WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return 1;
        } catch(SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return 0;
        }
    }

    public PersonaDto getById(Integer id) {
        PersonaDto resultado = null;
        Connection conn = null;

        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.obtenerOCrear().conectar();

            String query = "SELECT id, nombre, alturacm, pesokg " +
                    "FROM personas " +
                    "WHERE id = " + id;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            int objId = rs.getInt("id");
            String objNombre = rs.getString("nombre");
            int objAlturacm = rs.getInt("alturacm");
            float objPesokg = rs.getFloat("pesokg");

            resultado =
                    new PersonaDto(objId,objNombre,objAlturacm, objPesokg);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            resultado = null;
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

        return resultado;
    }
}
