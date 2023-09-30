package VO;

import java.util.ArrayList;

public class AutorVO extends UserVO {

    private int IDAutor;
    private ArrayList<ObraVO> obras;

    public AutorVO(int iDAutor, ArrayList<ObraVO> obras) {
        super();
        IDAutor = iDAutor;
        this.obras = obras;
    }

    public int getIDAutor() {
        return IDAutor;
    }

    public void setIDAutor(int iDAutor) {
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
        if (obras != null) {
            this.obras = obras;
        } else {
            throw new IllegalArgumentException("A lista de obras não pode ser nula.");
        }
    }
}

