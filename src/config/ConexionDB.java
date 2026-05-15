package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    // 1. Configura tus credenciales (Ajusta estos datos a tu base de datos)
    private final String URL = "jdbc:mysql://localhost:3306/rutaLibre";
    private final String USUARIO = "root"; // Tu usuario (por defecto suele ser root)
    private final String PASSWORD = "";    // Tu contraseña (si usas XAMPP suele estar vacía)

    // 2. Método para obtener la conexión
    public Connection obtenerConexion() {
        Connection conexion = null;
        try {
            // Se establece la conexión con la base de datos
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            // System.out.println("¡Conexión exitosa!"); // Descomenta esto para probar
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
        return conexion;
    }
}