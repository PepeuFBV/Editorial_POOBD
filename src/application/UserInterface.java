package application;

import model.entities.Usuario;
import java.util.List;

public interface UserInterface {
    public void inserir(Usuario usu);

    public void deletar(Usuario usu);
    
    public void atualizar(Usuario usu);

    public Usuario buscar(Usuario usu);
    
    public List listar(Usuario usu);
}
