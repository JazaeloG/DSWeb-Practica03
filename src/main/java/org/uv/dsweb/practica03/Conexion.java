package org.uv.dsweb.practica03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private Connection con;
    public static final String usuario = "postgres";
    public static final String password = "password";
    public static final String baseDatos = "prueba";
    public static final String url = "jdbc:postgresql://localhost:5432/" + baseDatos;

    public Connection obtenerConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado: " + e.getMessage());
        }
        return con;
    }

    public void desconectar() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Desconexión exitosa de la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al desconectar de la base de datos: " + e.getMessage());
            
        }
    }
}