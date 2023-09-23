import java.util.List;

//importar exceptions


public interface BaseInterBO<VO> {

    public void cadastrar(VO vo);

    public void buscarPorId(VO vo);

    public List<VO> listarTodos();

    public void alterar(VO vo);

    public void remover(VO vo);

}