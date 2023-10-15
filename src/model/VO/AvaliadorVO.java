package model.VO;

import java.util.ArrayList;

public class AvaliadorVO extends UsuarioVO {

    private Long IDAvaliador;
    private ArrayList<ObraVO> obras;

    public AvaliadorVO(Long iDAvaliador, ArrayList<ObraVO> obras, Long iDUsuario, String nome, String endereco, String cpf, String email, String senha, String tipo) {
        super(iDUsuario, nome, endereco, cpf, email, senha, tipo);
        setIDAvaliador(iDAvaliador);;
        setObras(obras);
    }
    
    public AvaliadorVO() {
    }

    public Long getIDAvaliador() {
        return IDAvaliador;
    }

    public void setIDAvaliador(Long iDAvaliador) {
        if (iDAvaliador > 0) {
            this.IDAvaliador = iDAvaliador;
        } else {
            throw new IllegalArgumentException("ID do Avaliador deve ser maior que zero.");
        }
    }

    public ArrayList<ObraVO> getObras() {
        return obras;
    }

    public void setObras(ArrayList<ObraVO> obras) {
        this.obras = obras;
    }

    
    public String toString() {
        return super.getNome();
    }
}
