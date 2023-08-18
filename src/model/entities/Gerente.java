package model.entities;

public class Gerente extends Usuario {
    private boolean isGerente;

    public Gerente() { }

    public Gerente(String nome, boolean isGerente, String login, String senha) {
        super(nome,login,senha);
        setIsGerente(isGerente);
    }

    public boolean getIsGerente() {
        return this.isGerente;
    }

    public void setIsGerente(boolean isGerente) {
        this.isGerente = isGerente;
    }

    //alterar metódos depois

}
