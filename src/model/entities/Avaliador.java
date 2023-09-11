package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Avaliador extends Usuario {

    private List<Obra> obras = new ArrayList<>();
    private List<Avaliador> avaliadores = new ArrayList<>();

    public Avaliador(Long id, String nome, String login, String senha, String endereco, String cpf) {
        super(id, nome, login, senha, endereco, cpf);
        setObras(obras);
        setAvaliadores(avaliadores);
    }
    
    //getters e setters

    public List<Obra> getObras() {
        return this.obras;
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }

    public List<Avaliador> getAvaliadores() {
        return this.avaliadores;
    }

    public void setAvaliadores(List<Avaliador> avaliadores) {
        this.avaliadores = avaliadores;
    }


    //alterar metódos depois
    /*
    public void cadastrar(Avaliador avaliador) {
    	System.out.printf(avaliador.getNome() + " está cadastrado!\n");
    }
    
    public void alterar(Avaliador avaliador) {
    	System.out.printf(avaliador.getNome() + " está alterado!\n");
    }
    
    public void excluir(Avaliador avaliador) {
    	System.out.printf(avaliador.getNome() + " foi excluido!\n");
    }
    
    public Avaliador buscarPorObra(Obra obra) {
        for (Avaliador avaliador : avaliadores) {
            List<Obra> obrasDoAvaliador = avaliador.getObras();
            for (Obra obraAvaliador : obrasDoAvaliador) {
                if (obraAvaliador != null && obraAvaliador.equals(obra)) {
                    System.out.println("Avaliador encontrado para a obra: " + obra.getTitulo());
                    return avaliador;
                }
            }
        }
    
        System.out.println("Nenhum avaliador encontrado para a obra.");
        return null;
    }
    
    */
    
}
