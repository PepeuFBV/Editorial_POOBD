package VO;

public class GerenteVO extends UserVO{

    private int IDGerente;

    public GerenteVO(int iDGerente) {
        super();
        setIDGerente(iDGerente);
    }

    public int getIDGerente() {
        return IDGerente;
    }

    public void setIDGerente(int iDGerente) {
        if (iDGerente > 0) {
            IDGerente = iDGerente;
        } else {
            throw new IllegalArgumentException("ID do gerente deve ser maior que zero.");
        }
    }
}
