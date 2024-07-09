package basedatos.dao.mysql;

import basedatos.dao.Conexion;
import basedatos.dao.PersonaDao;
import basedatos.dto.PersonaDto;
import basedatos.mapper.SimpleMapper;
import basedatos.searcher.SearchCriteria;
import cadenas.Lista;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class PersonaDaoMysql extends PersonaDao {
    private static final Logger logger = LogManager.getRootLogger();
    public Lista<PersonaDto> get() {
        return get(new SearchCriteria());
    }

    @Override
    public Lista<PersonaDto> get(SearchCriteria criteria) {
        SimpleMapper mapper = new SimpleMapper(PersonaDto.class);
        Lista<PersonaDto> resultado = new Lista<>();
        Connection conn = null;

        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.obtenerOCrear().conectar();

            String query = "SELECT id, nombre, alturacm, pesokg FROM personas WHERE " + criteria.toSqlString();
            logger.info("Query: " + query);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                PersonaDto dto = (PersonaDto) mapper.mapFromResultSet(rs);
                resultado.insert(dto);
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (InvocationTargetException ex) {
            System.out.println("InvocationTargetException: " + ex.getMessage());
        } catch (InstantiationException ex) {
            System.out.println("InstantiationException: " + ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.out.println("IllegalAccessException: " + ex.getMessage());
        } finally {
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
        SimpleMapper mapper = new SimpleMapper(PersonaDto.class);
        PersonaDto resultado = null;
        Connection conn = null;

        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.obtenerOCrear().conectar();

            String query = "SELECT id, nombre, alturacm, pesokg " +
                    "FROM personas " +
                    "WHERE id = " + id;
            logger.info("Query: " + query);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            resultado = (PersonaDto)mapper.mapFromResultSet(rs);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            resultado = null;
        } catch (InvocationTargetException ex) {
            System.out.println("InvocationTargetException: " + ex.getMessage());
        } catch (InstantiationException ex) {
            System.out.println("InstantiationException: " + ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.out.println("IllegalAccessException: " + ex.getMessage());
        } finally {
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
