package org.uv.dsweb.practica03;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "personaBean")
@RequestScoped
public class PersonaBean {
    private Usuario usuario;
    private UsuarioDAO usuarioDAO;

    public PersonaBean() {
        usuarioDAO = new UsuarioDAO();
        usuario = new Usuario();
    }

    public void crearUsuario() {
        usuarioDAO.crearUsuario(usuario);
        usuario = new Usuario();
    }

    public Usuario obtenerUsuario(int id) {
        return usuarioDAO.obtenerUsuario(id);
    }

    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioDAO.obtenerTodosUsuarios();
    }

    public void actualizarUsuario() {
        usuarioDAO.actualizarUsuario(usuario);
        usuario = new Usuario();
    }

    public void eliminarUsuario(int id) {
        usuarioDAO.eliminarUsuario(id);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
