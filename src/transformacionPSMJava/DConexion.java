/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transformacionPSMJava;

/**
 *
 * @author Arturo
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DConexion {

	private String databaseName;
	private String Usuario;
	private String Pass;

    private Connection con = null;

    public Connection conectar() {
        Connection conex = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //driver
            String databaseName = "db1";
            String url = "jdbc:mysql://localhost:3306/" + databaseName;
            conex = DriverManager.getConnection(url, "root", ""); //solo modificar el password
            if (conex != null)
                return conex;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conex;
    }

    public void closeConnection() {
        try {
            if (con != null)
                con.close();
            con = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerDatos(String consulta) {
        try {
            Connection con = conectar();
            if (con != null) {
                Statement stmt = con.createStatement();
                return stmt.executeQuery(consulta);
            }
        } catch (Exception ex) {
        }
        return null;
    }

    public void ejecutarConsulta(String consulta) {
        try {
            Connection con = conectar();
            if (con != null) {
                Statement stmt = con.createStatement();
                stmt.execute(consulta);
            }
        } catch (Exception ex) {
        }

    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }
    
    
    
}
