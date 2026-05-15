/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author vgmos
 */
public class Usuario {
    private int idUsuario;
    private int noUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String correoUsuario;
    private String telefonoUsuario;
    private String direccionUsuario;
    private String passwordUsuario;
    private int idRolUsuario;
    private String statusUsuario; // Usaremos String para representar el char 'A' o 'E'

    public Usuario() {}

    // Getters y Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getNoUsuario() { return noUsuario; }
    public void setNoUsuario(int noUsuario) { this.noUsuario = noUsuario; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getApellidoUsuario() { return apellidoUsuario; }
    public void setApellidoUsuario(String apellidoUsuario) { this.apellidoUsuario = apellidoUsuario; }

    public String getCorreoUsuario() { return correoUsuario; }
    public void setCorreoUsuario(String correoUsuario) { this.correoUsuario = correoUsuario; }
    
    public String getTelefonoUsuario() { return telefonoUsuario; }
    public void setTelefonoUsuario(String telefonoUsuario) { this.telefonoUsuario = telefonoUsuario; }

    public String getDireccionUsuario() { return direccionUsuario; }
    public void setDireccionUsuario(String direccionUsuario) { this.direccionUsuario = direccionUsuario; }

    public String getPasswordUsuario() { return passwordUsuario; }
    public void setPasswordUsuario(String passwordUsuario) { this.passwordUsuario = passwordUsuario; }

    public int getIdRolUsuario() { return idRolUsuario; }
    public void setIdRolUsuario(int idRolUsuario) { this.idRolUsuario = idRolUsuario; }

    public String getStatusUsuario() { return statusUsuario; }
    public void setStatusUsuario(String statusUsuario) { this.statusUsuario = statusUsuario; }
}