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
import java.util.List;

public class UsuarioBO<VO extends UsuarioVO> {

    private boolean autenticado = false;
    private String tipo;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
    public boolean isAutenticado() {
        return autenticado;
    }

    public String getTipo() {
        if (autenticado) {
            return tipo;
        } else {
            return null;
        }
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public UsuarioVO autenticar(UsuarioVO usuarioVO) throws ErroLoginException { //retorna um objeto do tipo UsuarioVO (GerenteVO, AutorVO ou AvaliadorVO)
        ArrayList<UsuarioVO> usuario = usuarioDAO.buscarPorEmail(usuarioVO); //deve haver apenas 1 usuário no ArrayList
        if (usuario.isEmpty()) {
            throw new ErroLoginException("Usuário não encontrado");
        } else {
            autenticado = true;
            for (UsuarioVO user : usuario) { //vai rodar apenas 1 vez
                if (user.getSenha().equals(usuarioVO.getSenha())) {
                    if (user.getTipo().equals("Gerente")) {
                        GerenteVO gerenteVO = new GerenteVO();
                        gerenteVO.setIDGerente(user.getIDUsuario());
                        gerenteVO.setNome(user.getNome());
                        gerenteVO.setEmail(user.getEmail());
                        gerenteVO.setSenha(user.getSenha());
                        gerenteVO.setTipo(user.getTipo());
                        setTipo(user.getTipo());
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
                        setTipo(user.getTipo());
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
                        setTipo(user.getTipo());
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
                if (usuarioVO.getTipo().equals("Gerente")) {
                    GerenteDAO gerenteDAO = new GerenteDAO();

                    GerenteVO gerenteVO = new GerenteVO();
                    gerenteVO.setNome(usuarioVO.getNome());
                    gerenteVO.setEndereco(usuarioVO.getEndereco());
                    gerenteVO.setCpf(usuarioVO.getCpf());
                    gerenteVO.setEmail(usuarioVO.getEmail());
                    gerenteVO.setSenha(usuarioVO.getSenha());
                    gerenteVO.setTipo(usuarioVO.getTipo());

                    gerenteDAO.inserir(gerenteVO);
                } else if (usuarioVO.getTipo().equals("Autor")) {
                    AutorDAO autorDAO = new AutorDAO();

                    AutorVO autorVO = new AutorVO();
                    autorVO.setNome(usuarioVO.getNome());
                    autorVO.setEndereco(usuarioVO.getEndereco());
                    autorVO.setCpf(usuarioVO.getCpf());
                    autorVO.setEmail(usuarioVO.getEmail());
                    autorVO.setSenha(usuarioVO.getSenha());
                    autorVO.setTipo(usuarioVO.getTipo());

                    autorDAO.inserir(autorVO);
                } else if (usuarioVO.getTipo().equals("Avaliador")) {
                    AvaliadorDAO avaliadorDAO = new AvaliadorDAO();

                    AvaliadorVO avaliadorVO = new AvaliadorVO();
                    avaliadorVO.setNome(usuarioVO.getNome());
                    avaliadorVO.setEndereco(usuarioVO.getEndereco());
                    avaliadorVO.setCpf(usuarioVO.getCpf());
                    avaliadorVO.setEmail(usuarioVO.getEmail());
                    avaliadorVO.setSenha(usuarioVO.getSenha());
                    avaliadorVO.setTipo(usuarioVO.getTipo());

                    avaliadorDAO.inserir(avaliadorVO);
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
                if (usuarioVO.getTipo().equals("Gerente")) {
                    GerenteDAO gerenteDAO = new GerenteDAO();
                    
                    GerenteVO gerenteVO = new GerenteVO();
                    gerenteVO.setIDGerente(usuarioVO.getIDUsuario());
                    gerenteVO.setNome(usuarioVO.getNome());
                    gerenteVO.setEmail(usuarioVO.getEmail());
                    gerenteVO.setSenha(usuarioVO.getSenha());
                    gerenteVO.setTipo(usuarioVO.getTipo());

                    gerenteDAO.atualizar(gerenteVO);
                } else if (usuarioVO.getTipo().equals("Autor")) {
                    AutorDAO autorDAO = new AutorDAO();
                    
                    AutorVO autorVO = new AutorVO();
                    autorVO.setIDAutor(usuarioVO.getIDUsuario());
                    autorVO.setNome(usuarioVO.getNome());
                    autorVO.setEmail(usuarioVO.getEmail());
                    autorVO.setSenha(usuarioVO.getSenha());
                    autorVO.setCpf(usuarioVO.getCpf());
                    autorVO.setEndereco(usuarioVO.getEndereco());
                    autorVO.setTipo(usuarioVO.getTipo());

                    autorDAO.atualizar(autorVO);
                } else if (usuarioVO.getTipo().equals("Avaliador")) {
                    AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                    
                    AvaliadorVO avaliadorVO = new AvaliadorVO();
                    avaliadorVO.setIDAvaliador(usuarioVO.getIDUsuario());
                    avaliadorVO.setNome(usuarioVO.getNome());
                    avaliadorVO.setEmail(usuarioVO.getEmail());
                    avaliadorVO.setSenha(usuarioVO.getSenha());
                    avaliadorVO.setCpf(usuarioVO.getCpf());
                    avaliadorVO.setEndereco(usuarioVO.getEndereco());
                    avaliadorVO.setTipo(usuarioVO.getTipo());

                    avaliadorDAO.atualizar(avaliadorVO);
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
                if (usuarioVO.getTipo().equals("Gerente")) {
                    GerenteDAO gerenteDAO = new GerenteDAO();
                    
                    GerenteVO gerenteVO = new GerenteVO();
                    gerenteVO.setIDGerente(usuarioVO.getIDUsuario());
                    gerenteVO.setNome(usuarioVO.getNome());
                    gerenteVO.setEmail(usuarioVO.getEmail());
                    gerenteVO.setSenha(usuarioVO.getSenha());
                    gerenteVO.setTipo(usuarioVO.getTipo());
                    
                    gerenteDAO.excluir(gerenteVO);
                } else if (usuarioVO.getTipo().equals("Autor")) {
                    AutorDAO autorDAO = new AutorDAO();
                    
                    AutorVO autorVO = new AutorVO();
                    autorVO.setIDAutor(usuarioVO.getIDUsuario());
                    autorVO.setNome(usuarioVO.getNome());
                    autorVO.setEmail(usuarioVO.getEmail());
                    autorVO.setSenha(usuarioVO.getSenha());
                    autorVO.setCpf(usuarioVO.getCpf());
                    autorVO.setEndereco(usuarioVO.getEndereco());
                    autorVO.setTipo(usuarioVO.getTipo());

                    autorDAO.excluir(autorVO);
                } else if (usuarioVO.getTipo().equals("Avaliador")) {
                    AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                    
                    AvaliadorVO avaliadorVO = new AvaliadorVO();
                    avaliadorVO.setIDAvaliador(usuarioVO.getIDUsuario());
                    avaliadorVO.setNome(usuarioVO.getNome());
                    avaliadorVO.setEmail(usuarioVO.getEmail());
                    avaliadorVO.setSenha(usuarioVO.getSenha());
                    avaliadorVO.setCpf(usuarioVO.getCpf());
                    avaliadorVO.setEndereco(usuarioVO.getEndereco());
                    avaliadorVO.setTipo(usuarioVO.getTipo());

                    avaliadorDAO.excluir(avaliadorVO);
                }
            } else {
                throw new DeletionException("O usuário que você está tentando excluir não existe no sistema!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<UsuarioVO> buscarPorEmail (UsuarioVO usuarioVO) {
    	ArrayList<UsuarioVO> usuarios = usuarioDAO.buscarPorEmail(usuarioVO);
        return usuarios;
    }  

}