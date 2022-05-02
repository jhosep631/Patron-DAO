
package com.emergentes.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConexionBD {
    static public String driver = "com.mysql.jdbc.Driver";
    static public String url = "jdbc:mysql://localhost:3309/bd_anuncios";
    static public String usuario = "root";
    static public String password = "";
    
    protected Connection conn = null;

    public ConexionBD() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR EN EL DRIVER: "+ex.getMessage());
        }
        try {
            conn = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException ex) {
            System.out.println("ERROR EN EL DRIVER: "+ex.getMessage());

        }
    }
    
    public Connection conectar(){
        return conn;
    }
    
    public void desconectar(){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR AL CERRAR LA CONEXION "+ex.getMessage());
        }
    }
}