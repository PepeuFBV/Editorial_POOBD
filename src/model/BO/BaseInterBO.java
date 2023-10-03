package model.BO;

import java.util.List;

import exceptions.InsertException;
import exceptions.NotFoundException;

public interface BaseInterBO<VO> {
	
	public void cadastrar(VO vo) throws InsertException;
	public void buscarPorId(VO vo) throws NotFoundException;
	public List<VO> listar() throws InsertException;
	public void alterar(VO vo) throws InsertException;
	public void remover(VO vo) throws InsertException;
}
	
