package model.VO;

public class GerenteVO extends UsuarioVO{

    private Long IDGerente;

    public GerenteVO(Long iDGerente, Long iDUsuario, String nome, String endereco, String cpf, String email, String senha, String tipo) {
        super(iDUsuario, nome, endereco, cpf, email, senha, tipo);
        setIDGerente(iDGerente);
    }
    
    public GerenteVO() {
    }

    public Long getIDGerente() {
        return IDGerente;
    }

    public void setIDGerente(Long iDGerente) {
        if (iDGerente > 0) {
            IDGerente = iDGerente;
        } else {
            throw new IllegalArgumentException("ID do gerente deve ser maior que zero.");
        }
    }
}
