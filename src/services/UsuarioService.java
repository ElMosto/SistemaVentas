/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;


import DAO.UsuarioDAO;
import java.security.MessageDigest;
import java.util.List;
import java.util.Random;
import models.Usuario;


/**
 *
 * @author vgmos
 */
public class UsuarioService {
    private UsuarioDAO dao = new UsuarioDAO();
    public int iniciarSesion(String correo, String passwordPlana) {
        String hash = convertirSHA256(passwordPlana);
        return dao.login(correo, hash);
    }

    // Obtener lista para el JTable
    public List<Usuario> obtenerListaUsuarios(String filtro) {
        // Si el filtro viene nulo, lo cambiamos a vacío para que traiga todos
        if (filtro == null) filtro = ""; 
        return dao.buscarUsuarios(filtro);
    }

    // Lógica de Registro
    public boolean registrarNuevoUsuario(Usuario u) {
        // 1. Generar noUsuario aleatorio de 5 dígitos (entre 10000 y 99999)
        Random random = new Random();
        int numeroAleatorio = 10000 + random.nextInt(90000); 
        u.setNoUsuario(numeroAleatorio);

        // 2. Encriptar la contraseña antes de mandarla al DAO
        String hash = convertirSHA256(u.getPasswordUsuario());
        u.setPasswordUsuario(hash);
        
        // 3. Forzar el estatus por default a 'A'
        u.setStatusUsuario("A");

        return dao.registrar(u);
    }

    // Lógica de Eliminado (Actualizado para recibir el noUsuario)
    public boolean eliminarUsuario(int noUsuario) {
        return dao.eliminarLogico(noUsuario);
    }

    // Lógica de Edición (Este se queda prácticamente igual, 
    // pero ahora depende del noUsuario que traiga tu objeto 'u')
    public boolean actualizarUsuario(Usuario u) {
        // Podrías agregar una validación extra aquí si lo deseas, por ejemplo:
        if (u.getNoUsuario() <= 0) {
            System.out.println("Error: Número de usuario no válido.");
            return false;
        }
        return dao.editar(u);
    }

    // --- UTILIDAD PRIVADA PARA ENCRIPTAR (HASH SHA-256) ---
    private String convertirSHA256(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error al encriptar la contraseña", ex);
        }
    }
    
}
