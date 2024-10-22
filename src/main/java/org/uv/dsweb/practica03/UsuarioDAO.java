package org.uv.dsweb.practica03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author omar_
 */
public class UsuarioDAO {
    private Conexion conexion;

    public UsuarioDAO() {
        this.conexion = new Conexion();
    }

    public void crearUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, telefono, direccion) VALUES (?, ?, ?)";

        try (Connection con = conexion.obtenerConexion();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
             
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getTelefono());
            pstmt.setString(3, usuario.getDireccion());
            pstmt.executeUpdate();
            System.out.println("Usuario creado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al crear el usuario: " + e.getMessage());
        }
    }

    public Usuario obtenerUsuario(int codigo) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        Usuario usuario = null;

        try (Connection con = conexion.obtenerConexion();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
             
            pstmt.setInt(1, codigo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el usuario: " + e.getMessage());
        }

        return usuario;
    }

    public List<Usuario> obtenerTodosUsuarios() {
    List<Usuario> usuarios = new ArrayList<>();
    String sql = "SELECT * FROM usuarios";

    Connection con = conexion.obtenerConexion();
    if (con == null) {
        System.out.println("No se pudo obtener la conexión a la base de datos.");
        return usuarios; 
    }

    try (PreparedStatement pstmt = con.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setTelefono(rs.getString("telefono"));
            usuario.setDireccion(rs.getString("direccion"));
            usuarios.add(usuario);
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener todos los usuarios: " + e.getMessage());
    } finally {
        conexion.desconectar();
    }

    return usuarios;
}

    public void actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, telefono = ?, direccion = ? WHERE id = ?";

        try (Connection con = conexion.obtenerConexion();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
             
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getTelefono());
            pstmt.setString(3, usuario.getDireccion());
            pstmt.setInt(4, usuario.getId());
            pstmt.executeUpdate();
            System.out.println("Usuario actualizado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    public void eliminarUsuario(int codigo) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection con = conexion.obtenerConexion();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
             
            pstmt.setInt(1, codigo);
            pstmt.executeUpdate();
            System.out.println("Usuario eliminado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el usuario: " + e.getMessage());
        }
    }
}
