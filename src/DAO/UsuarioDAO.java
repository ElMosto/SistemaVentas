/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Usuario;
import config.ConexionDB;

public class UsuarioDAO {
    private final ConexionDB conexion = new ConexionDB();

    // 1. Inicio de sesión (Retorna el ID o -1 si falla)
    public int login(String correo, String passwordHash) {
        String sql = "SELECT idUsuario FROM usuario WHERE correoUsuario = ? AND passwordUsuario = ? AND statusUsuario = 'A'";
        try (Connection con = conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            ps.setString(2, passwordHash);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("idUsuario");
            }
        } catch (Exception e) {
            System.err.println("Error en login: " + e.getMessage());
        }
        return -1; // Retorna -1 si las credenciales son incorrectas
    }

    // 2. Búsqueda con filtro para el JTable (Solo usuarios Activos)
    public List<Usuario> buscarUsuarios(String filtroNombre) {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT idUsuario, nombreUsuario, apellidoUsuario FROM usuario WHERE statusUsuario = 'A' AND nombreUsuario LIKE ?";
        try (Connection con = conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // Los % % se agregan para que busque coincidencias en cualquier parte del nombre
            ps.setString(1, "%" + filtroNombre + "%"); 
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNombreUsuario(rs.getString("nombreUsuario"));
                u.setApellidoUsuario(rs.getString("apellidoUsuario"));
                lista.add(u);
            }
        } catch (Exception e) {
            System.err.println("Error buscando usuarios: " + e.getMessage());
        }
        return lista;
    }

    // 3. Registrar
    public boolean registrar(Usuario u) {
    String sql = "INSERT INTO usuario (noUsuario, nombreUsuario, apellidoUsuario, correoUsuario, telefonoUsuario, direccionUsuario, passwordUsuario, idRolUsuario, statusUsuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection con = conexion.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        // Asignamos todo directamente
        ps.setInt(1, u.getNoUsuario());
        ps.setString(2, u.getNombreUsuario());
        ps.setString(3, u.getApellidoUsuario());
        ps.setString(4, u.getCorreoUsuario());
        ps.setString(5, u.getTelefonoUsuario()); // ¡AQUÍ ESTÁ LA MAGIA! Directo como String
        ps.setString(6, u.getDireccionUsuario());
        ps.setString(7, u.getPasswordUsuario());
        ps.setInt(8, u.getIdRolUsuario());
        ps.setString(9, u.getStatusUsuario());
        
        ps.executeUpdate();
        return true;
    } catch (Exception e) {
        System.err.println("Error registrando: " + e.getMessage());
        return false;
    }
}

    // 4. Eliminado Lógico (Ahora busca por noUsuario)
    public boolean eliminarLogico(int noUsuario) {
        String sql = "UPDATE usuario SET statusUsuario = 'E' WHERE noUsuario = ?";
        try (Connection con = conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, noUsuario); // Pasamos el número de serie
            ps.executeUpdate();
            return true;
            
        } catch (Exception e) {
            System.err.println("Error eliminando: " + e.getMessage());
            return false;
        }
    }

    // 5. Editar (Ahora busca por noUsuario)
    public boolean editar(Usuario u) {
        // NOTA: Quitamos "noUsuario=?" del SET, porque un número de serie no se debe sobreescribir.
        // Lo usamos exclusivamente al final en el WHERE.
        String sql = "UPDATE usuario SET nombreUsuario=?, apellidoUsuario=?, correoUsuario=?, direccionUsuario=?, idRolUsuario=? WHERE noUsuario=?";
        try (Connection con = conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getApellidoUsuario());
            ps.setString(3, u.getCorreoUsuario());
            ps.setString(4, u.getDireccionUsuario());
            ps.setInt(5, u.getIdRolUsuario());
            
            // Aquí le pasamos el noUsuario para que sepa a quién va a editar
            ps.setInt(6, u.getNoUsuario()); 
            
            ps.executeUpdate();
            return true;
            
        } catch (Exception e) {
            System.err.println("Error editando: " + e.getMessage());
            return false;
        }
    }
}