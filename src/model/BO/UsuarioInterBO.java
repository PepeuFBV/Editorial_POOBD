package model.BO;

import exceptions.ErroLoginException;
import model.VO.UsuarioVO;

public interface UsuarioInterBO<VO extends UsuarioVO> {

	public UsuarioVO autenticar (VO vo) throws ErroLoginException;
	
}
