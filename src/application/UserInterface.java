package application;

import java.util.List;
import model.entities.Usuario;

public interface UserInterface {
    public void inserir(Usuario usuario);

    public void deletar(Usuario usuario);

    public void atualizar(Usuario usuario);

    public Usuario buscar(Usuario usuario);

    public List<Usuario> listar();
}
