/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import org.uv.dsweb.practica03.Usuario;
import org.uv.dsweb.practica03.UsuarioDAO;

/**
 *
 * @author omar_
 */
@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {
    private Usuario usuario;
    private UsuarioDAO usuarioDAO;
    private boolean editMode;

    public UsuarioBean() {
        usuarioDAO = new UsuarioDAO();
        usuario = new Usuario();
        editMode = false;
    }

    public void prepararNuevoUsuario() {
        usuario = new Usuario(); 
        editMode = false;
    }

    public void prepararEditarUsuario(int id) {
        usuario = usuarioDAO.obtenerUsuario(id);
        editMode = true;
    }

    public void crearUsuario() {
        usuarioDAO.crearUsuario(usuario);
        usuario = new Usuario();
    }


    public void actualizarUsuario() {
        usuarioDAO.actualizarUsuario(usuario);
        usuario = new Usuario(); 
    }

    public void guardarUsuario() {
        if (editMode) {
            actualizarUsuario();
        } else {
            crearUsuario();
        }
    }

    public void eliminarUsuario(int id) {
        usuarioDAO.eliminarUsuario(id);
    }

    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioDAO.obtenerTodosUsuarios();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
}