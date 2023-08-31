package DAO;

import model.entities.Obra; //exemplo, mudar depois
import java.util.List;

public interface UserInterface {
    public void inserir(Obra obra);

    public void deletar(Obra obra);
    
    public void atualizar(Obra usu);

    public Obra buscar(Obra obra);
    
    public List listar(Obra obra);
}
