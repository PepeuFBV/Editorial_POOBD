package VO;

import java.util.ArrayList;

public class AvaliadorVO extends UserVO{

    private int IDAvaliador;
    private ArrayList<ObraVO> obras;

    public AvaliadorVO(int iDAvaliador, ArrayList<ObraVO> obras) {
        super();
        IDAvaliador = iDAvaliador;
        this.obras = obras;
    }
    
    public AvaliadorVO() {
    }

    public int getIDAvaliador() {
        return IDAvaliador;
    }

    public void setIDAvaliador(int iDAvaliador) {
        if (iDAvaliador > 0) {
            IDAvaliador = iDAvaliador;
        } else {
            throw new IllegalArgumentException("ID do Avaliador deve ser maior que zero.");
        }
    }

    public ArrayList<ObraVO> getObras() {
        return obras;
    }

    public void setObras(ArrayList<ObraVO> obras) {
        if (obras != null) {
            this.obras = obras;
        } else {
            throw new IllegalArgumentException("A lista de obras não pode ser nula.");
        }
    }
}
