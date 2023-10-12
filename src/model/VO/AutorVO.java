package model.VO;

import java.util.ArrayList;

public class AutorVO extends UsuarioVO {

    private Long IDAutor;
    private ArrayList<ObraVO> obras;

    public AutorVO(Long iDAutor, ArrayList<ObraVO> obras, Long iDUsuario, String nome, String endereco, String cpf, String email, String senha, String tipo) {
        super(iDUsuario, nome, endereco, cpf, email, senha, tipo);
        setIDAutor(iDAutor);
        setObras(obras);
    }
    
    public AutorVO() {
    }

    public Long getIDAutor() {
        return IDAutor;
    }

    public void setIDAutor(Long iDAutor) {
        if (iDAutor > 0) {
            IDAutor = iDAutor;
        } else {
            throw new IllegalArgumentException("ID do Autor deve ser maior que zero.");
        }
    }

    public ArrayList<ObraVO> getObras() {
        return obras;
    }

    public void setObras(ArrayList<ObraVO> obras) {
        this.obras = obras;
    }
}

