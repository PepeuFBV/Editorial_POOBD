import java.util.List;

//importar exceptions


public interface BaseInterBO<E> {

    public void cadastrar(E entity);

    public void buscarPorId(E entity);

    public List<E> listarTodos();

    public void alterar(E entity);

    public void remover(E entity);

}