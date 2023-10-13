package model.BO;

import java.sql.SQLException;
import exceptions.DeletionException;
import exceptions.ErroLoginException;
import exceptions.InsertException;
import model.DAO.AutorDAO;
import model.DAO.AvaliadorDAO;
import model.DAO.GerenteDAO;
import model.DAO.UsuarioDAO;
import model.VO.AutorVO;
import model.VO.AvaliadorVO;
import model.VO.GerenteVO;
import model.VO.UsuarioVO;
import java.util.ArrayList;

public class UserBO<VO extends UsuarioVO> {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
    public UsuarioVO autenticar(UsuarioVO usuarioVO) throws ErroLoginException { //retorna um objeto do tipo UsuarioVO (GerenteVO, AutorVO ou AvaliadorVO)
        ArrayList<UsuarioVO> usuario = usuarioDAO.buscarPorEmail(usuarioVO); //deve haver apenas 1 usuário no ArrayList
        if (usuario.isEmpty()) {
            throw new ErroLoginException("Usuário não encontrado");
        } else {
            for (UsuarioVO user : usuario) { //vai rodar apenas 1 vez
                if (user.getSenha().equals(usuarioVO.getSenha())) {
                    if (user.getTipo().equals("Gerente")) {
                        GerenteVO gerenteVO = new GerenteVO();
                        gerenteVO.setIDGerente(user.getIDUsuario());
                        gerenteVO.setNome(user.getNome());
                        gerenteVO.setEmail(user.getEmail());
                        gerenteVO.setSenha(user.getSenha());
                        gerenteVO.setTipo(user.getTipo());
                        return gerenteVO;
                    } else if (user.getTipo().equals("Autor")) { //na criação do autor o mesmo não tem nenhuma obra associada
                        AutorVO autorVO = new AutorVO();
                        autorVO.setIDAutor(user.getIDUsuario());
                        autorVO.setNome(user.getNome());
                        autorVO.setEmail(user.getEmail());
                        autorVO.setSenha(user.getSenha());
                        autorVO.setCpf(user.getCpf());
                        autorVO.setEndereco(user.getEndereco());
                        autorVO.setTipo(user.getTipo());
                        return autorVO;
                    } else if (user.getTipo().equals("Avaliador")) {
                        AvaliadorVO avaliadorVO = new AvaliadorVO();
                        avaliadorVO.setIDAvaliador(user.getIDUsuario());
                        avaliadorVO.setNome(user.getNome());
                        avaliadorVO.setEmail(user.getEmail());
                        avaliadorVO.setSenha(user.getSenha());
                        avaliadorVO.setCpf(user.getCpf());
                        avaliadorVO.setEndereco(user.getEndereco());
                        avaliadorVO.setTipo(user.getTipo());
                        return avaliadorVO;
                    }
                } else {
                    throw new ErroLoginException("Senha ou login inválido!");
                }
            }
            throw new ErroLoginException("Senha ou login inválido!");
        }
    }
	
    public void cadastrar(UsuarioVO usuarioVO) throws InsertException  {
        try {
            ArrayList<UsuarioVO> todosUsuarios = usuarioDAO.buscarPorEmail(usuarioVO); //se retornar algo quer dizer já existe o email cadastrado
            if (!todosUsuarios.isEmpty()) {
                throw new InsertException("O email que você inseriu já existe no sistema!");
            } else {
                String tipoDeUsuario = usuarioVO.getTipo();
                if (tipoDeUsuario.equals("Gerente")) {
                    GerenteDAO gerenteDAO = new GerenteDAO();
                    gerenteDAO.inserir((GerenteVO) usuarioVO);
                } else if (tipoDeUsuario.equals("Autor")) {
                    AutorDAO autorDAO = new AutorDAO();
                    autorDAO.inserir((AutorVO) usuarioVO);
                } else if (tipoDeUsuario.equals("Avaliador")) {
                    AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                    avaliadorDAO.inserir((AvaliadorVO) usuarioVO);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(UsuarioVO usuarioVO) throws InsertException {
        try {
            ArrayList<UsuarioVO> usuario = usuarioDAO.buscarPorEmail(usuarioVO);
            if (!usuario.isEmpty()) {
                String tipoDeUsuario = usuarioVO.getTipo();
                if (tipoDeUsuario.equals("Gerente")) {
                    GerenteDAO gerenteDAO = new GerenteDAO();
                    gerenteDAO.atualizar((GerenteVO) usuarioVO);
                } else if (tipoDeUsuario.equals("Autor")) {
                    AutorDAO autorDAO = new AutorDAO();
                    autorDAO.atualizar((AutorVO) usuarioVO);
                } else if (tipoDeUsuario.equals("Avaliador")) {
                    AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                    avaliadorDAO.atualizar((AvaliadorVO) usuarioVO);
                }
            } else {
                throw new InsertException("O usuário que você está tentando atualizar não existe no sistema!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(UsuarioVO usuarioVO) throws DeletionException {
        try {
            ArrayList<UsuarioVO> usuario = usuarioDAO.buscarPorEmail(usuarioVO);
            if (!usuario.isEmpty()) {
                String tipoDeUsuario = usuarioVO.getTipo();
                if (tipoDeUsuario.equals("Gerente")) {
                    GerenteDAO gerenteDAO = new GerenteDAO();
                    gerenteDAO.excluir((GerenteVO) usuarioVO);
                } else if (tipoDeUsuario.equals("Autor")) {
                    AutorDAO autorDAO = new AutorDAO();
                    autorDAO.excluir((AutorVO) usuarioVO);
                } else if (tipoDeUsuario.equals("Avaliador")) {
                    AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                    avaliadorDAO.excluir((AvaliadorVO) usuarioVO);
                }
            } else {
                throw new DeletionException("O usuário que você está tentando excluir não existe no sistema!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}