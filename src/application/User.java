package application;

import java.util.ArrayList;
import java.util.List;
import model.entities.Usuario;

public class User implements UserInterface {
    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    public void inserir(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public void deletar(Usuario usuario) {
        usuarios.remove(usuario);
    }

    @Override
    public void atualizar(Usuario usuario) {
        //encontrar o usuário na lista e atualizar
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == usuario.getId()) {
                usuarios.set(i, usuario);
                break;
            }
        }
    }

    @Override
    public Usuario buscar(Usuario usuario) {
        //encontrar o usuário na lista com base no ID
        for (Usuario u : usuarios) {
            if (u.getId() == usuario.getId()) {
                return u;
            }
        }
        return null;
    }

    @Override
    public List<Usuario> listar() {
        return usuarios;
    }
}
